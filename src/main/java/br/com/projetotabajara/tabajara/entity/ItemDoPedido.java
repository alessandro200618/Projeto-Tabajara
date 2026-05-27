package br.com.projetotabajara.tabajara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    private Integer quantidade;

    private Double preco;

    private Double subtotal;
    
    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idPedido_fk")
    private Pedido pedido;

    //Método para calcular subtotal
    public Double calcularSubtotal() {
        if (quantidade == null || preco == null) {
            return 0.0;
        }
        return quantidade * preco;
    }
    //Método para calcular subtotal
    public void atualizarSubtotal() {
        this.subtotal = calcularSubtotal();
    }
}
