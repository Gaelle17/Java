<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>Liste recipes</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<h1>Il y a ${nbRec} Recetes :</h1>
			<a href="createRecipe">Add New</a>
			<table class="table">
			
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Nom</th>
						<th>Description</th>
						<th>Difficulté</th>
						<th>temps de preparation</th>
						<th>Créateur</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="r" items="${recipes}">
							<tr onclick="location.href = 'http://localhost:8080/RecipeManager/showRecipe?id=${r.getId()}';">
								<th>${r.getId()}</th>
								<td>${r.getName()}</td>
								<td>${r.getDescription()}</td>
								<td>${r.getDifficultyLevel()}</td>
								<td>${r.getPreparationTime()}</td>
								<td>${r.getOwner().getUsername()}</td>
							</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
	</body>
</html>