package gt.gob.mineco.diaco.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author jjolon
 */

@Entity(name="diaco_parametros")
@Table(name="diaco_parametros")
@NamedQueries({
    @NamedQuery(name="ParametroGeneral.findById", query="SELECT p FROM diaco_parametros p where p.id_parametro = :id"),
    @NamedQuery(name="ParametroGeneral.findByName", query="SELECT p FROM diaco_parametros p where p.parametro = :name")
})
public class ParametroGeneral  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_parametro", precision = 0)
    private Integer id_parametro;

    @Column(name="parametro")
    private String parametro;

    @Column(name="valor")
    private String valor;

    @Column(name="usuario_adiciono")
    private String usuario_adiciono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_adicion")
    private Date fecha_adiciono;

    @Column(name="usuario_modifico")
    private String usuario_modifico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_modifico")
    private Date fecha_modifico;

    public ParametroGeneral() {
    }


    public Integer getId_parametro() {
        return id_parametro;
    }

    public void setId_parametro(Integer id_parametro) {
        this.id_parametro = id_parametro;
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

    public String getUsuario_adiciono() {
        return usuario_adiciono;
    }

    public void setUsuario_adiciono(String usuario_adiciono) {
        this.usuario_adiciono = usuario_adiciono;
    }

    public Date getFecha_adiciono() {
        return fecha_adiciono;
    }

    public void setFecha_adiciono(Date fecha_adiciono) {
        this.fecha_adiciono = fecha_adiciono;
    }

    public String getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(String usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public Date getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(Date fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }
}
