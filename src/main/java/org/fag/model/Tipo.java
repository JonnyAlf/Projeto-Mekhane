package org.fag.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "tipo")
    @JsonIgnore
    private List<PessoaTipo> pessoaTipos;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PessoaTipo> getPessoaTipos() {
        return pessoaTipos;
    }

    public void setPessoaTipos(List<PessoaTipo> pessoaTipos) {
        this.pessoaTipos = pessoaTipos;
    }

}
