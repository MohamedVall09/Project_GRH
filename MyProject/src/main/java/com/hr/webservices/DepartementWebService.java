package com.hr.webservices;

import com.hr.data.DepartementRepository;
import com.hr.models.Departement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/departements")
public class DepartementWebService {

    // 🔄 Récupérer tous les départements
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartements() {
        List<Departement> departements = DepartementRepository.getAllDepartements();
        if (departements.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("Aucun département trouvé.").build();
        }
        return Response.ok(departements).build();
    }

    // ➕ Ajouter un département
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajouterDepartement(Departement departement) {
        if (departement == null || departement.getNom() == null || departement.getNom().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nom du département invalide !").build();
        }

        // Génération d'un ID unique
        String id = UUID.randomUUID().toString();
        departement.setId(id);

        DepartementRepository.addDepartement(departement);
        System.out.println("✅ Département ajouté : " + departement.getNom());

        return Response.status(Response.Status.CREATED).entity("Département ajouté avec succès !").build();
    }

    // ✏ Modifier un département
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response modifierDepartement(@PathParam("id") String id, Departement departement) {
        Departement d = DepartementRepository.getDepartementById(id);
        if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Département non trouvé !").build();
        }

        if (departement.getNom() == null || departement.getNom().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le nom du département ne peut pas être vide !").build();
        }

        DepartementRepository.updateDepartement(id, departement.getNom(), departement.getDescription());
        System.out.println("✅ Département mis à jour : " + departement.getNom());

        return Response.ok("Département mis à jour avec succès !").build();
    }

    // 🗑 Supprimer un département
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response supprimerDepartement(@PathParam("id") String id) {
        Departement d = DepartementRepository.getDepartementById(id);
        if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Département non trouvé !").build();
        }

        DepartementRepository.removeDepartement(id);
        System.out.println("🗑 Département supprimé : " + d.getNom());

        return Response.ok("Département supprimé avec succès !").build();
    }
}
