package com.hr.servlets;

import com.hr.data.EmployeRepository;
import com.hr.models.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employes")
public class EmployeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // ✅ Ajout pour éviter l'avertissement

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // ✅ Correction de l'encodage UTF-8
        
        // ✅ Vérification de la méthode dans EmployeRepository
        List<Employe> employes = EmployeRepository.getAllEmployes();

        request.setAttribute("employes", employes);
        request.getRequestDispatcher("employes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // ✅ Correction de l'encodage UTF-8
        request.setCharacterEncoding("UTF-8");

        String nom = request.getParameter("nom");
        String role = request.getParameter("role");
        String departement = request.getParameter("departement");

        // ✅ Vérification des champs vides pour éviter les erreurs
        if (nom == null || nom.trim().isEmpty() ||
            role == null || role.trim().isEmpty() ||
            departement == null || departement.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires !");
            request.getRequestDispatcher("ajouterEmploye.jsp").forward(request, response);
            return;
        }

        // ✅ Ajout de l'employé dans la base de données en mémoire
        EmployeRepository.addEmploye(new Employe(nom, role, departement));

        // ✅ Redirection vers la liste des employés après l'ajout
        response.sendRedirect("employes");
    }
}
