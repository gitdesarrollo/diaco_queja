package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.MunicipioRepository;
import gt.gob.mineco.diaco.model.Municipio;

@Stateless
public class MunicipiosServiceImp {
	@Inject
	MunicipioRepository municipioDao;
	
	public List<Municipio> getAll() {
		return municipioDao.findAll();
	}
	
	public List<Municipio> getByDepartamento(Integer codigoDepartamento) {
		return municipioDao.findByDepartamento(codigoDepartamento);
	}
}
