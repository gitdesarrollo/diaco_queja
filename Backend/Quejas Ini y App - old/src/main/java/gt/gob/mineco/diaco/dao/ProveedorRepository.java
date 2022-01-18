package gt.gob.mineco.diaco.dao;

import gt.gob.mineco.diaco.dto.ProveedorDto;
import gt.gob.mineco.diaco.model.DiacoEstablecimiento;
import gt.gob.mineco.diaco.model.DiacoProveedor;
import gt.gob.mineco.diaco.model.DiacoProveedorAprovar;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoUsuario;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class ProveedorRepository {

    @PersistenceContext
    EntityManager em;

    public DiacoProveedor save(DiacoProveedor proveedor) {
        em.persist(proveedor);
        em.flush();
        return proveedor;
    }

    public DiacoProveedor merge(DiacoProveedor proveedor) {
        em.merge(proveedor);
        em.flush();
        return proveedor;
    }

    public DiacoProveedor findByNit(String nit) {
        try {
            this.em.getEntityManagerFactory().getCache().evict(DiacoProveedor.class);
            TypedQuery<DiacoProveedor> query = em.createNamedQuery("DiacoProveedor.findByNit", DiacoProveedor.class);
            query.setParameter("nit", nit);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public DiacoProveedorAprovar findByNitProveedor(String nit) {
        try {
            this.em.getEntityManagerFactory().getCache().evict(DiacoProveedorAprovar.class);
            TypedQuery<DiacoProveedorAprovar> query = em.createNamedQuery("DiacoProveedorAprovar.findByNit", DiacoProveedorAprovar.class);
            query.setParameter("nit", nit);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public DiacoProveedorAprovar saveProveedor(DiacoProveedorAprovar proveedor) {
        em.persist(proveedor);
        em.flush();
        return proveedor;
    }

    public DiacoProveedorAprovar mergeProveedor(DiacoProveedorAprovar proveedor) {
        em.merge(proveedor);
        em.flush();
        return proveedor;
    }

    public TipoUsuario findUserById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuario.class);
        return em.find(TipoUsuario.class, id);
    }

    public Long findQuejasValProv(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        TypedQuery<Long> query = em.createNamedQuery("DiacoQueja.findQuejasValProv", Long.class);
        query.setParameter("id_proveedor", id);
        return query.getSingleResult();
    }

    public Long findQuejasProv(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoQueja.class);
        TypedQuery<Long> query = em.createNamedQuery("DiacoQueja.findQuejasProv", Long.class);
        query.setParameter("id_proveedor", id);
        return query.getSingleResult();
    }

    public DiacoProveedor findById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoProveedor.class);
        return em.find(DiacoProveedor.class, id);
    }

    public DiacoProveedorAprovar findByIdProveedor(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoProveedorAprovar.class);
        return em.find(DiacoProveedorAprovar.class, id);
    }

    public List<ProveedorDto> getProveedorByNitNombre(String nit, String nombre) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoProveedor.class);
        String querystring = "SELECT v FROM DiacoProveedor v where 1=1 ";

        querystring = querystring + " and ( "
                + "coalesce(CONCAT( UPPER(v.primerNombre), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.segundoNombre), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.tercerNombre), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.primerApellido), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.segundoApellido), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.apellidoCasada), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.razonSocial), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.nombreEmpresa), ' '),'') + "
                + "coalesce(CONCAT( UPPER(v.nombre), ' '),'') ) NOT LIKE UPPER(:pro_pendiente)";
        String param;
        if (!nombre.isEmpty()) {
            querystring = querystring + " and ( "
                    + "coalesce(CONCAT( UPPER(v.primerNombre), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.segundoNombre), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.tercerNombre), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.primerApellido), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.segundoApellido), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.apellidoCasada), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.razonSocial), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.nombreEmpresa), ' '),'') + "
                    + "coalesce(CONCAT( UPPER(v.nombre), ' '),'') ) LIKE UPPER(:pro_nombre)";

        }
        if (!nit.isEmpty()) {
            querystring = querystring + " and UPPER(v.nitProveedor) LIKE UPPER(:pro_nit)";
        }
        Query nq = this.em.createQuery(querystring, DiacoProveedor.class);
        param = "%PENDIENTE%";
        nq.setParameter("pro_pendiente", param);

        if (!nombre.isEmpty()) {
            param = "%" + nombre.replaceAll(" ", "%") + "%";
            nq.setParameter("pro_nombre", param);
        }
        if (!nit.isEmpty()) {
            param = "%" + nit.replaceAll(" ", "%") + "%";
            nq.setParameter("pro_nit", param);
        }

        //System.out.println(".............. " + querystring + ", ........ " + nq.getParameters());
        return nq.getResultList();
    }

    public TipoTelefono findTelefonoById(Integer idProveedor) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTelefono.class);
        List<TipoTelefono> DataList = em.createNamedQuery("TipoTelefono.findAllP").setParameter("id_proveedor", idProveedor).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoEmail findCorreoById(Integer idProveedor) {
        this.em.getEntityManagerFactory().getCache().evict(TipoEmail.class);
        List<TipoEmail> DataList = em.createNamedQuery("TipoEmail.findAllP").setParameter("id_proveedor", idProveedor).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public List<TipoTelefono> findTelefonosById(Integer idProveedor) {
        this.em.getEntityManagerFactory().getCache().evict(TipoTelefono.class);
        List<TipoTelefono> DataList = em.createNamedQuery("TipoTelefono.findAllP").setParameter("id_proveedor", idProveedor).setMaxResults(100).getResultList();
        return DataList;
    }

    public List<TipoEmail> findCorreosById(Integer idProveedor) {
        this.em.getEntityManagerFactory().getCache().evict(TipoEmail.class);
        List<TipoEmail> DataList = em.createNamedQuery("TipoEmail.findAllP").setParameter("id_proveedor", idProveedor).setMaxResults(100).getResultList();
        return DataList;
    }

    public TipoEmail saveCorreo(TipoEmail correo) {
        em.persist(correo);
        em.flush();
        return correo;
    }

    public TipoEmail updateCorreo(TipoEmail correo) {
        em.merge(correo);
        em.flush();
        return correo;
    }

    public TipoTelefono saveTelefono(TipoTelefono telefono) {
        em.persist(telefono);
        em.flush();
        return telefono;
    }

    public TipoTelefono updateTelefono(TipoTelefono telefono) {
        em.merge(telefono);
        em.flush();
        return telefono;
    }

    public List<DiacoEstablecimiento> getEstableciminientoByNitProveedor(String nit) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoEstablecimiento.class);
        List<DiacoEstablecimiento> DataList = em.createNamedQuery("DiacoEstablecimiento.findByNit").setParameter("nit", nit).setMaxResults(100).getResultList();
        return DataList;
    }

    public DiacoEstablecimiento getEstableciminientoById(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoEstablecimiento.class);
        return em.find(DiacoEstablecimiento.class, id);
    }

    public DiacoEstablecimiento findEstableciminientoByNitProveedor(Integer id) {
        this.em.getEntityManagerFactory().getCache().evict(DiacoEstablecimiento.class);
        return em.find(DiacoEstablecimiento.class, id);
    }

    public DiacoEstablecimiento mergeEstableciminientoByNitProveedor(DiacoEstablecimiento est) {
        em.merge(est);
        em.flush();
        return est;
    }

    public DiacoEstablecimiento saveEstableciminientoByNitProveedor(DiacoEstablecimiento est) {
        em.persist(est);
        em.flush();
        return est;
    }

    public String findParametro(String id) {
        //this.em.getEntityManagerFactory().getCache().evict(DiacoParametros.class);
        TypedQuery<String> query = em.createNamedQuery("DiacoParametros.findByParametro", String.class);
        query.setParameter("parametro", id);
        return query.getSingleResult();
    }
}
