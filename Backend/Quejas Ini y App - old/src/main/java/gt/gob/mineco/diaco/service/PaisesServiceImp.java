package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.PaisRepository;
import gt.gob.mineco.diaco.model.DiacoPais;

@Stateless
public class PaisesServiceImp {
	@Inject
	PaisRepository paisDao;
	
	public List<DiacoPais> getAll() {
		return paisDao.findAll();
	}
}
