package dao;

import modelo.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em){
        this.em =em;
    }

    public void cadastrar(Cliente cliente){
        this.em.persist(cliente);
    }

    public Cliente buscarPorId(Long id){
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listar(){
        String jpql  ="SELECT p FROM Cliente p";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

}
