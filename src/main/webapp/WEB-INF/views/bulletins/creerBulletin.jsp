<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<title>Creation d'employe</title>
</head>
<body>
	<nav>
		<a href="<c:url value='../employes/lister'/>">Employés</a>
		<a href="<c:url value='/bulletins'/>">Bulletin</a>
	</nav>
	<h1>Ajouter un Bulletin</h1>
    <div class="container-fluid">
        <form:form id="principalForm" class="form-horizontal" modelAttribute="bulletin" method="post" novalidate="novalidate" style="text-align:center;margin:auto;">
        	<div class="row">
                <label for="periode">Période</label>
                <div class="col">
					<form:select name="periode" id="periodes" path="periode.id">
					<c:forEach var="periode" items="${periodes}">
						<form:option value="${periode.id}">${ periode.dateDebut} - ${periode.dateFin}</form:option>
					</c:forEach>
					</form:select> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    La période est obligatoire ! 
                </div> 
            <div class="row">
                <label for="employe">Matricule</label>
                <div class="col">
                    <form:select name="employe" path="remunerationEmploye.id">
						<c:forEach var="remunerationEmploye" items="${employes}">
								<form:option value="${remunerationEmploye.id}">${remunerationEmploye.matricule}</form:option>
						</c:forEach>
					</form:select> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le matricule est obligatoire ! 
                </div>    
            </div>
            <div class="row">
                <div class="col-2">Prime Exceptionnelle</div>
                <div class="col">
					<form:input path="primeExceptionnelle"></form:input>
                </div>
            </div>
     		 </div>
      
            <div class="row">
                <div class="offset-6">
                    <button type="submit" class="btn btn-info btn-lg">Ajouter</button>
                </div>
            </div> 
        </form:form>
    </div>

</body>
</html>