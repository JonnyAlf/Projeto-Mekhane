package org.fag.resource;

import org.fag.model.Servico;
import org.fag.service.ServicoService;

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

import java.util.List;

@Path("/servico")
public class ServicoResource {

    @Inject
    ServicoService servicoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Servico> getServico(@QueryParam("descricao") String descricao) {
        return servicoService.buscarPorServico(descricao);
    }

    @Path("/criar")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarServico(Servico servico) {
        Servico servicoGravar = new Servico();

        servicoGravar.setDescricao(servico.getDescricao());
        servicoGravar.setValor(servico.getValor());
        servicoGravar.setTipoServico(servico.getTipoServico());
        servicoGravar.setTempoServico(servico.getTempoServico());

        servicoGravar.persist();

        return Response.ok().entity(servicoGravar).build();
    }
}