package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.ActividadEconomicaRepository;
import gt.gob.mineco.diaco.model.DiacoActividadEconomica;

@Stateless
public class ActividadesEconomicasServiceImp {
	@Inject
	ActividadEconomicaRepository actividadEconomicaDao;
	
	public List<DiacoActividadEconomica> getAll() {
		return actividadEconomicaDao.findAll();
	}
}
