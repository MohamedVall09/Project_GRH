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

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 🔹 Vérifie si l'employé existe dans la base de données
        Employe utilisateur = EmployeRepository.getEmployeByLogin(username, password);

        if (utilisateur != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", utilisateur); // ✅ Stocke un objet Employe
            response.sendRedirect("dashboard.jsp"); // ✅ Redirige vers la liste des employés
        } else {
            response.sendRedirect("login.jsp?error=1"); // ❌ Identifiants incorrects
        }
    }
}
