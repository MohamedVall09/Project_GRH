package com.hr.webservices;

import com.hr.data.EmployeRepository;
import com.hr.data.DepartementRepository;

import com.hr.data.RoleRepository;
import com.hr.models.Employe;
import com.hr.models.Role;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/employes")
public class EmployeWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployes(@Context HttpHeaders headers) {
        String userId = headers.getHeaderString("user-id");

        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Accès refusé : utilisateur non connecté").build();
        }

        Employe utilisateur = EmployeRepository.getEmployeById(userId);
        if (utilisateur == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Utilisateur non trouvé").build();
        }

        System.out.println("Utilisateur connecté : " + utilisateur.getNom() + " - Role : " + utilisateur.getRole());

        List<Employe> employesAffiches;
        switch (utilisateur.getRole()) {
            case "Administrateur":
                employesAffiches = EmployeRepository.getAllEmployes();
                break;
            case "Responsable":
                employesAffiches = EmployeRepository.getAllEmployes()
                        .stream()
                        .filter(emp -> emp.getDepartement().equals(utilisateur.getDepartement()))
                        .collect(Collectors.toList());
                break;
            case "Employé":
                employesAffiches = List.of(utilisateur);
                break;
            default:
                return Response.status(Response.Status.FORBIDDEN).entity("Rôle non autorisé").build();
        }

        return Response.ok(employesAffiches).build();
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

        // 🔍 Vérifier si le département existe avant d'ajouter l'employé
        if (DepartementRepository.getDepartementByName(employe.getDepartement()) == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le département spécifié n'existe pas !").build();
        }

        // 🔍 Vérifier si le rôle existe
        if (RoleRepository.getRoleByName(employe.getRole()) == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le rôle spécifié n'existe pas !").build();
        }

        // ✅ Génération automatique d'un ID et ajout de l'employé
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
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
        }

        // ✅ Vérifier si le rôle existe avant modification
        Role role = RoleRepository.getAllRoles().stream()
                .filter(r -> r.getNom().equals(employe.getRole()))
                .findFirst()
                .orElse(null);

        if (role == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le rôle spécifié n'existe pas !").build();
        }

        // ✅ Vérifier si le département existe avant modification
        boolean departementExiste = DepartementRepository.getAllDepartements().stream()
                .anyMatch(d -> d.getNom().equals(employe.getDepartement()));

        if (!departementExiste) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le département spécifié n'existe pas !").build();
        }

        // ✅ Mise à jour des données de l'employé
        employeExistant.setNom(employe.getNom());
        employeExistant.setPrenom(employe.getPrenom());
        employeExistant.setEmail(employe.getEmail());
        employeExistant.setTelephone(employe.getTelephone());
        employeExistant.setAdresse(employe.getAdresse());
        employeExistant.setNumeroSecuriteSociale(employe.getNumeroSecuriteSociale());
        employeExistant.setRole(employe.getRole());
        employeExistant.setDepartement(employe.getDepartement()); // ✅ Ajout de la mise à jour du département

        System.out.println("✅ Employé mis à jour avec succès !");
        return Response.ok("Employé modifié avec succès").build();
    }


    @PUT
    @Path("/updatePersonalInfo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePersonalInfo(@PathParam("id") String id, Employe employe, @Context HttpHeaders headers) {
        String userId = headers.getHeaderString("user-id");

        // 🔒 Vérification que l'employé ne peut modifier que ses propres informations
        if (userId == null || !userId.equals(id)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Accès refusé : vous ne pouvez modifier que vos propres informations.").build();
        }

        Employe employeExistant = EmployeRepository.getEmployeById(id);
        if (employeExistant == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
        }

        // 🔄 Mise à jour des champs autorisés uniquement
        employeExistant.setAdresse(employe.getAdresse());
        employeExistant.setTelephone(employe.getTelephone());

        return Response.ok("Informations mises à jour avec succès").build();
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
