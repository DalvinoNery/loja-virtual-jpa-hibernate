package dao;

import modelo.Categoria;
import modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em){
        this.em =em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }
    public void remover(Categoria categoria){
        categoria = this.em.merge(categoria);// garantindo que o objeto vai ficar com o status managed antes da remoção.
        this.em.remove(categoria);
    }
}
