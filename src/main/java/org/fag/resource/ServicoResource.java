package org.fag.resource;

import org.fag.model.Servico;
import org.fag.service.ServicoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
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
}

