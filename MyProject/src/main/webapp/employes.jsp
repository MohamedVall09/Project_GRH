<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Employe, java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession();
    Employe utilisateur = (Employe) sessionObj.getAttribute("user");

    if (utilisateur == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>




<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des employés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .btn-ajouter {
            display: block;
            width: fit-content;
            padding: 10px 20px;
            background-color: #009879;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 20px auto;
            text-align: center;
        }
        .btn-ajouter:hover {
            background-color: #007b67;
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
        .actions button {
            padding: 8px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            margin: 2px;
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
    <h2>Liste des employés</h2>

    <% if ("Administrateur".equals(utilisateur.getRole())) { %>
        <a href="ajouterEmploye.jsp" class="btn-ajouter">➕ Ajouter un employé</a>
    <% } %>

    <table>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Rôle</th>
            <th>Département</th>
            <th>Actions</th>
        </tr>
        <%
            List<Employe> employes = (List<Employe>) request.getAttribute("employes");
            if (employes != null && !employes.isEmpty()) {
                for (Employe emp : employes) {
        %>
        <tr>
            <td><%= emp.getNom() %></td>
            <td><%= emp.getPrenom() %></td>
            <td><%= emp.getEmail() %></td>
            <td><%= emp.getRole() %></td>
            <td><%= emp.getDepartement() %></td>
            <td class="actions">
                <a href="modifierEmploye.jsp?id=<%= emp.getId() %>"><button class="btn-modifier">Modifier</button></a>
                <button class="btn-supprimer" onclick="supprimerEmploye('<%= emp.getId() %>')">Supprimer</button>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6">Aucun employé trouvé.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<script>
function supprimerEmploye(id) {
    if (confirm("Voulez-vous vraiment supprimer cet employé ?")) {
        fetch(`/api/employes/${id}`, { method: 'DELETE' })
            .then(response => response.text())
            .then(data => {
                alert(data);
                location.reload();
            })
            .catch(error => alert("Erreur : " + error.message));
    }
}
</script>


</body>
</html>
