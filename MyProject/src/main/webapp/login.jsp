<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0; /* Supprimer la marge par d�faut */
            padding: 0; /* Supprimer le padding par d�faut */
            display: flex; /* Centrer horizontalement et verticalement */
            justify-content: center;
            align-items: center;
            height: 100vh; /* Prendre toute la hauteur de la page */
        }
        .container {
            background: white;
            padding: 20px; /* Padding r�duit */
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 30%; /* Largeur � 20% */
            text-align: center;
        }
        h2 {
            margin-bottom: 20px; /* Espacement sous le titre */
            font-size: 1.5em; /* Taille de police r�duite */
            color: #333; /* Couleur coh�rente avec la page pr�c�dente */
        }
        .form-group {
            text-align: left;
            margin-bottom: 15px; /* Espacement entre les champs */
        }
        label {
            display: block;
            font-weight: bold;
            font-size: 1em; /* Taille de police r�duite */
            margin-bottom: 5px; /* Espacement sous le label */
        }
        input {
            width: 100%;
            padding: 8px; /* Padding r�duit */
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 1em; /* Taille de police r�duite */
        }
        .error {
            color: red;
            font-size: 0.9em; /* Taille de police r�duite */
            margin-top: 15px; /* Espacement au-dessus du message d'erreur */
        }
        .btn {
            background-color: #009879; /* Couleur coh�rente avec la page pr�c�dente */
            color: white;
            border: none;
            padding: 10px; /* Padding r�duit */
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em; /* Taille de police r�duite */
        }
        .btn:hover {
            background-color: #007b67; /* Couleur au survol coh�rente */
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Connexion</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Nom d'utilisateur</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Se connecter</button>
        </form>
        <% if (request.getParameter("error") != null) { %>
            <p class="error">Identifiants incorrects.</p>
        <% } %>
    </div>

</body>
</html>