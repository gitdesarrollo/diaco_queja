package gt.gob.mineco.diaco.service;

import java.util.List;

import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.util.FormConsumidorQuery;
import gt.gob.mineco.diaco.util.ResponseRs;

public interface AlertasNotificacionService {

    public ResponseRs ExpiredNotificationAlert();

    public void checkQueja();

    public List<TipoConsumidor> getDataByQueja(FormConsumidorQuery queja);
    
}
