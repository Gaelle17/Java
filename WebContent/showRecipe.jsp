<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>${recipe.getName()} - ${recipe.getOwner().getUsername()}</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<h1>${recipe.getName()}</h1>
			<p>
				By : ${recipe.getOwner().getUsername()} | 
				Difficulté : ${recipe.getDifficultyLevel()} | 
				Preparation : ${recipe.getPreparationTime()} |  
				<a href="editRecipe?id=${recipe.getId()}">Editer</a>
			</p>
			
			<div class="description">${recipe.getDescription()}</div>
		</div>
	</body>
</html>