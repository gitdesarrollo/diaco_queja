/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@Entity
@Table(name = "diaco_parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiacoParametros.findAll", query = "SELECT d FROM DiacoParametros d")
    , @NamedQuery(name = "DiacoParametros.findByIdParametro", query = "SELECT d FROM DiacoParametros d WHERE d.idParametro = :idParametro")
    , @NamedQuery(name = "DiacoParametros.findByParametro", query = "SELECT d.valor FROM DiacoParametros d WHERE d.parametro = :parametro")
    , @NamedQuery(name = "DiacoParametros.findByValor", query = "SELECT d FROM DiacoParametros d WHERE d.valor = :valor")
})
public class DiacoParametros implements Serializable {

	@Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_parametro")
	private Integer idParametro;
	
	@Column(name="parametro")
	private String parametro;

        @Column(name="valor")
	private String valor;
	
	@Column(name="usuario_adiciono")
	private String usuarioAdiciono;
	
	@Column(name="fecha_adicion")
	private Timestamp fechaAdicion;
	
	@Column(name="usuario_modifico")
	private String usuarioModifico;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

    public Timestamp getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Timestamp fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

        
}
