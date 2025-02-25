package com.hr.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") // Applique le filtre à toutes les pages
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre (facultatif)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Convertir les objets en HttpServletRequest et HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Récupérer la session de l'utilisateur
        HttpSession session = req.getSession(false);

        // Vérifier si l'utilisateur est connecté (session avec attribut "user")
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // Récupérer l'URL demandée
        String requestURI = req.getRequestURI();

        // Vérifier si l'utilisateur est déjà sur la page de connexion
        boolean isLoginPage = requestURI.endsWith("login.jsp") || requestURI.endsWith("login");

        // Autoriser l'accès si l'utilisateur est connecté ou s'il est sur la page de connexion
        if (isLoggedIn || isLoginPage) {
            chain.doFilter(request, response); // Continuer vers la page demandée
        } else {
            // Rediriger vers login.jsp si l'utilisateur n'est pas connecté
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Nettoyage des ressources du filtre (facultatif)
    }
}
