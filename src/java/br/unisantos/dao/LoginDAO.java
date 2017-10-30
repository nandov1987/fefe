package br.unisantos.dao;

import br.unisantos.entidade.Login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LoginDAO extends DAO {

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
    
    public List<Login> exibir() {
        EntityManager em = getEntityManager();

        try {
            Query q = em.createQuery("select object(c) from Login as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    

}
