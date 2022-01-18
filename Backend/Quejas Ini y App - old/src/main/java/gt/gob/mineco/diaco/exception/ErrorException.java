package gt.gob.mineco.diaco.exception;

/**
 * Esta clase define un uso personalizado para un tipo de Excepcion.
 *
 * @author Yeremy Anthony Chun Quinillo
 * <a href="mailto:yachunqu@ittraktys.com">Anthony Chun</a>
 * @since 07/02/2020
 * @version 1.0
 */
public class ErrorException extends Exception {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia de <code>ErrorException</code> sin detalle de
     * mensaje.
     */
    public ErrorException() {
        //SonarFix
    }

    /**
     * Construye una instancia de <code>ErrorException</code> con un mensaje
     * especifico.
     *
     * @param pMessage El detalle del mensaje.
     */
    public ErrorException(String pMessage) {
        super(pMessage);
    }

    /**
     * Construye una instancia de <code>ErrorException</code> con la causa del
     * error.
     *
     * @param pCause La causa de la excepcion.
     */
    public ErrorException(Throwable pCause) {
        super(pCause);
    }

    /**
     * Construye una instancia de <code>ErrorException</code> con la causa del
     * error y un mensaje especifico.
     *
     * @param pMessage El detalle del mensaje.
     * @param pCause La causa de la excepcion.
     */
    public ErrorException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
