package org.fag.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "rg")
    private String rg;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "idendereco")
    private int idEndereco;

    @OneToMany(mappedBy = "pessoa")
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "pessoa")
    @JsonIgnore
    private List<PessoaTipo> pessoaTipos;

    @OneToMany(mappedBy = "pessoa")
    private List<Pedido> Pedido;
    // Getters and Setters
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public List<PessoaTipo> getPessoaTipos() {
        return pessoaTipos;
    }

    public void setPessoaTipos(List<PessoaTipo> pessoaTipos) {
        this.pessoaTipos = pessoaTipos;
    }

    public List<Pedido> getPedido() {
        return Pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        Pedido = pedido;
    }
}