/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dao;

import gt.gob.mineco.diaco.model.TipoTokenBlacklist;
import gt.gob.mineco.diaco.model.TipoUsuario;
import gt.gob.mineco.diaco.model.TipoUsuarioPerfil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mps
 */
public class SecurityManagerRepository {

    @PersistenceContext
    EntityManager em;

    public int getCountToken(Map<String, Object> params) {
        TypedQuery<TipoTokenBlacklist> query = em.createNamedQuery("TipoTokenBlacklist.countByToken", TipoTokenBlacklist.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        return query.getResultList().size();
    }
    
    public List<TipoUsuario> findByUsuario(Map<String, Object> params) {
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuario.class);
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuarioPerfil.class);
        TypedQuery<TipoUsuario> query = em.createNamedQuery("TipoUsuario.findByLogin", TipoUsuario.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        return query.getResultList();
    }
    
    public TipoUsuario findByUsuario1(Map<String, Object> params) {
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuario.class);
        this.em.getEntityManagerFactory().getCache().evict(TipoUsuarioPerfil.class);
        TypedQuery<TipoUsuario> query = em.createNamedQuery("TipoUsuario.findByIdUsuario", TipoUsuario.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        return query.getSingleResult();
    }
    
    public TipoTokenBlacklist createEntity(TipoTokenBlacklist blacklist) {
        em.persist(blacklist);
        return blacklist;
    }
    
    public TipoUsuario saveUsuario(TipoUsuario usuario) {
        em.persist(usuario);
        return usuario;
    }
    
    public TipoTokenBlacklist getExisteToken(Map<String, Object> params)  {
            TypedQuery<TipoTokenBlacklist> query = em.createNamedQuery("TipoTokenBlacklist.findByToken", TipoTokenBlacklist.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        try {
        return query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
    
    public TipoTokenBlacklist getExisteTokenUser(Map<String, Object> params)  {
            TypedQuery<TipoTokenBlacklist> query = em.createNamedQuery("TipoTokenBlacklist.findByIdUsuario", TipoTokenBlacklist.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        try {
        return query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
    
    public List<TipoTokenBlacklist> getExisteTokenList(Map<String, Object> params)  {
            TypedQuery<TipoTokenBlacklist> query = em.createNamedQuery("TipoTokenBlacklist.findByToken", TipoTokenBlacklist.class);
        if (params != null) {
            Iterator<String> paramsIterator = params.keySet().iterator();
            while (paramsIterator.hasNext()) {
                String paramKey = paramsIterator.next();
                query.setParameter(paramKey, params.get(paramKey));
            }
        }
        try {
        return query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    

}
