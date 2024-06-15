package org.fag.model;

import java.time.LocalTime;

public class TemporariaGerarOrdem {

    private double valorTotal;
    private boolean confirmarPedido;
    private LocalTime horasServico;
    private int idPeca;
    private int idServico;
    private int idVeiculo;
    private int idPessoa;

    
    public TemporariaGerarOrdem(double valorTotal, boolean confirmarPedido, LocalTime horasServico, int idPeca, int idServico,
            int idVeiculo, int idPessoa) {
        this.valorTotal = valorTotal;
        this.confirmarPedido = confirmarPedido;
        this.horasServico = horasServico;
        this.idPeca = idPeca;
        this.idServico = idServico;
        this.idVeiculo = idVeiculo;
        this.idPessoa = idPessoa;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public boolean isConfirmarPedido() {
        return confirmarPedido;
    }
    public void setConfirmarPedido(boolean confirmarPedido) {
        this.confirmarPedido = confirmarPedido;
    }
    public LocalTime getHorasServico() {
        return horasServico;
    }
    public void setHorasServico(LocalTime horasServico) {
        this.horasServico = horasServico;
    }
    public int getIdPeca() {
        return idPeca;
    }
    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }
    public int getIdServico() {
        return idServico;
    }
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    


}
