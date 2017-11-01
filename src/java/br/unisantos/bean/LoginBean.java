package br.unisantos.bean;

import br.unisantos.dao.LoginDAO;
import br.unisantos.entidade.Login;
import br.unisantos.util.SessionUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean extends CrudBean<Login, LoginDAO> implements Serializable {

    private LoginDAO loginDAO;

    @Override
    public LoginDAO getDAO() {
        if (loginDAO == null) {
            loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

    @Override
    public Login criarNovaEntidade() {
        return new Login();
    }
    
    public String validar(){
        if(loginDAO.autenticar(l))
    }

}
