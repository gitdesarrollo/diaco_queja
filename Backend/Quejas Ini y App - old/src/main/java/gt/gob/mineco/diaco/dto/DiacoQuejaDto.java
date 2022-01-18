/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mps
 */
public class DiacoQuejaDto {

    private Integer idQueja;
    private Integer idConsumidor;
    private Integer idProveedor;
    private Integer idEstadoQueja;
    private Integer idSubActividadEconomica;
    private String facturaNumero;
    private Date fechaFactura;
    private String detalleQueja;
    private String solicitaQue;
    private String procedeQueja;
    private String razonNoProcede;
    private String datosVerificados;
    private Timestamp fechaQueja;
    private String motivo;
    private Integer idDepartamento;
    private Integer idMunicipio;
    private Integer usuarioAsignado;
    private Integer idDiacoSede;
    private Integer anio;
    private Integer noQueja;
    // otros datos
    private String nitProveedor;
    private String nombre;
    private String dpiPasaporte;
    private String telefono;
    private String correo;
    private Integer idFuente;
    

    public Integer getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(Integer idQueja) {
        this.idQueja = idQueja;
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdEstadoQueja() {
        return idEstadoQueja;
    }

    public void setIdEstadoQueja(Integer idEstadoQueja) {
        this.idEstadoQueja = idEstadoQueja;
    }

    public Integer getIdSubActividadEconomica() {
        return idSubActividadEconomica;
    }

    public void setIdSubActividadEconomica(Integer idSubActividadEconomica) {
        this.idSubActividadEconomica = idSubActividadEconomica;
    }

    public String getFacturaNumero() {
        return facturaNumero;
    }

    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getDetalleQueja() {
        return detalleQueja;
    }

    public void setDetalleQueja(String detalleQueja) {
        this.detalleQueja = detalleQueja;
    }

    public String getSolicitaQue() {
        return solicitaQue;
    }

    public void setSolicitaQue(String solicitaQue) {
        this.solicitaQue = solicitaQue;
    }

    public String getProcedeQueja() {
        return procedeQueja;
    }

    public void setProcedeQueja(String procedeQueja) {
        this.procedeQueja = procedeQueja;
    }

    public String getRazonNoProcede() {
        return razonNoProcede;
    }

    public void setRazonNoProcede(String razonNoProcede) {
        this.razonNoProcede = razonNoProcede;
    }

    public String getDatosVerificados() {
        return datosVerificados;
    }

    public void setDatosVerificados(String datosVerificados) {
        this.datosVerificados = datosVerificados;
    }

    public Timestamp getFechaQueja() {
        return fechaQueja;
    }

    public void setFechaQueja(Timestamp fechaQueja) {
        this.fechaQueja = fechaQueja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Integer usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public Integer getIdDiacoSede() {
        return idDiacoSede;
    }

    public void setIdDiacoSede(Integer idDiacoSede) {
        this.idDiacoSede = idDiacoSede;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getNoQueja() {
        return noQueja;
    }

    public void setNoQueja(Integer noQueja) {
        this.noQueja = noQueja;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpiPasaporte() {
        return dpiPasaporte;
    }

    public void setDpiPasaporte(String dpiPasaporte) {
        this.dpiPasaporte = dpiPasaporte;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
