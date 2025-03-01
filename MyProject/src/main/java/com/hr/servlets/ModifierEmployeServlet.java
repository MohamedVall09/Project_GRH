package com.hr.servlets;

import com.hr.data.EmployeRepository;
import com.hr.models.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifierEmployeServlet")
public class ModifierEmployeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des nouvelles valeurs envoyées par le formulaire
        String id = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numeroSecuriteSociale = request.getParameter("numeroSecuriteSociale");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String departement = request.getParameter("departement");

        System.out.println("Modification employé ID: " + id);
        System.out.println("Nouvelles valeurs : " + nom + " " + prenom + " - " + email);

        // Vérifier si l'employé existe
        Employe employe = EmployeRepository.getEmployeById(id);
        if (employe != null) {
            EmployeRepository.updateEmploye(
                id, 
                nom,  // Utilisation des nouvelles valeurs du formulaire
                prenom,  
                numeroSecuriteSociale,
                adresse, 
                telephone, 
                email,
                role
            );
            System.out.println("✅ Employé mis à jour !");
        } else {
            System.out.println("⚠ Erreur : Employé non trouvé !");
        }

        // Redirection vers la liste des employés
        response.sendRedirect("employes");
    }
}

