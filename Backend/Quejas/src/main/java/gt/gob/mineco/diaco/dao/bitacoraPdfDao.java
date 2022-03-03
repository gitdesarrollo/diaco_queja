package gt.gob.mineco.diaco.dao;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;

import gt.gob.mineco.diaco.model.bitacoraPdf;

@ApplicationScoped
public class bitacoraPdfDao {
    @PersistenceContext
    private EntityManager em;


    public void saveBitacora(bitacoraPdf bitacoraPdf) throws SystemException{

        if (this.countNameQueja(bitacoraPdf.getNombre_archivo()) == 0) {
            em.persist(bitacoraPdf);
        };
    }

    public List<bitacoraPdf> findByNamePdf(String no_queja){
        this.em.getEntityManagerFactory().getCache().evict(bitacoraPdf.class);
        TypedQuery<bitacoraPdf> Rs =  em.createNamedQuery("bitacora.findById",bitacoraPdf.class).setParameter("no_queja", no_queja);
        return Rs.getResultList();
    }

    public bitacoraPdf findByNameQueja(String name){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<bitacoraPdf> criteria = cb.createQuery(bitacoraPdf.class);
            Root<bitacoraPdf> bitacoraQuery = criteria.from(bitacoraPdf.class);
            criteria.select(bitacoraQuery)
                .where(cb.equal(bitacoraQuery.get("nombre_archivo"), name))
                .orderBy(cb.asc(bitacoraQuery.get("id")));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }

    public Long countNameQueja(String name){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<bitacoraPdf> bitacoraQuery = criteria.from(bitacoraPdf.class);
        Predicate restrictions = null;

        restrictions  = builder.equal(bitacoraQuery.get("nombre_archivo"), name);

        criteria = criteria.select(builder.construct(Long.class, builder.count(bitacoraQuery.get("id"))));

        if(restrictions != null) {
        	criteria = criteria.where(restrictions);
        }

        

        return em.createQuery(criteria).getSingleResult();
    }

    public bitacoraPdf findBitacoraByQueja(String no_queja){
        em.getEntityManagerFactory().getCache().evict(bitacoraPdf.class);
        TypedQuery<bitacoraPdf> Rs =  em.createNamedQuery("bitacora.findByName",bitacoraPdf.class).setParameter("nombre_archivo", no_queja);
        return Rs.getSingleResult();
    }



}
