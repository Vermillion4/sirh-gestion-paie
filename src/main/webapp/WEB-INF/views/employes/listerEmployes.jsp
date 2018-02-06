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
	<a href="/creer"><button type="input" value="Ajouter un employe"></button></a>
	<table>
		<thead>
			<tr>
			<th>Date/heure création</th>
			<th>Matricule</th>
			<th>Grade</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employe" items="${employes}">
				<tr>
					<td>employe.date</td>
					<td>employe.matricule</td>
					<td>employe.grade</td>
				</tr>
			</c:forEach> 
		</tbody>
		<tfoot>
		</tfoot>
	</table>
</body>
</html>