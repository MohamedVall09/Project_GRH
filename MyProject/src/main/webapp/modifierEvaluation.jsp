<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hr.models.Evaluation, com.hr.data.EvaluationRepository" %>

<%
    String id = request.getParameter("id");
    Evaluation evaluation = EvaluationRepository.getEvaluationById(id);

    if (evaluation == null) {
        response.sendRedirect("evaluations.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier une évaluation</title>
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
    <h2>Modifier Evaluation</h2>
    <form action="evaluations" method="post">
        <input type="hidden" name="action" value="modifier">
		<br>
		<label>Note:</label>
        <input type="text" name="note" value="<%= evaluation.getNote() %>" required>
		<br>
        <label>Commentaire:</label>
        <textarea name="commentaire" required><%= evaluation.getCommentaire() %></textarea>
		<br>
		<label>Date:</label>
        <input type="date" name="dateEvaluation" required><%= evaluation.getDateEvaluation() %></input>
		<br>
        <button type="submit" class="btn">Modifier</button>
    </form>
</div>

</body>
</html>
