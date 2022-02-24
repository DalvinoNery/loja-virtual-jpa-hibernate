package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em){
        this.em =em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> listar(){
        String jpql  ="SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> listarPorNome(String nome){
        String jpql  ="SELECT p FROM Produto p WHERE p.nome LIKE ?1";
        return em.createQuery(jpql, Produto.class).
                setParameter(1, nome).
                getResultList();
    }

    public List<Produto> listarPorCategoria(String nome){
        String jpql  ="SELECT p FROM Produto p WHERE p.categoria.nome LIKE ?1";
        return em.createQuery(jpql, Produto.class).
                setParameter(1, nome).
                getResultList();
    }

    public BigDecimal bucarPrecoPorNome(String nome){
        String jpql  ="SELECT p.preco FROM Produto p WHERE p.nome LIKE ?1";
        return em.createQuery(jpql, BigDecimal.class).
                setParameter(1, nome).
                getSingleResult();
    }


    public List <Produto> buscarPorParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();

        if(nome!=null && !nome.trim().isEmpty()){
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if(preco!=null ){
            filtros =  builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        if(dataCadastro!=null ){
            filtros =  builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }

        query.where(filtros);
        return em.createQuery(query).getResultList();
    }
}
