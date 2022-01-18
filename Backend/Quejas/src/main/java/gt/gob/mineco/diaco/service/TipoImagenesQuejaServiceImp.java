package gt.gob.mineco.diaco.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.TipoRepository;
import gt.gob.mineco.diaco.model.TipoBitacora;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoFlujoGuia;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.model.TipoImagenesInterno;
import gt.gob.mineco.diaco.model.TipoImagenesProveedor;
import gt.gob.mineco.diaco.model.TipoTipoImagen;
import gt.gob.mineco.diaco.model.TipoRepositorio;
import gt.gob.mineco.diaco.model.TipoPlantilla;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoResFinal;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.util.Constants;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Date;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import java.text.SimpleDateFormat;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TipoImagenesQuejaServiceImp implements TipoImagenesQuejaService {

	@Inject
	private TipoRepository tipoDao;
	/*@Inject
        private TipoAreaComunService tipoareacomun;*/
        
	@Override
	public ResponseRs listImagenesQueja(Integer idqueja,Integer idflujo, String token) {
		ResponseRs response = new ResponseRs();
		
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
                        response.setValue(tipoDao.findAllImagenesQueja(idqueja,idflujo));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
	}

	@Override
	public ResponseRs getImagenesQueja(Integer id, String token) {
		ResponseRs response = new ResponseRs();
		
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
			response.setValue(tipoDao.findByIdImagenesQueja(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
	}
	
	@Override
	public Response getImagen(Integer id, String token) {		
		try {
                    tipoDao.TokenCheck(token);
                    TipoImagenesQueja vtipoImagenesQueja = tipoDao.findByIdImagenesQueja(id);
                    if(vtipoImagenesQueja != null){
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenesQueja.getId_repositorio());
                        String idqueja = Integer.toString(vtipoImagenesQueja.getId_queja()).trim();
                        String file=vtipoImagenesQueja.getImagen();
                        String path = tipoRepositorio.getRepositorio()+idqueja+File.separator;
                        File fileDownload = new File(path + File.separator + file);
                        if( fileDownload.exists() ){
                            ResponseBuilder response = Response.ok((Object) fileDownload);
                            response.header("Content-Disposition", "attachment;filename=" + file);
                            return response.build();
                        }else{
                            ResponseBuilder response = Response.noContent();
                            return response.build();                          
                        }
                    }
                    ResponseBuilder response = Response.serverError();
                    return response.build();
		} catch (Exception e) {
			e.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
		}		
	}

        @Override
	public Response getImagenInterna(Integer id, String token) {		
		try {
                    tipoDao.TokenCheck(token);
                    TipoImagenesInterno vtipoImagenes = tipoDao.findByIdImagenesInterno(id);
                    if(vtipoImagenes != null){
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenes.getId_repositorio());
                        String idqueja = Integer.toString(vtipoImagenes.getId_queja()).trim();
                        String file=vtipoImagenes.getImagen();
                        String path = tipoRepositorio.getRepositorio()+idqueja+File.separator;
                        File fileDownload = new File(path + File.separator + file);
                        if( fileDownload.exists() ){
                            ResponseBuilder response = Response.ok((Object) fileDownload);
                            response.header("Content-Disposition", "attachment;filename=" + file);
                            return response.build();
                        }else{
                            ResponseBuilder response = Response.noContent();
                            return response.build();                          
                        }
                    }
                    ResponseBuilder response = Response.serverError();
                    return response.build();
		} catch (Exception e) {
			e.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
		}		
	}
        
        @Override
	public Response getImagenProveedor(Integer id, String token) {		
		try {
                    tipoDao.TokenCheck(token);
                    TipoImagenesProveedor vtipoImagenes = tipoDao.findByIdImagenesProveedor(id);
                    if(vtipoImagenes != null){
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenes.getId_repositorio());
                        String idproveedor = Integer.toString(vtipoImagenes.getId_proveedor_aprovar()).trim();
                        String file=vtipoImagenes.getImagen();
                        String path = tipoRepositorio.getRepositorio()+idproveedor+File.separator;
                        File fileDownload = new File(path + File.separator + file);
                        if( fileDownload.exists() ){
                            ResponseBuilder response = Response.ok((Object) fileDownload);
                            response.header("Content-Disposition", "attachment;filename=" + file);
                            return response.build();
                        }else{
                            ResponseBuilder response = Response.noContent();
                            return response.build();                          
                        }
                    }
                    ResponseBuilder response = Response.serverError();
                    return response.build();
		} catch (Exception e) {
			e.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
		}		
	}
        
    /**
     * Adds an extra file to the zip archive, copying in the created
     * date and a comment.
     * @param file file to be archived
     * @param zipStream archive to contain the file.
     */
    private void addToZipFile(Path file, ZipOutputStream zipStream, String name) throws IOException {
        System.out.println(file);
        String inputFileName = file.toFile().getPath();
        FileInputStream inputStream = new FileInputStream(inputFileName);

        //ZipEntry entry = new ZipEntry(file.toFile().getName());
        ZipEntry entry = new ZipEntry(name);
        entry.setCreationTime(FileTime.fromMillis(file.toFile().lastModified()));
        //entry.setComment("Created by TheCodersCorner");
        zipStream.putNextEntry(entry);
        byte[] readBuffer = new byte[2048];
        int amountRead;
        while ((amountRead = inputStream.read(readBuffer)) > 0) {
            zipStream.write(readBuffer, 0, amountRead);
        }
        zipStream.closeEntry();
        
    }
        
	@Override
	public Response getZip(Integer idqueja, Integer idflujo, String token) {		
            String fname=null;
            try {
                    tipoDao.TokenCheck(token);
                    String stridqueja = Integer.toString(idqueja).trim();
                    Integer pos=1;
                    List<TipoImagenesQueja> ImgList=tipoDao.findZipImagenesQueja(idqueja,idflujo);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    String directory="";
                    String file="";
                    //System.out.println("before list");            
                    if(!ImgList.isEmpty()){
			ZipOutputStream zos = new ZipOutputStream(baos);
                        for (TipoImagenesQueja ThisItem: ImgList) {
                            
                            TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(ThisItem.getId_repositorio());
                            directory = tipoRepositorio.getRepositorio()+stridqueja+File.separator;
                            file= ThisItem.getImagen();
                            Path path = Paths.get(directory + File.separator + file);
                            fname=Integer.toString(pos) + "."+ThisItem.getDescripcion_imagen_otros();
                            //System.out.println("**: "+fname);
                            addToZipFile(path, zos, fname);
                            pos++;
                        }
                        zos.close();
                        //System.out.println(baos.size());
                        ResponseBuilder response = Response.ok(baos.toByteArray());
                        response.header("Content-Disposition", "attachment;filename=" + stridqueja+".zip");
                        return response.build();
                    }else{
                        ResponseBuilder response = Response.noContent();
                        return response.build();
                    }
                } catch (IOException ex) {
			ex.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
                }catch(Exception e) {
                        e.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
		} 				
	}
        
        @Override
        public ResponseRs saveImagen(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen, Integer id_categoria_imagen, Integer id_flujo, String token){
            ResponseRs response = new ResponseRs();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String dateString = format.format( System.currentTimeMillis() );

                UserTransaction transaction=null;
		try {
                        tipoDao.TokenCheck(token);
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction.begin();
                        
                        TipoRepositorio tipoRepositorio = tipoDao.findActiveRepo("queja");    
                        String filename=fileDetail.getFileName();
                        String fileext=FileExtension(filename);
                        String newfilename = dateString+fileext;
                        String folderLocation = tipoRepositorio.getRepositorio()+ id_queja.toString().trim();
                        
                        TipoImagenesQueja newImagenesQueja;
                        
                        if(id_categoria_imagen != 1){
                            newImagenesQueja = tipoDao.findbyCatImagen(id_queja, id_categoria_imagen);
                            if(newImagenesQueja != null)
                                deleteImagen_NoT(newImagenesQueja.getId_imagen_queja());    
                        }
                        newImagenesQueja = new TipoImagenesQueja();
                        
                        switch(id_categoria_imagen){
                            case 2:                          
                                newfilename="Archivo_DPI_"+id_queja.toString()+fileext;
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(newfilename);
                                break;
                            case 3:
                                newfilename="Archivo_Factura_"+id_queja.toString()+fileext;
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(newfilename);
                                break;
                            default:
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(filename);
                        }
                        newImagenesQueja.setId_queja(id_queja);
                        newImagenesQueja.setId_tipo_imagen(id_tipo_imagen);
                        newImagenesQueja.setHabilitado("A");
                        
                        String fileLocation = folderLocation +File.separator+newfilename;  
                        
                        newImagenesQueja.setUsuario_adiciono(1);
                        newImagenesQueja.setFecha_adicion(new Date());
                        newImagenesQueja.setId_categoria_imagen(id_categoria_imagen);
                        newImagenesQueja.setId_flujo(id_flujo);
                        newImagenesQueja.setId_repositorio(tipoRepositorio.getId());
                        TipoTipoImagen vtipoTipoImagen = tipoDao.findByIdTipoImagen(id_tipo_imagen);
                        newImagenesQueja.setTipotipoimagen(vtipoTipoImagen);
                        tipoDao.saveImagenesQueja(newImagenesQueja);
                        response.setCode(0L);
                        response.setReason("OK");
                        response.setValue(newImagenesQueja);
                        
                        try{
                            System.out.println(uploadedInputStream);
                            System.out.println(fileLocation);
                            System.out.println(folderLocation);
                            writeFile(uploadedInputStream,fileLocation,folderLocation);            
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(1L);
                            response.setReason("ERROR");
                            try{
                                transaction.rollback();
                            }catch(Exception ee){
                                ee.printStackTrace();
                            }
                            return response;
                        }
                        
                        //bitacora auto log
                        String txtmensaje="Imagen adjunta a la queja "+newfilename;
                        BitacoraAutomatica(id_queja, id_flujo,1,txtmensaje,txtmensaje);
                        
                        transaction.commit();    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }  
                return response;  
        }
        
        @Override
        public ResponseRs saveImagenPub(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen, Integer id_categoria_imagen, Integer id_flujo){
            ResponseRs response = new ResponseRs();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String dateString = format.format( System.currentTimeMillis() );

                UserTransaction transaction=null;
		try {
                        //tipoDao.TokenCheck(token);
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction.begin();
                        
                        TipoRepositorio tipoRepositorio = tipoDao.findActiveRepo("queja");    
                        String filename=fileDetail.getFileName();
                        String fileext=FileExtension(filename);
                        String newfilename = dateString+fileext;
                        String folderLocation = tipoRepositorio.getRepositorio()+ id_queja.toString().trim();
                        
                        TipoImagenesQueja newImagenesQueja;
                        
                        if(id_categoria_imagen != 1){
                            newImagenesQueja = tipoDao.findbyCatImagen(id_queja, id_categoria_imagen);
                            if(newImagenesQueja != null)
                                deleteImagen_NoT(newImagenesQueja.getId_imagen_queja());    
                        }
                        newImagenesQueja = new TipoImagenesQueja();
                        
                        switch(id_categoria_imagen){
                            case 2:                          
                                newfilename="Archivo_DPI_"+id_queja.toString()+fileext;
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(newfilename);
                                break;
                            case 3:
                                newfilename="Archivo_Factura_"+id_queja.toString()+fileext;
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(newfilename);
                                break;
                            default:
                                newImagenesQueja.setImagen(newfilename);
                                newImagenesQueja.setDescripcion_imagen_otros(filename);
                        }
                        newImagenesQueja.setId_queja(id_queja);
                        newImagenesQueja.setId_tipo_imagen(id_tipo_imagen);
                        newImagenesQueja.setHabilitado("A");
                        
                        String fileLocation = folderLocation +File.separator+newfilename;  
                        
                        newImagenesQueja.setUsuario_adiciono(1);
                        newImagenesQueja.setFecha_adicion(new Date());
                        newImagenesQueja.setId_categoria_imagen(id_categoria_imagen);
                        newImagenesQueja.setId_flujo(id_flujo);
                        newImagenesQueja.setId_repositorio(tipoRepositorio.getId());
                        TipoTipoImagen vtipoTipoImagen = tipoDao.findByIdTipoImagen(id_tipo_imagen);
                        newImagenesQueja.setTipotipoimagen(vtipoTipoImagen);
                        tipoDao.saveImagenesQueja(newImagenesQueja);
                        response.setCode(0L);
                        response.setReason("OK");
                        response.setValue(newImagenesQueja);
                        
                        try{
                            System.out.println(uploadedInputStream);
                            System.out.println(fileLocation);
                            System.out.println(folderLocation);
                            writeFile(uploadedInputStream,fileLocation,folderLocation);            
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(1L);
                            response.setReason("ERROR");
                            try{
                                transaction.rollback();
                            }catch(Exception ee){
                                ee.printStackTrace();
                            }
                            return response;
                        }
                        
                        transaction.commit();    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }  
                return response;  
        }
        
        private String FileExtension(String filename){
            if(filename.length() > 0){
                int pos = filename.lastIndexOf(".");
                if (pos > 0) 
                    return filename.substring(pos);
            }
            return null;
        }
        
        @Override
        public ResponseRs deleteImagen(Integer id, String token){
            String filename;
            String idqueja;
            String fileLocation;  
            boolean result=true;
            ResponseRs response = new ResponseRs();            
                UserTransaction transaction=null;
		try {
                    tipoDao.TokenCheck(token);
                    transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                    transaction.begin();                    
                    TipoImagenesQueja vtipoImagenesQueja = tipoDao.findByIdImagenesQueja(id);
                    if (vtipoImagenesQueja != null){
                        idqueja = vtipoImagenesQueja.getId_queja().toString().trim();
                        filename = vtipoImagenesQueja.getImagen();
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenesQueja.getId_repositorio());
                        fileLocation = tipoRepositorio.getRepositorio() +filename;
                        tipoDao.deleteImagenesQueja(vtipoImagenesQueja);   
                        try{
                            result=deleteFile(fileLocation);                                  
                        }catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(0L);
                            response.setReason("OK");
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                        }
                        
                        response.setCode(0L);
                        response.setReason("OK");
                        if(result)
                            response.setValue("Referencia en base de datos y archivo eliminado.");
                        else
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                    }else{
                        response.setCode(1L);
                        response.setReason("ERROR");
                        response.setValue("Referencia en base de datos no encontrada");
                    }
                    transaction.commit();
                    return response;
                        
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    return response;
                }  
                  
        }

        private ResponseRs deleteImagen_NoT(Integer id) throws Exception{
            String filename;
            String idqueja;
            String fileLocation;  
            boolean result=true;
            ResponseRs response = new ResponseRs();            
                 TipoImagenesQueja vtipoImagenesQueja = tipoDao.findByIdImagenesQueja(id);
                    if (vtipoImagenesQueja != null){
                        idqueja = vtipoImagenesQueja.getId_queja().toString().trim();
                        filename = vtipoImagenesQueja.getImagen();
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenesQueja.getId_repositorio());
                        fileLocation = tipoRepositorio.getRepositorio() + filename;
                        tipoDao.deleteImagenesQueja(vtipoImagenesQueja);   
                        try{
                            result=deleteFile(fileLocation);                                  
                        }catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(0L);
                            response.setReason("OK");
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                        }
                        response.setCode(0L);
                        response.setReason("OK");
                        if(result)
                            response.setValue("Referencia en base de datos y archivo eliminado.");
                        else
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                    }else{
                        response.setCode(1L);
                        response.setReason("ERROR");
                        response.setValue("Referencia en base de datos no encontrada");
                    }
                    return response;
        }
        
        private ResponseRs deleteImagenInterna_NoT(Integer id) throws Exception{
            String filename;
            String idqueja;
            String fileLocation;  
            boolean result=true;
            ResponseRs response = new ResponseRs();            
                 TipoImagenesInterno vtipoImagenes = tipoDao.findByIdImagenesInterno(id);
                    if (vtipoImagenes != null){
                        idqueja = vtipoImagenes.getId_queja().toString().trim();
                        filename = vtipoImagenes.getImagen();
                        TipoRepositorio tipoRepositorio = tipoDao.findByIdRepositorio(vtipoImagenes.getId_repositorio());
                        fileLocation = tipoRepositorio.getRepositorio() + filename;
                        tipoDao.deleteImagenesInterno(vtipoImagenes);   
                        try{
                            result=deleteFile(fileLocation);                                  
                        }catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(0L);
                            response.setReason("OK");
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                        }
                        response.setCode(0L);
                        response.setReason("OK");
                        if(result)
                            response.setValue("Referencia en base de datos y archivo eliminado.");
                        else
                            response.setValue("Referencia eliminada en base de datos, archivo no encontrado");
                    }else{
                        response.setCode(1L);
                        response.setReason("ERROR");
                        response.setValue("Referencia en base de datos no encontrada");
                    }
                    return response;
        }
        
        private void writeFile(InputStream uploadedInputStream, String fileLocation, String folderLocation) throws IOException {
                //revisar si existe el folder, si no crearlo
                File customDir = new File(folderLocation);
                if (!customDir.exists()) {
                        customDir.mkdir();
                }

                FileOutputStream out;// = new FileOutputStream(new File(fileLocation));  
                int read = 0;  
                byte[] bytes = new byte[1024];  
                out = new FileOutputStream(new File(fileLocation));  
                while ((read = uploadedInputStream.read(bytes)) != -1) {  
                    out.write(bytes, 0, read);  
                }  
                out.flush();  
                out.close();  
                
            /*File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
            }*/
            
        }

        private boolean deleteFile(String fileLocation) throws IOException {

            File file = new File(fileLocation); 
            if(file.delete()){ 
                return true;
            }else{ 
                return false;
            }             
        }

        @Override
        public ResponseRs saveActaInterna(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen, Integer id_categoria_imagen, Integer id_flujo, String token){
            ResponseRs response = new ResponseRs();
            if(id_categoria_imagen < 4 || id_categoria_imagen > 13){
                response.setCode(1L);
                response.setReason("ERROR_CAT");
                return response;
            }
            
                UserTransaction transaction=null;
		try {
                        tipoDao.TokenCheck(token);
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction.begin();
                        
                        TipoRepositorio tipoRepositorio = tipoDao.findActiveRepo("interno");    
                        String filename=fileDetail.getFileName();
                        String fileext=FileExtension(filename);
                        String folderLocation = tipoRepositorio.getRepositorio()+ id_queja.toString().trim();
                        TipoImagenesInterno newImagenes;
                        boolean InformeFinal=false;
                        newImagenes = tipoDao.findbyCatInterna(id_queja, id_categoria_imagen);
                        if(newImagenes != null)
                            deleteImagenInterna_NoT(newImagenes.getId_imagen_interno());    
                        newImagenes = new TipoImagenesInterno();
                        
                        switch(id_categoria_imagen){
                            case 4:             
                                filename="Acta_Conciliacion_"+id_queja.toString()+fileext;
                                break;
                            case 5:
                                filename="Acta_Incomparecencia_"+id_queja.toString()+fileext;
                                break;
                            case 6:             
                                filename="Acta_Conciliacion_Scan_"+id_queja.toString()+fileext;
                                break;
                            case 7:
                                filename="Acta_Incomparecencia_Scan_"+id_queja.toString()+fileext;
                                break;
                            case 8:
                                filename="Acta_Verificacion_"+id_queja.toString()+fileext;
                                break;    
                            case 9:
                                filename="Acta_Verificacion_Scan_"+id_queja.toString()+fileext;
                                break;    
                            case 10:
                                filename="Informe_Final_"+id_queja.toString()+fileext;
                                InformeFinal=true;
                                break;    
                            case 11:
                                filename="Informe_Final_Scan_"+id_queja.toString()+fileext;
                                InformeFinal=true;
                                break;   
                            case 12:             
                                filename="Acta_Audiencia_Jur_"+id_queja.toString()+fileext;
                                break;
                            case 13:
                                filename="Acta_Audiencia_Jur_Scan_"+id_queja.toString()+fileext;
                                break;
                        }
                        newImagenes.setImagen(filename);
                        newImagenes.setDescripcion_imagen_otros(filename);
                        newImagenes.setId_queja(id_queja);
                        newImagenes.setId_tipo_imagen(id_tipo_imagen);
                        newImagenes.setHabilitado("A");
                        
                        String fileLocation = folderLocation +File.separator+filename;  
                        
                        newImagenes.setUsuario_adiciono(1);
                        newImagenes.setFecha_adicion(new Date());
                        newImagenes.setId_categoria_imagen(id_categoria_imagen);
                        newImagenes.setId_flujo(id_flujo);
                        newImagenes.setId_repositorio(tipoRepositorio.getId());
                        TipoTipoImagen vtipoTipoImagen = tipoDao.findByIdTipoImagen(id_tipo_imagen);
                        newImagenes.setTipotipoimagen(vtipoTipoImagen);
                        tipoDao.saveImagenesInterno(newImagenes);
                        
                        //para informe final actualizar flujo guia
                        if(InformeFinal){
                            saveTiposFlujoGuia(id_queja, 7);
                        }
                        //bitacora auto log
                        String txtmensaje="Acta ingresada en el sistema: "+filename;
                        BitacoraAutomatica(id_queja, id_flujo,1,txtmensaje,txtmensaje);
                        
                        response.setCode(0L);
                        response.setReason("OK");
                        response.setValue(newImagenes);
                        
                        try{
                            System.out.println(uploadedInputStream);
                            System.out.println(fileLocation);
                            System.out.println(folderLocation);
                            writeFile(uploadedInputStream,fileLocation,folderLocation);            
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(1L);
                            response.setReason("ERROR");
                            try{
                                transaction.rollback();
                            }catch(Exception ee){
                                ee.printStackTrace();
                            }
                            return response;
                        }
                        //bitacora auto log
                        //String txtmensaje="Se guardó "+filename;
                        //tipoareacomun.BitacoraAutomatica(id_queja,id_flujo,0,txtmensaje,txtmensaje);
                        transaction.commit();    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }  
                return response;  
        }
        
        @Override
        public ResponseRs saveActaInternaLinked(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_queja, Integer id_tipo_imagen, Integer id_categoria_imagen, Integer id_flujo, String token, String registro, Integer id_resfinal){
            ResponseRs response = new ResponseRs();
            String filename=null;
            if(id_categoria_imagen != 20){
                response.setCode(1L);
                response.setReason("ERROR_CAT");
                return response;
            }
            
                UserTransaction transaction=null;
		try {
                        tipoDao.TokenCheck(token);
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction.begin();
                        
                        TipoRepositorio tipoRepositorio = tipoDao.findActiveRepo("interno");    
                        filename=fileDetail.getFileName();
                        String fileext=FileExtension(filename);
                        String folderLocation = tipoRepositorio.getRepositorio()+ id_queja.toString().trim();
                        TipoImagenesInterno newImagenes;
                        boolean InformeFinal=false;
                        filename="Resolucion_Final_"+registro+"_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                        newImagenes = tipoDao.findbyQuejaImagenInterna(id_queja, filename);
                        if(newImagenes != null)
                            deleteImagenInterna_NoT(newImagenes.getId_imagen_interno());    
                        newImagenes = new TipoImagenesInterno();
                        
                        /*switch(id_registro){
                            case 18:             
                                filename="Resolucion_Final_Archivo_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                                break;
                            case 19:
                                filename="Resolucion_Final_SinSancion_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                                break;
                            case 20:             
                                filename="Resolucion_Final_ConSancion_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                                break;
                            case 21:
                                filename="Resolucion_Final_DenunciaMP_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                                break;
                            case 22:
                                filename="Resolucion_Final_DeclaratoriaRebeldia_"+id_queja.toString()+"_"+id_resfinal.toString()+fileext;
                                break;    
                        }*/
                        
                        newImagenes.setImagen(filename);
                        newImagenes.setDescripcion_imagen_otros(filename);
                        newImagenes.setId_queja(id_queja);
                        newImagenes.setId_tipo_imagen(id_tipo_imagen);
                        newImagenes.setHabilitado("A");
                        
                        String fileLocation = folderLocation +File.separator+filename;  
                        
                        newImagenes.setUsuario_adiciono(1);
                        newImagenes.setFecha_adicion(new Date());
                        newImagenes.setId_categoria_imagen(id_categoria_imagen);
                        newImagenes.setId_flujo(id_flujo);
                        newImagenes.setId_repositorio(tipoRepositorio.getId());
                        TipoTipoImagen vtipoTipoImagen = tipoDao.findByIdTipoImagen(id_tipo_imagen);
                        newImagenes.setTipotipoimagen(vtipoTipoImagen);
                        tipoDao.saveImagenesInterno(newImagenes);
                        
                        //bitacora auto log
                        String txtmensaje="Acta ingresada en el sistema: "+filename;
                        BitacoraAutomatica(id_queja, id_flujo,1,txtmensaje,txtmensaje);
                        
                        
                        response.setCode(0L);
                        response.setReason("OK");
                        response.setValue(newImagenes);
                        
                        try{
                            System.out.println(uploadedInputStream);
                            System.out.println(fileLocation);
                            System.out.println(folderLocation);
                            writeFile(uploadedInputStream,fileLocation,folderLocation);            
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(1L);
                            response.setReason("ERROR");
                            try{
                                transaction.rollback();
                            }catch(Exception ee){
                                ee.printStackTrace();
                            }
                            return response;
                        }
                        //bitacora auto log
                        //String txtmensaje="Se guardó "+filename;
                        //tipoareacomun.BitacoraAutomatica(id_queja,id_flujo,0,txtmensaje,txtmensaje);
                        transaction.commit();    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }
                
                UserTransaction transaction2=null;
		try {
                        transaction2 = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction2.begin();
                        TipoImagenesInterno vimgint = tipoDao.findbyQuejaImagenInterna(id_queja, filename); 
                        //actualizar id imagen en resolucion final
                        //System.out.println("-+"+newImagenes.getId_imagen_interno()+"**");
                        TipoResFinal vresfinal=tipoDao.findByIdResFinal(id_resfinal);
                        vresfinal.setId_imagen_acta(vimgint.getId_imagen_interno());
                        tipoDao.saveResFinal(vresfinal);
                        response.setValue(vresfinal);
                        transaction2.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction2.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }
                
                return response;  
        }
        
        @Override
        public ResponseRs saveImagenProveedor(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer id_proveedor, Integer id_tipo_imagen, Integer id_categoria_imagen, String token){
            ResponseRs response = new ResponseRs();
            if(id_categoria_imagen < 14 || id_categoria_imagen > 17){
                response.setCode(1L);
                response.setReason("ERROR_CAT");
                return response;
            }
            
                UserTransaction transaction=null;
		try {
                        tipoDao.TokenCheck(token);
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                        transaction.begin();
                        
                        TipoRepositorio tipoRepositorio = tipoDao.findActiveRepo("proveedor");    
                        String filename=fileDetail.getFileName();
                        String fileext=FileExtension(filename);
                        String folderLocation = tipoRepositorio.getRepositorio()+ id_proveedor.toString().trim();
                        TipoImagenesProveedor newImagenes;
                        boolean InformeFinal=false;
                        newImagenes = tipoDao.findbyCatProveedor(id_proveedor, id_categoria_imagen);
                        if(newImagenes != null)
                            deleteImagenInterna_NoT(newImagenes.getId_imagen_proveedor());    
                        newImagenes = new TipoImagenesProveedor();
                        
                        switch(id_categoria_imagen){
                            case 14:             
                                filename="Form_Registro_"+id_proveedor.toString()+fileext;
                                break;
                            case 15:
                                filename="Nombramiento_Rep_Legal_"+id_proveedor.toString()+fileext;
                                break;
                            case 16:             
                                filename="DPI_"+id_proveedor.toString()+fileext;
                                break;
                            case 17:
                                filename="Patente_"+id_proveedor.toString()+fileext;
                                break;
                        }
                        newImagenes.setImagen(filename);
                        newImagenes.setDescripcion_imagen_otros(filename);
                        newImagenes.setId_proveedor_aprovar(id_proveedor);
                        newImagenes.setId_tipo_imagen(id_tipo_imagen);
                        newImagenes.setHabilitado("A");
                        
                        String fileLocation = folderLocation +File.separator+filename;  
                        
                        newImagenes.setUsuario_adiciono(1);
                        newImagenes.setFecha_adicion(new Date());
                        newImagenes.setId_categoria_imagen(id_categoria_imagen);
                        newImagenes.setId_repositorio(tipoRepositorio.getId());
                        TipoTipoImagen vtipoTipoImagen = tipoDao.findByIdTipoImagen(id_tipo_imagen);
                        newImagenes.setTipotipoimagen(vtipoTipoImagen);
                        tipoDao.saveImagenesProveedor(newImagenes);
                                                
                        response.setCode(0L);
                        response.setReason("OK");
                        response.setValue(newImagenes);
                        
                        try{
                            System.out.println(uploadedInputStream);
                            System.out.println(fileLocation);
                            System.out.println(folderLocation);
                            writeFile(uploadedInputStream,fileLocation,folderLocation);            
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                            response.setCode(1L);
                            response.setReason("ERROR");
                            try{
                                transaction.rollback();
                            }catch(Exception ee){
                                ee.printStackTrace();
                            }
                            return response;
                        }
                        //bitacora auto log
                        //String txtmensaje="Se guardó "+filename;
                        //tipoareacomun.BitacoraAutomatica(id_queja,id_flujo,0,txtmensaje,txtmensaje);
                        transaction.commit();    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setCode(1L);
                    response.setReason("ERROR");
                    try{
                        transaction.rollback();
                    }catch(Exception ee){
                        ee.printStackTrace();
                    }
                    
                }  
                return response;  
        }
        
        @Override
        public Response getPlantilla(Integer id, String token){
		try {
                    tipoDao.TokenCheck(token);
                    TipoPlantilla vTipoPlantilla = tipoDao.findTipoPlantillaxID(id);
                    if(vTipoPlantilla != null){
                        String file= vTipoPlantilla.getFilename();
                        String path = vTipoPlantilla.getFolder();
                        File fileDownload = new File(path + File.separator + file);
                        if( fileDownload.exists() ){
                            ResponseBuilder response = Response.ok((Object) fileDownload);
                            response.header("Content-Disposition", "attachment;filename=" + file);
                            return response.build();
                        }else{
                            ResponseBuilder response = Response.noContent();
                            return response.build();                          
                        }
                    }
                    ResponseBuilder response = Response.serverError();
                    return response.build();
		} catch (Exception e) {
			e.printStackTrace();
                        ResponseBuilder response = Response.serverError();
                        return response.build();
		}		            
        }    
        
        @Override
        public ResponseRs getImagenesQxIdCat(Integer idqueja,Integer idcategoria, String token){
                ResponseRs response = new ResponseRs();
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
			response.setValue(tipoDao.findbyCatInterna(idqueja,idcategoria));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
        }
        @Override
        public ResponseRs getImagenesProveedorxIdCat(Integer idproveedor,Integer idcategoria, String token){
                ResponseRs response = new ResponseRs();
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
			response.setValue(tipoDao.findbyCatProveedor(idproveedor,idcategoria));
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
        }
        @Override
        public ResponseRs getImagenesProveedorxProv(Integer idproveedor, String token){
                ResponseRs response = new ResponseRs();
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
                        Integer form1;
                        Integer form2;
                        Integer form3;
                        Integer form4;
                        Integer tipo1;
                        Integer tipo2;
                        Integer tipo3;
                        Integer tipo4;
                        TipoImagenesProveedor vimagen= tipoDao.findbyCatProveedor(idproveedor,Constants.REG_DIACO_CAT_IMAGEN_FORMULARIO_REGISTRO);
                        if(vimagen != null){
                            form1=vimagen.getId_imagen_proveedor();
                            tipo1=vimagen.getId_tipo_imagen();
                        }else{
                            form1=0;
                            tipo1=0;
                        }
                        vimagen= tipoDao.findbyCatProveedor(idproveedor,Constants.REG_DIACO_CAT_IMAGEN_NOMBRAMIENTO_REP_LEGAL);
                        if(vimagen != null){
                            form2=vimagen.getId_imagen_proveedor();
                            tipo2=vimagen.getId_tipo_imagen();
                        }else{
                            form2=0;
                            tipo2=0;
                        }
                        vimagen= tipoDao.findbyCatProveedor(idproveedor,Constants.REG_DIACO_CAT_IMAGEN_DPI_DUENO_REP_LEGAL);
                        if(vimagen != null){
                            form3=vimagen.getId_imagen_proveedor();
                            tipo3=vimagen.getId_tipo_imagen();
                        }else{
                            form3=0;
                            tipo3=0;
                        }
                        vimagen= tipoDao.findbyCatProveedor(idproveedor,Constants.REG_DIACO_CAT_IMAGEN_PATENTE_SOCIEDAD_EMPRESA);
                        if(vimagen != null){
                            form4=vimagen.getId_imagen_proveedor();
                            tipo4=vimagen.getId_tipo_imagen();
                        }else{
                            form4=0;
                            tipo4=0;
                        }
                        
                        JsonObject queja = new JsonObject();
                        queja.addProperty("id_proveedor", idproveedor);
                        queja.addProperty("formulario_registro", form1);
                        queja.addProperty("nombramiento_rep_legal", form2);
                        queja.addProperty("dpi_dueno_rep_legal", form3);
                        queja.addProperty("patente_sociedad_empresa", form4);
                        queja.addProperty("formulario_registro_tipo", tipo1);
                        queja.addProperty("nombramiento_rep_legal_tipo", tipo2);
                        queja.addProperty("dpi_dueno_rep_legal_tipo", tipo3);
                        queja.addProperty("patente_sociedad_empresa_tipo", tipo4);
                        response.setValue(queja);
                        return response;
                        
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
        }
        @Override
        public ResponseRs getImagenesQobligatorio(Integer id_queja, String token){
                ResponseRs response = new ResponseRs();
		try {
                        tipoDao.TokenCheck(token);
			response.setCode(0L);
			response.setReason("OK");
                        Integer form1;
                        Integer form2;
                        Integer tipo1;
                        Integer tipo2;
                        TipoImagenesQueja vqueja= tipoDao.findbyCatImagen(id_queja, Constants.REG_DIACO_CAT_IMAGEN_DPI);
                        if(vqueja != null){
                            form1=vqueja.getId_imagen_queja();
                            tipo1=vqueja.getId_tipo_imagen();
                        }else{
                            form1=0;
                            tipo1=0;
                        }
                        vqueja= tipoDao.findbyCatImagen(id_queja, Constants.REG_DIACO_CAT_IMAGEN_FACTURA);
                        if(vqueja != null){
                            form2=vqueja.getId_imagen_queja();
                            tipo2=vqueja.getId_tipo_imagen();
                        }else{
                            form2=0;
                            tipo2=0;
                        }
                        
                        JsonObject queja = new JsonObject();
                        queja.addProperty("id_queja", id_queja);
                        queja.addProperty("dpi", form1);
                        queja.addProperty("factura", form2);
                        queja.addProperty("dpi_tipo", tipo1);
                        queja.addProperty("factura_tipo", tipo2);
                        response.setValue(queja);
                        return response;
                        
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(1L);
			response.setReason("ERROR");
		}		
		return response;
        }
        
        //funciones copiadas de Area Comun
        public void BitacoraAutomatica(Integer idqueja, Integer idflujo, Integer usuario, String bitacora, String consumidor) throws Exception{
                Integer local_id_tipo_registro=0;
                switch(idflujo){
                    case 1:
                        local_id_tipo_registro=12;
                        break;
                    case 2:
                        local_id_tipo_registro=26;
                        break;    
                    case 3:
                        local_id_tipo_registro=23;
                        break;
                    case 4:
                        local_id_tipo_registro=13;
                        break;
                    case 6:
                        local_id_tipo_registro=16;
                        break;
                }
                TipoBitacora tipobitacora=new TipoBitacora();
                tipobitacora.setBitacora(bitacora);
                tipobitacora.setConsumidor(consumidor);
                tipobitacora.setFecha(new Date());
                tipobitacora.setId_queja(idqueja);
                tipobitacora.setId_flujo(idflujo);
                if(usuario!=0){
                    tipobitacora.setUsuario(usuario);
                }
                tipobitacora.setId_tipo_registro(local_id_tipo_registro);
                tipoDao.saveBitacora(tipobitacora);
        }
        
        private boolean saveTiposFlujoGuia(Integer idqueja, Integer col) throws Exception{
		if(col < 0 || col > 12)
                    return false;
                TipoFlujoGuia vTipoFlujoGuia;
                    //verificar si es update o insert
                    vTipoFlujoGuia = tipoDao.findByIdTipoFlujoGuia(idqueja);
                    if(vTipoFlujoGuia == null){
                        vTipoFlujoGuia = new TipoFlujoGuia();
                        vTipoFlujoGuia.setAtcon_audiencia_conc(false);
                        vTipoFlujoGuia.setAtcon_audiencia_prueba(false);
                        vTipoFlujoGuia.setAtcon_com_permanente(false);
                        vTipoFlujoGuia.setAtcon_conc_previa(false);
                        vTipoFlujoGuia.setAtcon_verif_datos(false);
                        vTipoFlujoGuia.setVyv_flag(false);
                        vTipoFlujoGuia.setVyv_informe_final(false);
                        vTipoFlujoGuia.setJur_flag(false);
                        vTipoFlujoGuia.setJur_documentado(false);
                        vTipoFlujoGuia.setJur_result_audiencia(false);
                        vTipoFlujoGuia.setJur_resol_final(false);
                        vTipoFlujoGuia.setJur_cobro_sancion(false);
                        vTipoFlujoGuia.setSp_flag(false);
                        vTipoFlujoGuia.setIdqueja(idqueja);
                    }
                    switch(col){
                        case 1:
                            vTipoFlujoGuia.setAtcon_conc_previa(true);
                            break;
                        case 2:
                            vTipoFlujoGuia.setAtcon_verif_datos(true);
                            break;
                        case 3:
                            vTipoFlujoGuia.setAtcon_com_permanente(true);
                            break;
                        case 4:
                            vTipoFlujoGuia.setAtcon_audiencia_conc(true);
                            break;
                        case 5:
                            vTipoFlujoGuia.setAtcon_audiencia_prueba(true);
                            break;
                        case 6:
                            vTipoFlujoGuia.setVyv_flag(true);
                            break;
                        case 7:
                            vTipoFlujoGuia.setVyv_informe_final(true);
                            break;
                        case 8:
                            vTipoFlujoGuia.setJur_flag(true);
                            break;
                        case 9:
                            vTipoFlujoGuia.setJur_documentado(true);
                            break;
                        case 10:
                            vTipoFlujoGuia.setJur_result_audiencia(true);
                            break;
                        case 11:
                            vTipoFlujoGuia.setJur_resol_final(true);
                            break;
                        case 12:
                            vTipoFlujoGuia.setJur_cobro_sancion(true);
                            break;    
                        case 13:
                            vTipoFlujoGuia.setSp_flag(true);
                            break;    
                    }
                    tipoDao.saveFlujoGuia(vTipoFlujoGuia);
                    return true;
        }
        //fin funciones copiadas de Area Comun
        
	/** getters and setters **/
	public TipoRepository getTipoArticuloDao() {
		return tipoDao;
	}

	public void setTipoArticuloDao(TipoRepository tipoDao) {
		this.tipoDao = tipoDao;
	}

}
