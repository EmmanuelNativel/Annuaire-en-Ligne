<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="inc/style.css" />
	<title>Annuaire - Recherche de contact</title>
</head>
<body>

<c:choose>
	<c:when test="${requestScope.type==null }">
		<div class="lien" id="containerType"> <!-- Liste de choix de critère de recherche -->
				<p><a href="<c:url value='/Rechercher'> <c:param name='type' value='nom'/> </c:url>">nom</a></p>
				<p><a href="<c:url value='/Rechercher'> <c:param name='type' value='prenom'/> </c:url>">prenom</a></p>
				<p><a href="<c:url value='/Rechercher'> <c:param name='type' value='telephone'/> </c:url>">telephone</a></p>
				<p><a href="<c:url value='/Rechercher'> <c:param name='type' value='adresse'/> </c:url>">adresse</a></p>
				<p><a href="<c:url value='/Rechercher'> <c:param name='type' value='email'/> </c:url>">email</a></p>
		</div>
	</c:when>
	<c:otherwise>
		<form method="post" action='Rechercher' > <!-- Formulaire de recherche -->
			<div class="case-id">
				<fieldset>
					<label for="champ"> ${requestScope.type} </label >
					<input type='hidden' name='type' id='type' value='${requestScope.type}'>
					<input type=text name='champ' id='champ' size='20' maxlength="20"/>
					<input type='submit' value='Rechercher'/>
				</fieldset>
			</div>
		</form>
	</c:otherwise>
</c:choose>

</body>
</html>