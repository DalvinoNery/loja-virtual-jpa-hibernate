package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PopularBanco {
    public static void main(String[] args) {
        popularBancoDados();

        EntityManager em = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        Pedido pedido  =pedidoDAO.buscarPorId(1L);
        System.out.println(pedido.getDataPedido());



    }

    private static void popularBancoDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("LG K51", "Smartphone básico", new BigDecimal(1000), celulares);
        Produto videogame = new Produto("Xbox One", "Aparelho branco", new BigDecimal(3000), videogames);
        Produto notebook = new Produto("Dell X", "Notebook top de linha", new BigDecimal(5000), informatica);

        Cliente cliente = new Cliente("Rodrigo", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(2, pedido, celular));
        pedido.adicionarItem(new ItemPedido(1, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);

        EntityManager em = JPAUtil.getEntityManager();
        pedido2.adicionarItem(new ItemPedido(1, pedido, notebook));

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(notebook);

        clienteDAO.cadastrar(cliente);

        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        em.getTransaction().commit();
        em.close();
    }
}
