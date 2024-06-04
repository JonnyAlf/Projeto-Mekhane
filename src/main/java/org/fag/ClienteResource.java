package org.fag;

import org.fag.model.Pessoa;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/clientes")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes(@QueryParam("nome") String nome) {
        List<Pessoa> pessoas = Pessoa.buscarPorNome(nome);
        List<String> nomes = pessoas.stream().map(Pessoa::getNome).collect(Collectors.toList());
        return Response.ok(nomes).build();
    }
}
