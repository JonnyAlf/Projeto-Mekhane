package org.fag.service;

import org.fag.model.OrcamentoDTO;
import org.fag.model.Pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class OrcamentoService {

    private static final Logger LOGGER = Logger.getLogger(OrcamentoService.class);

    @Inject
    EntityManager entityManager;

    @Transactional
    public void processarOrcamento(OrcamentoDTO orcamentoDTO) {
        LOGGER.info("Iniciando o processamento do orçamento");
        try {
            entityManager.persist(orcamentoDTO);
            LOGGER.info("Orçamento persistido com sucesso: " + orcamentoDTO.getIdorcamento());

            // Iterar sobre os pedidos e inserir utilizando JPQL
            for (Pedido pedido : orcamentoDTO.getPedidos()) {
                pedido.setOrcamento(orcamentoDTO); // Configurar a associação bidirecional

                // Construir a consulta JPQL para inserir os dados
                String jpql = "INSERT INTO Pedido(confirmarPedido, horasServico, peca.idPeca, "
                        + "servico.idServico, veiculo.idVeiculo, pessoa.idPessoa, orcamento) "
                        + "VALUES (:confirmarPedido, :horasServico, :idPeca, :idServico, :idVeiculo, :idPessoa, :orcamento)";

                // Executar a consulta JPQL
                entityManager.createQuery(jpql)
                        .setParameter("confirmarPedido", pedido.isConfirmarPedido())
                        .setParameter("horasServico", pedido.getHorasServico())
                        .setParameter("idPeca", pedido.getPeca().getIdPeca())
                        .setParameter("idServico", pedido.getServico().getIdServico())
                        .setParameter("idVeiculo", pedido.getVeiculo().getIdVeiculo())
                        .setParameter("idPessoa", pedido.getPessoa().getIdPessoa())
                        .setParameter("orcamento", orcamentoDTO) // Passar o objeto OrcamentoDTO diretamente
                        .executeUpdate();

                LOGGER.info("Pedido persistido com sucesso: " + pedido.getIdPedido());
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao processar o orçamento", e);
            throw new RuntimeException("Erro ao processar o orçamento", e);
        }
        LOGGER.info("Processamento do orçamento concluído");
    }
}