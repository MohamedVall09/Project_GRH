package com.hr.servlets;

import com.hr.data.EvaluationRepository;
import com.hr.models.Evaluation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

@WebServlet("/evaluations")
public class EvaluationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Evaluation> evaluations = EvaluationRepository.getAllEvaluation();
        request.setAttribute("evaluations", evaluations);
        request.getRequestDispatcher("evaluations.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String dateStr = request.getParameter("dateEvaluation");

        Date dateEvaluation = null;
        try {
            if (dateStr != null && !dateStr.isEmpty()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateEvaluation = formatter.parse(dateStr);
            } else {
                System.out.println("Erreur : Aucune date fournie.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : Format de date invalide.");
        }

        if ("ajouter".equals(action)) {
        	String id = request.getParameter("id");
            String responsableId = request.getParameter("responsableId");
            String employeId = request.getParameter("employeId");
            String commentaire = request.getParameter("commentaire");

            int note = 0;
            try {
                note = Integer.parseInt(request.getParameter("note"));
            } catch (NumberFormatException e) {
                System.out.println("Erreur : La note doit être un nombre valide.");
            }

            if (commentaire != null && dateEvaluation != null) {
                EvaluationRepository.addEvaluation(new Evaluation(id, responsableId, employeId, note, commentaire, dateEvaluation));
            } else {
                System.out.println("Erreur : Un champ est vide, l’évaluation ne peut pas être ajoutée.");
            }
        }
        System.out.println("Données reçues du formulaire :");
        System.out.println("ID Responsable = " + request.getParameter("responsableId"));
        System.out.println("ID Employe = " + request.getParameter("employeId"));
        System.out.println("Note = " + request.getParameter("note"));
        System.out.println("Commentaire = " + request.getParameter("commentaire"));
        System.out.println("Date = " + request.getParameter("dateEvaluation"));

        response.sendRedirect("evaluations");
    
    }


    private void ajouterEvaluation(HttpServletRequest request, Date dateEvaluation) {
        String id = request.getParameter("id");
        String responsableId = request.getParameter("responsableId");
        String employeId = request.getParameter("employeId");
        String commentaire = request.getParameter("commentaire");

        int note = 0;
        try {
            note = Integer.parseInt(request.getParameter("note"));
        } catch (NumberFormatException e) {
            System.out.println("Erreur : La note doit être un nombre valide.");
        }

        if (commentaire != null && dateEvaluation != null) {
            Evaluation nouvelleEvaluation = new Evaluation(id, responsableId, employeId, note, commentaire, dateEvaluation);
            EvaluationRepository.addEvaluation(nouvelleEvaluation);
        } else {
            System.out.println("Erreur : Impossible d'ajouter l'évaluation, champs manquants !");
        }
    }

    private void modifierEvaluation(HttpServletRequest request, Date dateEvaluation) {
        String id = request.getParameter("id");
        String responsableId = request.getParameter("responsableId");
        String employeId = request.getParameter("employeId");
        String commentaire = request.getParameter("commentaire");

        int note = 0;
        try {
            note = Integer.parseInt(request.getParameter("note"));
        } catch (NumberFormatException e) {
            System.out.println("Erreur : La note doit être un nombre valide.");
        }

        if (id != null && !id.isEmpty() && commentaire != null && dateEvaluation != null) {
            Evaluation nouvelleEvaluation = new Evaluation(id, responsableId, employeId, note, commentaire, dateEvaluation);
            boolean updated = EvaluationRepository.updateEvaluation(id, nouvelleEvaluation);
            if (!updated) {
                System.out.println("Erreur : Impossible de modifier l'évaluation, ID introuvable !");
            }
        } else {
            System.out.println("Erreur : Impossible de modifier l'évaluation, champs manquants !");
        }
    }


    private void supprimerEvaluation(HttpServletRequest request) {
        String responsableId = request.getParameter("responsableId");
        if (responsableId != null && !responsableId.isEmpty()) {
            EvaluationRepository.removeEvaluation(responsableId);
        } else {
            System.out.println("Erreur : ID invalide pour la suppression !");
        }
    }
}
