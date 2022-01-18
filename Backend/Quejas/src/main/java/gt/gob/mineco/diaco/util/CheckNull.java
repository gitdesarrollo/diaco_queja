/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;
import java.util.Date;
/**
 *
 * @author TheTodd
 */
public class CheckNull {

  public CheckNull() {
  }
    
  public String cknull(String value) {
        if(value != null)
            return value;
        else
            return "";
  }
  
    public String cknull(Date value) {
        if(value != null)
            return value.toString();
        else
            return "";
    }
    
    public String cknull(Integer value) {
        if(value != null)
            return value.toString();
        else
            return "";
    }

    public String cknull(double value) {
        if(value != 0)
            return Double.toString(value);
        else
            return "";
    }

}
