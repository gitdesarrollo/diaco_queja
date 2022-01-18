/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

/**
 *
 * @author mps
 */
public class DiacoQuejaIniDto {

    private Integer idQueja;
    private String nombre;
    private String apellido;
    private String dpiPasaporte;
    private String telefono;
    private String correo;
    private String detalleQueja;
    private String solicitaQue;
    private String nitProveedor;
    private String ubicacion;
    private Integer idConsumidor;
    private Integer idProveedor;
    private Integer anio;
    private Integer secuencia;
    private Integer idCuenta;
    private String idFuente;
    private String conciliacion;
    private String presencial;
    private String facturaNumero;

    public Integer getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(Integer idQueja) {
        this.idQueja = idQueja;
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

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(String idFuente) {
        this.idFuente = idFuente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getConciliacion() {
        return conciliacion;
    }

    public void setConciliacion(String conciliacion) {
        this.conciliacion = conciliacion;
    }

    public String getPresencial() {
        return presencial;
    }

    public void setPresencial(String presencial) {
        this.presencial = presencial;
    }

    /**
     * @return the facturaNumero
     */
    public String getFacturaNumero() {
        return facturaNumero;
    }

    /**
     * @param facturaNumero the facturaNumero to set
     */
    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }
    
    
}
