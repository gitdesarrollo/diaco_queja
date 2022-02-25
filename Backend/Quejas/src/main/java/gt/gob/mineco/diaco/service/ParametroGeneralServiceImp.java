package gt.gob.mineco.diaco.service;

import javax.inject.Inject;

import gt.gob.mineco.diaco.dao.ParametrosConfig;
import gt.gob.mineco.diaco.model.ParametroGeneral;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.util.List;

/**
 *
 * @author jjolon
 */
public class ParametroGeneralServiceImp implements ParametroGeneralService{

    @Inject
    private ParametrosConfig parametroDao;


    @Override
    public ResponseRs getParametroById(Integer id){
        ResponseRs response = new ResponseRs();
        try {

            ParametroGeneral Parametros = parametroDao.findByIdParametro(id);

            response.setCode(200L);
            response.setValue(Parametros.getValor());

            
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(1L);
            response.setReason("error en parametro");
        }

        return response;
    }

    @Override
    public ResponseRs getParametroByName(String name){
        ResponseRs response = new ResponseRs();
        try{
            List<ParametroGeneral> parametros = parametroDao.findParametroByName(name);
            response.setCode(200L);
            response.setValue(parametros.get(0).getValor());
        } catch(Exception e){
            e.printStackTrace();
            response.setCode(1L);
            response.setReason("No se encontro el Parametro Solicitado");
        }

        return response;
    }
}
