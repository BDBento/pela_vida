package com.pela_vida.resources;

import com.pela_vida.entidade.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<User> listar() {
        return User.listAll();
    }

    @POST
    @Transactional
    public Response criar(User user) {
        user.persist();
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, User userAtualizado) {
        User entity = User.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }

        entity.nome = userAtualizado.nome; // Exemplo de atualização de campos
        // Atualize outros campos conforme necessário

        entity.persist();
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        User entity = User.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }

        entity.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
