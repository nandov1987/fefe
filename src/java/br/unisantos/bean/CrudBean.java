package br.unisantos.bean;

import br.unisantos.dao.CrudDAO;
import br.unisantos.util.SessionUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDAO> {

    private String estadoTela = "buscar"; //Inserir, Editar e Buscar
    private E entidade; // Usada para trafegar os dados
    private List<E> entidades;

    public void novo() {
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }

    public void salvar() {
        try {
            getDAO().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
        } catch (Exception ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    public void editar(E entidade) {
        this.entidade = entidade;
        mudarParaEdita();
    }

    public void deletar(E entidade) {
        try {

            getDAO().delete(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);

        } catch (Exception ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    public void buscar() {
        if (isBusca() == false) {
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDAO().exibir();
            if (entidades == null || entidades.size() < 1) {
                adicionarMensagem("Não tem nada cadastrado", FacesMessage.SEVERITY_WARN);

            }
        } catch (Exception ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    public String validar(E entidade) {
        entidades = getDAO().exibir();
        for (E i : entidades) {
            if(entidades.equals(entidade)){
                Object b = new Object();
                SessionUtil.setParam("USUARIOLogado", b);
                return "/principal";
            }
        }
         return "/erro";
    }
  

    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    //--------------------------------------------------------------------------
    //Responsavel por criar os métodos nas classes bean 
    public abstract D getDAO();

    public abstract E criarNovaEntidade();

    //--------------------------------------------------------------------------
    // Metodos para controle da tela
    public boolean isInseri() {
        return "inserir".equals(estadoTela);
    }

    public boolean isEdita() {
        return "editar".equals(estadoTela);
    }

    public boolean isBusca() {
        return "buscar".equals(estadoTela);
    }

    //--------------------------------------------------------------------------
    public void mudarParaInseri() {
        estadoTela = "inserir";
    }

    public void mudarParaEdita() {
        estadoTela = "editar";
    }

    public void mudarParaBusca() {
        estadoTela = "buscar";
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

}
