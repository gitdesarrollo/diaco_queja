package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.model.Departamento;

public class MunicipioRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Municipio> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(Municipio.class);
		TypedQuery<Municipio> query = em.createNamedQuery("Municipio.findAll", Municipio.class);
		return query.getResultList();
	}
	
	public Municipio findById(Integer codigoMunicipio) {
		this.em.getEntityManagerFactory().getCache().evict(Municipio.class);
		return em.find(Municipio.class, codigoMunicipio);
	}
	
	public List<Municipio> findByDepartamento(Integer codigoDepartamento) {
		this.em.getEntityManagerFactory().getCache().evict(Municipio.class);
		this.em.getEntityManagerFactory().getCache().evict(Departamento.class);
		TypedQuery<Municipio> query = em.createNamedQuery("Municipio.findByDepartamento", Municipio.class);
		query.setParameter("departamento", codigoDepartamento);
		return query.getResultList();
	}
	
	public Municipio save(Municipio municipio) {
		em.persist(municipio);
		return municipio;
	}
}
