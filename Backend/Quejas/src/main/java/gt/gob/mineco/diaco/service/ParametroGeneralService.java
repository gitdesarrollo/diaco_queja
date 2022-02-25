package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.util.ResponseRs;

/**
 *
 * @author jjolon
 */
public interface ParametroGeneralService {

    public ResponseRs getParametroById(Integer id);

    public ResponseRs getParametroByName(String name);

}
