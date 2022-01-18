/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author TheTodd
 */
public class DateParam {
  private final LocalDate date;

  public DateParam() {
      this.date = LocalDate.now();
  }
  
  public DateParam(String dateStr) {
      this.date = LocalDate.parse(dateStr);
  }
  
  public DateParam(LocalDate dateStr) {
      this.date = dateStr;
  }
  
@Override  
public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    return this.date.format(formatter);
}

  /*public void setDate(LocalDate date) {
    this.date = date;
  }*/
  
  public LocalDate getDate() {
    return this.date;
  }
}
