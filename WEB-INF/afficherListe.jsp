<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
		<link type="text/css" rel="stylesheet" href="inc/StyleList.css" />
		<title>Annuaire - Liste des contacts</title>
	</head>
	<body>
		<div class="Container">
			<div class="listeContact">
				<div id="menuListe">
					<c:if test="${requestScope.listeRechercher==null }"> <!-- on affiche le nombre de contact uniquement lorsque qu'on affiche la liste des contacts complète -->
					<p id="pL"> Nombre de contacts : ${ sessionScope.annuaire.nbContact } </p>
					</c:if>
					<p id="pR"><img id="plus" src="img/AddIcon.png"/><a href="<c:url value="/ModifierServlet"></c:url>">Nouveau contact</a></p>
				</div>
				<ul>
					<c:forEach var="i" items="${requestScope.listeRechercher==null ? sessionScope.annuaire.listeContact :requestScope.listeRechercher}" > <!-- On affiche chaque item de la liste de contact ou de la liste des contacts correspondants à la recherche -->
						<li class="ligne">
							<p><a href="<c:url value='/AfficherFiche'><c:param name="idContact" value='${ i.id }'/></c:url>">${i.nom} ${i.prenom}</a></p>
							<p class="option"><a  id="hidden" href="<c:url value='/SupprimerServlet'><c:param name="idSuppr" value='${ i.id }'/></c:url>">Supprimer</a> 
							<a id="hidden" href="<c:url value='/ModifierServlet'><c:param name="idModif" value='${ i.id }'/></c:url>">Modifier</a></p>
						</li>
					</c:forEach>
				</ul>
				
				<c:if test="${requestScope.listeRechercher != null}">
					<a href="<c:url value='/Rechercher'><c:param name='resetList' value='true'/></c:url>">Afficher tous les contacts</a>
				</c:if>
			</div>
			
			
			<div class="alignBouton">
				<form method="GET" action="Rechercher">
			           <input id="inputL" class="bouton" type="submit" value="Rechercher"  />
				</form>
					
				<form method="post" action="AuthentificationServlet">
					<input type="hidden" name="deconnection" id="deconnection" value="true"/>
					<input id="inputR" class="bouton" type="submit" value="Déconnexion"/>
				</form>
			</div>
		</div>
		
		
		<div id="ficheContact"> <!-- Fiche du contact sélectionné -->
			<c:if test="${ requestScope.currentContact != null}">
		    	<div id="contactHeader"><p id="icon"><img src="img/contactIcon.png"/></p><p id="nomP"><c:out value="${ requestScope.currentContact.nom }"/> <c:out value="${ requestScope.currentContact.prenom }"/></p></div>
		    	<ul>
					<li class="liC"><p class="bold">Telephone :</p> <p><c:out value="${ requestScope.currentContact.telephone }"/></p></li>
					<li class="liC"><p class="bold">Adresse :</p> <p><c:out value="${ requestScope.currentContact.adresse }"/></p></li>
					<li class="liC"><p class="bold">Email :</p> <p><c:out value="${ requestScope.currentContact.email }"/></p></li>
				</ul>
			</c:if>
		</div>

		
		
	</body>
</html>