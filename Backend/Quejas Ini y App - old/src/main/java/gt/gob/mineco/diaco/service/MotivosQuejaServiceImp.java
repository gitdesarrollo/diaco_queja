package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.MotivoQuejaRepository;
import gt.gob.mineco.diaco.model.DiacoMotivoQueja;

@Stateless
public class MotivosQuejaServiceImp {
	@Inject
	MotivoQuejaRepository motivoQuejaDao;
	
	public List<DiacoMotivoQueja> getAll() {
		return motivoQuejaDao.findAll();
	}
}
