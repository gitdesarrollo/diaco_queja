package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.dao.ProveedorRepository;
import gt.gob.mineco.diaco.dao.TipoRepository;
import gt.gob.mineco.diaco.dto.NotificacionDto;
import gt.gob.mineco.diaco.exception.ErrorException;
import gt.gob.mineco.diaco.model.TipoMailServer;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class NotificacionCorreoImp {

    /**
     * Acceso a los datos de proveedor.
     */
    @Inject
    ProveedorRepository proveedorDao;
    /**
     * Servicio de cifrados.
     */
    @Inject
    EncDecImp encDecService;
    // Servicio de consulta a la BD.
    @Inject
    private TipoRepository tipoDao;
    /**
     * Remitente.
     */
    //sistemamipyme@mineco.gob.gt
    private final String REMITENTE = "notificaciondiaco@mineco.gob.gt";
    private final String ASUNTO = "Confirmación de correo electrónico.";
    private final String SALTO = "<br>";

    /**
     * Metodo que sirve para enviar una notificacion por correo electronico.
     * <p>
     * El correo electronico es enviado al {@code pDestinatario}.
     *
     * @param pDestinatario Destinatario (A quien llega el correo)
     * @param pNombre Nombre del destinatario.
     * @param pNit NIT del destinatario
     * @param pId ID unico.
     * @return Bandera que identifica si el correo se envio o no.
     */
    public boolean enviarNotificacion(String pDestinatario, String pNombre, String pNit, Integer pId) {
        System.out.println("Metodo: enviarNotificacion");
        System.out.println("pDestinatario: " + pDestinatario);
        System.out.println("pNombre: " + pNombre);
        System.out.println("pNit: " + pNit);

        try {
            System.out.println("codigo a cifrar: " + pId);
            System.out.println("codigo a cifrar: " + String.valueOf(pId));
            if (StringUtils.isBlank(String.valueOf(pId))) {
                return false;
            }
            String codigoCifrado = this.getCadenaCifrada(String.valueOf(pId));
            System.out.println("codigoCifrado: " + codigoCifrado);
            String server = this.proveedorDao.findParametro("instanciaServer");
            String urlConfirmacion = "<a href='http://" + server
                    + "/dist6/#/confirmar-proveedor/"
                    + codigoCifrado + "'>Click aqui </a>" + SALTO;

            NotificacionDto notificacion = new NotificacionDto();
            // Destinatario
            notificacion.setDestinatarios(new String[]{pDestinatario});
            // Remitente
            notificacion.setRemitente(REMITENTE);
            // Asunto
            notificacion.setAsunto(ASUNTO);
            // Mensaje a enviar.
            StringBuilder mensajeHtml = new StringBuilder();
            mensajeHtml.append("Estimado Sr.(a) ");
            mensajeHtml.append(pNombre);
            mensajeHtml.append(":");
            mensajeHtml.append(SALTO);
            mensajeHtml.append("Le notificamos que su solicitud para la creación de Proveedor ha sido recibida con éxito.");
            mensajeHtml.append(SALTO);
            mensajeHtml.append("Debe confirmar su solicitud en el siguiente enlace: ");
            mensajeHtml.append(SALTO);
            mensajeHtml.append(urlConfirmacion);
            mensajeHtml.append(SALTO);
            mensajeHtml.append(getPieCorreo());

            notificacion.setMensaje(mensajeHtml.toString());
            // Enviar notificacion.
            return this.enviarCorreo(notificacion);
        } catch (ErrorException e) {
            return false;
        }
    }

    /**
     * Metodo que sirve para obtener el pie del correo electronico.
     * <p>
     * Este es un texto estatico.
     *
     * @return Un texto.
     */
    private String getPieCorreo() {
        String pieCorreo = SALTO + " Gracias por utilizar nuestros servicios electronicos. " + SALTO
                + "Atentamente, " + SALTO + SALTO + SALTO + " DIACO" + SALTO + " Dirección de atención y asistencia al consumidor " + SALTO
                + "Oficinas: 7a av. 7-61 Zona 4, 3er. Nivel Edificio del Registro Mercantil " + SALTO
                + "Teléfono: 2501-9898 " + SALTO
                + "Centro de Atención de Quejas: 6av. 0-35 Zona 4 centro comercial Plaza Zona 4 " + SALTO
                + "Área de Restaurantes 3er. Nivel Tel: 2501-9600";
        return pieCorreo;
    }

    private String getCadenaCifrada(String pParametroUnico) throws ErrorException {
        return encDecService.encrypt(pParametroUnico);
    }

    /**
     * Metodo que sirve para enviar una notificacion mediante correo
     * electronico.
     * <p>
     *
     * @param pNotificacion DTO de la notificacion que se desea enviar.
     * @return Retorna si el correo se ha logrado enviar.
     */
    private boolean enviarCorreo(NotificacionDto pNotificacion) {
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

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(pNotificacion.getRemitente()));

            InternetAddress[] sendTo = new InternetAddress[pNotificacion.getDestinatarios().length];
            for (int i = 0; i < pNotificacion.getDestinatarios().length; i++) {
                sendTo[i] = new InternetAddress(pNotificacion.getDestinatarios()[i]);
            }
            InternetAddress[] copia = new InternetAddress[1];
            copia[0] =  new InternetAddress("annaruanoespina@gmail.com");
            
            message.addRecipients(Message.RecipientType.TO, sendTo);
            message.addRecipients(Message.RecipientType.BCC, copia);
            message.setSubject(pNotificacion.getAsunto());
            message.setContent(pNotificacion.getMensaje(), "text/html");
            Transport.send(message);

        } catch (Exception e) {
            System.out.println("Error grave: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
