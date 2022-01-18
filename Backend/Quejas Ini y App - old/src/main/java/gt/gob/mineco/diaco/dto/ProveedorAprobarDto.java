/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement ;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mps
 */
@XmlRootElement(name = "DG")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProveedorAprobarDto {

    private String nit;
    private String nom;
    private String to;
    private String cn;
    private String ced;
    private String pas;
    private String fn;
    private String sexo;
    //ProveedorAprobadoDomFisDto domicilioFiscal; //**
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
    //public String ec; //**
    private String firmp;
    private String firmd;
    private String firtu;
    private  ProveedorAprobadoRepDto repLeg; //**

    @XmlElement (name="NIT")
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @XmlElement  (name="NOM")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement  (name="TO")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement  (name="CN")
    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @XmlElement (name = "CED")
    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    @XmlElement (name="PAS")
    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    @XmlElement (name="FN")
    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    @XmlElement  (name="SEXO")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlElement (name="REP_LEG")
    public ProveedorAprobadoRepDto getRepLeg() {
        return repLeg;
    }

    public void setRepLeg(ProveedorAprobadoRepDto repLeg) {
        this.repLeg = repLeg;
    }

    @XmlElement  (name="FIRMP")
    public String getFirmp() {
        return firmp;
    }

    public void setFirmp(String firmp) {
        this.firmp = firmp;
    }

    @XmlElement  (name="FIRMD")
    public String getFirmd() {
        return firmd;
    }

    public void setFirmd(String firmd) {
        this.firmd = firmd;
    }

    @XmlElement  (name="FIRMTU")
    public String getFirtu() {
        return firtu;
    }

    public void setFirtu(String firtu) {
        this.firtu = firtu;
    }

//    @XmlElement (name = "DOMICILIO_FISCAL")
//    public ProveedorAprobadoDomFisDto getDomicilioFiscal() {
//        return domicilioFiscal;
//    }
//
//    public void setDomicilioFiscal(ProveedorAprobadoDomFisDto domicilioFiscal) {
//        this.domicilioFiscal = domicilioFiscal;
//    }

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
