<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession();
    if (sessionObj.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }
        .container {
            margin: 100px auto;
            padding: 20px;
            width: 50%;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn {
            display: block;
            padding: 10px;
            margin: 10px auto;
            width: 60%;
            text-decoration: none;
            background-color: #009879;
            color: white;
            border-radius: 5px;
            text-align: center;
        }
        .btn:hover {
            background-color: #007b67;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Bienvenue sur le Dashboard</h2>
    <a href="employes" class="btn">📋 Gérer les employés</a>
    <a href="departements" class="btn">🏢 Gérer les départements</a>
</div>

</body>
</html>
