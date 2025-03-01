package com.hr.webservices;

import com.hr.data.DepartementRepository;
import com.hr.models.Departement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/departements")
public class DepartementWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Departement> getDepartements() {
        return DepartementRepository.getAllDepartements();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterDepartement(Departement departement) {
        DepartementRepository.addDepartement(departement);
        return Response.status(Response.Status.CREATED).entity("Département ajouté avec succès !").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifierDepartement(@PathParam("id") String id, Departement departement) {
        Departement d = DepartementRepository.getDepartementById(id);
        if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Département non trouvé !").build();
        }
        DepartementRepository.updateDepartement(id, departement.getNom(), departement.getDescription());
        return Response.ok("Département mis à jour avec succès !").build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimerDepartement(@PathParam("id") String id) {
        Departement d = DepartementRepository.getDepartementById(id);
        if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Département non trouvé !").build();
        }
        DepartementRepository.removeDepartement(id);
        return Response.ok("Département supprimé avec succès !").build();
    }
}
