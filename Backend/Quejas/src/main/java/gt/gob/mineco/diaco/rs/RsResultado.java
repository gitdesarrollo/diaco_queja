package gt.gob.mineco.diaco.rs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RsResultado<T> implements Serializable {

    public static final int SUCCESS = 200;
    public static final int ERROR = 600;
    public static final int PARTIAL_PROCESS = 601;
    public static final int BAD_PARAMETER = 602;
    public static final int NO_RESULTS = 603;
    public static final int ALREADY_EXISTS = 604;
    public static final int TOO_MANY_ERRORS = 605;
    public static final int NOT_AUTHORIZED = 606;
    public static final int INVALID_TOKEN = 607;

    int codigo;
    String descripcion;
    RsProceso resultado;  
    List<T> detalle;
    ResultDetail resultDetail;


    public RsResultado() {
    }

    public RsResultado(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public RsProceso getResultado() {
        return resultado;
    }

    public void setResultado(RsProceso resultado) {
        this.resultado = resultado;
    }

    public List<T> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<T> detalle) {
        this.detalle = detalle;
    }

    public void addDetalle(T newDetalle) {
        if (detalle == null) {
            detalle = new ArrayList<T>();
        }
        detalle.add(newDetalle);
    }

    public ResultDetail getResultDetail() {
        return resultDetail;
    }

    public void setResultDetail(ResultDetail resultDetail) {
        this.resultDetail = resultDetail;
    }

}
