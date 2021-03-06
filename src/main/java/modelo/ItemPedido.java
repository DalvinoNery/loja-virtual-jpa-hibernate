package modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitatio;
    private Integer quantidade;
    @ManyToOne (fetch = FetchType.LAZY)
    private Pedido pedido;

    @ManyToOne (fetch = FetchType.LAZY)
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitatio = pedido.getValorTotal();
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitatio() {
        return precoUnitatio;
    }

    public void setPrecoUnitatio(BigDecimal precoUnitatio) {
        this.precoUnitatio = precoUnitatio;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getValor(){
        return precoUnitatio.multiply(new BigDecimal((quantidade)));
    }
}
