package gt.gob.mineco.diaco.util;

import java.util.Iterator;
import java.util.List;

public class ResponseRs {

    private Long code;
    private String reason;
    private Object value;
    private Integer total;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value != null) {

            if (value instanceof List) {
                String valTextJson = "";
                List val = (List) value;
                for (Iterator iterator = val.iterator(); iterator.hasNext();) {
                    Object next = iterator.next();
                    valTextJson = valTextJson + next.toString();
                }
                this.value = valTextJson;
            } else { this.value=value.toString(); }

        } else {
            this.value = value;
        }
        // this.value = value;
    }

    /*public void setValue(Object value) {
		this.value = value;
    } */

    @Override
    public String toString() {
        return "ResponseRs [code:" + code + ", reason:" + reason + ", value:" + value + "]";
        //return "ResponseRs [code: 0L , reason: none, value: [{codigo_departamento:1,nombre_departamento:nombre1},{codigo_departamento:2,nombre_departamento:nombre2}] ]";
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
