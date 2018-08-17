<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>Liste Ingredients</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<h1>Il y a ${nbIng} Ingredients :</h1>
			<a href="addIngredient">Add New</a>
			<table class="table">
			
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Nom</th>
						<th>Prix</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="i" items="${ingredients}">
							<tr onclick="/*location.href = 'http://localhost:8080/SupCommerce/showProduct?id=${p.id}'*/;">
								<th>${i.getId()}</th>
								<td>${i.getName()}</td>
								<td>${i.getPrice()}</td>
							</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
	</body>
</html>