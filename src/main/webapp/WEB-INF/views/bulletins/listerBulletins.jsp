<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-4.0.0/dist/css/bootstrap.css">
	</head>
	<title>Liste des bulletins.</title>
</head>
<body>
	<h1>Liste des bulletins</h1>
	<form action="creer">
   		<input type="submit" value="Ajouter un bulletin" />
	</form>
	<table class="container" border="1">
		<thead>
			<tr class="row">
				<th class="col-2">Date/heure création</th>
				<th class="col-2">Période</th>
				<th class="col">Matricule</th>
				<th class="col">Salaire brut</th>
				<th class="col">Net Imposable</th>
				<th class="col">Net A Payer</th>
				<th class="col">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="info" items="${infos}">
				<tr class="row">
					<td class="col-2">${info.key.date}</td>
					<td class="col-2">${info.key.periode.dateDebut} - ${info.key.periode.dateFin}</td>
					<td class="col">${info.key.remunerationEmploye.matricule}</td>
					<td class="col">${info.value.salaireBrut }</td>
					<td class="col">${info.value.netImposable}</td>
					<td class="col">${info.value.netAPayer } </td>
					<td class="col"><a href="<c:url value='/visualiserBulletin${info.key.id}'/>"></a>Visualiser</a></td>
				</tr>
			</c:forEach> 
		</tbody>
		<tfoot>
		</tfoot>
	</table>
</body>
</html>