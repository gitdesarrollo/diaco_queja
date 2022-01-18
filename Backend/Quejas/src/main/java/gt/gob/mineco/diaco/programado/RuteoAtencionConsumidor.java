/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.programado;

import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.dao.TipoRepository;
import javax.inject.Inject;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import java.util.List;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.model.TipoProveedor;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
/*@Stateless
public class RuteoAtencionConsumidor {

    @Inject
    private TipoAreaComunServiceImp AreaComun;    

    public void run() throws InterruptedException{
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            AreaComun.SaveTareaProgramada(1,new Date(),"test string");
            System.out.println("Running...task1");
        };

        ses.schedule(task1, 20, TimeUnit.SECONDS);
        
    }
  }
*/
@Stateless
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class RuteoAtencionConsumidor {

    @Inject
    private TipoRepository tipoDao;
    @Inject
    private TipoAreaComunService AreaComun;
    
    
    //@Schedule(hour="*/1", minute="0", second="0", persistent=false)
    //@Schedule(hour="*", minute="*/5", second="0", persistent=false)
    @Schedule(hour = "3", minute = "0", second = "0", persistent = false)
    public void RutearQuejas140() { //140 pendiente de ruteo
        AreaComun.RuteoQuejas140();
    }

    @Schedule(hour = "3", minute = "20", second = "0", persistent = false)
    public void EmailQuejasExpiradas() { //email quejas ya expiradas
        AreaComun.EmailQuejasExpiradas();
    }
    
    @Schedule(hour = "3", minute = "10", second = "0", persistent = false)
    public void EmailAlertaQuejasExpiradas() { //email alerta quejas por expirar
        AreaComun.EmailAlertaQuejasExpiradas();
    }
    
    @Schedule(hour="*/2", minute="0", second="0", persistent=false)
    //@Schedule(hour="*", minute="*/3", second="0", persistent=false)
    public void ReenvioCorreos() { 
        AreaComun.ReenvioCorreos();
    }
        //tarea programada
        /**/

    
    
}