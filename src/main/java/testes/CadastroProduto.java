package testes;

import dao.ProdutoDAO;
import modelo.Categoria;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroProduto {


    public static void main(String[] args) {
        Produto celular = new Produto("S22", "Smartphone Top", new BigDecimal(2800), Categoria.CELULARES);
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);

        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }
}
