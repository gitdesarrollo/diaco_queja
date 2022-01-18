package gt.gob.mineco.diaco.util;

import gt.gob.mineco.diaco.model.*;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the diaco_tipo_imagen database table.
 * 
 */
public class FormImagenSubmit {
	private static final long serialVersionUID = 1L;

        private Integer id_queja;        
	private Integer id_tipo_imagen;
        private Integer id_categoria_imagen;
        private Integer id_flujo;
        private Integer id_proveedor;
        private String habilitado;
        private String token;
        
        public FormImagenSubmit() {
	}

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
        
        public Integer getId_categoria_imagen() {
            return id_categoria_imagen;
        }

        public void setId_categoria_imagen(Integer id_categoria_imagen) {
            this.id_categoria_imagen = id_categoria_imagen;
        }

        public Integer getId_flujo() {
            return id_flujo;
        }

        public void setId_flujo(Integer id_flujo) {
            this.id_flujo = id_flujo;
        }
        
        public Integer getId_queja() {
            return id_queja;
        }

        public void setId_queja(Integer id_queja) {
            this.id_queja = id_queja;
        }

        public Integer getId_tipo_imagen() {
            return id_tipo_imagen;
        }

        public void setId_tipo_imagen(Integer id_tipo_imagen) {
            this.id_tipo_imagen = id_tipo_imagen;
        }

        public String getHabilitado() {
            return habilitado;
        }

        public void setHabilitado(String habilitado) {
            this.habilitado = habilitado;
        }


        
}
