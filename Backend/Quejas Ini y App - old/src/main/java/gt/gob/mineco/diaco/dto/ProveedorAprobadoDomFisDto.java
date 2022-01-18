/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@XmlRootElement(name = "DOMICILIO_FISCAL")
public class ProveedorAprobadoDomFisDto {

    private String coa;
    private String nc;
    private String apa;
    private String zon;
    private String col;
    private String cd;
    private String cm;
    private String tel;
    private String fax;
    private String cp;
    private String ema;

    @XmlElement(name = "COA")
    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    @XmlElement(name = "NC")
    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    @XmlElement(name = "APA")
    public String getApa() {
        return apa;
    }

    public void setApa(String apa) {
        this.apa = apa;
    }

    @XmlElement(name = "ZON")
    public String getZon() {
        return zon;
    }

    public void setZon(String zon) {
        this.zon = zon;
    }

    @XmlElement(name = "COL")
    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    @XmlElement(name = "CD")
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    @XmlElement(name = "CM")
    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    @XmlElement(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @XmlElement(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @XmlElement(name = "CP")
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @XmlElement(name = "EMA")
    public String getEma() {
        return ema;
    }

    public void setEma(String ema) {
        this.ema = ema;
    }

}
