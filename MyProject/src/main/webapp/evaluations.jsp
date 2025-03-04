<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hr.models.Evaluation" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Format plus lisible
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Evaluations</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
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
            color: #333;
            margin-bottom: 9px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 200px;
            background: white;
            margin-top: 9px;
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
		<h2>Liste des évaluations</h2>
        <table>
            <tr>
                <th>ID Evaluation</th>
                <th>ID Responsable</th>
                <th>ID Employé</th>
                <th>Note</th>
                <th>Commentaire</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <%
                List<Evaluation> evaluations = (List<Evaluation>) request.getAttribute("evaluations");
                if (evaluations != null && !evaluations.isEmpty()) {
                    for (Evaluation e : evaluations) {
            %>
            <tr>
                <td><%= e.getId() %></td>
                <td><%= e.getResponsableId() %></td>
                <td><%= e.getEmployeId() %></td>
                <td><%= e.getNote() %></td>
                <td><%= e.getCommentaire() %></td>
                <td>
    				<%= (e.getDateEvaluation() != null) ? dateFormat.format(e.getDateEvaluation()) : "Non défini" %>
				</td>
                <td>
                    <a href="modifierEvaluation.jsp?id=<%= e.getId() %>" class="btn btn-modifier">Modifier</a>
                    <button class="btn btn-supprimer" onclick="supprimerEvaluation('<%= e.getId() %>')">Supprimer</button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">Aucun Evaluation trouvé.</td>
            </tr>
            <%
                }
            %>
        </table>
        <a href="ajouterEvaluation.jsp" class="btn btn-ajouter">➕Ajouter un Evaluations</a>
    </div>
    <script>
// 🔄 Chargement dynamique des départements
fetch("/api/evaluations")
    .then(response => response.json())
    .then(data => {
        let tableBody = document.getElementById("evaluationTable");
        tableBody.innerHTML = ""; // Nettoyer le tableau

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(evaluation => {
                let row = `<tr>
                    <td>${evaluation.id}</td>
                    <td>${evaluation.employeId}</td>
                    <td>${evaluation.responsableId}</td>
                    <td>${evaluation.note}</td>
                    <td>${evaluation.commentaire}</td>
                    <td>${evaluation.dateEvaluation}</td>
                    <td>
                        <a href="modifierEvaluation.jsp?id=${evaluation.id}" class="btn btn-edit">✏ Modifier</a>
                        <button class="btn btn-delete" onclick="supprimerEvaluation('${evaluation.id}')">🗑 Supprimer</button>
                    </td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        } else {
            tableBody.innerHTML = "<tr><td colspan='4'>Aucun evaluations disponible</td></tr>";
        }
    })
    .catch(error => console.error("Erreur chargement evaluations:", error));

// 🔥 Suppression d'un département
function supprimerEvaluation(id) {
    if (confirm("Voulez-vous vraiment supprimer ce evaluation ?")) {
        fetch(`/api/evaluations/${id}`, {
            method: "DELETE"
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            location.reload();
        })
        .catch(error => console.error("Erreur suppression evaluation : ", error));
    }
}
</script>
</body>
</html>
