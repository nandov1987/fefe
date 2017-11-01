package br.unisantos.bean;


import br.unisantos.dao.ClienteDAO;
import br.unisantos.entidade.Cliente;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ciro
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean extends CrudBean<Cliente, ClienteDAO> implements Serializable {

    private ClienteDAO clienteDAO;
    
    @Override
    public ClienteDAO getDAO() {
        if(clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }

    @Override
    public Cliente criarNovaEntidade() {
        return new Cliente();
    }

    

}
