package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoTipoComercio;

public class TipoComercioRepository {
	@PersistenceContext
	EntityManager em;
	
	public List<DiacoTipoComercio> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoTipoComercio.class);
		TypedQuery<DiacoTipoComercio> query = em.createNamedQuery("DiacoTipoComercio.findAll", DiacoTipoComercio.class);
		return query.getResultList();
	}
	
	public List<DiacoTipoComercio> findByActividadEconomica(Integer idActividadEconomica) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoTipoComercio.class);
		TypedQuery<DiacoTipoComercio> query = em.createNamedQuery("DiacoTipoComercio.findByActividadEconomica", DiacoTipoComercio.class);
		query.setParameter("actividadEconomica", idActividadEconomica);
		return query.getResultList();
	}
}
