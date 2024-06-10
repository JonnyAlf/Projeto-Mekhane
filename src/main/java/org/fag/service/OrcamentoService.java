package org.fag.service;

import org.fag.model.OrcamentoDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrcamentoService {

    @Inject
    EntityManager entityManager;
    
    @Transactional
    public void criarOrcamento(OrcamentoDTO orcamentoDTO) {

        entityManager.persist(orcamentoDTO);

        for (var pedido : orcamentoDTO.getPedidos()) {
            pedido.setOrcamento(orcamentoDTO);
            entityManager.persist(pedido);
        }
    }

}
