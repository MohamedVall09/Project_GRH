<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Employé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0; /* Supprimer la marge par défaut */
            padding: 0; /* Supprimer le padding par défaut */
        }
        h2 {
            color: #333;
            font-size: 1.5em; /* Taille de police cohérente */
            margin: 20px 0 20px 20px; /* Marge en haut et à gauche */
        }
        form {
            background: white;
            padding: 20px; /* Padding interne */
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 30%; /* Largeur à 35% */
            margin: 20px; /* Marge en haut et à gauche */
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px; /* Espacement sous le label */
            font-size: 1em; /* Taille de police cohérente */
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px; /* Padding des champs */
            margin-bottom: 15px; /* Espacement sous les champs */
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 1em; /* Taille de police cohérente */
        }
        input[type="submit"] {
            background-color: #009879; /* Couleur cohérente */
            color: white;
            border: none;
            padding: 10px; /* Padding du bouton */
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em; /* Taille de police cohérente */
        }
        input[type="submit"]:hover {
            background-color: #007b67; /* Couleur au survol cohérente */
        }
        a {
            display: inline-block;
            margin: 20px; /* Marge du lien */
            color: #009879; /* Couleur cohérente */
            text-decoration: none;
            font-size: 1em; /* Taille de police cohérente */
        }
        a:hover {
            text-decoration: underline; /* Effet au survol */
        }
    </style>
</head>
<body>
    <h2>Ajouter un Employé</h2>
    <form action="employes" method="post">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="role">Rôle :</label>
        <select id="role" name="role">
            <option value="Administrateur">Administrateur</option>
            <option value="Responsable">Responsable</option>
            <option value="Employé">Employé</option>
        </select><br>

        <label for="departement">Département :</label>
        <select id="departement" name="departement">
            <option value="IT">IT</option>
            <option value="Marketing">Marketing</option>
            <option value="RH">RH</option>
        </select><br>

        <input type="submit" value="Ajouter">
    </form>

    <br>
    <a href="employes">Voir la liste des employés</a>
</body>
</html>