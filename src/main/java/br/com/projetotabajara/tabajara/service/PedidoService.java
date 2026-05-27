package br.com.projetotabajara.tabajara.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.ItemDoPedido;
import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.entity.Usuario;
import br.com.projetotabajara.tabajara.repository.PedidoRepository;
import br.com.projetotabajara.tabajara.repository.ProdutoRepository;
import br.com.projetotabajara.tabajara.repository.UsuarioRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getUsuario() == null || pedido.getUsuario().getIdUsuario() == null) {
            throw new RuntimeException("Usuario do pedido nao informado");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new RuntimeException("Pedido sem itens");
        }

        Usuario usuario = usuarioRepository.findById(pedido.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        pedido.setUsuario(usuario);
        pedido.setDataPedido(LocalDate.now());

        for (ItemDoPedido item : pedido.getItens()) {
            if (item.getProduto() == null || item.getProduto().getIdProduto() == null) {
                throw new RuntimeException("Produto do item nao informado");
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new RuntimeException("Quantidade invalida para o item do pedido");
            }

            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

            if (produto.getValorProduto() == null) {
                throw new RuntimeException("Produto sem valor cadastrado");
            }

            item.setProduto(produto);
            item.setPreco(produto.getValorProduto());
            item.atualizarSubtotal();
            item.setPedido(pedido);
        }

        pedido.atualizarTotal();
        return pedidoRepository.save(pedido);
    }
}
