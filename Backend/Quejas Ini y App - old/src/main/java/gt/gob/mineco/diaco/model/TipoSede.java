package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.ws.rs.Produces;
import java.util.Date;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import gt.gob.mineco.diaco.model.TipoMunicipio;
/**
 * The persistent class for the diaco_sede database table.
 * 
 */
@Entity
@Table(name="diaco_sede")
@NamedQueries({
    @NamedQuery(name = "TipoSede.findAllExcOne",query = "SELECT t FROM TipoSede t where t.habilitado = 'A' and t.id_diaco_sede != :excluir_id"),
    @NamedQuery(name = "TipoSede.findAll",query = "SELECT t FROM TipoSede t where t.habilitado = 'A'")
})
public class TipoSede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="id_diaco_sede")
	private Integer id_diaco_sede;
        @Column(name="nombre_sede")
	private String nombre_sede;
        @Column(name="habilitado")
	private String habilitado;
        @Column(name="direccion_sede")
	private String direccion_sede;
        @Temporal(TemporalType.DATE)
        @Column(name="fecha_adicion")
        private Date fecha_adicion;
        @Temporal(TemporalType.DATE)
        @Column(name="fecha_modificacion")
        private Date fecha_modificacion;
        @Column(name="usuario_adiciono")
	private String usuario_adiciono;
        @Column(name="usuario_modifico")
	private String usuario_modifico;
        @OneToOne
        @JoinColumn(name = "codigo_municipio", referencedColumnName = "codigo_municipio")
        private TipoMunicipio tipoMunicipio;
        
	public TipoSede() {
	}

        public Integer getId_diaco_sede() {
            return id_diaco_sede;
        }

        public void setId_diaco_sede(Integer id_diaco_sede) {
            this.id_diaco_sede = id_diaco_sede;
        }
        
        public String getNombre_sede() {
            return nombre_sede;
        }

        public void setNombre_sede(String nombre_sede) {
            this.nombre_sede = nombre_sede;
        }

        public String getHabilitado() {
            return habilitado;
        }

        public void setHabilitado(String habilitado) {
            this.habilitado = habilitado;
        }

        public String getDireccion_sede() {
            return direccion_sede;
        }

        public void setDireccion_sede(String direccion_sede) {
            this.direccion_sede = direccion_sede;
        }

        public Date getFecha_adicion() {
            return fecha_adicion;
        }

        public void setFecha_adicion(Date fecha_adicion) {
            this.fecha_adicion = fecha_adicion;
        }

        public Date getFecha_modificacion() {
            return fecha_modificacion;
        }

        public void setFecha_modificacion(Date fecha_modificacion) {
            this.fecha_modificacion = fecha_modificacion;
        }

        public String getUsuario_adiciono() {
            return usuario_adiciono;
        }

        public void setUsuario_adiciono(String usuario_adiciono) {
            this.usuario_adiciono = usuario_adiciono;
        }

        public String getUsuario_modifico() {
            return usuario_modifico;
        }

        public void setUsuario_modifico(String usuario_modifico) {
            this.usuario_modifico = usuario_modifico;
        }

        public TipoMunicipio getTipoMunicipio() {
            return tipoMunicipio;
        }

        public void setTipoMunicipio(TipoMunicipio tipoMunicipio) {
            this.tipoMunicipio = tipoMunicipio;
        }
        
        @Override
        public String toString() {
            JsonObject queja = new JsonObject();
            queja.addProperty("id_diaco_sede",this.id_diaco_sede);
            queja.addProperty("nombre_sede",this.nombre_sede);
            queja.addProperty("habilitado",this.habilitado);
            queja.addProperty("direccion_sede",this.direccion_sede);
            /*queja.addProperty("fecha_adicion",this.fecha_adicion.toString());
            queja.addProperty("fecha_modificacion",this.fecha_modificacion.toString());
            queja.addProperty("usuario_adiciono",this.usuario_adiciono);
            queja.addProperty("usuario_modifico",this.usuario_modifico);*/
            queja.addProperty("codigo_departamento",this.tipoMunicipio.getTipotipoDpto().getCodigo_departamento());
            queja.addProperty("nombre_departamento",this.tipoMunicipio.getTipotipoDpto().getNombre_departamento());
            queja.addProperty("codigo_municipio",this.tipoMunicipio.getCodigo_municipio());
            queja.addProperty("nombre_municipio",this.tipoMunicipio.getNombre_municipio());
            return queja.toString()+",";
        }

}
