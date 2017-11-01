package br.unisantos.dao;

import br.unisantos.entidade.Login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LoginDAO extends DAO implements CrudDAO<Login> {

    public Login autenticar(Login l) {
        List<Login> logins = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(c) from Login as c");
            logins = q.getResultList();

            for (Login i : logins) {
                if(l.getUser() == i.getUser()){
                    if(l.getPassword() == i.getPassword()){
                        return i;
                    }
                }
            }

        } finally {
            em.close();
        }
        return null;
    }
    
    @Override
    public List<Login> exibir() {
        EntityManager em = getEntityManager();

        try {
            Query q = em.createQuery("select object(c) from Login as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void salvar(Login login) {
         EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Login l = em.find(Login.class, login.getId());
            l.setUser(login.getUser());
            l.setPassword(login.getPassword());          
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Login login) {
         EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Login l = em.find(Login.class, login.getId());
            em.remove(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    

}
