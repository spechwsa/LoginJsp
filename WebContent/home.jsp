<%@page import="com.wsa.bean.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Connexion</title>
</head>
<body>
hello
<c:choose>
	<%-- Si l'utilisateur existe en session, 2 possibilité inscription ou conexion--%>
	 <c:when test="${empty sessionScope.sessionUtilisateur}">
		<a href="Connexion">Connexion</a>
		<a href="Inscription">Inscription</a>	
	</c:when>
	<c:otherwise>
		<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
		<p class="succes">Bonjour monsieur ${sessionScope.sessionUtilisateur.nom}</p>
		<p class="succes">Vous êtes déjà connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
		<hr />
		<h1>Bienvenu sur le site</h1>
	</c:otherwise>
</c:choose>


</body>
</html>