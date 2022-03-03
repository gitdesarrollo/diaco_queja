package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import gt.gob.mineco.diaco.dao.bitacoraPdfDao;
import gt.gob.mineco.diaco.model.bitacoraPdf;
import gt.gob.mineco.diaco.util.FormBitacoraPdf;
import gt.gob.mineco.diaco.util.ResponseRs;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class bitacoraPdfServiceImp implements bitacoraPdfService{
    
    @Inject
    private bitacoraPdfDao bitacoraDao;

    @Override
    public ResponseRs addBitacoraPdf(FormBitacoraPdf bitacoraPdf){
        ResponseRs response = new ResponseRs();
        UserTransaction transaction = null;
        try {
            transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();

            bitacoraPdf bitacora = new bitacoraPdf();
            bitacora.setNo_queja(bitacoraPdf.getNo_queja());
            bitacora.setNombre_archivo(bitacoraPdf.getNombre_archivo());
            bitacora.setFormato(bitacoraPdf.getFormato());
            bitacora.setPath(bitacoraPdf.getPath());

            bitacoraDao.saveBitacora(bitacora);
            response.setCode(0L);
            response.setReason("ok");
            transaction.commit();
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("Error");
            try {
                transaction.rollback();
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public ResponseRs listBitacoraByName(String queja){
        ResponseRs response = new ResponseRs();
        try{
            List<bitacoraPdf> parametros = bitacoraDao.findByNamePdf(queja);
            response.setCode(200L);
            response.setValue(parametros);
        } catch(Exception e){
            e.printStackTrace();
            response.setCode(1L);
            response.setReason("No se encontro el Parametro Solicitado");
        }

        return response;
    }

    // @Override
    // public bitacoraPdf listBitacoraByQueja(String name){
    //     return bitacoraDao.findByNameQueja(name);
    // }

    @Override
    public Long listBitacoraByQuejaCount(String name){
        return bitacoraDao.countNameQueja(name);
    }
}
