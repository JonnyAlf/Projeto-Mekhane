package org.fag.model;

import java.time.LocalTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservico")
    private int idServico;
    
    @Column (name = "descricao")
    private String descricao;

    @Column (name = "valor")
    private double valor;

    @Column (name = "tiposervico")
    private String tipoServico;
    
   @Column(name = "tempo_servico")
    private LocalTime tempoServico;


public int getIdServico() {
    return idServico;
}

public void setIdServico(int idServico) {
    this.idServico = idServico;
}

public String getDescricao() {
    return descricao;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}

public double getValor() {
    return valor;
}

public void setValor(double valor) {
    this.valor = valor;
}

public String getTipoServico() {
    return tipoServico;
}

public void setTipoServico(String tipoServico) {
    this.tipoServico = tipoServico;
}

public LocalTime getTempoServico() {
    return tempoServico;
}

public void setTempoServico(LocalTime tempoServico) {
    this.tempoServico = tempoServico;
}

}
