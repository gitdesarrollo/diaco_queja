package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import gt.gob.mineco.diaco.model.Departamento;

public class DepartamentoRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Departamento> findAll() {
		this.em.getEntityManagerFactory().getCache().evict(Departamento.class);
		TypedQuery<Departamento> query = em.createNamedQuery("Departamento.findAll", Departamento.class);
		return query.getResultList();
	}
	
	public Departamento findById(Integer codigoDepartamento) {
		this.em.getEntityManagerFactory().getCache().evict(Departamento.class);
		return em.find(Departamento.class, codigoDepartamento);
	}
	
	public Departamento save(Departamento departamento) {
		em.persist(departamento);
		return departamento;
	}
	
	public Departamento delete(Departamento departamento) {
		em.remove(departamento);
		return departamento;
	}
}
