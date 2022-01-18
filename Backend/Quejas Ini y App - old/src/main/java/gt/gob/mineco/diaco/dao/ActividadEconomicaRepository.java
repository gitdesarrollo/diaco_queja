package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoActividadEconomica;

public class ActividadEconomicaRepository {
	@PersistenceContext
	EntityManager em;
	
	public List<DiacoActividadEconomica> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoActividadEconomica.class);
		TypedQuery<DiacoActividadEconomica> query = em.createNamedQuery("DiacoActividadEconomica.findAll", DiacoActividadEconomica.class);
		return query.getResultList();
	}
}
