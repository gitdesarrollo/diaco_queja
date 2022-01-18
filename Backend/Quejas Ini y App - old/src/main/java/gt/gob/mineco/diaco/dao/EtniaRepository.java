package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoEtnia;

public class EtniaRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<DiacoEtnia> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoEtnia.class);
		TypedQuery<DiacoEtnia> query = em.createNamedQuery("DiacoEtnia.findAll", DiacoEtnia.class);
		return query.getResultList();
	}
	
	public DiacoEtnia findById(Integer codigoEtnia) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoEtnia.class);
		return em.find(DiacoEtnia.class, codigoEtnia);
	}
	
	public DiacoEtnia save(DiacoEtnia etnia) {
		em.persist(etnia);
		return etnia;
	}
}
