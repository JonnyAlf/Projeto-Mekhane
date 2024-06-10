package org.fag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoatipo")
public class PessoaTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPessoaTipo;

    @ManyToOne
    @JoinColumn(name = "idpessoa")
    @JsonIgnore
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idtipo")
    @JsonIgnore
    private Tipo tipo;

    public int getIdPessoaTipo() {
        return idPessoaTipo;
    }

    public void setIdPessoaTipo(int idPessoaTipo) {
        this.idPessoaTipo = idPessoaTipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    
}
