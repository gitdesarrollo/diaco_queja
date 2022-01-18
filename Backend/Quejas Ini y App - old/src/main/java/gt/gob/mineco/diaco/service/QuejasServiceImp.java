package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.dao.ConsumidorRepository;
import gt.gob.mineco.diaco.dao.DepartamentoRepository;
import gt.gob.mineco.diaco.dao.MunicipioRepository;
import gt.gob.mineco.diaco.dao.PaisRepository;
import gt.gob.mineco.diaco.dao.ProveedorRepository;
import gt.gob.mineco.diaco.dao.QuejaRepository;
import gt.gob.mineco.diaco.dto.CalendarioDto;
import gt.gob.mineco.diaco.dto.DetalleCalendarioDto;
import gt.gob.mineco.diaco.dto.DiacoQuejaIniDto;
import gt.gob.mineco.diaco.dto.QuejaConDto;
import gt.gob.mineco.diaco.exception.ErrorException;
import gt.gob.mineco.diaco.model.Departamento;
import gt.gob.mineco.diaco.model.DiacoConcvirtInteraccion;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoMagicLink;
import gt.gob.mineco.diaco.model.DiacoPais;
import gt.gob.mineco.diaco.model.DiacoProveedor;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.util.Constants;
import gt.gob.mineco.diaco.util.Email;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class QuejasServiceImp {

    @Inject
    QuejaRepository quejaDao;
    @Inject
    ProveedorRepository proveedorDao;
    @Inject
    ConsumidorRepository consumidorDao;
    @Inject
    DepartamentoRepository deptoDao;
    @Inject
    MunicipioRepository muniDao;
    @Inject
    PaisRepository paisDao;
    
    
    ///////////////////////////////////////////////////FACTURA/////////////////////////////////////////////////////////////    
    /*public DiacoQueja getFacturaByProveedor(String proveedor) {
        try {
            return quejaDao.findByProveedor(proveedor);
        } catch (NoResultException ex) {
            return null;
        }
    }*/
    
    
    //////////////////////////////////////////////////////FINALIZA FACTURA/////////////////////////////////////////////////

    //procedimiento que se llama cuando se llena los 40 datos
    public DiacoQueja saveQuejadq(DiacoQueja queja) {
        boolean existeq = false;
        if (queja.getIdQueja() != null) {
            System.out.println("actualiza queja " + queja.getIdQueja());
            existeq = true;
            //busca proveedor para envio a cola de asignacion
        } else {
            System.out.println("graba nueva queja ");
            // asignar estado 401
            //queja.setIdEstadoQueja(Constants.QUEJA_NUEVA);
            // generar correlativo de queja
            Integer noQueja = 0;
            Integer noQuejaIni = this.findNoQueja();
            try {
                noQueja = quejaDao.findMaxFromYear();// + 1;
                if (noQueja != null) {
                    noQueja = noQueja + 1;
                    if (noQueja < noQuejaIni) {
                        noQueja = noQuejaIni;
                    }
                } else {
                    noQueja = noQuejaIni;
                }
            } catch (NoResultException ex) {
                // si no hay resultados para el año actual, es la 1
                noQueja = noQuejaIni;
            }
            queja.setAnio(LocalDate.now().getYear());
            queja.setNoQueja(noQueja);
            queja.setFechaQueja(new Timestamp(new Date().getTime()));

        }
        if (existeq) {
            quejaDao.merge(queja);
        } else {
            quejaDao.save(queja);
        }
        if (queja.getIdEstadoQueja() != null) {
            // pasar la linea 101
            this.quejaDao.grabaAtencionConsumidor(queja);
            System.out.println("finaliza la queja, tiene estado " + queja.getIdEstadoQueja());
        } else {
            DiacoProveedor pt = this.proveedorDao.findById(queja.getIdProveedor() != null ? queja.getIdProveedor() : 0);
            if (pt != null) {
                if (pt.getHabilitadoConciliacionPrevia() != null) {
                    if (pt.getHabilitadoConciliacionPrevia().equals("1")) {
                        System.out.println("finaliza la queja, habilitado para conciliacion ");
                    } else {
                        this.quejaDao.grabaAtencionConsumidor(queja);
                        System.out.println("ingreso a cola");
                    }
                } else {
                    System.out.println("finaliza la queja, el proveedor no tiene estado en la habilitacion .");
                    //this.quejaDao.grabaAtencionConsumidor(queja);
                    System.out.println("ingreso a cola");
                }
            } else {
                System.out.println("no existe proveedor ");
            }
        }
        //DiacoProveedor pt = this.proveedorDao.findByNit(quejaIni.getNitProveedor() != null ? quejaIni.getNitProveedor() : "0");
        return queja;
    }

    /*
    public DiacoQuejaDto saveQueja(DiacoQuejaDto quejaDto) {
        DiacoQueja queja = new DiacoQueja();
        // asignar estado 401
        queja.setIdEstadoQueja(Constants.QUEJA_NUEVA);
        // generar correlativo de queja
        Integer noQueja = 1;
        try {
            noQueja = quejaDao.findMaxFromYear() + 1;
        } catch (NoResultException ex) {
            // si no hay resultados para el año actual, es la 1
        }
        queja.setAnio(LocalDate.now().getYear());
        queja.setNoQueja(noQueja);
        queja.setFechaQueja(new Timestamp(new Date().getTime()));
        queja.setDetalleQueja(quejaDto.getDetalleQueja());
        queja.setSolicitaQue(quejaDto.getSolicitaQue());
        //busca proveedor
        DiacoProveedor prov = new DiacoProveedor();
        prov = proveedorDao.findByNit(quejaDto.getNitProveedor());
        if (prov != null) {
            queja.setIdProveedor(prov.getIdProveedor());
        } else {
            // crea el proveedor
            prov = new DiacoProveedor();
            prov.setIdProveedor(noQueja);
            prov.setNitProveedor(quejaDto.getNitProveedor());
            prov.setNombre("pendiente");
            prov.setNombreEmpresa("pendiente");
            proveedorDao.save(prov);
            queja.setIdProveedor(prov.getIdProveedor());
        }
        //busca consumidor
        DiacoConsumidor cons = new DiacoConsumidor();
        cons = consumidorDao.findByDocumentoIdentificacion(quejaDto.getDpiPasaporte());
        if (cons != null) {
            queja.setIdConsumidor(cons.getIdConsumidor());
        } else {
            // crea consumidor
            cons = new DiacoConsumidor();
            cons.setDocumentoIdentificacion(quejaDto.getDpiPasaporte());
            cons.setCorreoElectronico1(quejaDto.getCorreo());
            cons.setTelefono(quejaDto.getTelefono());
            cons.setNombre1(quejaDto.getNombre());
            consumidorDao.save(cons);
            queja.setIdConsumidor(cons.getIdConsumidor());
        }
        queja.setIdDiacoSede(1);
        quejaDao.save(queja);
        return quejaDto;
    }*/
    private Integer findNoQueja() {
        return this.quejaDao.findValInicialSec();//MANDA A TRAER EL CORRELATIVO DE DONDE DEBE EMPEZAR EL CORRELATIVO DE CADA AÑO
    }

    public DiacoQuejaIniDto saveQuejaIni(DiacoQuejaIniDto quejaIni) {
        DiacoQueja queja = new DiacoQueja();
        Integer noQueja = 0;
        Integer noQuejaIni = this.findNoQueja(); //noQuejaIni contiene al correlativo de donde debe empezar.
        String conciliacion = "0";
        //verifica si existe el proveedor
        //DiacoProveedor pt = this.proveedorDao.findById(quejaIni.getIdProveedor() != null ? quejaIni.getIdProveedor() : 0);
        //DiacoProveedor pt = this.proveedorDao.findByNit(quejaIni.getNitProveedor() != null ? quejaIni.getNitProveedor() : "0");    //ORIGINAL
        DiacoProveedor pt = this.proveedorDao.findByNit(quejaIni.getNitProveedor() != null && "".equals(quejaIni.getNitProveedor()) ? "NULL" : quejaIni.getNitProveedor());
        //DiacoConsumidor ct = this.consumidorDao.findById(quejaIni.getIdConsumidor() != null ? quejaIni.getIdConsumidor() : 0);
        DiacoConsumidor ct = this.consumidorDao.findByDocumentoIdentificacion(quejaIni.getDpiPasaporte() != null ? quejaIni.getDpiPasaporte() : "0");

        //verificamos que exista el 
        try {
            //System.out.println("noqueja "+noQuejaIni!=null?noQuejaIni:0);
            noQueja = quejaDao.findMaxFromYear();// + 1; //TRAE EL NO. DE QUEJA DE LA ULTIMA QUEJA DEL AÑO
            //System.out.println("noqueja "+noQueja!=null?noQueja:0);
            if (noQueja != null) {
                noQueja = noQueja + 1;
                if (noQueja < noQuejaIni) {
                    noQueja = noQuejaIni;
                }
            } else {
                noQueja = noQuejaIni;
            }
        } catch (NoResultException ex) {
            // si no hay resultados para el año actual, es la 1
            noQueja = noQuejaIni;
        }
        //if (!quejaIni.getIdProveedor().toString().equals("0")) {
        if (pt != null) {
            queja.setIdProveedor(pt.getIdProveedor());

            if (pt.getHabilitadoConciliacionPrevia().equals("1")) {
                System.out.println("graba estado queja 401");
                queja.setIdEstadoQueja(Constants.QUEJA_NUEVA);
                queja.setDireccionProveedor(pt.getDireccion() != null ? pt.getDireccion() : (pt.getDireccionDetalle() != null ? pt.getDireccionDetalle() : "Sin direccion"));
                queja.setIdDepartamentoProveedor(pt.getCodigoDepartamento() != null ? pt.getCodigoDepartamento() : (pt.getDepartamento() != null ? pt.getDepartamento().getCodigoDepartamento() : 9));
                queja.setIdMunicipioProveedor(pt.getCodigoMunicipio() != null ? pt.getCodigoMunicipio() : (pt.getMunicipio() != null ? pt.getMunicipio().getCodigoMunicipio() : 1));
                queja.setZonaProveedor(pt.getDireccionZona() != null ? Integer.parseInt(pt.getDireccionZona()) : 0);
                queja.setIdDiacoSede(1);
                conciliacion = "1";
            }
        } else {
            //Graba proveedor
            DiacoProveedor prov = new DiacoProveedor();
            prov = new DiacoProveedor();
            //prov.setIdProveedor(noQueja);
            prov.setNitProveedor(quejaIni.getNitProveedor());
            prov.setNombre("pendiente");
            prov.setPrimerNombre("pendiente");
            prov.setPrimerApellido("pendiente");
            prov.setNombreEmpresa("pendiente");
            prov.setHabilitadoConciliacionPrevia("0");  //para que pueda arrncar sin problemas la cola
            prov.setDireccion("direccion");
            prov.setDireccionDetalle("direccion");
            prov.setAutorizoPublicar("0");
            prov.setTipoProveedor("6");
            prov.setEmail("pendiente@correo.com");
            prov.setFechaAdicion(new Timestamp(new Date().getTime()));
            prov.setHabilitado("1");
            prov.setIdActividadEconomica("1");
            prov.setIdTipoComercio("1");
            prov.setTelefono("00000000");
            prov.setNombreEmpresa("pendiente");
            prov.setRazonSocial("pendiente");
            prov.setCodigoDepartamento(9);
            prov.setCodigoMunicipio(1);
            Departamento depto = this.deptoDao.findById(9);
            Municipio muni = this.muniDao.findById(1);
            prov.setDepartamento(depto);
            prov.setMunicipio(muni);
            proveedorDao.save(prov);
            queja.setIdProveedor(prov.getIdProveedor());
            conciliacion = "0";
        }
        //if (!quejaIni.getIdConsumidor().toString().equals("0")) {
        if (ct != null) {
            System.out.println("existe consumidor");
            queja.setIdConsumidor(ct.getIdConsumidor());
            //actualiza los datos si hubieron
            String[] nombres = quejaIni.getNombre().replace(" ", "-").split("-");
            switch (nombres.length) {
                case 1:
                    ct.setNombre1(nombres[0]);
                    ct.setNombre2("");
                    ct.setNombre3("");
                    break;
                case 2:
                    ct.setNombre1(nombres[0]);
                    ct.setNombre2(nombres[1]);
                    ct.setNombre3("");
                    break;
                case 3:
                    ct.setNombre1(nombres[0]);
                    ct.setNombre2(nombres[1]);
                    ct.setNombre3(nombres[2]);
                    break;
            }
            String[] apellidos = quejaIni.getApellido().replace(" ", "-").split("-");
            switch (apellidos.length) {
                case 1:
                    ct.setApellido1(apellidos[0]);
                    ct.setApellido2("");
                    break;
                case 2:
                    ct.setApellido1(apellidos[0]);
                    ct.setApellido2(apellidos[1]);
                    break;
                default:
                    ct.setApellido1(apellidos[0]);
                    ct.setApellido2(apellidos[1]);
                    break;
            }
            //verifica si existe telefono grabado en la tabla telefono
            List<TipoEmail> correos = this.consumidorDao.findCorreosById(quejaIni.getIdConsumidor());
            for (TipoEmail te : correos) {
                if (te.getCorreo_electronico().equals(ct.getCorreoElectronico1())) {
                    te.setCorreo_electronico(quejaIni.getCorreo());
                    this.consumidorDao.updateCorreo(te);
                }
            }
            //verifica si existe correo en la tabla correo
            List<TipoTelefono> telefonos = this.consumidorDao.findTelefonosById(quejaIni.getIdConsumidor());
            for (TipoTelefono te : telefonos) {
                if (te.getTelefono().equals(ct.getTelefono())) {
                    te.setTelefono(quejaIni.getTelefono());
                    this.consumidorDao.updateTelefono(te);
                }
            }
            ct.setTelefono(quejaIni.getTelefono());
            ct.setCorreoElectronico1(quejaIni.getCorreo());
            this.consumidorDao.updateCon(ct);
        } else {
            // grabar consumidor
            System.out.println("no existe consumidor");
            DiacoConsumidor cons = new DiacoConsumidor();
            //cons.setIdConsumidor(noQueja);

            String[] nombres = quejaIni.getNombre().replace(" ", "-").split("-");
            switch (nombres.length) {
                case 1:
                    cons.setNombre1(nombres[0]);
                    break;
                case 2:
                    cons.setNombre1(nombres[0]);
                    cons.setNombre2(nombres[1]);
                    break;
                case 3:
                    cons.setNombre1(nombres[0]);
                    cons.setNombre2(nombres[1]);
                    cons.setNombre3(nombres[2]);
                    break;
            }
            String[] apellidos = quejaIni.getApellido().replace(" ", "-").split("-");
            switch (apellidos.length) {
                case 1:
                    cons.setApellido1(apellidos[0]);
                    break;
                case 2:
                    cons.setApellido1(apellidos[0]);
                    cons.setApellido2(apellidos[1]);
                    break;
                default:
                    cons.setApellido1(apellidos[0]);
                    cons.setApellido2(apellidos[1]);
                    break;
            }
            //cons.setNombre1(quejaIni.getNombre());
            cons.setDocumentoIdentificacion(quejaIni.getDpiPasaporte());
            cons.setTelefono(quejaIni.getTelefono());
            cons.setCorreoElectronico1(quejaIni.getCorreo());
            cons.setCodigoDepartamento(9);
            cons.setCodigoMunicipio(1);
            Departamento depto = this.deptoDao.findById(9);
            Municipio muni = this.muniDao.findById(1);
            cons.setDepartamento(depto);
            cons.setMunicipio(muni);
            cons.setNacionalidad(1);
            cons.setTipoConsumidor(6);
            cons.setFechaAdicion(new Timestamp(new Date().getTime()));
            cons.setAutorizoPublicar("0");
            cons.setDireccion("pendiente");
            cons.setDireccionDetalle("pendiente");
            DiacoPais pais = paisDao.findById(1);
            cons.setPais(pais);
            consumidorDao.save(cons);

            queja.setIdConsumidor(cons.getIdConsumidor());
            //graba el correo
            TipoEmail correo = new TipoEmail();
            correo.setCorreo_electronico(quejaIni.getCorreo());
            correo.setFecha_adicion(new Date());
            correo.setId_consumidor(cons.getIdConsumidor());
            this.consumidorDao.saveCorreo(correo);
            TipoTelefono tel = new TipoTelefono();
            tel.setId_consumidor(cons.getIdConsumidor());
            tel.setTelefono(quejaIni.getTelefono());
            tel.setTipo_telefono(3);
            tel.setFecha_adicion(new Date());
            this.consumidorDao.saveTelefono(tel);
        }
//        queja.setIdDiacoSede(2);
//        setSede
//        queja.setIdDiacoSede(ct.getSedeDiacoCercana());
        queja.setAnio(LocalDate.now().getYear());
        queja.setNoQueja(noQueja);
        //queja.setFechaQueja(new Timestamp(new Date().getTime()));
        queja.setFechaQueja(new Date());
        queja.setDetalleQueja(quejaIni.getDetalleQueja());
        queja.setSolicitaQue(quejaIni.getSolicitaQue());
        queja.setFuente(quejaIni.getIdFuente() != null && !quejaIni.getIdFuente().equals("") ? quejaIni.getIdFuente() : "webMobil");
        queja.setUbicacion(quejaIni.getUbicacion());
        queja.setIdDepartamento(9);
        queja.setIdMunicipio(1);
        queja.setFacturaNumero(quejaIni.getFacturaNumero());
        //queja.setIdFuente(Integer.parseInt(quejaIni.getIdFuente()));
        //graba la queja
        quejaDao.save(queja);

        quejaIni.setAnio(queja.getAnio());
        quejaIni.setIdQueja(queja.getIdQueja());
        quejaIni.setSecuencia(queja.getNoQueja());
        quejaIni.setConciliacion(conciliacion);
        System.out.println("a cola");
        //boolean aquejanueva = false;
        //if (quejaIni.getIdProveedor().toString().equals("0")) {
        try {
            DiacoProveedor tempp = this.proveedorDao.findById(queja.getIdProveedor());
            System.out.println("------" + tempp.getHabilitadoConciliacionPrevia());
            if (tempp.getHabilitadoConciliacionPrevia().equals("1")) {
                System.out.println("no ingreso -graba estado queja 401");

                // CODIGO modificado
                //queja.setIdEstadoQueja(Constants.QUEJA_NUEVA);
                //quejaDao.save(queja);
            } else {
                if (quejaIni.getPresencial() != null) {
                    System.out.println("ingreso presencial a 30-40 datos, conciliacion " + quejaIni.getConciliacion());
                } else {
                    //System.out.println('verificar jj');
                    //this.quejaDao.grabaAtencionConsumidor(queja);
                    System.out.println("ingreso");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}

        return quejaIni;
    }

    /*
    public List<DiacoQuejaIniDto> getquejas(DiacoQuejaIniDto quejaIni) {
        List<DiacoQuejaIniDto> rlist = new ArrayList<>();

        return rlist;
    }*/
    public Long getSecuencia() {
        return quejaDao.getSecuencia();
    }

    public DiacoQueja findBySecuencia(Integer fuente) {
        return quejaDao.findByIdFuente(fuente);
    }

    public DiacoQueja getquejasId(Integer id) {
        DiacoQueja resp = quejaDao.getquejasId(id);
        if (resp != null) {
            resp.setConsumidor(this.consumidorDao.findById(resp.getIdConsumidor()));
            resp.setProveedor(this.proveedorDao.findById(resp.getIdProveedor()));
            resp.setSede(this.quejaDao.findSedeById(resp.getIdDiacoSede()));
            TipoPasoQueja tpq = this.quejaDao.findPasoQuejaById(resp.getIdQueja());
            if (tpq != null) {
                resp.setPasoQueja(tpq);
                resp.setEstadoQueja(this.quejaDao.findEstadoQuejaById(tpq.getId_estado_operado()));
            }
            resp.setEstadoQueja(this.quejaDao.findEstadoQuejaById(resp.getIdEstadoQueja()));
            resp.setColor("#FF0000");
        }
        return resp;
    }

    public DiacoQueja getquejasAnioSec(Integer anio, Integer correlativo) {
        DiacoQueja resp = quejaDao.getquejasAnioSec(anio, correlativo);
        if (resp != null) {
            resp.setConsumidor(this.consumidorDao.findById(resp.getIdConsumidor()));
            resp.setProveedor(this.proveedorDao.findById(resp.getIdProveedor()));
            resp.setSede(this.quejaDao.findSedeById(resp.getIdDiacoSede()));
            TipoPasoQueja tpq = this.quejaDao.findPasoQuejaById(resp.getIdQueja());
            if (tpq != null) {
                resp.setPasoQueja(tpq);
                resp.setEstadoQueja(this.quejaDao.findEstadoQuejaById(tpq.getId_estado_operado()));
            }
            resp.setEstadoQueja(this.quejaDao.findEstadoQuejaById(resp.getIdEstadoQueja()));
            resp.setColor("#FF0000");
        }
        return resp;
    }

    public boolean sendMail(DiacoQuejaIniDto queja, String subject, String tipo, String link) {
        Email correo = new Email();
        boolean resp = false;
        String server = this.proveedorDao.findParametro("instanciaServer");
        String salto = "<br>";
        String from = "info@diaco.gob.gt";
        String body = "";
        String body3 = "";
        String body5 = "";
        String body1 = "Estimado Sr.(a) " + queja.getNombre() + ":" + salto
                + " Le notificamos que su queja ha sido recibida con éxito, "
                //+ " para darle seguimiento a la misma puede consultar el No. " + queja.getSecuencia().toString().concat("-").concat(queja.getAnio().toString()) + salto;
                + " la misma se identifica con el No. " + queja.getSecuencia().toString().concat("-").concat(queja.getAnio().toString()) + salto;
        String body4 = "Estimado Sr.(a) " + queja.getNombre() + ":" + salto
                + " Le notificamos que sus documentos de su queja se han recibido con éxito, "
                //+ " para darle seguimiento a la misma puede consultar el No. " + queja.getSecuencia().toString().concat("-").concat(queja.getAnio().toString()) + salto;
                + " la misma se identifica con el No. " + queja.getSecuencia().toString().concat("-").concat(queja.getAnio().toString()) + salto;
        String body2 = " Gracias por utilizar nuestros servicios electronicos. " + salto
                + "Atentamente, " + salto + salto + salto + " DIACO" + salto + " Dirección de atención y asistencia al consumidor " + salto
                + "Oficinas: 7a av. 7-61 Zona 4, 3er. Nivel Edificio del Registro Mercantil " + salto
                + "Teléfono: 2501-9898 " + salto
                + "Centro de Atención de Quejas: 6av. 0-35 Zona 4 centro comercial Plaza Zona 4 " + salto
                + "Área de Restaurantes 3er. Nivel Tel: 2501-9600";
        switch (tipo) {
            case "1":
                body5 = "Para poder continuar con el trámite de su queja necesitamos que complete la " + salto
                        + "información del siguiente link, así como que adjunte copia de la factura o" + salto
                        + " documento de respaldo de la compra y su DPI: "
                        + "<a href='http://" + server + "/dist6/#/consumidor/presencial/" + link + "/" + queja.getIdQueja() + "'>Click aqui </a>" + salto + salto
                        + "Por favor, tome en cuenta que tiene 48 horas después de recibido este correo, " + salto
                        + "para ingresar la información y subir sus los documentos." + salto + salto;
                body = body1 + body5 + body2; //notificación de ingreso de queja web//notificación de ingreso de queja web 
                break;
            case "2":
                body = body4 + body2; //notificación de ingreso de queja por el usuario.
                break;
            case "3": //notificación de ingreso de queja call center
                /*
                body3 = "Le solicitamos por favor ingresar al siguiente link: " + salto + salto
                        + "http://"+server+"/dist5/#/quejacon/0/" + link + salto + salto
                        + " para ingresar copia de la factura o documento y su DPI" + salto
                        + " tome en cuenta que tiene 24 horas despues de recibido este correo para subir sus documentos." + salto;
                 */
                body3 = "Para poder continuar con el trámite de su queja necesitamos que rectifique" + salto
                        + " la información del siguiente link, así como que adjunte copia de la factura " + salto
                        + " o documento de respaldo de la compra y su DPI: "
                        + "<a href='http://" + server + "/dist5/#/quejacon/0/" + link + "'>Click aqui </a>" + salto + salto
                        + "Por favor, tome en cuenta que tiene 48 horas después de recibido este correo, " + salto
                        + "para verificar la información y subir los documentos.";
                body = body1 + body3 + body2 + salto + salto + salto; //notificación de ingreso de que archivos
                break;
            default:
                break;
        }
        try {
            subject = "Notificación de queja grabada ".concat(queja.getSecuencia().toString().concat("-").concat(queja.getAnio().toString()));
            System.out.println(queja.getCorreo());
            String quejas[] = {queja.getCorreo()};
            System.out.println(quejas.length);

            resp = correo.SendEmail(from, quejas, subject, body,
                    this.proveedorDao.findParametro("email_host"),
                    this.proveedorDao.findParametro("email_user"),
                    this.proveedorDao.findParametro("email_pass"));
        } catch (Exception e) {

        }
        return resp;
    }

    public List<DiacoQueja> getAllQuejas(String noQueja) {
        return quejaDao.getAllQuejas(noQueja);
    }

    public DiacoQueja buscaArchivos(DiacoQueja pq) {
        List<TipoImagenesQueja> larchivos = this.quejaDao.buscaArchivos(pq.getIdQueja());
        List<String> temp = new ArrayList();
        for (TipoImagenesQueja t : larchivos) {
            if (t.getId_categoria_imagen() == 3) {
                pq.setFdpi(t.getImagen());
            }
            if (t.getId_categoria_imagen() == 2) {
                pq.setFdocto(t.getImagen());
            }
            if (t.getId_categoria_imagen() == 1) {
                temp.add((t.getImagen()));
            }
            pq.setFotros(temp);
        }
        return pq;
    }

    public List<TipoImagenesQueja> listArchivos(Integer id) {
        return this.quejaDao.buscaArchivos(id);
    }

    public DiacoQueja updateQuejaCon(QuejaConDto queja) {
        DiacoQueja q = new DiacoQueja();
        if (queja.getIdQueja() != null) {
            q = this.quejaDao.findByDIdQueja(queja.getIdQueja());
            if (q != null) {
                if (queja.getVdatos().equals("1")) {
                    q.setDetalleQueja(queja.getXqueja());
                    q.setSolicitaQue(queja.getXsolicitud());
                    q.setUbicacion(queja.getXubicacion());
                }
                this.quejaDao.merge(q);
                List<DiacoMagicLink> links = this.quejaDao.findTokenIntExt(queja.getToken());
                if (links != null && !links.isEmpty()) {
                    DiacoMagicLink link = links.get(0);
                    DiacoConcvirtInteraccion cv = this.quejaDao.findByDIConvirt(link.getId_referencia());
                    if (cv != null) {
                        cv.setRespuestaCon(queja.getResp());
                        this.quejaDao.mergeConcvirtInteraccion(cv);
                    }
                }
            } else {
                q = null;
            }

        }
        return q;
    }

    /**
     * Metodo que sirve para consultar el detalle del calendario:
     * <p>
     * <ul>
     * <li>Audiencias</li>
     * <li>Conciliador/Usuario</li>
     * <li>Proveedor</li>
     * <li>Fecha de inicio</li>
     * <li>Color para diferenciar.</li>
     * </ul>
     *
     * @param pCalendarioDto Detalle de los parametros para realizar la
     * consulta.
     * @return Objeto con el detalle de audiencias.
     * @throws ErrorException Si ocurre un error.
     */
    public List<DetalleCalendarioDto> consultarDetalleCalendario(CalendarioDto pCalendarioDto)
            throws ErrorException {
        System.out.println("Metodo: consultarDetalleCalendario");
        try {
            System.out.println("Params Calendario: " + pCalendarioDto.toString());
            List<DetalleCalendarioDto> registro = this.quejaDao.findDetalleCalendario(pCalendarioDto);
            return registro;
        } catch (ErrorException e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new ErrorException("Error al procesar los datos del proveedor.", e);
        }
    }
}
