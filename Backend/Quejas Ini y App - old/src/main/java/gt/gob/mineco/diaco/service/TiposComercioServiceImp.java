package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.TipoComercioRepository;
import gt.gob.mineco.diaco.model.DiacoTipoComercio;

@Stateless
public class TiposComercioServiceImp {
	@Inject
	TipoComercioRepository tipoComercioDao;
	
	public List<DiacoTipoComercio> getAll() {
		return tipoComercioDao.findAll();
	}
	
	public List<DiacoTipoComercio> getByActividadEconomica(Integer idActividadEconomica) {
		return tipoComercioDao.findByActividadEconomica(idActividadEconomica);
	}
}
