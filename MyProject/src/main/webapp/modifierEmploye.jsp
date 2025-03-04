<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Employe, com.hr.data.EmployeRepository, jakarta.servlet.http.HttpSession" %>

<%
    String id = request.getParameter("id");
    HttpSession sessionObj = request.getSession();
    Employe utilisateur = (Employe) sessionObj.getAttribute("user");
    Employe employe = EmployeRepository.getEmployeById(id);

    if (utilisateur == null || employe == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    boolean isAdmin = "Administrateur".equals(utilisateur.getRole());
    boolean isSelf = utilisateur.getId().equals(employe.getId());
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

    <% if (isAdmin) { %>
    <!-- 🔴 Formulaire de modification ADMINISTRATEUR -->
    <form id="adminUpdateForm">
        <input type="hidden" id="id" value="<%= employe.getId() %>">

        <label>Nom :</label>
        <input type="text" id="nom" value="<%= employe.getNom() %>" required>

        <label>Prénom :</label>
        <input type="text" id="prenom" value="<%= employe.getPrenom() %>" required>

        <label>Email :</label>
        <input type="email" id="email" value="<%= employe.getEmail() %>" required>

        <label>Téléphone :</label>
        <input type="text" id="telephone" value="<%= employe.getTelephone() %>" required>

        <label>Adresse :</label>
        <input type="text" id="adresse" value="<%= employe.getAdresse() %>" required>

        <label>Numéro de sécurité sociale :</label>
        <input type="text" id="numeroSecuriteSociale" value="<%= employe.getNumeroSecuriteSociale() %>" required>

        <label>Rôle :</label>
        <select id="role" name="role"></select>

        <label>Département :</label>
        <select id="departement" name="departement"></select>

        <button type="button" class="btn" onclick="mettreAJourAdmin()">Modifier</button>
    </form>

    <% } else if (isSelf) { %>
    <!-- 🔵 Formulaire de modification EMPLOYÉ (mise à jour limitée) -->
    <form id="userUpdateForm">
        <input type="hidden" id="id" value="<%= employe.getId() %>">

        <label>Adresse :</label>
        <input type="text" id="adresse" value="<%= employe.getAdresse() %>">

        <label>Téléphone :</label>
        <input type="text" id="telephone" value="<%= employe.getTelephone() %>">

        <button type="button" class="btn" onclick="mettreAJourEmploye()">Mettre à jour</button>
    </form>
    <% } %>
</div>

<script>
// 🔴 Mise à jour complète par un administrateur
function mettreAJourAdmin() {
    let id = document.getElementById("id").value;
    let jsonData = {
        nom: document.getElementById("nom").value,
        prenom: document.getElementById("prenom").value,
        email: document.getElementById("email").value,
        telephone: document.getElementById("telephone").value,
        adresse: document.getElementById("adresse").value,
        numeroSecuriteSociale: document.getElementById("numeroSecuriteSociale").value,
        role: document.getElementById("role").value,
        departement: document.getElementById("departement").value
    };

    fetch(`/api/employes/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jsonData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        window.location.href = "employes.jsp";
    })
    .catch(error => console.error("Erreur :", error));
}

// 🔵 Mise à jour limitée par un employé
function mettreAJourEmploye() {
    let id = document.getElementById("id").value;
    let jsonData = {
        adresse: document.getElementById("adresse").value,
        telephone: document.getElementById("telephone").value
    };

    fetch(`/api/employes/updatePersonalInfo/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jsonData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        window.location.href = "employes.jsp";
    })
    .catch(error => console.error("Erreur :", error));
}

// 🔄 Chargement dynamique des rôles
fetch("/api/roles")
    .then(response => response.json())
    .then(data => {
        let roleSelect = document.getElementById("role");
        roleSelect.innerHTML = "<option value=''>Sélectionner un rôle</option>"; // Option par défaut

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.nom;
                option.textContent = role.nom;
                roleSelect.appendChild(option);
            });
        } else {
            let option = document.createElement("option");
            option.value = "";
            option.textContent = "Aucun rôle disponible";
            roleSelect.appendChild(option);
        }
    })
    .catch(error => console.error("Erreur chargement rôles:", error));

// 🔄 Chargement dynamique des départements
fetch("/api/departements")
    .then(response => response.json())
    .then(data => {
        let departementSelect = document.getElementById("departement");
        departementSelect.innerHTML = "<option value=''>Sélectionner un département</option>";

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(departement => {
                let option = document.createElement("option");
                option.value = departement.nom;
                option.textContent = departement.nom;
                departementSelect.appendChild(option);
            });
        } else {
            let option = document.createElement("option");
            option.value = "";
            option.textContent = "Aucun département disponible";
            departementSelect.appendChild(option);
        }
    })
    .catch(error => console.error("Erreur chargement départements:", error));
</script>

</body>
</html>
