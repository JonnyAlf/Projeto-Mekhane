package org.fag.resource;

import java.util.List;

import org.fag.model.Montagem;
import org.fag.service.MontagemService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/montagem")
public class MontagemResource {
    
    @Inject
    MontagemService montagemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Montagem> getPeca(@QueryParam("descricao") String descricao, @QueryParam ("idVeiculo") int idVeiculo){
        return montagemService.buscarPorNomeDaPecaEIdDoVeiculo(descricao, idVeiculo);
    }
}
