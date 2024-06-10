package org.fag.service;

import java.util.List;

import org.fag.model.Servico;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ServicoService {

    @Inject
    EntityManager entityManager;

    public List<Servico> buscarPorServico(String descricao) {
        String jpql = "SELECT s FROM Servico s WHERE s.descricao LIKE :descricao";
        TypedQuery<Servico> query = entityManager.createQuery(jpql, Servico.class);
        query.setParameter("descricao", "%" + descricao + "%");
        return query.getResultList();
    }
}
