package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.ProveedorComunRepository;
import gt.gob.mineco.diaco.model.DiacoProveedorComun;

@Stateless
public class ProveedoresComunesServiceImp {
	@Inject
	ProveedorComunRepository proveedorComunDao;
	
	public List<DiacoProveedorComun> getAll() {
		return proveedorComunDao.findAll();
	}
}
