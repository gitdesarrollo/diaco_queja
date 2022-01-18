package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoMotivoQueja;

public class MotivoQuejaRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<DiacoMotivoQueja> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoMotivoQueja.class);
		TypedQuery<DiacoMotivoQueja> query = em.createNamedQuery("DiacoMotivoQueja.findAll", DiacoMotivoQueja.class);
		return query.getResultList();
	}
	
	public DiacoMotivoQueja findById(Integer codigoMotivo) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoMotivoQueja.class);
		return em.find(DiacoMotivoQueja.class, codigoMotivo);
	}
	
	public DiacoMotivoQueja save(DiacoMotivoQueja motivoQueja) {
		em.persist(motivoQueja);
		return motivoQueja;
	}
}
