package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TesteCriteria {
    public static void main(String[] args) {
        popularBancoDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        produtoDAO.buscarPorParametrosCriteria("LG K51", null, null);




    }

    private static void popularBancoDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("LG K51", "Smartphone básico", new BigDecimal(1000), celulares);
        Produto videogame = new Produto("Xbox One", "Aparelho branco", new BigDecimal(3000), videogames);
        Produto notebook = new Produto("Dell X", "Notebook top de linha", new BigDecimal(5000), informatica);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(notebook);

        em.getTransaction().commit();
        em.close();
    }
}
