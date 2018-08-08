<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<title>Login page</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="navigate.jsp" %>
			<form action="/RecipeManager/login" method="post">
				<input name="username" type="text" placeholder="username" />
				<input name="pwd" type="password" placeholder="password" />
				<input type="submit" value="Send" class="button"/>
			</form>
		</div>
	</body>
</html>