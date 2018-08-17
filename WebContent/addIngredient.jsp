<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>Create recipe</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<form action="addIngredient" method="post">
				<input name="name" type="text" placeholder="nom de l'ingredient" />
				<input name="price" type="number" placeholder="prix" />
				<input type="submit" value="Ajouter" class="button"/>
			</form>
		</div>
	</body>
</html>