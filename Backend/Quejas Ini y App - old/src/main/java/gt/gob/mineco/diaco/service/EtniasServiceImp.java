package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.EtniaRepository;
import gt.gob.mineco.diaco.model.DiacoEtnia;

@Stateless
public class EtniasServiceImp {
	@Inject
	EtniaRepository etniaDao;
	
	public List<DiacoEtnia> getAll() {
		return etniaDao.findAll();
	}
}
