<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
		<title>Annuaire - Connexion</title>
	</head>
	<body>
		<!-- On test si la jsp doit afficher le formulaire de connexion ou le formulaire d'inscription -->
		<c:choose>
			<c:when test="${requestScope.etat=='create' }">
				<div class="case-id">
	            <form method="POST" action="AuthentificationServlet">
	                <fieldset>
	                    <legend>Inscription</legend>
	    
	                    <label for="login">Identifiant </label>
	                    <input type="text" id="login" name="login" value="" size="20" />
	                    <br />
	                    
	                    <label for="pass">Mot de passe </label>
	                    <input type="password" id="pass" name="pass" value="" size="20" />
	                    <br />
	                    
	                    <label for="passConfirm">Confirmer le mot de passe </label>
	                    <input type="password" id="passConfirm" name="passConfirm" value="" size="20" />
	                    <br />
	                    
	                    <c:if test="${requestScope.erreur != 'null'}">
         					<p class="error">${requestScope.erreur}</p>
      					</c:if>
	
	                </fieldset>
	                <div class="align">
		                <input class="bouton" type="submit" value="Confirmer" /> 
		                <p><a class="link" href="<c:url value='/AuthentificationServlet'><c:param name='etat' value='null'/></c:url>">Retour</a></p>
	            	</div>
	            </form>
        	</div>
			</c:when>
			<c:otherwise>
				<div class="case-id">
	            <form method="POST" action="AuthentificationServlet">
	                <fieldset>
	                    <legend>Identification</legend>
	    
	                    <label for="login">Identifiant </label>
	                    <input type="text" id="login" name="login" value="" size="20" />
	                    <br />
	                    
	                    <label for="pass">Mot de passe </label>
	                    <input type="password" id="pass" name="pass" value="" size="20"/>
	                    <br />
	                    
	                    <c:if test = "${requestScope.erreur != null}"><!-- on test si il y a eu une erreur si oui on affiche -->
         					<p class="error">${requestScope.erreur}</p>
      					</c:if>
      					<c:if test = "${requestScope.succes != null}"><!-- on test si il y a eu une erreur si oui on affiche -->
         					<p class="succes">${requestScope.succes}</p>
      					</c:if>
	
	                </fieldset>
	                <div class="align">
		                <input class="bouton" type="submit" value="Connexion" />
		                <!-- on cree un parametre createuUser dans le but de savoir si on clique sur le lien inscriptions -->
		            	<p><a class="link" href="<c:url value='/AuthentificationServlet'><c:param name='createUser' value='true'/></c:url>">Inscription</a></p>
	           		</div>
	            </form>
        	</div>
			</c:otherwise>
		</c:choose>
		
		
		
	</body>
</html>