package com.hr.servlets;

import com.hr.data.DepartementRepository;
import com.hr.models.Departement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departements")
public class DepartementServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Departement> departements = DepartementRepository.getAllDepartements();
        request.setAttribute("departements", departements);
        request.getRequestDispatcher("departements.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            String id = request.getParameter("id");
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");

            DepartementRepository.addDepartement(new Departement(id, nom, description));
        } else if ("modifier".equals(action)) {
            String id = request.getParameter("id");
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");

            DepartementRepository.updateDepartement(id, nom, description);
        } else if ("supprimer".equals(action)) {
            String id = request.getParameter("id");
            DepartementRepository.removeDepartement(id);
        }

        response.sendRedirect("departements");
    }
}

