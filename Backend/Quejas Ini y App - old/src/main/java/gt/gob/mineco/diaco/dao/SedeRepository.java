package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.DiacoSede;

public class SedeRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<DiacoSede> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(DiacoSede.class);
		TypedQuery<DiacoSede> query = em.createNamedQuery("DiacoSede.findAll", DiacoSede.class);
		return query.getResultList();
	}
	
	public DiacoSede findById(Integer idDiacoSede) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoSede.class);
		return em.find(DiacoSede.class, idDiacoSede);
	}
	
	public List<DiacoSede> findByMunicipio(Integer codigoMunicipio) {
		this.em.getEntityManagerFactory().getCache().evict(DiacoSede.class);
		TypedQuery<DiacoSede> query = em.createNamedQuery("DiacoSede.findByMunicipio", DiacoSede.class);
		query.setParameter("municipio", codigoMunicipio);
		return query.getResultList();
	}
	
	public DiacoSede save(DiacoSede sede) {
		em.persist(sede);
		return sede;
	}
}
