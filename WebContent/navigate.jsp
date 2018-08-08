<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav">
  	<c:if test="${empty sessionScope.username && empty sessionScope.pwd}">
		<a class="nav-link active" href="/RecipeManager/login">login</a>
  	</c:if>
  	<c:if test="${!(empty sessionScope.username && empty sessionScope.pwd)}">
  		<a class="nav-link active" href="/RecipeManager/auth/"></a>
  		<a class="nav-link" href="/RecipeManager/auth/"></a>
		<a class="nav-link" href="/RecipeManager/auth/"></a>
		<a class="nav-link" href="/RecipeManager/auth/"></a>
  		<a class="nav-link" href="/RecipeManager/lauth/logout">Logout</a>
  	</c:if>
</nav>