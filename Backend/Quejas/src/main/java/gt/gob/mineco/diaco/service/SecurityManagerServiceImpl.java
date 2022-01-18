/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.exception.UnauthorizedException;
import gt.gob.mineco.diaco.dao.SecurityManagerRepository;
import gt.gob.mineco.diaco.dto.ChangePasswordDto;
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
import gt.gob.mineco.diaco.util.Email;
import gt.gob.mineco.diaco.util.JWTUtils;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author mps
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SecurityManagerServiceImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    SecurityManagerRepository dao;
    @Inject
    private Email email;
    @Inject
    Crypto cryptoSvc;

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
        UserTransaction transaction=null;
        try {
            transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            List<TipoUsuario> users = dao.findByUsuario(params);
            if (users != null && !users.isEmpty()) {
                //verifica que no exista un token asociado
                params.clear();
                params.put("idUsuario", users.get(0).getIdUsuario());
                TipoTokenBlacklist existeToken = dao.getExisteTokenUser(params);
                if (existeToken != null) {
                    if (this.authorized(existeToken.getToken())) {
                        
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
                        
                        if(principal.getCode() == 1){
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
                    List<TipoUsuarioPerfil> grants = user.getTipoUsuarioPerfilList();
                    StringBuffer sbRoles = new StringBuffer();
                    List<String> rolesSb = new ArrayList<>();
                    for (TipoUsuarioPerfil grant : grants) {
                        sbRoles.append(grants.get(0).getTipoPerfilesPuesto().getPerfil()).append(",");
                        rolesSb.add(grants.get(0).getTipoPerfilesPuesto().getPerfil());

                    }
                    roles = sbRoles.toString();
                    //System.out.println("**"+roles+"**");
                    if(roles.length() > 0)
                        roles = roles.substring(0, roles.length() - 1);
                    else
                        roles = "vacio";
                    //principal.setRoles(rolesSb);
                    principal.setTipousuario(user);
                    payload.put("roles", roles);
                    String tokenGenerado = JWTUtils.generateToken(principal.getUsername(), payload, Constants.SESSION_DURATION);
                    principal.setToken(tokenGenerado);
                    principal.setFullname(user.getNombre());
                    TipoTokenBlacklist blacklist = new TipoTokenBlacklist();
                    blacklist.setToken(principal.getToken());
                    Timestamp ahora = new Timestamp(new Date().getTime());
                    blacklist.setInvalidationDate(ahora);
                    blacklist.setIdUsuario(user.getIdUsuario());
                    dao.createEntity(blacklist);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            principal.setToken("");
            principal.setCode(Principal.SERVICE_UNAVAILABLE);
            e.printStackTrace();
            try{
                transaction.rollback();
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }

        return principal;
    }
    
    public Principal logout(Principal principal) {
        UserTransaction transaction=null;
        try {
            transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
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
            transaction.commit();
        } catch (Exception e) { 
                e.printStackTrace();
                principal.setCode(Principal.SERVICE_UNAVAILABLE);
                try{
                    transaction.rollback();
                }catch(Exception ee){
                    ee.printStackTrace();
                }
        }		
        return principal;    
    }

    public boolean getExisteToken(String pToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", pToken);
        return dao.getExisteToken(params) != null;
    }

    public boolean changepassword(String token, ChangePasswordDto changepass) {
        UserTransaction transaction=null;
        boolean result = false;
        boolean siguiente = false;
        //obtiene el usuario del tocken
        Map<String, Object> params = new HashMap<>();
        try {
            transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
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
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                transaction.rollback();
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }
        return result;
    }

    public boolean authorized(String token) throws UnauthorizedException {
        boolean autorized = false;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);

        List<TipoTokenBlacklist> sesion = dao.getExisteTokenList(params);

        if (sesion.size() > 0) {
            TipoTokenBlacklist sesionActual = sesion.get(0);

            //Invalidacion del token default 24 horas
            long horas = (new Date().getTime() - sesionActual.getInvalidationDate().getTime()) / (1000 * 60 * 60);

            if (horas < 24) {
                autorized = true;
            } else {
                sesionActual.setToken("");
                dao.createEntity(sesionActual);
                return false;
            }

        }
        return autorized;
    }

    public String CheatEncrypt(String passw){
        return cryptoSvc.encrypt(passw, Crypto.ALG_SHA256);
    }    
    
    /*public Map<String, String> genNewPassword(Integer idusuario) throws Exception{
        Map<String, String> resp = new HashMap<>();
        String genPass = this.cryptoSvc.getPassword();
        //gen.put("clave", genPass);
        //gen.put("db", this.cryptoSvc.encrypt(genPass, Crypto.ALG_SHA256));
        Map<String, Object> params = new HashMap<>();
        params.put("id_usuario", idusuario);
        TipoUsuario user = dao.findByUsuario1(params);
        String local_encript=cryptoSvc.encrypt(genPass, Crypto.ALG_SHA256);
        user.setClave(local_encript);
        dao.saveUsuario(user);
        resp.put("clave", genPass);
        resp.put("encriptado", local_encript);
        //return gen;
        //enviar correo
        return resp;
    }*/
    
    public Map<String, String> GetRandomPW(){
        String genPass = this.cryptoSvc.getPassword();
        Map<String, String> resp = new HashMap<>();
        resp.put("encriptado",cryptoSvc.encrypt(genPass, Crypto.ALG_SHA256));
        resp.put("clave",genPass);
        return resp;
    }
    
    public Map<String, String> PWNewUser(){
        String genPass = "12345678";
        Map<String, String> resp = new HashMap<>();
        resp.put("encriptado",cryptoSvc.encrypt(genPass, Crypto.ALG_SHA256));
        resp.put("clave",genPass);
        return resp;
    }
    
    public Map<String, String> SetNewPW(String newpassword){
        String genPass = newpassword;
        Map<String, String> resp = new HashMap<>();
        resp.put("encriptado",cryptoSvc.encrypt(genPass, Crypto.ALG_SHA256));
        resp.put("clave",genPass);
        return resp;
    }
    
}
