/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dao;

import gt.gob.mineco.diaco.dto.CalificacionProveedorDto;
import gt.gob.mineco.diaco.model.Departamento;
import gt.gob.mineco.diaco.model.DiacoCalificacionUsuarioProv;
import gt.gob.mineco.diaco.model.DiacoCatalogoCatagoriaCalificacion;
import gt.gob.mineco.diaco.model.DiacoPtsGeneralCalifProv;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author julio
 */
@ApplicationScoped
public class CalificacionRepository {

    @PersistenceContext
    private EntityManager em;

    public List<DiacoCalificacionUsuarioProv> findCalificacionUsuarioProv(Integer pIdProveedor, String pIdInstalacionMobil) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoCalificacionUsuarioProv.class);
        TypedQuery<DiacoCalificacionUsuarioProv> query = em.createNamedQuery("DiacoCalificacionUsuarioProv.findByIdProveedorAndInstalacionId", DiacoCalificacionUsuarioProv.class);
        query.setParameter("idProveedor", pIdProveedor);
        query.setParameter("idInstalacionMobil", pIdInstalacionMobil);
        return query.getResultList();
    }

    public DiacoCalificacionUsuarioProv findCalificacionUsuarioProvCategoria(Integer pIdProveedor, String pIdInstalacionMobil, DiacoCatalogoCatagoriaCalificacion pIdCategoria) {

        try {
            this.em.getEntityManagerFactory().getCache().evict(DiacoCalificacionUsuarioProv.class);
            TypedQuery<DiacoCalificacionUsuarioProv> query = em.createNamedQuery("DiacoCalificacionUsuarioProv.findByIdProvAndInstallIdAndCategoria", DiacoCalificacionUsuarioProv.class);

            query.setParameter("idProveedor", pIdProveedor);
            query.setParameter("idInstalacionMobil", pIdInstalacionMobil);
            query.setParameter("idCategoriaCalificacion", pIdCategoria);

            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<DiacoPtsGeneralCalifProv> findCalificacionGeneralProv(Integer pIdProveedor) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoPtsGeneralCalifProv.class);
        TypedQuery<DiacoPtsGeneralCalifProv> query = em.createNamedQuery("DiacoPtsGeneralCalifProv.findByIdProveedor", DiacoPtsGeneralCalifProv.class);
        query.setParameter("idProveedor", pIdProveedor);
        return query.getResultList();
    }

    public List<CalificacionProveedorDto> findCalificacionesUserGeneral(Integer pIdProveedor, String pId_instalacion_Mobil) {
        // this.em.getEntityManagerFactory().getCache().evict(DiacoPtsGeneralCalifProv.class);
       List<Object[]> rawResultList;

        Query query = em.createNativeQuery("SELECT\n" +
"dccc.id_categoria_calificacion as idCategoria,dccc.nombre_categoria as nombreCategoria,\n" +
"dcu.calificacion as calificacion,dpc.num_calificaciones as totalCalificaciones,dpc.promedio as promedioCalificaciones\n" +
"                FROM\n" +
"                    diaco_catalogo_catagoria_calificacion dccc\n" +
"                    LEFT JOIN diaco_pts_general_calif_prov dpc  ON\n" +
"                    dccc.id_categoria_calificacion=dpc.id_categoria_calificacion\n" +
"                    and dpc.id_proveedor=?\n" +
"                    LEFT JOIN diaco_calificacion_usuario_prov dcu ON \n" +
"                dccc.id_categoria_calificacion= dcu.id_categoria_calificacion and dcu.id_proveedor=dpc.id_proveedor\n" +
"               and dcu.id_instalacion_mobil=?");
        query.setParameter(1, pIdProveedor);
        query.setParameter(2, pId_instalacion_Mobil);

        rawResultList= query.getResultList();
        List<CalificacionProveedorDto> califDtoList=new ArrayList<CalificacionProveedorDto>();
        CalificacionProveedorDto califUni;
        for (Object[] objects : rawResultList) {
            califUni=new CalificacionProveedorDto();
            califUni.setIdCategoria((String) ((objects[0]!=null)?objects[0].toString():""));
            califUni.setNombreCategoria((String) ((objects[1]!=null)?objects[1].toString():""));
            califUni.setCalificacion((String) ((objects[2]!=null)?objects[2].toString():""));
            califUni.setTotalCalificaciones((String) ((objects[3]!=null)?objects[3].toString():""));
            califUni.setPromedioCalificaciones((String) ((objects[4]!=null)?objects[4].toString():""));
            califDtoList.add(califUni);
        }
        return califDtoList;
                
    }

    public DiacoCalificacionUsuarioProv saveCalificacionUsuario(DiacoCalificacionUsuarioProv calificacion) {

        em.persist(calificacion);
        em.flush();
        return calificacion;
    }

}
