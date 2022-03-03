package gt.gob.mineco.diaco.service;


import java.util.List;

import gt.gob.mineco.diaco.model.bitacoraPdf;
import gt.gob.mineco.diaco.util.FormBitacoraPdf;
import gt.gob.mineco.diaco.util.ResponseRs;

public interface bitacoraPdfService {

    public ResponseRs addBitacoraPdf(FormBitacoraPdf bitacoraPdf);

    public ResponseRs listBitacoraByName(String queja);

    public Long listBitacoraByQuejaCount(String name);
    
}
