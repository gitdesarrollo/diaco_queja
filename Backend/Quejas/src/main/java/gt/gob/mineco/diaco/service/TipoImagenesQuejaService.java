package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.util.FormImagenSubmit;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.io.InputStream;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public interface TipoImagenesQuejaService {
	
	public ResponseRs listImagenesQueja(Integer idqueja,Integer idflujo, String token);
	public ResponseRs getImagenesQueja(Integer id, String token);
	public Response getImagen(Integer id, String token);
        public Response getImagenInterna(Integer id, String token);
        public Response getImagenProveedor(Integer id, String token);
        public Response getZip(Integer idqueja, Integer idflujo, String token);
        public ResponseRs saveImagen(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen,Integer id_categoria_imagen, Integer id_flujo, String token);
        public ResponseRs saveImagenPub(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen,Integer id_categoria_imagen, Integer id_flujo);
        public ResponseRs deleteImagen(Integer id, String token);
        public ResponseRs saveActaInterna(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen,Integer id_categoria_imagen, Integer id_flujo, String token);
        public ResponseRs saveActaInternaLinked(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen,Integer id_categoria_imagen, Integer id_flujo, String token, String registro, Integer id_resfinal); 
        public ResponseRs saveImagenProveedor(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen,Integer id_categoria_imagen, String token);
        public ResponseRs getImagenesQxIdCat(Integer idqueja,Integer idcategoria, String token);
        public ResponseRs getImagenesProveedorxIdCat(Integer idproveedor,Integer idcategoria, String token);
        public ResponseRs getImagenesProveedorxProv(Integer idproveedor, String token);
        public ResponseRs getImagenesQobligatorio(Integer id_queja, String token);
        public Response getPlantilla(Integer id, String token);
        /*public ResponseRs updateImagenesQueja(Integer id);
	public ResponseRs deleteImagenesQueja(Integer id);*/
}
