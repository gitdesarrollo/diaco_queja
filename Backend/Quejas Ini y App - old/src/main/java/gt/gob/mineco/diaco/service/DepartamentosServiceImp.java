package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.DepartamentoRepository;
import gt.gob.mineco.diaco.model.Departamento;

@Stateless
public class DepartamentosServiceImp {
	@Inject
	DepartamentoRepository departamentoDao;
	
	public List<Departamento> getAll() {
		return departamentoDao.findAll();
	}
		
	public Departamento saveDepartamento(Departamento pDepartamento) {
		return departamentoDao.save(pDepartamento);
	}
	
	public Departamento deleteDepartamento(Departamento pDepartamento) {
		return departamentoDao.delete(pDepartamento);
	}
}
