package gt.gob.mineco.diaco.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import gt.gob.mineco.diaco.model.ParametroGeneral;

/**
 *
 * @author jjolon
 */


@ApplicationScoped
public class ParametrosConfig {

    @PersistenceContext
    private EntityManager em;


    public ParametroGeneral findByIdParametro(Integer id){
        this.em.getEntityManagerFactory().getCache().evict(ParametroGeneral.class);
        return em.find(ParametroGeneral.class, id);
    }

    public List<ParametroGeneral> findParametroByName(String name){
        this.em.getEntityManagerFactory().getCache().evict(ParametroGeneral.class);
        return em.createNamedQuery("ParametroGeneral.findByName").setParameter("name", name).getResultList();
    }
}
