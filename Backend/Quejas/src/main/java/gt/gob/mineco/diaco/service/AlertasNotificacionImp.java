package gt.gob.mineco.diaco.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import gt.gob.mineco.diaco.dao.TipoRepository;
import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.model.TipoUsuario_Conf;
import gt.gob.mineco.diaco.util.FormConsumidorQuery;
import gt.gob.mineco.diaco.util.ResponseRs;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AlertasNotificacionImp implements AlertasNotificacionService{

    @Inject
    private TipoRepository tipoDao;
    
    String ListQuejas = "";



    private TipoUsuario_Conf JefePorUnidad(Integer id_flujo) {
        TipoUsuario_Conf vuser = tipoDao.findByJefeUsuarioConf(id_flujo);
        if (vuser != null) {
            return vuser;
        } else {
            return null;
        }
    }

    public void checkQueja(){
        List<TipoQueja> lst_queja = tipoDao.findAllTiposQuejaPendienteRuteo();
        System.out.println("listado.." + lst_queja);
    }

    public List<TipoConsumidor> getDataByQueja(FormConsumidorQuery queja){
        return tipoDao.getIndoConsumidor(queja);
    }


    public ResponseRs ExpiredNotificationAlert(){
        ResponseRs resp = new ResponseRs();
        UserTransaction transaction = null;
        List<TipoQueja> lst_queja_atcon = tipoDao.findAllExpiradoAtConCorreo();
        TipoUsuario_Conf jefe_atcon = JefePorUnidad(1);
        Boolean error = false;


        System.out.println("listado quejas.." + lst_queja_atcon);

        // try {
            // transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            // transaction.begin();

            for(TipoQueja estado : lst_queja_atcon){
                if(ListQuejas.equals("")){
                    ListQuejas = estado.getQuejaNumero();
                }
                else{
                    ListQuejas = ListQuejas + ", " + estado.getQuejaNumero();
                }
                TipoQueja local = tipoDao.findByIdQueja(estado.getId_queja());
                local.setExpirado_mail(1);
                tipoDao.saveQueja(local);
            }
            if (!ListQuejas.equals("")) {
                String cuerpo = "Estimado Usuario, a continuaci&oacute;n le mostramos el listado de quejas que han sido expiradas el dia de hoy.<br><br>";
                cuerpo = cuerpo + ListQuejas;
                // String[] mailstring = GetEmailStringUsuario(jefe_atcon.getEmail());
                // boolean sendmail = saveEmailEnviar(mailstring, Constants.REG_DIACO_FUENTE_EMAIL_QUEJA_EXPIRADA_ATCON, cuerpo);
            }
			System.out.println("listado.." + ListQuejas);
            // transaction.commit();
        // } catch (Exception e) {
        //     //TODO: handle exception EmailQuejasExpiradas
        //     e.printStackTrace();
        //     error = true;
        //     try {
        //         transaction.rollback();
        //     } catch (Exception ee) {
        //         ee.printStackTrace();
        //     }
        // }

        return resp;

    }
    
}
