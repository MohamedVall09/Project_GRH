package com.hr.webservices;

import com.hr.data.EmployeRepository;
import com.hr.models.Employe;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/employes")
public class EmployeWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employe> getEmployes() {
        return EmployeRepository.getAllEmployes();
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterEmploye(Employe employe) {
        EmployeRepository.addEmploye(employe);
        return Response.status(Response.Status.CREATED).entity("Employé ajouté avec succès !").build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmploye(@PathParam("id") String id, @QueryParam("role") String role, @QueryParam("departement") String departement) {
    	Employe employe = EmployeRepository.getEmployeById(id);
    	if (employe == null) {
    	    return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
    	}

    	EmployeRepository.updateEmploye(
    	    id, 
    	    employe.getNom(), 
    	    employe.getPrenom(), 
    	    employe.getNumeroSecuriteSociale(),
    	    employe.getAdresse(), 
    	    employe.getTelephone(), 
    	    employe.getEmail(),
    	    role
    	);

    	// ✅ Si tout s'est bien passé, on retourne un message de succès
    	return Response.ok("Employé mis à jour avec succès").build();

        
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmploye(@PathParam("id") String id) {
        System.out.println("🔴 Requête DELETE reçue pour employé ID : " + id);

        Employe employe = EmployeRepository.getEmployeById(id);
        if (employe == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé").build();
        }
        EmployeRepository.removeEmploye(id);
        System.out.println("✅ Employé supprimé avec succès !");
        
        return Response.ok("Employé supprimé").build();
    }
    
    

}

