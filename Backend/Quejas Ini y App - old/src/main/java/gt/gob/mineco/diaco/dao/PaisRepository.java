package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoPais;

public class PaisRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<DiacoPais> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoPais.class);
		TypedQuery<DiacoPais> query = em.createNamedQuery("DiacoPais.findAll", DiacoPais.class);
		return query.getResultList();
	}

	public DiacoPais findById(Integer idPais) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoPais.class);
		return em.find(DiacoPais.class, idPais);
	}
	
	public DiacoPais save(DiacoPais pais) {
		em.persist(pais);
		return pais;
	}
}
