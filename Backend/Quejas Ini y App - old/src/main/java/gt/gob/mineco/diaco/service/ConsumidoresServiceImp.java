package gt.gob.mineco.diaco.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import gt.gob.mineco.diaco.dao.ConsumidorRepository;
import gt.gob.mineco.diaco.dao.DepartamentoRepository;
import gt.gob.mineco.diaco.dao.MunicipioRepository;
import gt.gob.mineco.diaco.dao.PaisRepository;
import gt.gob.mineco.diaco.dao.SedeRepository;
import gt.gob.mineco.diaco.dto.ConsumidorDto;
import gt.gob.mineco.diaco.model.Departamento;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoPais;
import gt.gob.mineco.diaco.model.DiacoSede;
import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import java.util.List;

@Stateless
public class ConsumidoresServiceImp {

    @Inject
    ConsumidorRepository consumidorDao;

    @Inject
    PaisRepository paisDao;

    @Inject
    DepartamentoRepository departamentoDao;

    @Inject
    MunicipioRepository municipioDao;

    @Inject
    SedeRepository sedeDao;

    public DiacoConsumidor saveConsumidor(DiacoConsumidor consumidor) {
        // asociar pais
        DiacoPais pais = paisDao.findById(consumidor.getNacionalidad());
        consumidor.setPais(pais);
        // asociar departamento
        Departamento departamento = departamentoDao.findById(consumidor.getCodigoDepartamento());
        consumidor.setDepartamento(departamento);
        // asociar municipio
        Municipio municipio = municipioDao.findById(consumidor.getCodigoMunicipio());
        consumidor.setMunicipio(municipio);
        // asociar sede
        DiacoSede sede = sedeDao.findById(consumidor.getSedeDiacoCercana());
        consumidor.setSede(sede);
        consumidor.setFechaAdicion(new Timestamp(new Date().getTime()));
        return consumidorDao.save(consumidor);
    }

    public DiacoConsumidor updateConsumidor(DiacoConsumidor consumidor) {
        System.out.println("actualiza "+(consumidor!=null?consumidor.getIdConsumidor():"null"));
        if (consumidor != null) {
            DiacoConsumidor temp = this.findConsumidorById(consumidor.getIdConsumidor());
            //System.out.println("temp "+(temp!=null?temp.getIdConsumidor():"null"));
            if (temp != null) {
                temp.setHabilitadoNotificacionElectronica("1");
                System.out.println("a actualizar "+(temp!=null?temp.getIdConsumidor():"null"));
                return this.consumidorDao.merge(temp);
            } else {
                //System.out.println("error1 ");
                return null;
            }
        } else {
            //System.out.println("error2 ");
            return null;
        }
    }

    public ConsumidorDto saveConsumidorDto(ConsumidorDto pconsumidor) {
        DiacoConsumidor consumidor = new DiacoConsumidor();
        if (pconsumidor.getNacionalidad() != null) {
            consumidor.setNacionalidad(pconsumidor.getNacionalidad());
        }
        if (pconsumidor.getNitConsumidor() != null) {
            consumidor.setNitConsumidor(pconsumidor.getNitConsumidor());
        }
        if (pconsumidor.getDocumentoIdentificacion() != null) {
            consumidor.setDocumentoIdentificacion(pconsumidor.getDocumentoIdentificacion());
        }
        if (pconsumidor.getGenero() != null) {
            consumidor.setGenero(pconsumidor.getGenero());
        }
        // asociar departamento
        if (pconsumidor.getCodigoDepartamento() != null) {
            Departamento departamento = departamentoDao.findById(pconsumidor.getCodigoDepartamento());
            consumidor.setDepartamento(departamento);
        }
        // asociar municipio
        if (pconsumidor.getCodigoMunicipio() != null) {
            Municipio municipio = municipioDao.findById(pconsumidor.getCodigoMunicipio());
            consumidor.setMunicipio(municipio);
        }
        // asociar sede
        //System.out.println("sede ----------------- "+pconsumidor.getSedeDiacoCercana());
        if (pconsumidor.getSedeDiacoCercana() != null) {
            //DiacoSede sede = rsRespositoryDao.findByIdSede(Integer.parseInt(pconsumidor.getSedeDiacoCercana()));
            consumidor.setSedeDiacoCercana(Integer.parseInt(pconsumidor.getSedeDiacoCercana()));
            //System.out.println("sede ----------------- "+sede.getIdDiacoSede());
        }
        consumidor.setHabilitadoNotificacionElectronica("0");
        consumidor.setFechaAdicion(new Timestamp(new Date().getTime()));
        //consumidor.setUsuarioAdiciono("");
        if (pconsumidor.getNombre1() != null) {
            consumidor.setNombre1(pconsumidor.getNombre1());
        }
        if (pconsumidor.getNombre2() != null) {
            consumidor.setNombre2(pconsumidor.getNombre2());
        }
        if (pconsumidor.getNombre3() != null) {
            consumidor.setNombre3(pconsumidor.getNombre3());
        }
        if (pconsumidor.getApellido1() != null) {
            consumidor.setApellido1(pconsumidor.getApellido1());
        }
        if (pconsumidor.getApellido2() != null) {
            consumidor.setApellido2(pconsumidor.getApellido2());
        }
        if (pconsumidor.getApellidoCasada() != null) {
            consumidor.setApellidoCasada(pconsumidor.getApellidoCasada());
        }
        //6	Individual	tabla_consumidor
        //7	Juridico	tabla_consumidor
        if (pconsumidor.getTipoConsumidor() != null) {
            consumidor.setTipoConsumidor(pconsumidor.getTipoConsumidor().equals("6") ? 6 : 7);
        }
        if (pconsumidor.getTipoDocumento() != null) {
            consumidor.setTipoDocumento(pconsumidor.getTipoDocumento());
        }
        
        // asociar pais
        if (pconsumidor.getPais() != null) {
            DiacoPais pais = paisDao.findById(pconsumidor.getPais());
            consumidor.setPais(pais);
        } else {
            //Guatemala
            DiacoPais pais = paisDao.findById(1);
            consumidor.setPais(pais);
        }
        // asociar etnia
        if (pconsumidor.getEtnia() != null) {
            consumidor.setIdEtnia(pconsumidor.getEtnia());
        }
        if (pconsumidor.getDireccionDetalle() != null) {
            consumidor.setDireccionDetalle(pconsumidor.getDireccionDetalle());
        }
        if (pconsumidor.getTelefonoCelular() != null) {
            consumidor.setTelefono(pconsumidor.getTelefonoCelular());
        }
        consumidor.setDomicilio(pconsumidor.getTelefonoDomicilio() != null ? pconsumidor.getTelefonoCelular() : null);
        consumidor.setReferencia(pconsumidor.getTelefonoReferencia() != null ? pconsumidor.getTelefonoReferencia() : null);
        if (pconsumidor.getEmail() != null) {
            consumidor.setCorreoElectronico1(pconsumidor.getEmail());
        }
        if (pconsumidor.getEmail2() != null) {
            consumidor.setCorreoElectronico2(pconsumidor.getEmail2());
        }
        if (pconsumidor.getDireccionDetalle() != null) {
            consumidor.setDireccion(pconsumidor.getDireccionDetalle());
        }
        if (pconsumidor.getAutorizoPublicar() != null) {
            consumidor.setAutorizoPublicar(pconsumidor.getAutorizoPublicar());
        }
        if (pconsumidor.getDireccionZona() != null) {
            consumidor.setDireccionZona(pconsumidor.getDireccionZona());
        }
        consumidor.setRepresentanteLegal(pconsumidor.getRepresentanteLegal() != null ? pconsumidor.getRepresentanteLegal() : null);
        consumidor.setNombreEmpresa(pconsumidor.getNombreEmpresa() != null ? pconsumidor.getNombreEmpresa() : null);
        consumidor.setRazonSocial(pconsumidor.getRazonSocial() != null ? pconsumidor.getRazonSocial() : null);
        if (pconsumidor.getMotivoQueja() != null) {
            consumidor.setIdMotivoQueja(Integer.parseInt(pconsumidor.getMotivoQueja()));
        }
        String correo1 = "";
        String correo2 = "";
        
        //verifica si existe el consumidor, para actualizar o grabar.
        DiacoConsumidor temp = this.consumidorDao.findByDocumentoIdentificacion(consumidor.getDocumentoIdentificacion());
        //System.out.println("Vienso flujo de Grabando Consumidor"+temp.getCorreoElectronico1()+temp.getCorreoElectronico2()+consumidor.getDocumentoIdentificacion()+temp.getDocumentoIdentificacion());
        if (temp != null) {
            if (temp.getCorreoElectronico1() != null) {
                correo1 = temp.getCorreoElectronico1();
            }
            if (temp.getCorreoElectronico2() != null) {
                correo2 = temp.getCorreoElectronico2();
            }
            consumidor.setIdConsumidor(temp.getIdConsumidor());
            consumidor = consumidorDao.updateConsumidor(consumidor);
        } else {
            temp = this.consumidorDao.findByDocumentoNit(consumidor.getNitConsumidor());
            if (temp != null) {
                if (temp.getCorreoElectronico1() != null) {
                    correo1 = temp.getCorreoElectronico1();
                }
                if (temp.getCorreoElectronico2() != null) {
                    correo2 = temp.getCorreoElectronico2();
                }
                consumidor.setIdConsumidor(temp.getIdConsumidor());
                consumidor = consumidorDao.updateConsumidor(consumidor);
            } else {
                consumidor = consumidorDao.saveConsumidor(consumidor);
            }
        }
        List<TipoEmail> correos = consumidorDao.getCorreos(consumidor.getIdConsumidor(), 0); //SI ES CERO BUSCA CONSUMIDOR, SI ES 1 BUSCA PROVEEDOR
        System.out.println("Correos obtenidos: "+correos.get(0));
        pconsumidor.setIdConsumidor(consumidor.getIdConsumidor());
        //graba el correo 1
        if (pconsumidor.getEmail() != null) { // SI EL FORM TRAE EMAIL
            if (correo1.equals("")) { //SI NO ENCONTRÓ EMAIL
                TipoEmail correo = new TipoEmail();
                correo.setCorreo_electronico(pconsumidor.getEmail());
                correo.setFecha_adicion(new Date());
                correo.setId_consumidor(consumidor.getIdConsumidor());
                this.consumidorDao.saveCorreo(correo);
            } else {
                for (TipoEmail t : correos) {
                    if (t.getCorreo_electronico().equals(correo1)) {
                        t.setCorreo_electronico(pconsumidor.getEmail());
                        t.setFecha_modificacion(new Date());
                        consumidorDao.updateCorreo(t);
                    }
                }
            }
        }
        //graba correo 2
        if (pconsumidor.getEmail2() != null) {
            if (correo2.equals("")) {
                TipoEmail correo = new TipoEmail();
                correo.setCorreo_electronico(consumidor.getCorreoElectronico2());
                correo.setFecha_adicion(new Date());
                correo.setId_consumidor(consumidor.getIdConsumidor());
                this.consumidorDao.saveCorreo(correo);
            } else {
                for (TipoEmail t : correos) {
                    if (t.getCorreo_electronico().equals(correo2)) {
                        t.setCorreo_electronico(pconsumidor.getEmail2());
                        t.setFecha_modificacion(new Date());
                        consumidorDao.updateCorreo(t);
                    }
                }
            }
        }
        List<TipoTelefono> tels = consumidorDao.getTels(consumidor.getIdConsumidor(), 0);

        // graba telefono
        Boolean existeTel = false;
        if (pconsumidor.getTelefonoCelular() != null) {
            for (TipoTelefono t : tels) {
                if (t.getTipo_telefono().equals(3)) {
                    t.setTelefono(pconsumidor.getTelefonoCelular());
                    t.setFecha_modificacion(new Date());
                    this.consumidorDao.updateTelefono(t);
                    existeTel = true;
                }
            }
            if (!existeTel) {
                TipoTelefono tel = new TipoTelefono();
                tel.setId_consumidor(consumidor.getIdConsumidor());
                tel.setTelefono(pconsumidor.getTelefonoCelular());
                tel.setTipo_telefono(3); //personal
                tel.setFecha_adicion(new Date());
                this.consumidorDao.saveTelefono(tel);
            }
        }
        existeTel = false;
        if (pconsumidor.getTelefonoDomicilio() != null) {
            for (TipoTelefono t : tels) {
                if (t.getTipo_telefono().equals(4)) {
                    t.setTelefono(pconsumidor.getTelefonoDomicilio());
                    t.setFecha_modificacion(new Date());
                    this.consumidorDao.updateTelefono(t);
                    existeTel = true;
                }
            }
            if (!existeTel) {
                TipoTelefono tel = new TipoTelefono();
                tel.setId_consumidor(consumidor.getIdConsumidor());
                tel.setTelefono(pconsumidor.getTelefonoDomicilio());
                tel.setTipo_telefono(4); //domicilio
                tel.setFecha_adicion(new Date());
                this.consumidorDao.saveTelefono(tel);
            }
        }
        existeTel = false;
        if (pconsumidor.getTelefonoReferencia() != null) {
            for (TipoTelefono t : tels) {
                if (t.getTipo_telefono().equals(5)) {
                    t.setTelefono(pconsumidor.getTelefonoReferencia());
                    t.setFecha_modificacion(new Date());
                    this.consumidorDao.updateTelefono(t);
                    existeTel = true;
                }
            }
            if (!existeTel) {
                TipoTelefono tel = new TipoTelefono();
                tel.setId_consumidor(consumidor.getIdConsumidor());
                tel.setTelefono(pconsumidor.getTelefonoReferencia());
                tel.setTipo_telefono(5); //referencia
                tel.setFecha_adicion(new Date());
                this.consumidorDao.saveTelefono(tel);
            }
        }

        return pconsumidor;
    }

    public DiacoConsumidor getConsumidorByDocumentoIdentificacion(String documentoIdentificacion) {
        try {
            return consumidorDao.findByDocumentoIdentificacion(documentoIdentificacion);
        } catch (NoResultException ex) {
            return null;
        }
    }

    public TipoTelefono findTelefonoById(Integer id) {
        return consumidorDao.findTelefonoById(id);
    }

    public TipoEmail findCorreoById(Integer id) {
        return consumidorDao.findCorreoById(id);
    }

    public DiacoConsumidor findConsumidorById(Integer id) {
        return this.consumidorDao.findById(id);
    }

    public List<TipoTelefono> findTelefonosById(Integer id) {
        return consumidorDao.findTelefonosById(id);
    }

    public List<TipoEmail> findCorreosById(Integer id) {
        return consumidorDao.findCorreosById(id);
    }

    ///////////////////////////////////
    public ConsumidorDto getConsumidorByDocumentoIdentificacion(String tipo, String documentoIdentificacion) {
        try {
            DiacoConsumidor con;
            if (tipo.equals("documento")) {
                con = consumidorDao.findByDocumentoIdentificacion(documentoIdentificacion);
            } else {
                con = consumidorDao.findByDocumentoNit(documentoIdentificacion);
            }
            ConsumidorDto ret = new ConsumidorDto();
            if (con != null) {
                ret.setIdConsumidor(con.getIdConsumidor());
                ret.setNacionalidad(con.getNacionalidad() != null ? con.getNacionalidad() : null);
                ret.setTipoDocumento(con.getTipoDocumento() != null ? con.getTipoDocumento() : null);
                //6	Individual	tabla_consumidor
                //7	Juridico	tabla_consumidor
                if (con.getTipoConsumidor() == 6) {
                    ret.setTipoConsumidor("6");
                } else {
                    ret.setTipoConsumidor("7");
                }
                ret.setDocumentoIdentificacion(con.getDocumentoIdentificacion() != null ? con.getDocumentoIdentificacion() : "");
                ret.setPais(con.getPais() != null ? con.getPais().getIdPais() : null);
                //ret.setPais(con.getPais() != null ? con.getPais() : null);
                ret.setRepresentanteLegal(con.getRepresentanteLegal() != null ? con.getRepresentanteLegal() : "");
                ret.setNitConsumidor(con.getNitConsumidor() != null ? con.getNitConsumidor() : "");
                ret.setNombreEmpresa(con.getNombreEmpresa() != null ? con.getNombreEmpresa() : "");
                ret.setRazonSocial(con.getRazonSocial() != null ? con.getRazonSocial() : "");
                ret.setNombre1(con.getNombre1() != null ? con.getNombre1() : "");
                ret.setNombre2(con.getNombre2() != null ? con.getNombre2() : "");
                ret.setNombre3(con.getNombre3() != null ? con.getNombre3() : "");
                ret.setApellido1(con.getApellido1() != null ? con.getApellido1() : "");
                ret.setApellido2(con.getApellido2() != null ? con.getApellido2() : "");
                ret.setApellidoCasada(con.getApellidoCasada() != null ? con.getApellidoCasada() : "");
                ret.setGenero(con.getGenero() != null ? con.getGenero() : "");
                ret.setEtnia(con.getIdEtnia() != null ? con.getIdEtnia() : null);
                ret.setMotivoQueja(String.valueOf(con.getIdMotivoQueja()) != null ? String.valueOf(con.getIdMotivoQueja()) : "");
                ret.setCodigoMunicipio(con.getCodigoMunicipio() != null ? con.getCodigoMunicipio() : null);
                ret.setCodigoDepartamento(con.getCodigoDepartamento() != null ? con.getCodigoDepartamento() : null);
                //ret.setdireccionCalle(con.getD);
                //ret.setdireccionAvenida(con.get);
                //ret.setdireccionZona(con.get);
                ret.setDireccionDetalle(con.getDireccionDetalle() != null ? con.getDireccionDetalle() : "");
                ret.setDireccionDetalle(con.getDireccionZona() != null ? con.getDireccionZona() : "");
                ret.setSedeDiacoCercana(con.getSedeDiacoCercana().toString() != null ? con.getSedeDiacoCercana().toString() : "");

                List<TipoTelefono> tc = this.consumidorDao.getTels(con.getIdConsumidor(), 0);
                for (TipoTelefono t : tc) {
                    if (t.getTipo_telefono() == 3) {
                        ret.setTelefonoCelular(t.getTelefono());
                    }
                    if (t.getTipo_telefono() == 4) {
                        ret.setTelefonoDomicilio(t.getTelefono());
                    }
                    if (t.getTipo_telefono() == 5) {
                        ret.setTelefonoReferencia(t.getTelefono());
                    }
                }

                List<TipoEmail> correo = this.consumidorDao.getCorreos(con.getIdConsumidor(), 0);
                int cont = 1;
                for (TipoEmail t : correo) {
                    if (cont == 1) {
                        ret.setEmail(t.getCorreo_electronico());
                        cont++;
                    } else {
                        ret.setEmail2(t.getCorreo_electronico());
                        cont++;
                    }
                }

                ret.setiCheck(con.getAutorizoPublicar() != null ? con.getAutorizoPublicar() : "");
            }
            return ret;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
