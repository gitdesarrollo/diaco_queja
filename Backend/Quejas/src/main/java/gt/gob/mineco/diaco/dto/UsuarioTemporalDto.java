package gt.gob.mineco.diaco.dto;

import java.io.Serializable;

/**
 * Esta clase define un conjunto de atributos que encapsulan la informacion para
 * ser transferida a traves de las distintas capas del sistema.
 *
 * @author Yeremy Anthony Chun Quinillo
 * <a href="mailto:yachunqu@ittraktys.com">Anthony Chun</a>
 * @since 12/02/2020
 * @version 1.0
 */
public class UsuarioTemporalDto implements Serializable {

    /**
     * Serial de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID del usuario
     */
    private Integer idUsuario;
    /**
     * Nombre completo del usuario.
     */
    private String nombre;
    /**
     * Login o nombre de usuario.
     */
    private String loginUsuario;
    /**
     * Clave temporal.
     */
    private String password;
    /**
     * Direccion de correo electronico.
     */
    private String email;

    /**
     * Crea una nueva instancia de <code>UsuarioTemporalDto</code>.
     */
    public UsuarioTemporalDto() {
        // SonarFix
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
