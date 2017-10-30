package br.unisantos.bean;


import br.unisantos.dao.ClienteDAO;
import br.unisantos.entidade.Cliente;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.enterprise.inject.New;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    private @Inject
    @New
    Cliente cliente;
    private List<Cliente> clientes;

    /**
     * Creates a new instance of ContatoBean
     */
    public ClienteBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> cliente) {
        this.clientes = clientes;
    }
    
    public String pag_novo() throws SQLException {
        cliente = new Cliente();
        return "/index";
    }

   /* public String pag_consultar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        clientes = cdao.listar();
        return "/consultar";
    }
*/
    public String alterar(Cliente c) {
        cliente = c;
        return "/cadastrarCliente";
    }

    public String novoContato() throws SQLException {
      
        ClienteDAO dao = new ClienteDAO();
        dao.salvar(cliente);
        cliente = new Cliente();
        return "/principal";
    }
    
    public List<Cliente> getC(){
        ClienteDAO dao = new ClienteDAO();
        try{
            clientes = dao.exibir();
        }catch(Exception e){
            
        }
        return clientes;
    }
    
    public void exluir(Cliente c) throws SQLException {
        
        ClienteDAO dao = new ClienteDAO();       
        dao.delete(c);
    }

    /*public void consultar() throws SQLException {
        ClienteDAO cdao = new ClienteDAO();
        clientes = cdao.consultar(cliente.getNome());
    }

    public void exluir(Cliente c) throws SQLException {
        
        clientes.remove(c);
        DAO dao = new DAO(Cliente.class);
        dao.excluir(c.getId());
    }


    public String alterar() throws SQLException {
       
        DAO dao = new DAO(Cliente.class);
        dao.alterar(cliente);
        cliente = new Cliente();
        return "/consultar";
    }

    public String cancelar() {
        cliente = new Cliente();
        return "/consultar";
    }*/

}
