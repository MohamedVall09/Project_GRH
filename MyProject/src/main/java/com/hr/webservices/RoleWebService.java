package com.hr.webservices;

import com.hr.data.RoleRepository;
import com.hr.models.Role;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/roles")
public class RoleWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getRoles() {
        return RoleRepository.getAllRoles();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajouterRole(Role role) {
        if (role == null || role.getNom().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nom du rôle requis").build();
        }
        RoleRepository.addRole(role.getNom());
        return Response.status(Response.Status.CREATED).entity("Rôle ajouté avec succès !").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response modifierRole(@PathParam("id") String id, Role role) {
        RoleRepository.updateRole(id, role.getNom());
        return Response.ok("Rôle modifié avec succès").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response supprimerRole(@PathParam("id") String id) {
        RoleRepository.removeRole(id);
        return Response.ok("Rôle supprimé avec succès").build();
    }
}

