package org.fag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "confirmar_pedido")
    private boolean confirmarPedido;

    @Column(name = "horas_servico")
    private int horasServico;

    @ManyToOne
    @JoinColumn(name = "idpeca")
    private Peca peca;

    @ManyToOne
    @JoinColumn(name = "idservico")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "idveiculo")
    private Veiculo veiculo;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idorcamento") 
    private OrcamentoDTO orcamento;

    public int getIdPedido() {
      return idPedido;
    }

    public void setIdPedido(int idPedido) {
      this.idPedido = idPedido;
    }

    public boolean isConfirmarPedido() {
      return confirmarPedido;
    }

    public void setConfirmarPedido(boolean confirmarPedido) {
      this.confirmarPedido = confirmarPedido;
    }

    public int getHorasServico() {
      return horasServico;
    }

    public void setHorasServico(int horasServico) {
      this.horasServico = horasServico;
    }

    public Peca getPeca() {
      return peca;
    }

    public void setPeca(Peca peca) {
      this.peca = peca;
    }

    public Servico getServico() {
      return servico;
    }

    public void setServico(Servico servico) {
      this.servico = servico;
    }

    public Veiculo getVeiculo() {
      return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
      this.veiculo = veiculo;
    }

    public Pessoa getPessoa() {
      return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
      this.pessoa = pessoa;
    }

    public OrcamentoDTO getOrcamento() {
      return orcamento;
    }

    public void setOrcamento(OrcamentoDTO orcamento) {
      this.orcamento = orcamento;
    }

    
}
