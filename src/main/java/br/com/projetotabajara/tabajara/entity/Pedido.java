package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    private LocalDate dataPedido;
    private Double valorTotalPedido;
    @ManyToOne
    @JoinColumn(name = "idUsuario_fk")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDoPedido> itens;

    // Método para calcular o valor total
    public Double calcularTotal() {
        double total = 0.0;
        if (itens != null) {
            for (ItemDoPedido item : itens) {
                total += item.getSubtotal();
            }
        }
        return total;
    }
    //Método para atualizar o total do pedido
    public void atualizarTotal() {
        this.valorTotalPedido = calcularTotal();
    }
}
