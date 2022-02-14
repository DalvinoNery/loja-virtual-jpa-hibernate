package dao;

import modelo.Produto;

import javax.persistence.EntityManager;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager manager){
        this.em =em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
}