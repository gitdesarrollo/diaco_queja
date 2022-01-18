package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.text.SimpleDateFormat;
import gt.gob.mineco.diaco.model.TipoViewMainQueja;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormBusqueda;
import gt.gob.mineco.diaco.util.FormViewMainQueja;
import gt.gob.mineco.diaco.util.FormViewMainQueja1;
import gt.gob.mineco.diaco.util.FormViewMainQueja2;


@Path("/tipos-viewmainqueja")
@RequestScoped
public class TipoViewMainQuejaRs {

	@Inject
	private TipoAreaComunService tipoViewMainQuejaService;
	
	@GET
	@Path("/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQueja(@PathParam("token") String token){
		return tipoViewMainQuejaService.listViewMainQueja(token);
	}
	
        @PUT
	@Path("/customfilter")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQueja(FormViewMainQueja1 formViewMainQueja) {
		return tipoViewMainQuejaService.listQuejaCustomFilter1(formViewMainQueja);
	}
        
        @PUT
	@Path("/customfilterSP")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQuejaSP(FormViewMainQueja1 formViewMainQueja) {
		return tipoViewMainQuejaService.listQuejaCustomFilterSP(formViewMainQueja);
	}
        
        @PUT
	@Path("/customfilterVyV")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQuejaVyV(FormViewMainQueja1 formViewMainQueja) {
		return tipoViewMainQuejaService.listQuejaCustomFilterVyV(formViewMainQueja);
	}
        
        @PUT
	@Path("/customfilterJuridico")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQuejaJuridico(FormViewMainQueja1 formViewMainQueja) {
		return tipoViewMainQuejaService.listQuejaCustomFilterJuridico(formViewMainQueja);
	}
        
        @PUT
	@Path("/customfilter2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewMainQueja2(FormViewMainQueja2 formViewMainQueja) {
		return tipoViewMainQuejaService.listQuejaCustomFilter2(formViewMainQueja);
	}
        
        
	@GET
	@Path("/simple/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimpleAct(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimpleAct(token);
	}
        
        @GET
	@Path("/simplearchivo/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimpleArchivo(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimpleArchivo(token);
	}

        @GET
	@Path("/simplejuridico/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimpleJuridico(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimpleJuridico(token);
	}

        @GET
	@Path("/simplevyv/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimpleVyV(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimpleVyV(token);
	}
        
        @GET
	@Path("/simpletodo/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimpleTodo(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimpleTodo(token);
	}

        @GET
	@Path("/simple_pendasig/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaSimplePendAsig(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listQuejaSimplePendAsig(token);
	}    
        
        @GET
	@Path("/VyVsimplearchivo/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaVyVSimpleArchivo(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listVyVQuejaSimpleArchivo(token);
	}

        @GET
	@Path("/VyVsimplejuridico/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaVyVSimpleJuridico(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listVyVQuejaSimpleJuridico(token);
	}

        @GET
	@Path("/VyVsimpleAtCon/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaVyVSimpleAtCon(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listVyVQuejaSimpleAtCon(token);
	}
        
        @GET
	@Path("/VyVsimple_pendasig/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaVyVSimplePendAsig(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listVyVQuejaSimplePendAsig(token);
	}   
        
        @GET
	@Path("/Jursimplearchivo/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaJurSimpleArchivo(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listJurQuejaSimpleArchivo(token);
	}

        @GET
	@Path("/Jursimpleverificacion/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaJurSimpleVerificacion(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listJurQuejaSimpleVerificacion(token);
	}

        @GET
	@Path("/JursimpleAtCon/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaJurSimpleAtCon(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listJurQuejaSimpleAtCon(token);
	}
        
        @GET
	@Path("/Jursimple_pendasig/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getViewQuejaJurSimplePendAsig(@PathParam("token") String token) {
		return tipoViewMainQuejaService.listJurQuejaSimplePendAsig(token);
	}   
        
        @PUT
	@Path("/custombusqueda")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs findTipoQuejaCustomFilterBusqueda(FormBusqueda formBusqueda) {
		return tipoViewMainQuejaService.findTipoQuejaCustomFilterBusqueda(formBusqueda);
	}
                
}
