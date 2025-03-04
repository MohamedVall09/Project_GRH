<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Départements</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }
        .container { background: white; padding: 20px; margin: 50px auto; width: 60%;
            border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
        th { background-color: #009879; color: white; }
        .btn { padding: 8px; margin: 5px; cursor: pointer; border: none; border-radius: 5px; }
        .btn-edit { background-color: #ffc107; }
        .btn-delete { background-color: #dc3545; color: white; }
    </style>
</head>
<body>

<div class="container">
    <h2>Liste des Départements</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="departementTable"></tbody>
    </table>
    
    <a href="ajouterDepartement.jsp" class="btn">➕ Ajouter un Département</a>
</div>

<script>
// 🔄 Chargement dynamique des départements
fetch("/api/departements")
    .then(response => response.json())
    .then(data => {
        let tableBody = document.getElementById("departementTable");
        tableBody.innerHTML = ""; // Nettoyer le tableau

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(departement => {
                let row = `<tr>
                    <td>${departement.id}</td>
                    <td>${departement.nom}</td>
                    <td>${departement.description}</td>
                    <td>
                        <a href="modifierDepartement.jsp?id=${departement.id}" class="btn btn-edit">✏ Modifier</a>
                        <button class="btn btn-delete" onclick="supprimerDepartement('${departement.id}')">🗑 Supprimer</button>
                    </td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        } else {
            tableBody.innerHTML = "<tr><td colspan='4'>Aucun département disponible</td></tr>";
        }
    })
    .catch(error => console.error("Erreur chargement départements:", error));

// 🔥 Suppression d'un département
function supprimerDepartement(id) {
    if (confirm("Voulez-vous vraiment supprimer ce département ?")) {
        fetch(`/api/departements/${id}`, {
            method: "DELETE"
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            location.reload();
        })
        .catch(error => console.error("Erreur suppression département:", error));
    }
}
</script>

</body>
</html>
