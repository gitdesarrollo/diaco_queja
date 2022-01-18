package gt.gob.mineco.diaco.dto;

import java.io.Serializable;

/**
 * Esta clase define un conjunto de atributos y metodos, los cuales representan
 * el estado y el comportamiento del objeto mismo.
 *
 * @author Yeremy Anthony Chun Quinillo
 * <a href="mailto:yachunqu@ittraktys.com">Anthony Chun</a>
 * @since 07/02/2020
 * @version 1.0
 */
public class NotificacionDto implements Serializable {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Lista de destinatarios.
     */
    private String[] destinatarios;
    /**
     * Remitente del mensaje.
     */
    private String remitente;
    /**
     * Asunto del mensaje.
     */
    private String asunto;
    /**
     * Texto o cuerpo del mensaje
     */
    private String mensaje;

    /**
     * Crea una nueva instancia de <code>NotificacionDto</code>.
     */
    public NotificacionDto() {
        // SonarFix
    }

    public NotificacionDto(String[] destinatarios, String remitente, String asunto, String mensaje) {
        this.destinatarios = destinatarios;
        this.remitente = remitente;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String[] getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
