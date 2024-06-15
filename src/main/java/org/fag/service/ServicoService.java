package org.fag.service;

import java.util.List;

import org.fag.model.Servico;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

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

    @Transactional
    public void criarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O objeto Servico não pode ser nulo.");
        }
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição do serviço é obrigatória.");
        }
        Servico servicoGravar = new Servico();
        
        servicoGravar.setDescricao(servico.getDescricao());
        servicoGravar.setValor(servico.getValor());
        servicoGravar.setTipoServico(servico.getTipoServico());
        servicoGravar.setTempoServico(servico.getTempoServico());

        entityManager.persist(servicoGravar);
    }
}