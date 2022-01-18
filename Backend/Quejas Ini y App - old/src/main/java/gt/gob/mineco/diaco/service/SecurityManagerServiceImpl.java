/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.dao.QuejaRepository;
import gt.gob.mineco.diaco.exception.UnauthorizedException;
import gt.gob.mineco.diaco.dao.SecurityManagerRepository;
import gt.gob.mineco.diaco.dto.ChangePasswordDto;
import gt.gob.mineco.diaco.dto.DiacoQuejaIniDto;
import gt.gob.mineco.diaco.model.DiacoConcvirtInteraccion;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoEmail;
import gt.gob.mineco.diaco.model.DiacoMagicLink;
import gt.gob.mineco.diaco.model.DiacoProveedor;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.model.DiacoTelefono;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoTokenBlacklist;
import gt.gob.mineco.diaco.model.TipoUsuario;
import gt.gob.mineco.diaco.model.TipoUsuarioPerfil;
import gt.gob.mineco.diaco.rs.Principal;
import gt.gob.mineco.diaco.util.Constants;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gt.gob.mineco.diaco.util.Crypto;
import gt.gob.mineco.diaco.util.JWTUtils;

/**
 *
 * @author mps
 */
@Stateless
public class SecurityManagerServiceImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    SecurityManagerRepository dao;

    @Inject
    Crypto cryptoSvc;

    @Inject
    QuejaRepository quejaDao;

    @Inject
    ProveedoresServiceImp proveedor;
    @Inject
    ConsumidoresServiceImp consumidor;

    private String secretFile(String tipo, String data) {
        String result = "";
        String path = "/bin/";
        try {
            if (tipo.equals("read")) {
                try (FileReader reader = new FileReader(path.concat("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5"))) {
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result = line;
                    }
                }

            }
            if (tipo.equals("write")) {
                FileWriter writer = new FileWriter(path.concat("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5"), false);
                try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    bufferedWriter.write(data.isEmpty() ? "diacoMineco" : data);
                }
                result = data;
                writer.close();
            }
        } catch (IOException e) {

        }
        return result;
    }

    public Principal login(Principal principal) {
        Map<String, Object> params = new HashMap<>();
        params.put("login", principal.getUsername());
        boolean siguiente = false;
        TipoUsuario user = new TipoUsuario();
        try {
            List<TipoUsuario> users = dao.findByUsuario(params);
            if (users != null && !users.isEmpty()) {
                //verifica que no exista un token asociado
                params.clear();
                params.put("idUsuario", users.get(0).getIdUsuario());
                TipoTokenBlacklist existeToken = dao.getExisteTokenUser(params);
                if (existeToken != null) {
                    if (this.authorized(existeToken.getToken(), "0")) {

                        //si existe el token revisar la clave
                        user = users.get(0);
                        principal.setPassword(cryptoSvc.encrypt(principal.getPassword(), Crypto.ALG_SHA256));
                        if (!user.getClave().isEmpty() && user.getClave().equals(principal.getPassword())) {
                            if (user.getHabilitado().equals("1")) {
                                principal.setCode(Principal.LOGIN_SUCCESS);
                                siguiente = false;
                            } else if (user.getHabilitado().equals("H")) {
                                principal.setCode(Principal.USER_TEMPORARILY_DISABLED);
                                siguiente = false;
                            } else if (user.getHabilitado().equals("I")) {
                                principal.setCode(Principal.USER_DISABLED);
                                siguiente = false;
                            }
                        } else {
                            // clave invalida
                            principal.setCode(Principal.AUTHENTICATION_FAILED);
                            siguiente = false;
                        }

                        if (principal.getCode() == 1) {
                            principal.setToken(existeToken.getToken());
                            principal.setCode(Principal.LOGIN_SUCCESS);
                            principal.setUserId(users.get(0).getIdUsuario());
                            principal.setFullname(users.get(0).getNombre());
                            /*String roles[] = JWTUtils.getRolesUser(existeToken.getToken()).split(",");
                            List<String> rolToken = new ArrayList<>();
                            for (String a : roles) {
                                rolToken.add(a);
                            }
                            principal.setRoles(rolToken);*/
                            principal.setTipousuario(user);
                            siguiente = false;
                        }
                    } else {
                        siguiente = true;
                    }
                } else {
                    siguiente = true;
                }
            } else {
                principal.setCode(Principal.USER_NOT_FOUND);
                siguiente = false;
            }
            if (siguiente) {
                // users.get(0);
                user = users.get(0);
                principal.setPassword(cryptoSvc.encrypt(principal.getPassword(), Crypto.ALG_SHA256));
                if (!user.getClave().isEmpty() && user.getClave().equals(principal.getPassword())) {
                    if (user.getHabilitado().equals("1")) {
                        principal.setCode(Principal.LOGIN_SUCCESS);
                    } else if (user.getHabilitado().equals("H")) {
                        principal.setCode(Principal.USER_TEMPORARILY_DISABLED);
                    } else if (user.getHabilitado().equals("I")) {
                        principal.setCode(Principal.USER_DISABLED);
                    }
                } else {
                    // clave invalida
                    principal.setCode(Principal.AUTHENTICATION_FAILED);
                }

                if (principal.getCode() == Principal.LOGIN_SUCCESS) {
                    principal.setCode(Principal.LOGIN_SUCCESS);
                    Map<String, Object> payload = new HashMap<>();
                    payload.put("name", principal.getUsername());
                    payload.put("username", user.getLogin());
                    String roles;
                    //List<TipoUsuarioPerfil> grants = user.getTipoUsuarioPerfilList();
                    StringBuffer sbRoles = new StringBuffer();
                    List<String> rolesSb = new ArrayList<>();
                    //for (TipoUsuarioPerfil grant : grants) {
                    //    sbRoles.append(grants.get(0).getTipoPerfilesPuesto().getPerfil()).append(",");
                    //    rolesSb.add(grants.get(0).getTipoPerfilesPuesto().getPerfil());

                    //}
                    roles = sbRoles.toString();
                    //System.out.println("**"+roles+"**");
                    if (roles.length() > 0) {
                        roles = roles.substring(0, roles.length() - 1);
                    } else {
                        roles = "vacio";
                    }
                    //principal.setRoles(rolesSb);
                    principal.setTipousuario(user);
                    payload.put("roles", roles);
                    String tokenGenerado = JWTUtils.generateToken(principal.getUsername(), payload, Constants.SESSION_DURATION);
                    principal.setToken(tokenGenerado);
                    principal.setFullname(user.getNombre());
                    principal.setUserId(user.getIdUsuario());
                    TipoTokenBlacklist blacklist = new TipoTokenBlacklist();
                    blacklist.setToken(principal.getToken());
                    Timestamp ahora = new Timestamp(new Date().getTime());
                    blacklist.setInvalidationDate(ahora);
                    blacklist.setIdUsuario(user.getIdUsuario());
                    dao.createEntity(blacklist);
                }
            }
        } catch (Exception e) {
            principal.setToken("");
            principal.setCode(Principal.SERVICE_UNAVAILABLE);
            e.printStackTrace();
        }

        return principal;
    }

    public Principal logout(Principal principal) {

        Map<String, Object> params = new HashMap<>();
        params.put("token", principal.getToken());
        TipoTokenBlacklist existeToken = dao.getExisteToken(params);
        if (existeToken != null) {
            existeToken.setToken("");
            existeToken.setIdUsuario(0);
            dao.createEntity(existeToken);
            principal.setToken("");
            principal.setCode(Principal.LOGOUT_SUCCESS);
        } else {
            principal.setCode(Principal.TOKEN_NOT_FOUND);
        }
        return principal;
    }

    public boolean getExisteToken(String pToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", pToken);
        return dao.getExisteToken(params) != null;
    }

    public boolean changepassword(String token, ChangePasswordDto changepass) {
        boolean result = false;
        boolean siguiente = false;
        //obtiene el usuario del tocken
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("login", JWTUtils.getUserName(token));
            List<TipoUsuario> users = dao.findByUsuario(params);
            if (users != null && !users.isEmpty()) {
                siguiente = true;
            }
            if (siguiente) {
                String password = "";
                password = users.get(0).getClave();
                // valida que la clave anterior sea igual
                if (password.equals(cryptoSvc.encrypt(changepass.getOldpassword(), Crypto.ALG_SHA256))) {
                    //graba la nueva clave
                    TipoUsuario change = users.get(0);
                    change.setClave(cryptoSvc.encrypt(changepass.getNewpassword(), Crypto.ALG_SHA256));
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean authorized(String token, String dato) throws UnauthorizedException {
        boolean autorized = false;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);

        List<TipoTokenBlacklist> sesion = dao.getExisteTokenList(params);
        //System.out.println("existe en db ");
        if (sesion.size() > 0) {
            //System.out.println("existe en db ");
            TipoTokenBlacklist sesionActual = sesion.get(0);

            //Invalidacion del token default 24 horas
            long horas = (new Date().getTime() - sesionActual.getInvalidationDate().getTime()) / (1000 * 60 * 60);
            System.out.println("ahora " + new Date().getTime() + ", grabada " + sesionActual.getInvalidationDate().getTime());
            System.out.println("lleva de vida " + horas);
            if (!dato.equals("0")) {
                sesionActual.setToken("");
                dao.createEntity(sesionActual);
                return false;
            }
            if (horas < 48) {
                autorized = true;
            } else {
                sesionActual.setToken("");
                dao.createEntity(sesionActual);
                return false;
            }

        }
        System.out.println("autorizado " + autorized);
        return autorized;
    }

    public DiacoQueja findTokenIntExt(String dato, String token, String idqueja) {
        DiacoQueja quejaini = new DiacoQueja();
        try {
            if (dato.equals("externo") || dato.equals("externo1") || dato.equals("pr")) {
                List<DiacoMagicLink> links = this.quejaDao.findTokenIntExt(token); //link en tabla
                if (links != null && !links.isEmpty()) {
                    DiacoMagicLink link = links.get(0);
                    if (dato.equals("externo1")) {
                        link = this.quejaDao.updateStateLink(link);
                    } else {
                        if (link.getId_queja() != null) {
                            quejaini = this.quejaDao.findByDIdQueja(link.getId_queja());
                            if (quejaini != null) {
                                if (dato.equals("pr")) {
                                   if (link.getId_referencia() != null) {
                                       DiacoConsumidor c =  this.consumidor.findConsumidorById(quejaini.getIdConsumidor());
                                       if (c!=null) {
                                           quejaini.setDocc(c.getNitConsumidor() != null?c.getNitConsumidor():c.getDocumentoIdentificacion());
                                       }
                                       DiacoProveedor p = this.proveedor.getProveedorById(quejaini.getIdProveedor());
                                       if (p!=null) {
                                           quejaini.setDocp(p.getNitProveedor()!=null?p.getNitProveedor():"");
                                           quejaini.setNomp(p.getNombre()!=null?p.getNombre():"");
                                       }
                                       DiacoConcvirtInteraccion cv = this.quejaDao.findByDIConvirt(link.getId_referencia());
                                       Boolean tnull = false;
                                       if (cv != null) {
                                           if (cv.getEstado() != null) {
                                               if (cv.getEstado().equals("A")) {
                                                   //setea los valorea a queja
                                                   quejaini.setPregunta(cv.getPregunta());
                                                   quejaini.setCarchivos(cv.getCambiarArchivos()?"1":"0");
                                                   quejaini.setCdatos(cv.getCambiarDatos()?"1":"0");
                                                   quejaini.setRespuesta(cv.getRespuestaCon()!= null?cv.getRespuestaCon():"" );
                                                   tnull = true;
                                               }
                                           }
                                       }
                                       if (!tnull) {
                                           quejaini = null;
                                       }
                                   } else {
                                       quejaini = null; 
                                   }
                                } 
                            } else {
                                quejaini = null;
                            }
                        }
                    }
                } else {
                    quejaini = null;
                }
            } else if (dato.equals("interno") || dato.equals("presencial")) {
                //valida token
                System.out.println(dato + ", " + token + ", " + idqueja);
                if (this.authorized(token, "0") || this.authorized(token.replace("a1b2c3", "."), "0") ) { // verifica si el token esta activo en db
                    quejaini = this.quejaDao.findByDIdQueja(Integer.parseInt(idqueja));
                    System.out.println(quejaini != null ? "existe" : "no existe");
                    quejaini.setUsuarioToken(JWTUtils.getUserName(token));
                    System.out.println(quejaini != null ? JWTUtils.getUserName(token) : "no existe user");
                } else {
                    quejaini = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            quejaini = null;
        }
        return quejaini;
    }

    public TipoUsuario findTokenPresenccial(String dato, String token) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        try {
            //valida token
            System.out.println(dato + ", " + token );
            if (this.authorized(token, "0")) { // verifica si el token esta activo en db
                tipoUsuario = this.quejaDao.findUserByUserName(JWTUtils.getUserName(token));
                System.out.println(tipoUsuario != null ? JWTUtils.getUserName(token) : "no existe user");
            } else {
                tipoUsuario = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            tipoUsuario = null;
        }
        return tipoUsuario;
    }

    /**
     * *
     * Verifica que el token enviado sea valido
     *
     * @param token token
     * @param dato token
     * @return valores del token o vacio
     */
    public DiacoQuejaIniDto findTokenByConsumidor(String dato, String token) {
        DiacoQuejaIniDto quejaini = new DiacoQuejaIniDto();
        try {
            //System.out.println("findTokenByConsumidor");
            if (this.authorized(token.replace("a1b2c3", "."), dato)) {
                //retorna los datos 
                //System.out.println("verifica token y busca dato");
                quejaini = JWTUtils.decodeTokenForCon(token.replace("a1b2c3", "."));
                if (quejaini != null) {
                    DiacoQueja queja = this.quejaDao.findByDIdQueja(quejaini.getIdQueja());
                    if (queja != null) {
                        quejaini.setIdQueja(queja.getIdQueja());
                        DiacoConsumidor consumidor = this.consumidor.findConsumidorById(queja.getIdConsumidor());
                        if (consumidor != null) {
                            quejaini.setNombre(
                                    (consumidor.getNombre1() != null ? consumidor.getNombre1() + " " : " ")
                                    + (consumidor.getNombre2() != null ? consumidor.getNombre2() + " " : "")
                                    + (consumidor.getNombre3() != null ? consumidor.getNombre3() + " " : "")
                                    + (consumidor.getApellido1() != null ? consumidor.getApellido1() + " " : "")
                                    + (consumidor.getApellido2() != null ? consumidor.getApellido2() + " " : "")
                                    + (consumidor.getApellidoCasada() != null ? consumidor.getApellidoCasada() : ""));
                            quejaini.setDpiPasaporte(consumidor.getDocumentoIdentificacion());
                            TipoTelefono telefono = this.consumidor.findTelefonoById(consumidor.getIdConsumidor());
                            if (telefono != null) {
                                quejaini.setTelefono(telefono.getTelefono());
                            } else {
                                quejaini.setTelefono(consumidor.getTelefono());
                            }
                            TipoEmail correo = this.consumidor.findCorreoById(consumidor.getIdConsumidor());
                            if (correo != null) {
                                quejaini.setCorreo(correo.getCorreo_electronico());
                            } else {
                                quejaini.setCorreo(consumidor.getCorreoElectronico1());
                            }

                        }
                        DiacoProveedor proveedor = this.proveedor.getProveedorById(queja.getIdProveedor());
                        if (proveedor != null) {
                            quejaini.setNitProveedor(proveedor.getNitProveedor());
                        }
                        quejaini.setDetalleQueja(queja.getDetalleQueja());
                        quejaini.setSolicitaQue(queja.getSolicitaQue());
                        quejaini.setIdConsumidor(queja.getIdConsumidor());
                        quejaini.setIdProveedor(queja.getIdProveedor());
                        quejaini.setAnio(queja.getAnio());
                        quejaini.setSecuencia(queja.getNoQueja());
                        quejaini.setIdFuente(queja.getIdFuente() != null ? queja.getIdFuente().toString() : "0");
                        quejaini.setUbicacion(queja.getUbicacion());
                        System.out.println("retorna datos de queja");
                    } else {
                        System.out.println("no hay datos en queja");
                    }
                } else {
                    System.out.println("no hay datos en token");
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quejaini;
    }

    public String createTokenByConsumidor(DiacoQuejaIniDto quejaini) {
        Map<String, Object> payload = new HashMap<>();
        //payload.put("nombre", quejaini.getNombre());
        //payload.put("dpiPasaporte", quejaini.getDpiPasaporte());
        // payload.put("telefono", quejaini.getTelefono());
        //payload.put("correo", quejaini.getCorreo());
        //payload.put("detalleQueja", quejaini.getDetalleQueja());
        //payload.put("solicitaQue", quejaini.getSolicitaQue());
        //payload.put("nitProveedor", quejaini.getDpiPasaporte());
        //payload.put("ubicacion", (quejaini.getUbicacion()!=null?quejaini.getUbicacion():"ubicacion"));
        //payload.put("idFuente", (quejaini.getIdFuente()));

        payload.put("idConsumidor", quejaini.getIdConsumidor());
        payload.put("idProveedor", quejaini.getIdProveedor());
        payload.put("anio", quejaini.getAnio());
        payload.put("secuencia", quejaini.getSecuencia());
        //payload.put("idCuenta", quejaini.getIdCuenta());
        payload.put("idQueja", quejaini.getIdQueja());
        //System.out.println("payload "+payload);
        String token = JWTUtils.generateToken(quejaini.getAnio().toString().concat(quejaini.getSecuencia().toString()), payload, Constants.SESSION_DURATION);
        //System.out.println("token "+token);
        TipoTokenBlacklist blacklist = new TipoTokenBlacklist();
        blacklist.setToken(token);
        Timestamp ahora = new Timestamp(new Date().getTime());
        blacklist.setInvalidationDate(ahora);
        blacklist.setIdUsuario(quejaini.getIdConsumidor());
        dao.createEntity(blacklist);
        //System.out.println("saved and replace "+token.replace(".", "a1b2c3"));
        return token.replace(".", "a1b2c3");
    }
}
