package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroPedido {
    public static void main(String[] args) {
        popularBancoDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        Cliente cliente = clienteDAO.buscarPorId(1L);
        Produto produto = produtoDAO.buscarPorId(1L);
        Pedido pedido = new Pedido(cliente);

        em.getTransaction().begin();

        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        pedidoDAO.cadastrar(pedido);


        em.getTransaction().commit();

        BigDecimal total = pedidoDAO.valorTotalVendido();

        System.out.println("Total: "+total);



    }

    private static void popularBancoDados() {
        Categoria celulares = new Categoria("celulares");
        Cliente cliente = new Cliente("Rodrigo", "123456");
        Produto celular = new Produto("LG K51", "Smartphone básico", new BigDecimal(1000), celulares);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
