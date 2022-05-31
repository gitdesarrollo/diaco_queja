/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author Oscar Blancarte <oscarblancarte3@gmail.com>
 */
@ApplicationScoped
@ServerEndpoint("/socket")
public class ServerDashboardWebSocket {

    //private Set<Session> sessions = new HashSet<>();
    static HashSet<Session> sessions = new HashSet<Session>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened ==> JJ" + session.getId());
        sessions.add(session);
        System.out.println("Cantidad de Sesiones: "+sessions.size());
        for (Session s : sessions) {
            if (s.isOpen()) {
            System.out.println("s.getId(): "+s.getId());
            }
        }
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("new message ==> " + message);
        Mensaje mensaje = new Gson().fromJson(message, Mensaje.class);
        session.getOpenSessions();
        if (!(mensaje.getUsuario().length() > 0)){
            System.out.println("Mensaje de Chat");
            try {
                for (int c = 0; c < 1; c++) {
                    for (Session s : sessions) {
                        if (s.isOpen()) {
                        //s.getBasicRemote().sendText("{\"value\" : \"" + (s.getId()) +  "\"}");
                        s.getBasicRemote().sendText(mensaje.toString());
                        System.out.println("Ciclo: "+c+", Session: "+s.getId()+", Mensaje: "+mensaje.getMsg());
                        }
                    }
                //Thread.sleep(10000);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Mensaje de Iniciar Conexion");
            try {
                for (Session s : sessions) {
                    if (s.isOpen()) {
                    //s.getBasicRemote().sendText("{\"value\" : \"" + (s.getId()) +  "\"}");
                    mensaje.setConexion("1");
                    s.getBasicRemote().sendText(mensaje.toString());
                    System.out.println("Conectado: "+mensaje.getConexion()+", Session: "+s.getId()+", Mensaje: "+mensaje.getMsg());
                    }
                }           
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>"+ (session.getId()));
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}

class Mensaje{
    private String msg;
    private String autor;
    private Integer audiencia;
    private String usuario = "";
    private String conexion;

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getConexion() {
        return conexion;
    }
    public void setConexion(String conexion) {
        this.conexion = conexion;
    }
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public Integer getAudiencia() {
        return audiencia;
    }
    
    public void setAudiencia(Integer audiencia){
        this.audiencia = audiencia;
    }
    
    public String toString()
    {
        JsonObject temp = new JsonObject();
        temp.addProperty("autor",this.autor);
        temp.addProperty("msg",this.msg);
        temp.addProperty("audiencia",this.audiencia);
        temp.addProperty("conexion",this.conexion); 
          
        return temp.toString();
    }
   
}