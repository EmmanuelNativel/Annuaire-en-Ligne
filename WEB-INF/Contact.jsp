<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
		<title>Annuaire - Contact</title>
	</head>
	<body>
		<form method="GET" action="ModifierServlet">
                <fieldset>
                    <legend>${ empty requestScope.idModif ? 'Ajouter un Contact' : 'Modifier le Contact'  }</legend>
    				
    				<input type="hidden" name="id" id="id" value="${  empty requestScope.idModif ? 'new' : requestScope.idModif }"/>
                    <label for="nom">Nom* </label>
                    <input type="text" id="nom" name="nom" value="" size="20" />
                    <br />
                    
                    <label for="prenom">Prenom* </label>
                    <input type="text" id="prenom" name="prenom" value="" size="20"/>
                    <br />
                    
                    <label for="telephone">Telephone </label>
                    <input type="text" id="telephone" name="telephone" value="" size="20"/>
                    <br />
                    
                    <label for="adresse">Adresse </label>
                    <input type="text" id="adresse" name="adresse" value="" size="20" />
                    <br />
                    
                    <label for="email">Email </label>
                    <input type="text" id="email" name="email" value="" size="20" />
                    <br />
                    
                    <c:if test = "${requestScope.erreur != 'null'}">
         				<p class="error">${requestScope.erreur}</p>
      				</c:if>

                </fieldset>
                	<div class="align"><input class="bouton" type="submit" value="Enregistrer"  /></div>
            </form>
	</body>
</html>