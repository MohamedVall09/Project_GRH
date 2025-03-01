<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Ajouter un Employé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 40%;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
            align-items: center;
        }
        label {
            font-weight: bold;
            text-align: left;
            width: 100%;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            background-color: #009879;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            width: 100%;
        }
        .btn:hover {
            background-color: #007b67;
        }
        .back-link {
            display: inline-block;
            margin-top: 15px;
            color: #009879;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Ajouter un Employé</h2>
    <form action="employes" method="post">
    <input type="hidden" name="action" value="ajouter">

    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required>

    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom" required>

    <label for="email">Email :</label>
    <input type="email" id="email" name="email" required>

    <label for="telephone">Téléphone :</label>
    <input type="text" id="telephone" name="telephone" required>

    <label for="adresse">Adresse :</label>
    <input type="text" id="adresse" name="adresse" required>

    <label for="numeroSecuriteSociale">Numéro de sécurité sociale :</label>
    <input type="text" id="numeroSecuriteSociale" name="numeroSecuriteSociale" required>

    <label for="role">Rôle :</label>
    <select id="role" name="role">
        <option value="Administrateur">Administrateur</option>
        <option value="Responsable">Responsable</option>
        <option value="Employé">Employé</option>
    </select>

    <label for="departement">Département :</label>
    <select id="departement" name="departement">
        <option value="IT">IT</option>
        <option value="Marketing">Marketing</option>
        <option value="RH">RH</option>
    </select>

    <button type="submit" class="btn">Ajouter</button>
</form>

	
    <a href="employes" class="back-link">⬅ Retour à la liste des employés</a>
</div>

<script>
function disableSubmitButton() {
    let submitButton = document.querySelector('input[type="submit"]');
    submitButton.disabled = true;
}
</script>


</body>

</html>
