package org.fag.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.fag.model.Pessoa;

import java.util.List;

@ApplicationScoped
public class PessoaService {

    @Inject
    EntityManager entityManager;

    public List<Pessoa> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Pessoa p WHERE LOWER(p.nome) LIKE LOWER (:nome)";
        TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
