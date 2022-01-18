/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import gt.gob.mineco.diaco.dao.TipoRepository;
//import gt.gob.mineco.diaco.model.TipoMailServer;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TheTodd
 */
public class Email {
    
    @Inject
    private TipoRepository tipoDao;
    
    /*String host="localhost";  
    final String user="frank@testmail.com";  
    final String password="admin";  */
    //String host="128.5.11.49";  
    //final String user="notificaciondiaco@mineco.gob.gt";  
    //final String password="@DIACO$2019!";
    
    public boolean SendEmail(String from, String[] to, String subject, String body, String host, String user, String password) throws Exception{
            //Get the session object  
            Properties props = new Properties();  
            //TipoMailServer mailserverparams = tipoDao.findAllTipoMailServer();
            from = "notificaciondiaco@mineco.gob.gt";
            props.put("mail.transport.protocol", "smtps");
            /*props.put("mail.smtp.user", mailserverparams.getUsuario());
            props.put("mail.smtp.password", mailserverparams.getPassword());
            props.put("mail.smtp.host", mailserverparams.getHost());
            props.put("mail.smtp.port", mailserverparams.getPuerto());     */
            props.put("mail.smtp.user", user);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", 25);
            props.put("mail.smtp.ehlo", "false");

              /*Session session = Session.getInstance(props,
             new javax.mail.Authenticator() {  
               protected PasswordAuthentication getPasswordAuthentication() {  
             return new PasswordAuthentication(user,password);  
               }  
             });*/              

               props.put("mail.smtp.auth", "false"); 
               Session session = Session.getInstance(props); 
              
               //Compose the message  
                try {  
                 MimeMessage message = new MimeMessage(session);  
                 message.setFrom(new InternetAddress(from));  
                 
                InternetAddress[] sendTo = new InternetAddress[to.length];
		for (int i = 0; i <to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]);
		}
                 
                 message.addRecipients(Message.RecipientType.TO,sendTo);  
                 message.setSubject(subject);  
                 //message.setText(body);  
                 message.setContent(body, "text/html");

                //send the message  
                 Transport.send(message);  
                 System.out.println("message sent successfully...");  

            } catch (MessagingException e) {e.printStackTrace(); return false;}
            return true;
    }
}
