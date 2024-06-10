package org.fag.resource;

import org.fag.model.Pessoa;
import org.fag.service.PessoaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @Inject
    PessoaService pessoaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> getPessoas(@QueryParam("nome") String nome) {    
        return pessoaService.buscarPorNome(nome);
    }
}
