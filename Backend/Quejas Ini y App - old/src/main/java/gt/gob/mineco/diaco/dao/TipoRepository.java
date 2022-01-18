package gt.gob.mineco.diaco.dao;

import gt.gob.mineco.diaco.model.DiacoImagenes;
import gt.gob.mineco.diaco.model.DiacoImagenesProveedor;
import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.model.TipoMailServer;
import gt.gob.mineco.diaco.model.TipoRepositorio;
import gt.gob.mineco.diaco.model.TipoTipoImagen;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
/*@Stateless
@TransactionManagement(TransactionManagementType.BEAN)*/
public class TipoRepository {

    @PersistenceContext
    private EntityManager em;

    public TipoRepositorio findByIdRepositorio(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoRepositorio.class);
        return em.find(TipoRepositorio.class, id);
    }

    /*
    @SuppressWarnings("unchecked")
    public TipoRepositorio findActiveRepo() {
        List<TipoRepositorio> DataList = em.createNamedQuery("TipoRepositorio.findActive").setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }
     */
    @SuppressWarnings("unchecked")
    public List<TipoImagenesQueja> findAllImagenesQueja(Integer idqueja, Integer idflujo) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        switch (idflujo) {
            case 1:
                return em.createNamedQuery("TipoImagenesQueja.findAllFlujoAtencionCon").setParameter("id_queja", idqueja).getResultList();
            case 4:
                return em.createNamedQuery("TipoImagenesQueja.findAllFlujoConcVirt").setParameter("id_queja", idqueja).getResultList();
            default:
                return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<TipoImagenesQueja> findbyQuejaImagen(Integer idqueja, String imagen) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        return em.createNamedQuery("TipoImagenesQueja.findbyQuejaImagen").setParameter("id_queja", idqueja).setParameter("archivo", imagen).getResultList();
    }

    public TipoImagenesQueja findByIdImagenesQueja(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        return em.find(TipoImagenesQueja.class, id);
    }

    public TipoImagenesQueja findbyTipoI(Integer idqueja) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        List<TipoImagenesQueja> DataList = em.createNamedQuery("TipoImagenesQueja.findbyTipoI").setParameter("id_queja", idqueja).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public void saveImagenesQueja(TipoImagenesQueja tipoImagenesQueja) {
        em.persist(tipoImagenesQueja);
    }

    public void saveImagenesProveedor(DiacoImagenesProveedor tipoImagenesProveedor) {
        em.persist(tipoImagenesProveedor);
    }

    public void saveImagenes(DiacoImagenes tipoImagenesPC) {
        em.persist(tipoImagenesPC);
    }

    public void deleteImagenesQueja(TipoImagenesQueja tipoImagenesQueja) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        em.remove(tipoImagenesQueja);
    }

    public TipoTipoImagen findByIdTipoImagen(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTipoImagen.class);
        return em.find(TipoTipoImagen.class, id);
    }

    public TipoImagenesQueja findbyCatImagen(Integer idqueja, Integer categoria) {
        this.em.getEntityManagerFactory().getCache().evict(TipoImagenesQueja.class);
        List<TipoImagenesQueja> DataList = em.createNamedQuery("TipoImagenesQueja.findbyCatImagen").setParameter("id_queja", idqueja).setParameter("categoria", categoria).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public DiacoImagenesProveedor findbyCatImagenProv(Integer idproveedor, Integer categoria) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoImagenesProveedor.class);
        List<DiacoImagenesProveedor> DataList = em.createNamedQuery("DiacoImagenesProveedor.findbyCatImagen").setParameter("idproveedor", idproveedor).setParameter("categoria", categoria).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public DiacoImagenes findbyCatImagenP(Integer id, Integer categoria) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoImagenes.class);
        List<DiacoImagenes> DataList = em.createNamedQuery("DiacoImagenes.findbyCatImagenProv").setParameter("idproveedor", id).setParameter("categoria", categoria).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public DiacoImagenes findbyCatImagenC(Integer id, Integer categoria) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoImagenes.class);
        List<DiacoImagenes> DataList = em.createNamedQuery("DiacoImagenes.findbyCatImagenCons").setParameter("idconsumidor", id).setParameter("categoria", categoria).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoRepositorio findActiveRepo(String tipo) {
        this.em.getEntityManagerFactory().getCache().evict(TipoRepositorio.class);
        List<TipoRepositorio> DataList = em.createNamedQuery("TipoRepositorio.findActive").setParameter("tipo", tipo).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoTipoImagen findTipoImagen(String desc) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTipoImagen.class);
        List<TipoTipoImagen> DataList = em.createNamedQuery("TipoTipoImagen.findByDescripcion").setParameter("descripcion", desc).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);

    }

    @SuppressWarnings("unchecked")
    public TipoMailServer findAllTipoMailServer() {
        this.em.getEntityManagerFactory().getCache().evict(TipoMailServer.class);
        List<TipoMailServer> DataList = em.createNamedQuery("TipoMailServer.findAll").setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }
}
