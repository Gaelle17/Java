<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav">
  	<c:if test="${empty sessionScope.userId}">
		<a class="nav-link active" href="/RecipeManager/login">login</a>
  	</c:if>
  	<c:if test="${!(empty sessionScope.userId)}">
  		<a class="nav-link" href="/RecipeManager/listRecipe">Les recettes</a>
  		<a class="nav-link" href="/RecipeManager/listMyRecipe">Mes recettes</a>
		<a class="nav-link" href="/RecipeManager/auth/"></a>
		<a class="nav-link" href="/RecipeManager/auth/"></a>
  		<a class="nav-link" href="/RecipeManager/logout">Logout</a>
  	</c:if>
</nav>