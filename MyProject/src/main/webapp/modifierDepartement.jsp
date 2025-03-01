<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Departement, com.hr.data.DepartementRepository" %>

<%
    String id = request.getParameter("id");
    Departement departement = DepartementRepository.getDepartementById(id);

    if (departement == null) {
        response.sendRedirect("departements.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Département</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
            background: white;
            padding: 20px;
            margin: 50px auto;
            width: 40%;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        input, textarea {
            width: 90%;
            padding: 8px;
            margin: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .btn {
            background-color: #009879;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Modifier Département</h2>
    <form action="departements" method="post">
        <input type="hidden" name="action" value="modifier">
        <input type="hidden" name="id" value="<%= departement.getId() %>">

        <label>Nom:</label>
        <input type="text" name="nom" value="<%= departement.getNom() %>" required>

        <label>Description:</label>
        <textarea name="description" required><%= departement.getDescription() %></textarea>

        <button type="submit" class="btn">Modifier</button>
    </form>
</div>

</body>
</html>
