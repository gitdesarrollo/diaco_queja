package gt.gob.mineco.diaco.dao;

import gt.gob.mineco.diaco.dto.CalendarioDto;
import gt.gob.mineco.diaco.dto.DetalleCalendarioDto;
import gt.gob.mineco.diaco.exception.ErrorException;
import gt.gob.mineco.diaco.model.DiacoConcvirtInteraccion;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoEstadoQueja;
import gt.gob.mineco.diaco.model.DiacoMagicLink;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.model.DiacoQuejaCorrelativo;
import gt.gob.mineco.diaco.model.DiacoSede;
import gt.gob.mineco.diaco.model.TipoColaAsignacion;
import gt.gob.mineco.diaco.model.TipoConfirmarAccion;
import gt.gob.mineco.diaco.model.TipoEstadoQueja;
import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.model.TipoQuejaAsignacion;
import gt.gob.mineco.diaco.model.TipoTipoCola;
import gt.gob.mineco.diaco.model.TipoUsuario;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;

public class QuejaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<DiacoMagicLink> findTokenIntExt(String token) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoMagicLink.class);
        TypedQuery<DiacoMagicLink> query = em.createNamedQuery("DiacoMagicLink.findAll", DiacoMagicLink.class);
        query.setParameter("linkcode", token);
        return query.getResultList();
    }

    public DiacoMagicLink updateStateLink(DiacoMagicLink dat) {
        dat.setEstado("I");
        dat.setFecha_enviado(new Date());
        em.merge(dat);
        return dat;
    }

    public DiacoQueja save(DiacoQueja queja) {
        em.persist(queja);
        em.flush();
        return queja;
    }

    public DiacoQueja merge(DiacoQueja queja) {
        em.merge(queja);
        em.flush();
        return queja;
    }

    public Integer findMaxFromYear() {
        LocalDate today = LocalDate.now();
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        TypedQuery<Integer> query = em.createNamedQuery("DiacoQueja.findMaxFromYear", Integer.class);
        query.setParameter("anio", today.getYear());
        return query.getSingleResult();
    }
    ///////////////////////////////FACTURAS //////////////////////////////////////////////////////////////////////////////////////////////////////
    public Integer findFacturas() {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        TypedQuery<Integer> query = em.createNamedQuery("DiacoQueja.findFacturas", Integer.class);
        return query.getSingleResult();
    }
    
    public List<DiacoQueja> findByProveedor(Integer proveedor) {
        try {
            System.out.println("Ingresando a findByProveedor "+proveedor);
            /*this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
            return em.createNamedQuery("DiacoQueja.findFacturas").setParameter("proveedor", proveedor).getResultList();*/
            
            TypedQuery<DiacoQueja> query = em.createNamedQuery("DiacoQueja.findFacturas", DiacoQueja.class);
            query.setParameter("proveedor", proveedor);
            System.out.println("Imprimiendo resultado de query: "+query.getResultList());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    /*public DiacoQueja findByProveedor(String proveedor) {
        try {
            this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
            TypedQuery<DiacoQueja> query = em.createNamedQuery("DiacoQueja.findFacturas", DiacoQueja.class);
            query.setParameter("proveedor", proveedor);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }*/   
    

    //////////////////////////////////////////////////FINALIZA FACTURAS///////////////////////////////////////////////////////////////////////////

    public Integer findValInicialSec() {
        LocalDate today = LocalDate.now();
        this.em.getEntityManagerFactory().getCache().evict(DiacoQuejaCorrelativo.class);
        TypedQuery<String> query = em.createNamedQuery("DiacoQuejaCorrelativo.findByAnio", String.class);
        query.setParameter("anio", String.valueOf(today.getYear()));
        String resp = query.getSingleResult();
        return Integer.valueOf(resp);
    }
//

    public DiacoQueja findByIdFuente(Integer fuente) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        List<DiacoQueja> DataList = em.createNamedQuery("DiacoQueja.findByIdFuente").setParameter("idfuente", fuente).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);

    }

    public DiacoQueja findByDIdQueja(Integer idqueja) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        return em.find(DiacoQueja.class, idqueja);
    }

    public DiacoConcvirtInteraccion findByDIConvirt(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoConcvirtInteraccion.class);
        List<DiacoConcvirtInteraccion> DataList = em.createNamedQuery("DiacoConcvirtInteraccion.findById").setParameter("id", id).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);

    }

    public TipoUsuario findUserByUserName(String userName) {
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuario.class);
        List<TipoUsuario> DataList = em.createNamedQuery("TipoUsuario.findByLogin").setParameter("login", userName).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    //public SaveConfirmarAccion
    public DiacoQueja grabaAtencionConsumidor(DiacoQueja tipoqueja) {
        //ResponseRs response = new ResponseRs();
        //UserTransaction transaction = null;
        try {
            //TokenCheck(token);
            //transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            //transaction.begin();
            //130 pendiente confirmar accion en atencion al consumidor
            //TipoQueja tipoqueja = findByIdQueja(id_queja);
            //if (tipoqueja.getId_estado_queja() == 130) {
            if (tipoqueja.getIdEstadoQueja() != null) {

            } else {
                tipoqueja.setIdEstadoQueja(0);
            }
            if (tipoqueja.getIdEstadoQueja() == 130) {
                //response.setCode(1L);
                //response.setReason("ERROR_REPETIDO");
            } else {
                /*
                Mantenimiento: Copia del repositorio.
                 */
                tipoqueja.setIdEstadoQueja(401);
                em.merge(tipoqueja);
                //SaveConfirmarAccion(tipoqueja.getIdQueja(), 130);
//                SaveConfirmarAccion(tipoqueja.getIdQueja(), 1, "f", tipoqueja); cambio de sede
                //SaveConfirmarAccion(tipoqueja.getIdQueja(), 1, "f", tipoqueja);
                //response.setCode(0L);
                //response.setReason("OK");
                //response.setValue("{ id_queja: " + id_queja.toString() + " }");
            }
            //transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //response.setCode(1L);
            //response.setReason("ERROR");
            try {
                //transaction.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return tipoqueja;
    }

    /*
    private void SaveConfirmarAccion(Integer id_queja, Integer estado) throws Exception {
        int flujo = 0;
        int siguiente_usuario = 0;
        TipoColaAsignacion tipocolaasignacion;
        TipoTipoCola tipotipocola;
        switch (estado) {
            case 130:
                flujo = 1;
                break;
            case 230:
                flujo = 2;
                break;
            case 330:
                flujo = 3;
                break;
        }
        tipotipocola = findTiposTipoCola(flujo);
        //save confirmar accion
        TipoConfirmarAccion tipoconfirmaraccion = new TipoConfirmarAccion();
        tipoconfirmaraccion.setEstado("A");
        tipoconfirmaraccion.setFecha_creacion(new Date());
        tipoconfirmaraccion.setId_queja(id_queja);
        //revisar si la queja ya fue asignada antes a este flujo
        TipoQuejaAsignacion tipoqasig = findTiposQuejaAsignacion(id_queja);
        boolean NuevaAsignacion = false;
        if (tipoqasig != null) {
            int usuario_asignado = 0;
            switch (flujo) {
                case 1:
                    if (tipoqasig.getAtencion_consumidor() != null) {
                        usuario_asignado = tipoqasig.getAtencion_consumidor();
                    }
                    break;
                case 2:
                    if (tipoqasig.getJuridico() != null) {
                        usuario_asignado = tipoqasig.getJuridico();
                    }
                    break;
                case 3:
                    if (tipoqasig.getVerificacion_vigilancia() != null) {
                        usuario_asignado = tipoqasig.getVerificacion_vigilancia();
                    }
                    break;
            }
            if (usuario_asignado != 0) {
                //si ya fue asignada, confirmar que el usuario esta activo en la cola de asignacion
                tipocolaasignacion = findTiposColaAsignacionxTipoyUsuario(tipotipocola.getId_tipo_cola(), usuario_asignado);
                if (tipocolaasignacion != null) {
                    NuevaAsignacion = false;
                    siguiente_usuario = usuario_asignado;
                } else {
                    NuevaAsignacion = true;
                }
            } else {
                NuevaAsignacion = true;
            }
        } else {
            NuevaAsignacion = true;
        }

        if (NuevaAsignacion) {
            //get nuevo dueño            
            if (tipotipocola.getUltima_asignacion() != null) {
                tipocolaasignacion = findTiposColaAsignacionSiguiente(tipotipocola.getId_tipo_cola(), tipotipocola.getUltima_asignacion());
                if (tipocolaasignacion != null) {
                    //agarrar el siguiente en cola de asignacion
                    siguiente_usuario = tipocolaasignacion.getId_usuario();
                } else {
                    //si no hay siguiente en cola de asignacion agarrar al primero otra vez
                    tipocolaasignacion = findTiposColaAsignacionPrimero(tipotipocola.getId_tipo_cola());
                    siguiente_usuario = tipocolaasignacion.getId_usuario();
                }
            } else {
                //si tipo cola no tiene ultima asignacion agarrar el primero de la cola de asignacion
                tipocolaasignacion = findTiposColaAsignacionPrimero(tipotipocola.getId_tipo_cola());
                siguiente_usuario = tipocolaasignacion.getId_usuario();
            }
            //actualizar tipo cola
            tipotipocola.setUltima_asignacion(siguiente_usuario);
            //saveTipoCola(tipotipocola);
            em.persist(tipotipocola);
            //guardar en diaco queja asignacion
            if (tipoqasig == null) {
                tipoqasig = new TipoQuejaAsignacion();
                tipoqasig.setId_queja(id_queja);
            }
            switch (flujo) {
                case 1:
                    tipoqasig.setAtencion_consumidor(siguiente_usuario);
                    break;
                case 2:
                    tipoqasig.setJuridico(siguiente_usuario);
                    break;
                case 3:
                    tipoqasig.setVerificacion_vigilancia(siguiente_usuario);
                    break;
            }
            //saveTipoQuejaAsignacion(tipoqasig);
            em.persist(tipoqasig);
        }
        //guardar asignado en queja
        //TipoQueja tipoqueja = findByIdQueja(id_queja);
        TipoQueja tipoqueja = findByIdQueja(id_queja);
        tipoqueja.setUsuario_asignado(siguiente_usuario);
        
        //saveQueja(tipoqueja);
        em.persist(tipoqueja);
        //save confirmar accion
        tipoconfirmaraccion.setId_usuario(siguiente_usuario);
        //saveConfirmarAccion(tipoconfirmaraccion);
        em.persist(tipoconfirmaraccion);
    }
     */
    // find datos
    public TipoTipoCola findTiposTipoCola(Integer id_flujo) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTipoCola.class);
        List<TipoTipoCola> DataList = em.createNamedQuery("TipoTipoCola.findxflujo").setParameter("id_flujo", id_flujo).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoQuejaAsignacion findTiposQuejaAsignacion(Integer id_queja) {
        this.em.getEntityManagerFactory().getCache().evict(TipoQuejaAsignacion.class);
        List<TipoQuejaAsignacion> DataList = em.createNamedQuery("TipoQuejaAsignacion.findXIDqueja").setParameter("id_queja", id_queja).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoColaAsignacion findTiposColaAsignacionxTipoyUsuario(Integer id_tipo_cola, Integer id_usuario) {
        this.em.getEntityManagerFactory().getCache().evict(TipoColaAsignacion.class);
        List<TipoColaAsignacion> DataList = em.createNamedQuery("TipoColaAsignacion.findXTipoyUsuario").setParameter("id_tipo_cola", id_tipo_cola).setParameter("id_usuario", id_usuario).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoColaAsignacion findTiposColaAsignacionSiguiente(Integer id_tipo_cola, Integer id_usuario) {
        this.em.getEntityManagerFactory().getCache().evict(TipoColaAsignacion.class);
        List<TipoColaAsignacion> DataList = em.createNamedQuery("TipoColaAsignacion.findSiguiente").setParameter("id_tipo_cola", id_tipo_cola).setParameter("id_usuario", id_usuario).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoColaAsignacion findTiposColaAsignacionPrimero(Integer id_tipo_cola) {
        this.em.getEntityManagerFactory().getCache().evict(TipoColaAsignacion.class);
        List<TipoColaAsignacion> DataList = em.createNamedQuery("TipoColaAsignacion.findPrimero").setParameter("id_tipo_cola", id_tipo_cola).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoQueja findByIdQueja(Integer idqueja) {
        this.em.getEntityManagerFactory().getCache().evict(TipoEstadoQueja.class);
        this.em.getEntityManagerFactory().getCache().evict(TipoQueja.class);
        return em.find(TipoQueja.class, idqueja);
    }

    public Long getSecuencia() {
        //this.em.getEntityManagerFactory().getCache().evict(sec_quejaini_id.class);
        Query query = em.createNativeQuery("SELECT (NEXT VALUE FOR dbo.sec_quejaini_id)");
        return (Long) query.getSingleResult();
    }

    public TipoTipoCola findTiposTipoCola(Integer id, String tipo) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTipoCola.class);
        List<TipoTipoCola> DataList = null;
        if (tipo.equals("f")) {
            DataList = em.createNamedQuery("TipoTipoCola.findxflujo").setParameter("id_flujo", id).setMaxResults(1).getResultList();
        } else {
            DataList = em.createNamedQuery("TipoTipoCola.findxsede").setParameter("sede", id).setMaxResults(1).getResultList();
        }
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoQuejaAsignacion findTiposQuejaAsignacion(Integer id_queja, Integer id_tipo_cola) {
        this.em.getEntityManagerFactory().getCache().evict(TipoQuejaAsignacion.class);
        List<TipoQuejaAsignacion> DataList = em.createNamedQuery("TipoQuejaAsignacion.findXIDqueja").setParameter("id_queja", id_queja).setParameter("id_tipo_cola", id_tipo_cola).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    private void SaveConfirmarAccion(Integer id_queja, Integer id, String tipo, DiacoQueja tipoqueja) throws Exception {
        int flujo = 0;
        int siguiente_usuario = 0;
        TipoColaAsignacion tipocolaasignacion;
        TipoTipoCola tipotipocola;
        tipotipocola = this.findTiposTipoCola(id, tipo);
        //save confirmar accion
        TipoConfirmarAccion tipoconfirmaraccion = new TipoConfirmarAccion();
        tipoconfirmaraccion.setEstado("A");
        tipoconfirmaraccion.setFecha_creacion(new Date());
        tipoconfirmaraccion.setId_queja(id_queja);
        //revisar si la queja ya fue asignada antes a este flujo
        TipoQuejaAsignacion tipoqasig = this.findTiposQuejaAsignacion(id_queja, tipotipocola.getId_tipo_cola());
        boolean NuevaAsignacion = false;
        if (tipoqasig != null) {
            int usuario_asignado = 0;
            usuario_asignado = tipoqasig.getId_usuario();
            if (usuario_asignado != 0) {
                //si ya fue asignada, confirmar que el usuario esta activo en la cola de asignacion
                tipocolaasignacion = this.findTiposColaAsignacionxTipoyUsuario(tipotipocola.getId_tipo_cola(), usuario_asignado);
                if (tipocolaasignacion != null) {
                    NuevaAsignacion = false;
                    siguiente_usuario = usuario_asignado;
                } else {
                    NuevaAsignacion = true;
                }
            } else {
                NuevaAsignacion = true;
            }
        } else {
            NuevaAsignacion = true;
        }

        if (NuevaAsignacion) {
            //get nuevo dueño            
            if (tipotipocola.getUltima_asignacion() != null) {
                tipocolaasignacion = this.findTiposColaAsignacionSiguiente(tipotipocola.getId_tipo_cola(), tipotipocola.getUltima_asignacion());
                if (tipocolaasignacion != null) {
                    //agarrar el siguiente en cola de asignacion
                    siguiente_usuario = tipocolaasignacion.getId_usuario();
                } else {
                    //si no hay siguiente en cola de asignacion agarrar al primero otra vez
                    tipocolaasignacion = this.findTiposColaAsignacionPrimero(tipotipocola.getId_tipo_cola());
                    siguiente_usuario = tipocolaasignacion.getId_usuario();
                }
            } else {
                //si tipo cola no tiene ultima asignacion agarrar el primero de la cola de asignacion
                tipocolaasignacion = this.findTiposColaAsignacionPrimero(tipotipocola.getId_tipo_cola());
                siguiente_usuario = tipocolaasignacion.getId_usuario();
            }
            //actualizar tipo cola
            tipotipocola.setUltima_asignacion(siguiente_usuario);
            //tipoDao.saveTipoCola(tipotipocola);            
            em.persist(tipotipocola);
            //guardar en diaco queja asignacion
            if (tipoqasig == null) {
                tipoqasig = new TipoQuejaAsignacion();
                tipoqasig.setId_queja(id_queja);
                tipoqasig.setId_tipo_cola(tipotipocola.getId_tipo_cola());
            }
            tipoqasig.setId_usuario(siguiente_usuario);
            //tipoDao.saveTipoQuejaAsignacion(tipoqasig);
            em.persist(tipoqasig);
        }
        //guardar asignado en queja
        //TipoQueja tipoqueja = this.findByIdQueja(id_queja);
        tipoqueja.setUsuarioAsignado(siguiente_usuario);
        //tipoDao.saveQueja(tipoqueja);
        //em.persist(tipoqueja);
        em.merge(tipoqueja);
        //save confirmar accion
        tipoconfirmaraccion.setId_usuario(siguiente_usuario);
        //tipoDao.saveConfirmarAccion(tipoconfirmaraccion);
        //em.persist(tipoconfirmaraccion);
        em.persist(tipoconfirmaraccion);
    }

    public DiacoQueja getquejasId(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        return em.find(DiacoQueja.class, id);
    }

    public DiacoQueja getquejasAnioSec(Integer anio, Integer correlativo) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        List<DiacoQueja> DataList = em.createNamedQuery("DiacoQueja.findAnioSec").setParameter("anio", anio).setParameter("noQueja", correlativo).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public DiacoSede findSedeById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoSede.class);
        return em.find(DiacoSede.class, id);
    }

    public DiacoEstadoQueja findEstadoQuejaById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoEstadoQueja.class);
        return em.find(DiacoEstadoQueja.class, id);
    }

    public TipoPasoQueja findPasoQuejaById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoPasoQueja.class);
        List<TipoPasoQueja> DataList = em.createNamedQuery("TipoPasoQueja.findByIdQueja").setParameter("idqueja", id).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public List<DiacoQueja> getAllQuejas(String noQueja) {
        String consulta = "select  FORMAT(o.fecha_operacion , 'dd/MM/yyyy HH:mm:ss'), e.descripcion "
                + " from dbo.diaco_paso_queja o, dbo.diaco_estado_queja e "
                + " where o.id_estado_operado = e.id_estado_queja "
                + " and id_queja = (select id_queja from dbo.diaco_queja where CONVERT(varchar(10),no_queja)+'-'+CONVERT(varchar(50),anio) = '" + noQueja + "') "
                + " order by o.fecha_operacion desc ";
        consulta = "select FORMAT(fecha , 'dd/MM/yyyy HH:mm:ss'), bitacora from dbo.diaco_bitacora WHERE id_queja =  (select id_queja from dbo.diaco_queja where CONVERT(varchar(10),no_queja)+'-'+CONVERT(varchar(50),anio) = '" + noQueja + "') "
                + "order by fecha desc";
        Query query = em.createNativeQuery(consulta);
        //query.setParameter("panio", noQueja.substring(0, 4));
        //query.setParameter("pno", noQueja.substring(5));
        return query.getResultList();
    }

    public List<TipoImagenesQueja> buscaArchivos(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        List<TipoImagenesQueja> DataList = em.createNamedQuery("TipoImagenesQueja.findbyTipoI").setParameter("id_queja", id).getResultList();
        return DataList.isEmpty() ? null : DataList;

        //List<TipoImagenesQueja> larchivos = this.quejaDao.buscaArchivos(id);
    }

    public DiacoConcvirtInteraccion mergeConcvirtInteraccion(DiacoConcvirtInteraccion cv) {
        this.em.merge(cv);
        this.em.flush();
        return cv;
    }

    /**
     * Metodo que realiza una consulta a la base de datos para buscar el detalle
     * del calendario segun los parametros de busqueda recibidos.
     *
     * @param pCalendarioDto DTO con los parametros de busqueda.
     * @return Lista con el detalle de registros / Audiencias con formato para
     * visualizar en calendario.
     * @throws ErrorException Si ocurre un error.
     */
    public List<DetalleCalendarioDto> findDetalleCalendario(CalendarioDto pCalendarioDto)
            throws ErrorException {
        // this.em.getEntityManagerFactory().getCache().evict(DiacoPtsGeneralCalifProv.class);
        try {
             System.out.println("Metodo: findDetalleCalendario");
            List<Object[]> resultList;

            Query query = em.createNativeQuery(""
                    + "SELECT\n"
                    + "  pa.id_progra_aud id,\n"
                    + "  convert(varchar, pa.fecha_programada, 126) START,\n"
                    + "  convert(varchar, (DATEADD(hour,1,pa.fecha_programada)), 126) fin,\n"
                    + "  q.detalle_queja description,\n"
                    + "  '#007bff' AS color,\n"
                    + "  p.nombre title,\n"
                    + "  u.nombre usuario,\n"
                    + "  q.no_queja no_queja,\n"
                    + "  q.anio año\n"
                    + "FROM\n"
                    + "  dbo.diaco_programa_audiencia pa,\n"
                    + "  diaco_audiencia a,\n"
                    + "  dbo.diaco_paso_queja og,\n"
                    + "  dbo.diaco_queja q,\n"
                    + "  dbo.diaco_proveedor p,\n"
                    + "  dbo.diaco_usuario u\n"
                    + "WHERE\n"
                    + "  a.id_audiencia        =pa.id_audiencia\n"
                    + "AND a.id_queja_paso     =og.id_queja_paso\n"
                    + "AND q.id_proveedor      =p.id_proveedor\n"
                    + "AND og.usuario_operacion=u.id_usuario\n"
                    + "AND a.id_queja_paso     =og.id_queja_paso\n"
                    + "AND og.id_queja         =q.id_queja\n"
                    + "AND pa.activo           ='true'\n"
                    + "AND fecha_programada BETWEEN ? AND ?\n"
                    + "AND usuario_operacion= ?\n"
                    + "UNION\n"
                    + "SELECT\n"
                    + "  pa.id_progra_aud id,\n"
                    + "  convert(varchar, pa.fecha_programada, 126) START,\n"
                    + "  convert(varchar, (DATEADD(hour,1,pa.fecha_programada)), 126) fin,\n"
                    + "  q.detalle_queja description,\n"
                    + "  '#006600' AS color,\n"
                    + "  p.nombre title,\n"
                    + "  u.nombre usuario,\n"
                    + "  q.no_queja no_queja,\n"
                    + "  q.anio año\n"
                    + "FROM\n"
                    + "  dbo.diaco_programa_audiencia pa,\n"
                    + "  diaco_audiencia a,\n"
                    + "  dbo.diaco_paso_queja og,\n"
                    + "  dbo.diaco_queja q,\n"
                    + "  dbo.diaco_proveedor p,\n"
                    + "  dbo.diaco_usuario u\n"
                    + "WHERE\n"
                    + "  a.id_audiencia        =pa.id_audiencia\n"
                    + "AND a.id_queja_paso     =og.id_queja_paso\n"
                    + "AND q.id_proveedor      =p.id_proveedor\n"
                    + "AND og.usuario_operacion=u.id_usuario\n"
                    + "AND a.id_queja_paso     =og.id_queja_paso\n"
                    + "AND og.id_queja         =q.id_queja\n"
                    + "AND pa.activo           ='true'\n"
                    + "AND fecha_programada  BETWEEN ? AND ?\n"
                    + "AND q.id_Proveedor = p.id_proveedor\n"
                    + "AND p.id_proveedor=(select id_proveedor\n" +
                        " FROM\n" + 
                        "  dbo.diaco_queja q\n" +
                        "  where id_queja= ? )");
            
            System.out.println("Carga de parametros.");
            System.out.println(pCalendarioDto.toString());
            
            query.setParameter(1, pCalendarioDto.getFechaInicio());
            query.setParameter(2, pCalendarioDto.getFechaFinal());
            query.setParameter(3, pCalendarioDto.getUsuarioOperacion());

            query.setParameter(4, pCalendarioDto.getFechaInicio());
            query.setParameter(5, pCalendarioDto.getFechaFinal());
            query.setParameter(6, pCalendarioDto.getIdQueja());
            System.out.println("Ejecuta consulta.");
            // Lista de datos
            resultList = query.getResultList();
            // Instancia para procesar los datos hacia una lista de objetos.
            List<DetalleCalendarioDto> listaResultados = new ArrayList<>();
            // Registro/Item del tipo: DetalleCalendarioDto
            DetalleCalendarioDto detalleAudiencia;
            System.out.println("Procesando datos.");
            for (Object[] item : resultList) {
                // Nuevo registro.
                detalleAudiencia = new DetalleCalendarioDto();
                // Setear los atributos
                detalleAudiencia.setId(Integer.valueOf((String) ((item[0] != null) ? item[0].toString() : "")));
                // Fechas
                detalleAudiencia.setStart((String) ((item[1] != null) ? item[1].toString() : ""));
                detalleAudiencia.setEnd((String) ((item[2] != null) ? item[2].toString() : ""));
                // Detalles.
                detalleAudiencia.setDescription((String) ((item[3] != null) ? item[3].toString() : ""));
                detalleAudiencia.setColor((String) ((item[4] != null) ? item[4].toString() : ""));
                detalleAudiencia.setTitle((String) ((item[5] != null) ? item[5].toString() : ""));
                detalleAudiencia.setUser((String) ((item[6] != null) ? item[6].toString() : ""));
                detalleAudiencia.setNo_queja((String) ((item[7] != null) ? item[7].toString() : ""));
                detalleAudiencia.setAño((String) ((item[8] != null) ? item[8].toString() : ""));

                listaResultados.add(detalleAudiencia);
            }
            return listaResultados;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new ErrorException(e);
        }
    }

    /**
     * Metodo que sirve para convertir una fecha {@code Date} en formato de
     * cadena.
     *
     * @param pFechaEnCadena Fecha solicitada
     * @param pFormatoPersonalizado Formato personalizado. Si no se envia el
     * formato, se tomara de referencia: {@link Constantes#FORMATO_FECHA}
     * @return Fecha en formato de Cadena segun el formato especificado.
     * @throws ErrorException Si ocurre un error
     */
    public static String convertirFechaCadena(Date pFechaEnCadena,
            String pFormatoPersonalizado)
            throws ErrorException {
        try {
            String formato = "yyyy/MM/dd HH:mm:ss";
            // Se establece el formato de la fecha para aplicar.
            String formatoFecha = StringUtils.isBlank(pFormatoPersonalizado)
                    ? formato : pFormatoPersonalizado;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoFecha);
            String fecha = simpleDateFormat.format(pFechaEnCadena);

            return fecha;
        } catch (Exception e) {
            throw new ErrorException("Error al convertir la fecha.", e);
        }
    }
}
