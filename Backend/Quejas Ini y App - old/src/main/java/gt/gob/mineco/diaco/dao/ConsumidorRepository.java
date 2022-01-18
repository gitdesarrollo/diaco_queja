package gt.gob.mineco.diaco.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import java.sql.Timestamp;
import java.util.List;

public class ConsumidorRepository {

    @PersistenceContext
    private EntityManager em;

    public DiacoConsumidor save(DiacoConsumidor consumidor) {
        em.persist(consumidor);
        em.flush();
        return consumidor;
    }

    public DiacoConsumidor merge(DiacoConsumidor consumidor) {
        em.merge(consumidor);
        em.flush();
        return consumidor;
    }

    public DiacoConsumidor updateCon(DiacoConsumidor consumidor) {
        em.merge(consumidor);
        em.flush();
        return consumidor;
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

    public DiacoConsumidor findByDocumentoIdentificacion(String documentoIdentificacion) {
        try {
            this.em.getEntityManagerFactory().getCache().evict(DiacoConsumidor.class);
            TypedQuery<DiacoConsumidor> query = em.createNamedQuery("DiacoConsumidor.findByDocumentoIdentificacion", DiacoConsumidor.class);
            query.setParameter("documentoIdentificacion", documentoIdentificacion);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public TipoTelefono findTelefonoById(Integer idConsumidor) {
		this.em.getEntityManagerFactory().getCache().evict(TipoTelefono.class);
        List<TipoTelefono> DataList = em.createNamedQuery("TipoTelefono.findAllC").setParameter("id_consumidor", idConsumidor).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public TipoEmail findCorreoById(Integer idConsumidor) {
		this.em.getEntityManagerFactory().getCache().evict(TipoEmail.class);
        List<TipoEmail> DataList = em.createNamedQuery("TipoEmail.findAllC").setParameter("id_consumidor", idConsumidor).setMaxResults(1).getResultList();
        return DataList.isEmpty() ? null : DataList.get(0);
    }

    public List<TipoTelefono> findTelefonosById(Integer idConsumidor) {
		this.em.getEntityManagerFactory().getCache().evict(TipoTelefono.class);
        List<TipoTelefono> DataList = em.createNamedQuery("TipoTelefono.findAllC").setParameter("id_consumidor", idConsumidor).setMaxResults(100).getResultList();
        return DataList;
    }

    public List<TipoEmail> findCorreosById(Integer idConsumidor) {
		this.em.getEntityManagerFactory().getCache().evict(TipoEmail.class);
        List<TipoEmail> DataList = em.createNamedQuery("TipoEmail.findAllC").setParameter("id_consumidor", idConsumidor).setMaxResults(100).getResultList();
        return DataList;
    }

    public DiacoConsumidor findById(Integer id) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoConsumidor.class);
        return em.find(DiacoConsumidor.class, id);
    }

    //28-10-2010
    public DiacoConsumidor findByDocumentoNit(String documentoIdentificacion) {
        //DiacoConsumidor resp1 = new DiacoConsumidor();
        //TypedQuery<DiacoConsumidor> query1 = em.createNamedQuery("DiacoConsumidor.findByDocumentoIdentificacion", DiacoConsumidor.class);
        try {
			this.em.getEntityManagerFactory().getCache().evict(DiacoConsumidor.class);
            TypedQuery<DiacoConsumidor> query2 = em.createNamedQuery("DiacoConsumidor.findByNit", DiacoConsumidor.class);
            //query1.setParameter("documentoIdentificacion", documentoIdentificacion);
            query2.setParameter("nit", documentoIdentificacion);
            DiacoConsumidor resp1 = query2.getSingleResult();
            //DiacoConsumidor resp2 = query2.getSingleResult();
            //System.out.println("... "+(resp1.getIdConsumidor()!=null?"documento":"nit" ));
            //System.out.println("... "+(resp2.getIdConsumidor()!=null?"nit":"documenot" ));
            //System.out.println("... "+(resp1!=null?"por documento":"  consultara por nit"));
            //if (resp1 == null) {
            //    System.out.println("... "+(resp1!=null?"por nit":"  vacio"));
            //}
            return resp1;
        } catch (Exception e) {
            return null;
        }
    }

    /*
        public DiacoConsumidor findByDocumentoIdentificacion(String documentoIdentificacion) {
        //DiacoConsumidor resp1 = new DiacoConsumidor();
		try {
			TypedQuery<DiacoConsumidor> query1 = em.createNamedQuery("DiacoConsumidor.findByDocumentoIdentificacion", DiacoConsumidor.class);
			//TypedQuery<DiacoConsumidor> query2 = em.createNamedQuery("DiacoConsumidor.findByNit", DiacoConsumidor.class);
			query1.setParameter("documentoIdentificacion", documentoIdentificacion);
			//query2.setParameter("nit", documentoIdentificacion);
			DiacoConsumidor resp1 = query1.getSingleResult();
			//DiacoConsumidor resp2 = query2.getSingleResult();
			//System.out.println("... "+(resp1.getIdConsumidor()!=null?"documento":"nit" ));
			//System.out.println("... "+(resp2.getIdConsumidor()!=null?"nit":"documenot" ));
			//System.out.println("... "+(resp1!=null?"por documento":"  consultara por nit"));
			//if (resp1 == null) {
			//    System.out.println("... "+(resp1!=null?"por nit":"  vacio"));
			//}
			return resp1;
		} catch (Exception e) {
			return null;
		}
    }
     */
    public List<TipoTelefono> getTels(int id, int tipo) {
        try {
			this.em.getEntityManagerFactory().getCache().evict(TipoTelefono.class);
            TypedQuery<TipoTelefono> query = em.createNamedQuery((tipo == 0 ? "TipoTelefono.findAllC" : "TipoTelefono.findAllP"), TipoTelefono.class);
            query.setParameter((tipo == 0 ? "id_consumidor" : "id_proveedor"), id);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TipoEmail> getCorreos(int id, int tipo) {
        try {
			this.em.getEntityManagerFactory().getCache().evict(TipoEmail.class);
            TypedQuery<TipoEmail> query = em.createNamedQuery((tipo == 0 ? "TipoEmail.findAllC" : "TipoEmail.findAllP"), TipoEmail.class);
            query.setParameter((tipo == 0 ? "id_consumidor" : "id_proveedor"), id);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public DiacoConsumidor saveConsumidor(DiacoConsumidor consumidor) {
        consumidor.setFechaAdicion(new Timestamp(System.currentTimeMillis()));
        em.persist(consumidor);
        //em.flush();
        return consumidor;
    }

    public DiacoConsumidor updateConsumidor(DiacoConsumidor consumidor) {
        em.merge(consumidor);
        //em.flush();
        return consumidor;
    }
}
