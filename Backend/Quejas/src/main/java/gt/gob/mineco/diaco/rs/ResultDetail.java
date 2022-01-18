/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.rs;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ResultDetail<T> implements Serializable {
    int total;
    List<T> detalle;
    

    public List<T> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<T> detalle) {
        this.detalle = detalle;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
