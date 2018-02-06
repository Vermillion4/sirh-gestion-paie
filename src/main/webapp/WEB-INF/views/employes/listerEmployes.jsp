<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-4.0.0/dist/css/bootstrap.css">
	</head>
	<title>Liste des employes.</title>
</head>
<body>
	<h1>Liste des employés</h1>
	<form action="creer">
   		<input type="submit" value="Ajouter un employe" />
	</form>
	<table class="container" border="1">
		<thead>
			<tr class="row">
			<th class="col">Date/heure création</th>
			<th class="col">Matricule</th>
			<th class="col">Grade</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employe" items="${employes}">
				<tr class="row">
					<td class="col">${employe.date}</td>
					<td class="col">${employe.matricule}</td>
					<td class="col">${employe.grade.code}</td>
				</tr>
			</c:forEach> 
		</tbody>
		<tfoot>
		</tfoot>
	</table>
</body>
</html>