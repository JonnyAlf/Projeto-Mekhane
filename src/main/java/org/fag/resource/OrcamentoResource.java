package org.fag.resource;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.inject.Inject;
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
import org.fag.service.OrcamentoService;

@Path("/orcamento")
public class OrcamentoResource {

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarOrcamento(OrcamentoDTO orcamentoDTO) {
        try {
            orcamentoService.criarOrcamento(orcamentoDTO);
            JsonObjectBuilder builder = Json.createObjectBuilder()
                    .add("message", "Orçamento criado com sucesso");
            jakarta.json.JsonObject responseJson = builder.build();
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Json.createObjectBuilder()
                            .add("error", "Erro ao criar o orçamento: " + e.getMessage())
                            .build())
                    .build();
        }
    }
}
