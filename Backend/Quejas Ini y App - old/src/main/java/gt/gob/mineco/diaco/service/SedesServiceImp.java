package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.SedeRepository;
import gt.gob.mineco.diaco.model.DiacoSede;

@Stateless
public class SedesServiceImp {
	@Inject
	SedeRepository sedeDao;
	
	public List<DiacoSede> getAll() {
		return sedeDao.findAll();
	}
	
	public List<DiacoSede> getByMunicipio(Integer codigoMunicipio) {
		return sedeDao.findByMunicipio(codigoMunicipio);
	}
}
