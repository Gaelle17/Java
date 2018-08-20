<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>${recipe.getName()} - ${recipe.getOwner().getUsername()}</title>
	</head>
	<body>
		<form action="" method="POST">
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<input type="text" name="name" value ="${recipe.getName()}"/>
			<div>
				By : ${recipe.getOwner().getUsername()} | 
				Difficulté : <input type="number" name="difficultyLevel" value="${recipe.getDifficultyLevel()}"/> | 
				Preparation : <input type="number" name="preparationTime" value="${recipe.getPreparationTime()}"/> 
				<input type="submit" value="enregistrer"/>
			</div>
			
			<textarea class="description" name="description">${recipe.getDescription()}</textarea>
		</div>
		</form>
	</body>
</html>