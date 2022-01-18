package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;

import gt.gob.mineco.diaco.service.TipoImagenesQuejaService;
import gt.gob.mineco.diaco.util.ResponseRs;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;  
import org.glassfish.jersey.media.multipart.FormDataParam; 
import gt.gob.mineco.diaco.util.FormImagenSubmit;
import java.io.InputStream;

@Path("/tipos-imgqueja")
@RequestScoped
public class TipoImagenesQuejaRs {

	@Inject
	private TipoImagenesQuejaService tipoImgQuejaService;
	
        @GET
	@Path("/obligatorios/{idqueja}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getObligatorios(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token){
		return tipoImgQuejaService.getImagenesQobligatorio(idqueja,token);
	}
        
        @GET
	@Path("/plantilla/{id}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public Response getPlantilla(@PathParam("id") Integer id, @PathParam("token") String token){
		return tipoImgQuejaService.getPlantilla(id,token);
	}
        
        @GET
	@Path("/interna/{idqueja}/{idcategoria}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getImagenQuejaxIdCat(@PathParam("idqueja") Integer idqueja, @PathParam("idcategoria") Integer idcategoria, @PathParam("token") String token){
		return tipoImgQuejaService.getImagenesQxIdCat(idqueja,idcategoria,token);
	}
        
        @GET
	@Path("/proveedor/{idproveedor}/{idcategoria}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getImagenProveedorxIdCat(@PathParam("idproveedor") Integer idproveedor, @PathParam("idcategoria") Integer idcategoria, @PathParam("token") String token){
		return tipoImgQuejaService.getImagenesProveedorxIdCat(idproveedor,idcategoria,token);
	}
        
        @GET
	@Path("/proveedorall/{idproveedor}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getImagenProveedorxIdCat(@PathParam("idproveedor") Integer idproveedor, @PathParam("token") String token){
		return tipoImgQuejaService.getImagenesProveedorxProv(idproveedor,token);
	}
        
	@GET
	@Path("/{idqueja}/{idflujo}/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getImagenesQuejas(@PathParam("idqueja") Integer idqueja, @PathParam("idflujo") Integer idflujo, @PathParam("token") String token){
		return tipoImgQuejaService.listImagenesQueja(idqueja,idflujo,token);
	}
	
	@GET
	@Path("/single/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getImagenesQueja(@PathParam("id") Integer id, @PathParam("token") String token) {
               return tipoImgQuejaService.getImagenesQueja(id,token);
        }
        
        @GET
	@Path("/image/{id}/{token}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getImagenQueja(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoImgQuejaService.getImagen(id,token);
	}
        
        @GET
	@Path("/imageinterna/{id}/{token}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getImagenQuejaInterna(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoImgQuejaService.getImagenInterna(id,token);
	}
        
        @GET
	@Path("/imageproveedor/{id}/{token}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getImagenQuejaProveedor(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoImgQuejaService.getImagenProveedor(id,token);
	}
        
        @GET
	@Path("/zip/{idqueja}/{idflujo}/{token}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getZipQueja(@PathParam("idqueja") Integer idqueja, @PathParam("idflujo") Integer idflujo, @PathParam("token") String token) {
		return tipoImgQuejaService.getZip(idqueja, idflujo,token);
	}
        
        @POST
        @Path("/privado")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
         public ResponseRs uploadFile(  
            @FormDataParam("attachment") InputStream uploadedInputStream, @FormDataParam("attachment") FormDataContentDisposition fileDetail,
            @FormDataParam("id_queja") String id_queja, @FormDataParam("id_tipo_imagen") String id_tipo_imagen, 
            @FormDataParam("id_categoria_imagen") String id_categoria_imagen, @FormDataParam("id_flujo") String id_flujo,
            @FormDataParam("token") String token) { 
                return tipoImgQuejaService.saveImagen(uploadedInputStream,fileDetail,Integer.parseInt(id_queja),Integer.parseInt(id_tipo_imagen),Integer.parseInt(id_categoria_imagen),Integer.parseInt(id_flujo),token);                
         }  

         @POST
         @Path("/publico")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
         public ResponseRs uploadFilePub(  
            @FormDataParam("attachment") InputStream uploadedInputStream, @FormDataParam("attachment") FormDataContentDisposition fileDetail,
            @FormDataParam("id_queja") String id_queja, @FormDataParam("id_tipo_imagen") String id_tipo_imagen, 
            @FormDataParam("id_categoria_imagen") String id_categoria_imagen, @FormDataParam("id_flujo") String id_flujo) { 
                return tipoImgQuejaService.saveImagenPub(uploadedInputStream,fileDetail,Integer.parseInt(id_queja),Integer.parseInt(id_tipo_imagen),Integer.parseInt(id_categoria_imagen),Integer.parseInt(id_flujo));                
         }
         
        @POST
        @Path("/interna")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
         public ResponseRs uploadFileInterno(  
            @FormDataParam("attachment") InputStream uploadedInputStream, @FormDataParam("attachment") FormDataContentDisposition fileDetail,
            @FormDataParam("id_queja") String id_queja, @FormDataParam("id_tipo_imagen") String id_tipo_imagen, 
            @FormDataParam("id_categoria_imagen") String id_categoria_imagen, @FormDataParam("id_flujo") String id_flujo,
            @FormDataParam("token") String token) { 
                return tipoImgQuejaService.saveActaInterna(uploadedInputStream,fileDetail,Integer.parseInt(id_queja),Integer.parseInt(id_tipo_imagen),Integer.parseInt(id_categoria_imagen),Integer.parseInt(id_flujo),token);                
         }  
         
         @POST
        @Path("/internalinked")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
         public ResponseRs uploadFileInternoLinked(  
            @FormDataParam("attachment") InputStream uploadedInputStream, @FormDataParam("attachment") FormDataContentDisposition fileDetail,
            @FormDataParam("id_queja") String id_queja, @FormDataParam("id_tipo_imagen") String id_tipo_imagen, 
            @FormDataParam("id_categoria_imagen") String id_categoria_imagen, @FormDataParam("id_flujo") String id_flujo,
            @FormDataParam("token") String token, @FormDataParam("registro") String registro, @FormDataParam("id_resfinal") String id_resfinal) { 
                return tipoImgQuejaService.saveActaInternaLinked(uploadedInputStream,fileDetail,Integer.parseInt(id_queja),Integer.parseInt(id_tipo_imagen),Integer.parseInt(id_categoria_imagen),Integer.parseInt(id_flujo),token,registro,Integer.parseInt(id_resfinal));                
         }  
         
         @POST
        @Path("/proveedor")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
         public ResponseRs uploadFileProveedor(  
            @FormDataParam("attachment") InputStream uploadedInputStream, @FormDataParam("attachment") FormDataContentDisposition fileDetail,
            @FormDataParam("id_proveedor") String id_proveedor, @FormDataParam("id_tipo_imagen") String id_tipo_imagen, 
            @FormDataParam("id_categoria_imagen") String id_categoria_imagen, @FormDataParam("token") String token) { 
                return tipoImgQuejaService.saveImagenProveedor(uploadedInputStream,fileDetail,Integer.parseInt(id_proveedor),Integer.parseInt(id_tipo_imagen),Integer.parseInt(id_categoria_imagen),token);                
         }  
         
	@DELETE
	@Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteImagenesQueja(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoImgQuejaService.deleteImagen(id, token);
	}
        
}
