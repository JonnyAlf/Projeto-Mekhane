package org.fag.resource;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static java.util.Objects.requireNonNull;

import org.fag.model.OrcamentoDTO;
import org.fag.model.Pedido;
import org.fag.service.OrcamentoService;
import org.jboss.logging.Logger;

@Path("/orcamento")
public class OrcamentoResource {

    private static final Logger LOGGER = Logger.getLogger(OrcamentoResource.class);

    private final Template orcamento;

    @Inject
    OrcamentoService orcamentoService;

    public OrcamentoResource(Template orcamento) {
        this.orcamento = requireNonNull(orcamento, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("name") String name) {
        return orcamento.data("name", name);
    }

    @POST
    @Transactional
    @Path("/gerar-ordem-servico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarOrcamento(OrcamentoDTO orcamentoDTO) {
        LOGGER.info("Recebendo requisição para criar orçamento");
        try {
            orcamentoService.processarOrcamento(orcamentoDTO);
            LOGGER.info("Orçamento criado com sucesso");
            return Response.ok(orcamentoDTO).build();
        } catch (Exception e) {
            LOGGER.error("Erro ao criar orçamento", e);

            // Configurar a associação bidirecional para todos os pedidos
            for (Pedido pedido : orcamentoDTO.getPedidos()) {
                pedido.setOrcamento(orcamentoDTO);
            }

            // Montar uma resposta de erro detalhada
            JsonObject responseJson = Json.createObjectBuilder()
                    .add("message", "Erro ao processar o pedido")
                    .add("detalhes", montarDetalhesErro(orcamentoDTO))
                    .build();

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseJson.toString())
                    .build();
        }
    }

    private JsonObject montarDetalhesErro(OrcamentoDTO orcamentoDTO) {
        JsonObjectBuilder detalhesBuilder = Json.createObjectBuilder();

        // Adicionar detalhes de cada pedido
        JsonArrayBuilder pedidosArrayBuilder = Json.createArrayBuilder();
        for (Pedido pedido : orcamentoDTO.getPedidos()) {
            JsonObjectBuilder pedidoBuilder = Json.createObjectBuilder()
                    .add("confirmarPedido", pedido.isConfirmarPedido())
                    .add("horasServico", pedido.getHorasServico().toString())
                    .add("idPeca", pedido.getPeca().getIdPeca())
                    .add("idServico", pedido.getServico().getIdServico())
                    .add("idVeiculo", pedido.getVeiculo().getIdVeiculo())
                    .add("idPessoa", pedido.getPessoa().getIdPessoa());

            pedidosArrayBuilder.add(pedidoBuilder);
        }

        detalhesBuilder.add("pedidos", pedidosArrayBuilder);

        // Adicionar outras informações relevantes
        detalhesBuilder.add("orcamento", orcamentoDTO.toString()); // Supondo que toString de OrcamentoDTO existe

        return detalhesBuilder.build();
    }
}