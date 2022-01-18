package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

        private double id;
        private Integer id_queja;
        private Integer usuario;
	private String bitacora;
	private String consumidor;
        private Integer id_flujo;
        private String descripcion;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private String descripcion_consumidor;
        private String token;
        
	public FormBitacora() {
	}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }
    
    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getBitacora() {
        return bitacora;
    }

    public void setBitacora(String bitacora) {
        this.bitacora = bitacora;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_sede_diaco_operacio() {
        return id_sede_diaco_operacio;
    }

    public void setId_sede_diaco_operacio(Integer id_sede_diaco_operacio) {
        this.id_sede_diaco_operacio = id_sede_diaco_operacio;
    }

    public Integer getId_departamento_interno() {
        return id_departamento_interno;
    }

    public void setId_departamento_interno(Integer id_departamento_interno) {
        this.id_departamento_interno = id_departamento_interno;
    }

    public String getDescripcion_consumidor() {
        return descripcion_consumidor;
    }

    public void setDescripcion_consumidor(String descripcion_consumidor) {
        this.descripcion_consumidor = descripcion_consumidor;
    }


        
}
