package br.com.api.fatec.apifatec.entities;

import br.com.api.fatec.apifatec.shared.enums.PedidoVendaStatusEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido_venda")
public class PedidoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "emissao")
    private LocalDate emissao;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private PedidoVendaStatusEnum status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoVenda", orphanRemoval = true)
    private List<PedidoVendaItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PedidoVendaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PedidoVendaStatusEnum status) {
        this.status = status;
    }

    public List<PedidoVendaItem> getItems() {
        return items;
    }

//    public void setItems(List<PedidoVendaItem> items) {
//        this.items = items;
//    }

    public void addItem(PedidoVendaItem item) {
        this.items.add(item);
        item.setPedidoVenda(this);
    }

    public void setTotal() {
        this.total = this.items.stream()
                .map(PedidoVendaItem::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
