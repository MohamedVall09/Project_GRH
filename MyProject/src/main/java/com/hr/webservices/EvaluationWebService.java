package com.hr.webservices;

import com.hr.data.EvaluationRepository;
import com.hr.models.Evaluation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/evaluations") // URL de base du Web Service
public class EvaluationWebService {

    /** ✅ 1️⃣ Récupérer toutes les évaluations (GET /evaluations) */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evaluation> getEvaluations() {
        return EvaluationRepository.getAllEvaluation();
    }

    /** ✅ 2️⃣ Ajouter une nouvelle évaluation (POST /evaluations) */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajouterEvaluation(Evaluation evaluation) {
        if (evaluation == null || evaluation.getEmployeId() == null || 
            evaluation.getResponsableId() == null || evaluation.getCommentaire() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Données invalides ! Tous les champs sont obligatoires.")
                    .build();
        }

        EvaluationRepository.addEvaluation(evaluation);
        return Response.status(Response.Status.CREATED)
                .entity("Évaluation ajoutée avec succès !")
                .build();
    }

    /** ✅ 3️⃣ Modifier une évaluation existante (PUT /evaluations/{id}) */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response modifierEvaluation(@PathParam("id") String id, Evaluation evaluation) {
        boolean updated = EvaluationRepository.updateEvaluation(id, evaluation);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Évaluation non trouvée !")
                    .build();
        }
        return Response.ok("Évaluation mise à jour avec succès !").build();
    }

    /** ✅ 4️⃣ Supprimer une évaluation existante (DELETE /evaluations/{id}) */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response supprimerEvaluation(@PathParam("id") String id) {
        boolean removed = EvaluationRepository.removeEvaluation(id);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Évaluation non trouvée !")
                    .build();
        }
        return Response.ok("Évaluation supprimée avec succès !").build();
    }
}
