package com.hr.webservices;

import com.hr.data.EmployeRepository;
import com.hr.models.Employe;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/employes")
public class EmployeWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employe> getEmployes() {
        return EmployeRepository.getAllEmployes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeById(@PathParam("id") String id) {
        Employe employe = EmployeRepository.getEmployeById(id);
        if (employe == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
        }
        return Response.ok(employe).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajouterEmploye(Employe employe) {
        if (employe == null || employe.getNom().isEmpty() || employe.getPrenom().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Données invalides !").build();
        }
        
        // Génération automatique d'un ID
        employe.setId(UUID.randomUUID().toString());
        EmployeRepository.addEmploye(employe);
        
        return Response.status(Response.Status.CREATED).entity("Employé ajouté avec succès !").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEmploye(@PathParam("id") String id, Employe employe) {
        System.out.println("🔄 Requête PUT reçue pour employé ID: " + id);
        
        Employe employeExistant = EmployeRepository.getEmployeById(id);
        if (employeExistant == null) {
            System.out.println("⚠ Employé non trouvé !");
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
        }

        // Mise à jour des données de l'employé
        employeExistant.setNom(employe.getNom());
        employeExistant.setPrenom(employe.getPrenom());
        employeExistant.setEmail(employe.getEmail());
        employeExistant.setTelephone(employe.getTelephone());
        employeExistant.setAdresse(employe.getAdresse());
        employeExistant.setNumeroSecuriteSociale(employe.getNumeroSecuriteSociale());
        employeExistant.setRole(employe.getRole());

        System.out.println("✅ Employé mis à jour avec succès !");
        return Response.ok("Employé modifié avec succès").build();
    }


    @DELETE
    @Path("/{id}")
    public Response supprimerEmploye(@PathParam("id") String id) {
        Employe existant = EmployeRepository.getEmployeById(id);
        if (existant == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé !").build();
        }
        EmployeRepository.removeEmploye(id);
        return Response.ok("Employé supprimé avec succès !").build();
    }
}
