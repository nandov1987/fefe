package br.unisantos.dao;

import br.unisantos.entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteDAO extends DAO implements CrudDAO<Cliente>{

    @Override
    public void salvar(Cliente cliente) {
        EntityManager em = getEntityManager();

        try {

            if (cliente.getId() == null) {
                em.getTransaction().begin();
                em.persist(cliente);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                Cliente c = em.find(Cliente.class, cliente.getId());
                c.setNome(cliente.getNome());
                c.setSobrenome(cliente.getSobrenome());
                c.setCpf(cliente.getCpf());
                c.setEndereco(cliente.getEndereco());
                c.setBairro(cliente.getBairro());
                c.setComplemento(cliente.getComplemento());
                c.setCep(cliente.getCep());
                c.setCidade(cliente.getCidade());
                c.setEstado(cliente.getEstado());
                c.setEmail(cliente.getEmail());
                c.setTelefone(cliente.getTelefone());
                c.setCelular(cliente.getCelular());
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Cliente getById(int idCliente) {
        EntityManager em = getEntityManager();
        return em.find(Cliente.class, idCliente);
    }

    public void update(Cliente cliente) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, cliente.getId());
            c.setNome(cliente.getNome());
            c.setSobrenome(cliente.getSobrenome());
            c.setCpf(cliente.getCpf());
            c.setEndereco(cliente.getEndereco());
            c.setBairro(cliente.getBairro());
            c.setComplemento(cliente.getComplemento());
            c.setCep(cliente.getCep());
            c.setCidade(cliente.getCidade());
            c.setEstado(cliente.getEstado());
            c.setEmail(cliente.getEmail());
            c.setTelefone(cliente.getTelefone());
            c.setCelular(cliente.getCelular());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Cliente cliente) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, cliente.getId());
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> exibir() {
        EntityManager em = getEntityManager();

        try {
            Query q = em.createQuery("select object(c) from Cliente as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
