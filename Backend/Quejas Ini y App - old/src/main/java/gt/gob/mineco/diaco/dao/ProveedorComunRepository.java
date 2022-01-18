package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoProveedorComun;

public class ProveedorComunRepository {
	@PersistenceContext
	EntityManager em;
	
	public List<DiacoProveedorComun> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoProveedorComun.class);
		TypedQuery<DiacoProveedorComun> query = em.createNamedQuery("DiacoProveedorComun.findAll", DiacoProveedorComun.class);
		return query.getResultList();
	}
}
