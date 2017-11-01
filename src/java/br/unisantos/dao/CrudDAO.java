package br.unisantos.dao;

import java.util.List;


public interface CrudDAO<E>  { //"E" representa minha entidade
    
    public void salvar(E entidade);
    public void delete(E entidade);
    public List<E> exibir();
}
