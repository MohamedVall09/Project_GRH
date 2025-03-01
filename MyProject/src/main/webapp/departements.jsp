<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hr.models.Departement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Départements</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #009879;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .btn {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            margin: 5px;
            text-decoration: none;
            display: inline-block;
        }
        .btn-ajouter {
            background-color: #009879;
            color: white;
        }
        .btn-ajouter:hover {
            background-color: #007b67;
        }
        .btn-modifier {
            background-color: #f4a261;
            color: white;
        }
        .btn-modifier:hover {
            background-color: #e76f51;
        }
        .btn-supprimer {
            background-color: #e63946;
            color: white;
        }
        .btn-supprimer:hover {
            background-color: #d62828;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Liste des Départements</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            <%
                List<Departement> departements = (List<Departement>) request.getAttribute("departements");
                if (departements != null && !departements.isEmpty()) {
                    for (Departement dep : departements) {
            %>
            <tr>
                <td><%= dep.getId() %></td>
                <td><%= dep.getNom() %></td>
                <td><%= dep.getDescription() %></td>
                <td>
                    <a href="modifierDepartement.jsp?id=<%= dep.getId() %>" class="btn btn-modifier">Modifier</a>
                    <button class="btn btn-supprimer" onclick="supprimerDepartement('<%= dep.getId() %>')">Supprimer</button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">Aucun département trouvé.</td>
            </tr>
            <%
                }
            %>
        </table>
        <a href="ajouterDepartement.jsp" class="btn btn-ajouter">Ajouter un Département</a>
    </div>
</body>
</html>
