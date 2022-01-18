/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import gt.gob.mineco.diaco.dao.TipoRepository;
import gt.gob.mineco.diaco.model.TipoMailServer;
import gt.gob.mineco.diaco.model.TipoBitacora;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class Email {
    
    @Inject
    private TipoRepository tipoDao;
    
    /*String host="localhost";  
    final String user="frank@testmail.com";  
    final String password="admin";*/
    String host="128.5.11.49";  
    final String user="notificaciondiaco@mineco.gob.gt";  
    final String password="@DIACO$2019!";
    
    public boolean SendEmail(String from, String[] to, String subject, String body) throws Exception{
            //Get the session object  
            Properties props = new Properties();  
            TipoMailServer mailserverparams = tipoDao.findAllTipoMailServer();
            
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtp.user", mailserverparams.getUsuario());
            props.put("mail.smtp.password", mailserverparams.getPassword());
            props.put("mail.smtp.host", mailserverparams.getHost());
            props.put("mail.smtp.port", mailserverparams.getPuerto());            
            props.put("mail.smtp.ehlo", "false");
            props.put("mail.smtp.auth", "false"); 
               Session session = Session.getInstance(props);
            System.out.println("JJ-> SendEmail: Usuario: "+mailserverparams.getUsuario());
            System.out.println("JJ-> SendEmail: from: "+from);
            System.out.println("JJ-> SendEmail: to: "+to);
            System.out.println("JJ-> SendEmail: subject: "+subject);
            System.out.println("JJ-> SendEmail: body: "+body);
            
                //seccion para version local
                /*props.put("mail.smtp.auth", "true");
              Session session = Session.getInstance(props,
             new javax.mail.Authenticator() {  
               protected PasswordAuthentication getPasswordAuthentication() {  
             return new PasswordAuthentication(user,password);  
               }  
             });*/            
               
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
                 System.out.println("JJ-> SendEmail: message: "+message);
                 Transport.send(message);  
                 System.out.println("message sent successfully...");  

            } catch (MessagingException e) {
                System.out.println("Entrando al Catch de SendEmail.");
                e.printStackTrace(); 
                System.out.println("Error: SendEmail: "+e.getMessage());
                return false;
            }
            return true;
    }
}
