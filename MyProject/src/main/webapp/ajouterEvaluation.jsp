<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une évaluation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
		    width: 50%; /* Réduit la largeur */
		    max-width: 500px; /* Fixe une largeur maximale */
		    margin: 50px auto; /* Centre le formulaire */
		    background: white;
		    padding: 20px;
		    border-radius: 10px;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
		
		input, textarea {
		    width: 100%; /* Tous les champs prennent 100% de la largeur du conteneur */
		    padding: 8px;
		    margin: 5px 0; /* Réduit l'espace vertical entre les champs */
		    border-radius: 5px;
		    border: 1px solid #ccc;
		    font-size: 14px;
		}
		
		button {
		    width: 100%; /* Bouton centré et adaptatif */
		    padding: 10px;
		    background-color: #009879;
		    color: white;
		    border: none;
		    cursor: pointer;
		    border-radius: 5px;
		}
		
		button:hover {
		    background-color: #007b67;
		}

    </style>
</head>
<body>

<div class="container">
    <h2>Ajouter un Evaluation</h2>
    <form action="evaluations" method="post">
        <input type="hidden" name="action" value="ajouter">
        
        <label>ID Evaluation:</label>
        <input type="text" name="id" required>
        <br>
        <label>ID Responsable:</label>
        <input type="text" name="responsableId" required>
        <br>
        <label>ID Employé:</label>
        <input type="text" name="employeId" required>
		<br>
		<label>Note:</label>
        <input type="text" name="note" required>
		<br>
        <label>Commentaire:</label>
        <textarea name="commentaire" required></textarea>
		<br>
		<label for="dateEvaluation">Date:</label>
	    <input type="date" name="dateEvaluation" required>
		<br>
        <button type="submit" class="btn">Ajouter</button>
    </form>
</div>

</body>
</html>
