package org.fag.service;

import java.util.List;

import org.fag.model.Montagem;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class MontagemService {

    @Inject
    EntityManager entityManager;
    
    public List<Montagem> buscarPorNomeDaPecaEIdDoVeiculo(String descricao, int idVeiculo) {
        String jpql;
        if (descricao != null) {
            jpql = "SELECT m FROM Montagem m " +
                   "JOIN m.peca p " +
                   "JOIN m.veiculo v " +
                   "WHERE p.descricao LIKE :descricao AND v.idVeiculo = :idVeiculo";
            return entityManager.createQuery(jpql, Montagem.class)
                                .setParameter("descricao", "%" + descricao + "%")
                                .setParameter("idVeiculo", idVeiculo)
                                .getResultList();
        } else {
            jpql = "SELECT m FROM Montagem m " +
       "JOIN m.veiculo v " +
       "WHERE v.idVeiculo = :idVeiculo";
            return entityManager.createQuery(jpql, Montagem.class)
                                .setParameter("idVeiculo", idVeiculo)
                                .getResultList();
        }
    }
    
}
