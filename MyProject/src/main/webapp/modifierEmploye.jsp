<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Employe, com.hr.data.EmployeRepository" %>

<%
    String id = request.getParameter("id");
    Employe employe = EmployeRepository.getEmployeById(id);

    if (employe == null) {
        response.sendRedirect("employes.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Employé</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }
        .container { background: white; padding: 20px; margin: 50px auto; width: 50%;
            border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
        input, select { width: 90%; padding: 8px; margin: 10px; border-radius: 5px; border: 1px solid #ccc; }
        .btn { background-color: #009879; color: white; padding: 10px; border: none; cursor: pointer; border-radius: 5px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Modifier Employé</h2>
    <form action="/MyProject/api/employes/<%= employe.getId() %>" method="POST">
    <input type="hidden" name="_method" value="PUT"> <!-- Trick pour utiliser PUT en HTML -->
    <label>Nom :</label>
    <input type="text" name="nom" value="<%= employe.getNom() %>" required>

    <label>Prénom :</label>
    <input type="text" name="prenom" value="<%= employe.getPrenom() %>" required>

    <label>Email :</label>
    <input type="email" name="email" value="<%= employe.getEmail() %>" required>

    <label>Téléphone :</label>
    <input type="text" name="telephone" value="<%= employe.getTelephone() %>" required>

    <label>Adresse :</label>
    <input type="text" name="adresse" value="<%= employe.getAdresse() %>" required>

    <label>Numéro de sécurité sociale :</label>
    <input type="text" name="numeroSecuriteSociale" value="<%= employe.getNumeroSecuriteSociale() %>" required>

    <label>Rôle :</label>
    <select name="role">
        <option value="Employé" <%= "Employé".equals(employe.getRole()) ? "selected" : "" %>>Employé</option>
        <option value="Responsable" <%= "Responsable".equals(employe.getRole()) ? "selected" : "" %>>Responsable</option>
        <option value="Administrateur" <%= "Administrateur".equals(employe.getRole()) ? "selected" : "" %>>Administrateur</option>
    </select>

    <button type="submit">Modifier</button>
</form>
</div>
<script>
document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault(); // Empêche l'envoi classique du formulaire

    let id = "<%= employe.getId() %>";
    let formData = new FormData(this);
    let jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });
    console.log("🟠 Envoi de la requête PUT à l’URL :", `http://localhost:8080/MyProject/api/employes/${id}`);

    fetch(`http://localhost:8080/MyProject/api/employes/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        console.log("🟡 Réponse statut:", response.status);
        return response.text();
    })
    .then(data => {
        console.log("🟢 Réponse du serveur:", data);
        if (data.includes("succès") || data.includes("modifié")) {
            window.location.href = "employes.jsp"; // Redirection après modification
        } else {
            alert("Erreur lors de la modification : " + data);
        }
    })
    .catch(error => console.error("❌ Erreur:", error));
});
</script>


</body>
</html>
