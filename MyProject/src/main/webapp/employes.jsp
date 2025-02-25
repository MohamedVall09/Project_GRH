<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Employe, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des employés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0; /* Supprimer la marge par défaut */
            padding: 0; /* Supprimer le padding par défaut */
        }
        h2 {
            color: #333;
            text-align: center;
            font-size: 1.5em; /* Taille de police inchangée */
            margin-top: 20px; /* Espacement en haut */
        }
        table {
            width: 80%; /* Largeur du tableau à 80% */
            margin: 20px auto; /* Centrer horizontalement avec une marge en haut */
            border-collapse: collapse;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            font-size: 1em; /* Taille de police inchangée */
        }
        table th, table td {
            padding: 10px 15px; /* Padding raisonnable pour les cellules */
            text-align: left;
        }
        table th {
            background-color: #009879;
            color: #ffffff;
            text-transform: uppercase;
            font-size: 1em; /* Taille de police inchangée */
        }
        table tr {
            border-bottom: 1px solid #dddddd;
        }
        table tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }
        table tr:last-of-type {
            border-bottom: 2px solid #009879;
        }
        table tr:hover {
            background-color: #f1f1f1;
        }
        .btn-ajouter {
            display: block;
            width: 200px; /* Largeur du bouton */
            margin: 20px auto; /* Centrer le bouton */
            padding: 10px; /* Padding interne */
            background-color: #009879; /* Couleur cohérente */
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1em; /* Taille de police cohérente */
        }
        .btn-ajouter:hover {
            background-color: #007b67; /* Couleur au survol cohérente */
        }
    </style>
</head>
<body>
    <div>
        <h2>Liste des employés</h2>
        <!-- Bouton "Ajouter un employé" -->
        <a href="ajouterEmploye.jsp" class="btn-ajouter">Ajouter un employé</a>

        <table>
            <tr>
                <th>Nom</th>
                <th>Rôle</th>
                <th>Département</th>
            </tr>
            <%
                List<Employe> employes = (List<Employe>) request.getAttribute("employes");
                if (employes != null && !employes.isEmpty()) {
                    for (Employe emp : employes) {
            %>
            <tr>
                <td><%= emp.getNom() %></td>
                <td><%= emp.getRole() %></td>
                <td><%= emp.getDepartement() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3" style="text-align: center;">Aucun employé trouvé.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>