package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.dao.CalificacionRepository;
import gt.gob.mineco.diaco.dao.DepartamentoRepository;
import gt.gob.mineco.diaco.dao.MunicipioRepository;
import gt.gob.mineco.diaco.dao.ProveedorRepository;
import gt.gob.mineco.diaco.dto.CalificacionProveedorDto;
import gt.gob.mineco.diaco.dto.ProveedorAprobarDto;
import gt.gob.mineco.diaco.dto.ProveedorDto;
import gt.gob.mineco.diaco.exception.ErrorException;
import gt.gob.mineco.diaco.model.Departamento;
import gt.gob.mineco.diaco.model.DiacoCalificacionUsuarioProv;
import gt.gob.mineco.diaco.model.DiacoCatalogoCatagoriaCalificacion;
import gt.gob.mineco.diaco.model.DiacoEstablecimiento;
import gt.gob.mineco.diaco.model.DiacoProveedor;
import gt.gob.mineco.diaco.model.DiacoProveedorAprovar;
import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.util.FormCalificacionUsuario;
import gt.gob.rgm.sat.ws.ConsultaSiafLocator;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class ProveedoresServiceImp {

    @Inject
    ProveedorRepository proveedorDao;

    @Inject
    DepartamentoRepository departamentoDao;

    @Inject
    MunicipioRepository municipioDao;

    @Inject
    CalificacionRepository calificacionDao;

    /**
     * Servicio de notificaciones.
     */
    @Inject
    NotificacionCorreoImp notificacionService;
    /**
     * Servicio de cifrados.
     */
    @Inject
    EncDecImp encDecService;

    public DiacoProveedor saveProveedor(DiacoProveedor proveedor) {
        // asociar departamento
        Departamento departamento = departamentoDao.findById(proveedor.getCodigoDepartamento());
        proveedor.setDepartamento(departamento);
        // asociar municipio
        Municipio municipio = municipioDao.findById(proveedor.getCodigoMunicipio());
        proveedor.setMunicipio(municipio);
        proveedor.setRazonSocial(proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : "-----");
        proveedor.setNombreEmpresa(proveedor.getNombreEmpresa() != null ? proveedor.getNombreEmpresa() : "-----");
        DiacoProveedor temp = this.getProveedorById(proveedor.getIdProveedor() != null ? proveedor.getIdProveedor() : 0);
        proveedor.setDireccion(proveedor.getDireccionDetalle() != null ? proveedor.getDireccionDetalle() : "pendiente");
        if (proveedor.getTipoProveedor().equals("6")) {
            proveedor.setNombre(proveedor.getPrimerNombre() + " " + proveedor.getPrimerApellido());
        } else {
            proveedor.setNombre(proveedor.getNombreEmpresa());
        }
        if (temp != null) {
            System.out.println("modifica proveedor ");
            proveedor.setFechaModificacion(new Timestamp(new Date().getTime()));
            proveedor.setFechaAdicion(temp.getFechaAdicion() != null ? temp.getFechaAdicion() : new Timestamp(new Date().getTime()));
            if (temp.getFechaRegistroConciliacionPrevia() != null) {
                proveedor.setFechaRegistroConciliacionPrevia(temp.getFechaRegistroConciliacionPrevia());
            }
            proveedorDao.merge(proveedor);
        } else {
            System.out.println("graba proveedor ");
            proveedor.setFechaAdicion(new Timestamp(new Date().getTime()));
            proveedorDao.save(proveedor);
        }
        if (proveedor != null) {
            //graba telefono
            if (proveedor.getTelefono() != null) {
                //busca telefono
                List<TipoTelefono> tels = proveedorDao.findTelefonosById(proveedor.getIdProveedor());
                if (tels != null && !tels.isEmpty()) {
                    for (TipoTelefono t : tels) {
                        t.setTelefono(proveedor.getTelefono());
                        this.proveedorDao.updateTelefono(t);
                    }
                } else {
                    TipoTelefono tel = new TipoTelefono();
                    tel.setId_proveedor(proveedor.getIdProveedor());
                    tel.setTelefono(proveedor.getTelefono());
                    tel.setTipo_telefono(3); //personal
                    tel.setFecha_adicion(new Timestamp(new Date().getTime()));
                    this.proveedorDao.saveTelefono(tel);

                }
            }
            //graba correo
            if (proveedor.getEmail() != null) {
                List<TipoEmail> correos = proveedorDao.findCorreosById(proveedor.getIdProveedor());
                if (correos != null && !correos.isEmpty()) {
                    for (TipoEmail e : correos) {
                        e.setCorreo_electronico(proveedor.getEmail());
                        this.proveedorDao.updateCorreo(e);
                    }
                } else {
                    TipoEmail correo = new TipoEmail();
                    correo.setCorreo_electronico(proveedor.getEmail());
                    correo.setFecha_adicion(new Date());
                    correo.setId_proveedor(proveedor.getIdProveedor());
                    this.proveedorDao.saveCorreo(correo);
                }
            }
        }
        return proveedor;
    }

    public DiacoProveedor updateProveedor(DiacoProveedor proveedor) {
        if (proveedor != null) {
            DiacoProveedor temp = this.findConsumidorById(proveedor.getIdProveedor());
            if (temp != null) {
                temp.setHabilitadoNotificacionElectronica("1");
                return this.proveedorDao.merge(temp);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public DiacoProveedorAprovar saveProveedorAprobar(DiacoProveedorAprovar proveedor) {
        // asociar departamento
        Departamento departamento = departamentoDao.findById(proveedor.getIdDepartamento());
        proveedor.setDepartamento(departamento);
        // asociar municipio
        Municipio municipio = municipioDao.findById(proveedor.getIdMunicipio());
        proveedor.setMunicipio(municipio);

        proveedor.setRazonSocial(proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : "-----");
        proveedor.setNombre(proveedor.getNombre() != null ? proveedor.getNombre() : "-----");
        proveedor.setDireccion(proveedor.getDireccion() != null ? proveedor.getDireccion() : "pendiente");
        proveedor.setCreadoPor(this.proveedorDao.findUserById(1));
        DiacoProveedorAprovar tempp = proveedorDao.findByNitProveedor(proveedor.getNit());

        if (tempp != null) {
            System.out.println("actualiza proveedor ");
            proveedor.setFechaCreado(tempp.getFechaCreado());
            if (tempp.getNotaRechazo() != null) {
                proveedor.setNotaRechazo(tempp.getNotaRechazo());
            }
            proveedor.setEstado(tempp.getEstado() != null ? tempp.getEstado() : "P");
            proveedorDao.mergeProveedor(proveedor);
        } else {
            System.out.println("graba proveedor ");
            proveedor.setEstado("P");
            proveedor.setFechaCreado(new Timestamp(new Date().getTime()));
            proveedorDao.saveProveedor(proveedor);
        }

//        if (proveedor != null) {
//            //graba telefono
//            if (proveedor.getTelefono() != null) {
//                //busca telefono
//                List<TipoTelefono> tels = proveedorDao.findTelefonosById(proveedor.getIdProveedor());
//                if (tels != null && !tels.isEmpty()) {
//                    for (TipoTelefono t : tels) {
//                        t.setTelefono(proveedor.getTelefono());
//                        this.proveedorDao.updateTelefono(t);
//                    }
//                } else {
//                    TipoTelefono tel = new TipoTelefono();
//                    tel.setId_proveedor(proveedor.getIdProveedor());
//                    tel.setTelefono(proveedor.getTelefono());
//                    tel.setTipo_telefono(3); //personal
//                    tel.setFecha_adicion(new Date());
//                    this.proveedorDao.saveTelefono(tel);
//
//                }
//            }
//            //graba correo
//            if (proveedor.getEmail() != null) {
//                List<TipoEmail> correos = proveedorDao.findCorreosById(proveedor.getIdProveedor());
//                if (correos != null && !correos.isEmpty()) {
//                    for (TipoEmail e : correos) {
//                        e.setCorreo_electronico(proveedor.getEmail());
//                        this.proveedorDao.updateCorreo(e);
//                    }
//                } else {
//                    TipoEmail correo = new TipoEmail();
//                    correo.setCorreo_electronico(proveedor.getEmail());
//                    correo.setFecha_adicion(new Date());
//                    correo.setId_proveedor(proveedor.getIdProveedor());
//                    this.proveedorDao.saveCorreo(correo);
//                }
//            }
//        }
        return proveedor;
    }

    public DiacoProveedor getProveedorByNit(String nit) {
        try {
            //DiacoProveedor result = proveedorDao.findByNit(nit);
            DiacoProveedor result = this.getProveedorByNitSAT(nit);
            if (result != null) {
                // hace las consultas a quejas para determinar el valor.
                Long totquejas = proveedorDao.findQuejasProv(result.getIdProveedor());
                Long totquejasval = proveedorDao.findQuejasValProv(result.getIdProveedor());
                result.setQuejas_validas(totquejas - totquejasval);
                result.setQuejas_validas_resuelta(totquejasval);
            }
            return result;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public DiacoEstablecimiento getEstableciminientoById(Integer id) {
        DiacoEstablecimiento e = this.proveedorDao.getEstableciminientoById(id);
        if (e.getCodigoDepartamento() != null) {
            e.setIdDepartamento(e.getCodigoDepartamento().getCodigoDepartamento());
        }
        if (e.getCodigoMunicipio() != null) {
            e.setIdMunicipio(e.getCodigoMunicipio().getCodigoMunicipio());
        }
        return e;
    }

    public List<DiacoEstablecimiento> getEstableciminientoByNitProveedor(String nit) {
        List<DiacoEstablecimiento> temp = this.proveedorDao.getEstableciminientoByNitProveedor(nit);
        List<DiacoEstablecimiento> temn = new ArrayList<>();
        for (DiacoEstablecimiento e : temp) {
            if (e.getCodigoDepartamento() != null) {
                e.setIdDepartamento(e.getCodigoDepartamento().getCodigoDepartamento());
            }
            if (e.getCodigoMunicipio() != null) {
                e.setIdMunicipio(e.getCodigoMunicipio().getCodigoMunicipio());
            }
            //e.setCodigoDepartamento(null);
            //e.setCodigoMunicipio(null);
            temn.add(e);
        }
        if (!temn.isEmpty()) {
            return temn;
        } else {
            return temp;
        }
    }

    public DiacoEstablecimiento setEstableciminientoByNitProveedor(DiacoEstablecimiento est) {
        //ingresa datos de municipio y departamento
        if (est.getIdDepartamento() != null) {
            est.setCodigoDepartamento(this.departamentoDao.findById(est.getIdDepartamento()));
            est.setCodigoMunicipio(this.municipioDao.findById(est.getIdMunicipio()));
        }
        //busca si existe el establecimiento
        if (est.getIdEstablecimiento() != null) {
            DiacoEstablecimiento temp = this.proveedorDao.findEstableciminientoByNitProveedor(est.getIdEstablecimiento());
            if (temp != null) {
                est.setFechaModificacion(new Date());
                return this.proveedorDao.mergeEstableciminientoByNitProveedor(est);
            } else {
                est.setFechaAdicion(new Date());
                est.setHabilitado("1");
                return this.proveedorDao.saveEstableciminientoByNitProveedor(est);
            }
        } else {
            est.setFechaAdicion(new Date());
            est.setHabilitado("1");
            return this.proveedorDao.saveEstableciminientoByNitProveedor(est);
        }
    }

    public DiacoProveedorAprovar getProveedorByNitProveedor(String nit) {
        //invocar ws de sat
        try {
            //verifica  si ya existe 
            DiacoProveedorAprovar result = new DiacoProveedorAprovar();
            DiacoProveedorAprovar temp = proveedorDao.findByNitProveedor(nit.toUpperCase());
            if (temp != null) {
                result.setCorreo(temp.getCorreo());
                result.setDireccion(temp.getDireccion());
                result.setEstado(temp.getEstado());
                result.setId(temp.getId());
                result.setNit(temp.getNit());
                if (temp.getDepartamento() != null) {
                    result.setIdDepartamento(temp.getDepartamento().getCodigoDepartamento());
                }
                if (temp.getMunicipio() != null) {
                    result.setIdMunicipio(temp.getMunicipio().getCodigoMunicipio());
                }
                result.setNombre(temp.getNombre());
                if (temp.getNotaRechazo() != null) {
                    result.setNotaRechazo(temp.getNotaRechazo());
                }
                if (temp.getRazonSocial() != null) {
                    result.setRazonSocial(temp.getRazonSocial());
                }
                result.setTelefono(temp.getTelefono());
                if (temp.getCorreoNotif() != null) {
                    result.setCorreoNotif(temp.getCorreoNotif());
                }
                if (temp.getTelefonoNotif() != null) {
                    result.setTelefonoNotif(temp.getTelefonoNotif());
                }
//                result.setDepartamento(null);
//                result.setMunicipio(null);
//                result.setCreadoPor(null);
            } else {
                String url = this.proveedorDao.findParametro("sat_prov_url");
                String user = this.proveedorDao.findParametro("sat_prov_user");
                String pass = this.proveedorDao.findParametro("sat_prov_pass");
                ConsultaSiafLocator cs = new ConsultaSiafLocator();
                cs.setConsultasPublicasPortEndpointAddress(url);
                String resp = cs.getConsultasPublicasPort().findContribuyenteByNitMFP(user, pass, nit.toUpperCase());
                System.out.println("JJ BUSCANDO EN SAT: resp: "+resp);
                System.out.println("url: " + url + "  data: " + resp);
                if (!resp.isEmpty()) {
                    if (resp.contains("CODIGO_ERROR")) {
                        result = null;
                    } else {
                        result = new DiacoProveedorAprovar();
                        StringReader reader = new StringReader(
                                resp.replace("<GC_REQCONTRIBUYENTE>", "").replace("</GC_REQCONTRIBUYENTE>", "")
                                        .replace("<DOMICILIO_FISCAL>", "").replace("</DOMICILIO_FISCAL>", "")
                                        .replace("<EMA>", "<EMA><![CDATA[").replace("</EMA>", "]]></EMA>"));
                        JAXBContext jaxbContext = JAXBContext.newInstance(ProveedorAprobarDto.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        ProveedorAprobarDto pa = (ProveedorAprobarDto) jaxbUnmarshaller.unmarshal(reader);
                        System.out.println("-----------------------" + pa.toString());
                        result.setNit(pa.getNit());
                        result.setCorreo(pa.getEma());
                        result.setTelefono(pa.getTel());
                        result.setDireccion(
                                (pa.getCoa() != null ? pa.getCoa() : "") + " "
                                + (pa.getNc() != null ? pa.getNc() : "") + " "
                                + (pa.getZon() != null ? (pa.getZon().trim().length() > 0 ? "zona " + pa.getZon() : "") : "") + " "
                                + (pa.getCol() != null ? (pa.getCol().trim().length() > 0 ? "colonia " + pa.getCol() : "") : "") + " "
                                + (pa.getApa() != null ? (pa.getApa().trim().length() > 0 ? " apartamento " + pa.getApa() : "") : "")
                        );
                        result.setNombre(pa.getNom());
                    }
                }
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public DiacoProveedor getProveedorById(Integer id) {
        return this.proveedorDao.findById(id);
    }

    public List<ProveedorDto> getProveedorByNitNombre(String nit, String nombre) {
        System.out.println("Busca proveedor por " + (!nit.isEmpty() ? nit : (!nombre.isEmpty() ? nombre : "sin parametros")));
        List<ProveedorDto> result = this.proveedorDao.getProveedorByNitNombre(nit, nombre);
        /*for (ProveedorDto pd : result) {
                if (pd.getNombre().toUpperCase().equals("PENDIENTE")) {
                    result.remove(pd);
                }
            }*/
        if (result != null && !result.isEmpty()) {
            System.out.println("Encontro  " + result.size() + " datos");
        } else {
            System.out.println("Busca dato en SAT ");
            DiacoProveedor temp = this.getProveedorByNitSAT(nit);
            if (temp != null) {
                ProveedorDto pd = new ProveedorDto();
                pd.setIdProveedor(temp.getIdProveedor());
                pd.setNitProveedor(temp.getNitProveedor());
                pd.setNombre(temp.getNombre());
                result.add(pd);
            } else {
                System.out.println("Encontro  0  datos");
            }
        }
        return result;
    }

    public TipoTelefono findTelefonoById(Integer id) {
        return proveedorDao.findTelefonoById(id);
    }

    public TipoEmail findCorreoById(Integer id) {
        return proveedorDao.findCorreoById(id);
    }

    public DiacoProveedor findConsumidorById(Integer id) {
        return this.proveedorDao.findById(id);
    }

    public List<TipoTelefono> findTelefonosById(Integer id) {
        return proveedorDao.findTelefonosById(id);
    }

    public List<TipoEmail> findCorreosById(Integer id) {
        return proveedorDao.findCorreosById(id);
    }

    public DiacoProveedor getProveedorAndCalifByNit(String nit, String pIdInstalacionMobil) {
        try {
            //DiacoProveedor result = proveedorDao.findByNit(nit);
            DiacoProveedor result = this.getProveedorByNitSAT(nit);
            if (result != null) {
                // hace las consultas a quejas para determinar el valor.
                Long totquejas = proveedorDao.findQuejasProv(result.getIdProveedor());
                Long totquejasval = proveedorDao.findQuejasValProv(result.getIdProveedor());
                result.setQuejas_validas(totquejas - totquejasval);
                result.setQuejas_validas_resuelta(totquejasval);
            }
            //XHGCJDJE2342
            if (result != null) {
                /*  List<DiacoCalificacionUsuarioProv> diacoCalificacionUsuarioProv = calificacionDao.findCalificacionUsuarioProv(result.getIdProveedor(), pIdInstalacionMobil);
                result.setDiacoCalificacionUsuarioProv(diacoCalificacionUsuarioProv);

                List<DiacoPtsGeneralCalifProv> diacoPtsGeneralCalifProv = calificacionDao.findCalificacionGeneralProv(result.getIdProveedor());
                result.setDiacoPtsGeneralCalifProv(diacoPtsGeneralCalifProv);*/
                List<CalificacionProveedorDto> calificacionProveedorDto = calificacionDao.findCalificacionesUserGeneral(result.getIdProveedor(), pIdInstalacionMobil);
                result.setDiacoCalificacionProveedorDto(calificacionProveedorDto);

            }
            return result;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public DiacoCalificacionUsuarioProv saveCalificacionProveedor(FormCalificacionUsuario pCalificacion) {
        UserTransaction transaction = null;
        DiacoCalificacionUsuarioProv calificacionModel;
        DiacoCalificacionUsuarioProv result = null;
        try {
            transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            //DiacoProveedor proveedorModel = proveedorDao.findByNit(pCalificacion.getNit_proveedor());
            DiacoProveedor proveedorModel = this.getProveedorByNitSAT(pCalificacion.getNit_proveedor());
            DiacoCatalogoCatagoriaCalificacion categoriaCalificacion = new DiacoCatalogoCatagoriaCalificacion(pCalificacion.getIdCategoriaCalificacion());
            calificacionModel = calificacionDao.findCalificacionUsuarioProvCategoria(proveedorModel.getIdProveedor(), pCalificacion.getId_instalacion(), categoriaCalificacion);

            if (calificacionModel != null) { //ya existe la calificacion , solo se actualiza
                calificacionModel.setCalificacion(pCalificacion.getCalificacion());
                calificacionModel.setFecha_calificacion(new Date());
                calificacionDao.saveCalificacionUsuario(calificacionModel);
            } else {  //Es una calificacion nueva
                calificacionModel = new DiacoCalificacionUsuarioProv();
                calificacionModel.setCalificacion(pCalificacion.getCalificacion());
                calificacionModel.setIdInstalacionMobil(pCalificacion.getId_instalacion());

                calificacionModel.setIdCategoriaCalificacion(categoriaCalificacion);

                calificacionModel.setIdProveedor(proveedorModel.getIdProveedor());
                calificacionModel.setFecha_calificacion(new Date());

                result = calificacionDao.saveCalificacionUsuario(calificacionModel);
            }
            transaction.commit();

        } catch (Exception e) {
            try {
                e.printStackTrace();
                transaction.rollback();
            } catch (Exception ec) {
                ec.printStackTrace();
            }

        }

        return result;

    }

    public DiacoProveedor getProveedorByNitSAT(String nit) {
        //invocar ws de sat
        try {
            //verifica  si ya existe 
            DiacoProveedor result = new DiacoProveedor();
            DiacoProveedor temp = proveedorDao.findByNit(nit.toUpperCase());
            if (temp != null) {
                result = temp;
            } else {
                String url = this.proveedorDao.findParametro("sat_prov_url");
                String user = this.proveedorDao.findParametro("sat_prov_user");
                String pass = this.proveedorDao.findParametro("sat_prov_pass");
                ConsultaSiafLocator cs = new ConsultaSiafLocator();
                cs.setConsultasPublicasPortEndpointAddress(url);
                String resp = cs.getConsultasPublicasPort().findContribuyenteByNitMFP(user, pass, nit.toUpperCase());
                System.out.println("url: " + url + "  data: " + resp);
                if (!resp.isEmpty()) {
                    if (resp.contains("CODIGO_ERROR")) {
                        result = null;
                    } else {
                        result = new DiacoProveedor();
                        StringReader reader = new StringReader(
                                resp.replace("<GC_REQCONTRIBUYENTE>", "").replace("</GC_REQCONTRIBUYENTE>", "")
                                        .replace("<DOMICILIO_FISCAL>", "").replace("</DOMICILIO_FISCAL>", "")
                                        .replace("<EMA>", "<EMA><![CDATA[").replace("</EMA>", "]]></EMA>"));
                        JAXBContext jaxbContext = JAXBContext.newInstance(ProveedorAprobarDto.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        ProveedorAprobarDto pa = (ProveedorAprobarDto) jaxbUnmarshaller.unmarshal(reader);
                        System.out.println("-----------------------" + pa.toString());
                        result.setNitProveedor(pa.getNit());
                        result.setEmail(pa.getEma());
                        result.setTelefono(pa.getTel());
                        result.setDireccion(
                                (pa.getCoa() != null ? pa.getCoa() : "") + " "
                                + (pa.getNc() != null ? pa.getNc() : "") + " "
                                + (pa.getZon() != null ? (pa.getZon().trim().length() > 0 ? "zona " + pa.getZon() : "") : "") + " "
                                + (pa.getCol() != null ? (pa.getCol().trim().length() > 0 ? "colonia " + pa.getCol() : "") : "") + " "
                                + (pa.getApa() != null ? (pa.getApa().trim().length() > 0 ? " apartamento " + pa.getApa() : "") : "")
                        );
                        result.setNombre(pa.getNom());
                        // asociar departamento
                        Departamento departamento = departamentoDao.findById(9);
                        result.setDepartamento(departamento);
                        // asociar municipio
                        Municipio municipio = municipioDao.findById(1);
                        result.setMunicipio(municipio);
                        result.setRazonSocial("-----");
                        result.setNombreEmpresa("-----");
                        result.setCalificacionAutorizada("0");
                        result.setFechaAdicion(new Timestamp(new Date().getTime()));
                        result.setHabilitado("1");
                        result.setTipoProveedor("6");
                        result.setHabilitadoConciliacionPrevia("0");
                        result.setHabilitado("1");
                        result.setIdActividadEconomica("1");
                        result.setIdTipoComercio("1");
                        result.setDireccionZona(pa.getZon());
                        this.proveedorDao.save(result);
                    }
                }
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que sirve para guardar la informacion del Proveedor.
     * <p>
     * Cuando el proveedor ya existe lo actualiza. Cuando no existe lo crea.
     *
     * @param pProveedor Modelo {@link DiacoProveedorAprovar}
     * @return Modelo {@link DiacoProveedorAprovar}
     */
    public DiacoProveedorAprovar saveProveedorTmp(DiacoProveedorAprovar pProveedor) {
        // asociar departamento
        Departamento departamento = departamentoDao.findById(pProveedor.getIdDepartamento());
        pProveedor.setDepartamento(departamento);
        // asociar municipio
        Municipio municipio = municipioDao.findById(pProveedor.getIdMunicipio());
        pProveedor.setMunicipio(municipio);
        // Informacion por defecto en caso de no existir.
        pProveedor.setRazonSocial(pProveedor.getRazonSocial() != null ? pProveedor.getRazonSocial() : "-----");
        pProveedor.setNombre(pProveedor.getNombre() != null ? pProveedor.getNombre() : "-----");
        pProveedor.setDireccion(pProveedor.getDireccion() != null ? pProveedor.getDireccion() : "pendiente");
        // Otra informacion adicional.
        pProveedor.setCreadoPor(this.proveedorDao.findUserById(1));
        // Previo a guardar se busca el registro.
        DiacoProveedorAprovar regProveedor = proveedorDao.findByNitProveedor(pProveedor.getNit());
        // Cuando el registro existe entonces se deben actualizar los datos.
        if (regProveedor != null) {
            pProveedor.setFechaCreado(regProveedor.getFechaCreado());
            // Cuando existe informacion de rechazo.
            if (StringUtils.isNotBlank(regProveedor.getNotaRechazo())) {
                pProveedor.setNotaRechazo(regProveedor.getNotaRechazo());
            }
            // Cuando ya posee estado se deja el mismo por defecto. Pero si no 
            // posee estado se le asigna T = Temporal.
            pProveedor.setEstado(regProveedor.getEstado() != null ? regProveedor.getEstado() : "T");
            // Actualiza en la base de datos.
            proveedorDao.mergeProveedor(pProveedor);
        } else {
            // Al registro nuevo le asigamos el estado T = Temporal.
            pProveedor.setEstado("T");
            pProveedor.setFechaCreado(new Timestamp(new Date().getTime()));
            // Graba en la base de datos.
            proveedorDao.saveProveedor(pProveedor);
        }
        // Retornamos el mismo DTO pero con los atributos actualizados.
        return pProveedor;
    }

    /**
     * Metodo que sirve para procesar al proveedor:
     * <ul>
     * <li>Se almacena al proveedor en Base de datos.</li>
     * <li>Se notifica al proveedor por medio de correo electronico.</li>
     * </ul>
     *
     * @param pProveedor Datos del proveedor para almacenar en Base de datos.
     * @return DTO del tipo {@link DiacoProveedorAprovar}.
     * @throws ErrorException Si ocurre un error.
     */
    public DiacoProveedorAprovar procesarProveedor(DiacoProveedorAprovar pProveedor)
            throws ErrorException {
        System.out.println("Metodo: procesarProveedor");
        try {
            System.out.println("procesarProveedor: " + pProveedor.toString());
            // Se realiza el proceso de guardar/actualizar al proveedor.
            DiacoProveedorAprovar proveedor = saveProveedorTmp(pProveedor);
            // Se verifica que el correo electronico no se encuentre en blanco.
            if (StringUtils.isBlank(proveedor.getCorreo())) {
                throw new ErrorException("El correo electronico no puede estar en blanco.");
            }
            // Se realiza el proceso de enviar notificacion por correo electronico.
            boolean enviado = notificacionService.enviarNotificacion(proveedor.getCorreo(),
                    proveedor.getNombre(), proveedor.getNit(), pProveedor.getId());
            // Si el correo no se puede notificar se lanza excepcion.
            if (!enviado) {
                throw new ErrorException("Error al notificar por correo electrónico.");
            }
            // Retornamos el mismo DTO pero con los atributos actualizados.
            return proveedor;
        } catch (ErrorException e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new ErrorException("Error al procesar los datos del proveedor.", e);
        }
    }

    /**
     * Metodo que sirve para confirmar mediante un enlace con un codigo unico,
     * la solicitud del Proveedor.
     * <p>
     * Se procesa la llave recibida y se descifra.
     *
     * @param pLlave Llave cifrada / codigo unico.
     * @throws ErrorException Si ocurre un error.
     */
    public void confirmarProveedor(String pLlave) throws ErrorException {
        System.out.println("Metodo: confirmarProveedor");
        try {
            // Decrypt - Llave de proveedores.
            String codigoProveedor = encDecService.decrypt(pLlave);
            System.out.println("Proveedor a confirmar - codigoProveedor: " + codigoProveedor);
            // Busqueda de proveedor usando la llave.
            DiacoProveedorAprovar registroProveedor = proveedorDao.findByIdProveedor(Integer.valueOf(codigoProveedor));
            // Al no existir lanzar error.
            if (registroProveedor == null) {
                throw new ErrorException("No existe el Proveedor.");
            } else {
                // Al proveedor encontrado cambiarle el estado a "P" = Pendiente.
                registroProveedor.setEstado("P");
                // Grabar a Base de datos.
                proveedorDao.mergeProveedor(registroProveedor);
            }
        } catch (ErrorException e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new ErrorException("Error al confirmar los datos del proveedor.", e);
        }
    }
}
