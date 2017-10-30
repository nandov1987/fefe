package br.unisantos.bean;

import br.unisantos.dao.LoginDAO;
import br.unisantos.entidade.Login;
import br.unisantos.util.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.enterprise.inject.New;
import javax.inject.Inject;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private @Inject
    @New
    Login login;
    private List<Login> logins = new ArrayList<>();

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public String validar() {

        logins = getL();
        for (Login i : logins) {
            if (i.equals(login)) {
                Object b = new Object();
                SessionUtil.setParam("USUARIOLogado", b);
                return "/principal";
            }
        }

        return "/erro";
    }

    public String registraSaida() {

        //REMOVER USUARIO DA SESSION
        SessionUtil.remove("USUARIOLogado");
        return "/index";
    }
    

    public List<Login> getL() {
        LoginDAO dao = new LoginDAO();
        try {
            logins = dao.exibir();
        } catch (Exception e) {

        }
        return logins;
    }

}
