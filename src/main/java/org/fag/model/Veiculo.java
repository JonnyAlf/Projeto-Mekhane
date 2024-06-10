
package org.fag.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVeiculo;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fabricacao")
    private String fabricacao;

    @Column(name = "marca")
    private String marca;

    @Column(name = "placa")
    private String placa;

    @Column(name = "cor")
    private String cor;

    @OneToMany(mappedBy = "veiculo")
    @JsonIgnore
    private List<Montagem> montagens;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    // Getters and Setters
    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(String fabricacao) {
        this.fabricacao = fabricacao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa Pessoa) {
        this.pessoa = Pessoa;
    }

    public List<Montagem> getMontagens() {
        return montagens;
    }

    public void setMontagens(List<Montagem> montagens) {
        this.montagens = montagens;
    }


}
