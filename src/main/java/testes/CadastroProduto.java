package testes;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import modelo.Categoria;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {


    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto produto = produtoDAO.buscarPorId(1L);
        System.out.println(produto.getDescricao());

        List<Produto> listaProdutos = produtoDAO.listarPorCategoria("celulares");
        listaProdutos.forEach(produto1 -> {
            System.out.println(produto1.getNome());
        });

        BigDecimal precoProduto = produtoDAO.bucarPrecoPorNome("LG K51");
        System.out.println("LG K51 = " + precoProduto);
     //   cadastrarProduto();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("celulares");

        Produto celular = new Produto("LG K51", "Smartphone básico", new BigDecimal(1000), celulares);
        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
