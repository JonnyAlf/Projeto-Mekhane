package org.fag.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orcamento")
public class OrcamentoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idorcamento;

    @Column(name = "valortotal")
    private double valorTotal;

    @OneToMany(mappedBy = "orcamento")
    private List<Pedido> pedidos;

    public int getIdorcamento() {
        return idorcamento;
    }

    public void setIdorcamento(int idorcamento) {
        this.idorcamento = idorcamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
