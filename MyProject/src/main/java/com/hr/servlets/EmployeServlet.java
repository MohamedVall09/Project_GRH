package com.hr.servlets;

import com.hr.data.EmployeRepository;
import com.hr.models.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/employes")
public class EmployeServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Object utilisateurObj = session.getAttribute("user");

        if (utilisateurObj == null || !(utilisateurObj instanceof Employe)) {
            response.sendRedirect("login.jsp");
            return;
        }

        Employe utilisateur = (Employe) utilisateurObj;
        List<Employe> employesAffiches = new ArrayList<>();

        System.out.println("Utilisateur connecté : " + utilisateur.getNom() + " - Role : " + utilisateur.getRole());

        switch (utilisateur.getRole()) {
            case "Administrateur":
                employesAffiches = EmployeRepository.getAllEmployes();
                System.out.println("📜 Liste des employés après ajout : " + EmployeRepository.getAllEmployes().size());
                break;
            case "Responsable":
                employesAffiches = EmployeRepository.getEmployesByDepartement(utilisateur.getDepartement());
                break;
            case "Employé":
                employesAffiches.add(utilisateur);
                break;
            default:
                System.out.println("⚠ Rôle inconnu : " + utilisateur.getRole());
        }

        request.setAttribute("employes", employesAffiches);
        request.getRequestDispatcher("employes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            System.out.println("📩 Requête POST reçue pour ajouter un employé");

            String id = UUID.randomUUID().toString();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String numeroSecuriteSociale = request.getParameter("numeroSecuriteSociale");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String departement = request.getParameter("departement");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Employe employe = new Employe(id, nom, prenom, numeroSecuriteSociale, adresse, telephone, email, role, departement, username, password);
            EmployeRepository.addEmploye(employe);

            System.out.println("✅ Employé ajouté avec succès : " + employe.getNom());

            response.sendRedirect("employes");
            return;
        }

        if ("supprimer".equals(action)) {
            String employeId = request.getParameter("id");  // ✅ Suppression de la duplication de `id`

            System.out.println("🗑 Suppression de l'employé ID : " + employeId);

            Employe empASupprimer = EmployeRepository.getEmployeById(employeId);
            if (empASupprimer != null) {
                EmployeRepository.removeEmploye(employeId);
                System.out.println("✅ Employé supprimé avec succès !");
            } else {
                System.out.println("⚠ Employé introuvable !");
            }

            response.sendRedirect("employes");
            return;
        }

        // Si aucune action correspondante, rediriger vers la liste des employés
        response.sendRedirect("employes");
    }
}
