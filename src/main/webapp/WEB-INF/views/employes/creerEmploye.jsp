<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!--  Création des variables de liens aux sources xml et aux beans initiaux. 
	<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
	Ne fait qu'un import, ne transform pas en liste d'objets.
	<c:url value="classpath:entreprises.xml" var="urlEntreprises" />
	<c:url value="classpath:grades.xml" var="urlGrades" />
	<c:url value="classpath:profils-remuneration.xml" var="urlProfils" />
	<c:import var="xmlEntreprises" url="${urlEntreprises}" />
	<c:import var="xmlGrades" url="${urlGrades}" />
	<c:import var="xmlprofils" url="${urlProfils}" />
		-->
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
		<a href="<c:url value='/lister'/>">Employés</a>
		<a href="<c:url value='/bulletin'/>">Bulletin</a>
	</nav>
	<h1>Ajouter un Employé</h1>
    <div class="container-fluid">
        <form:form id="principalForm" class="form-horizontal" modelAttribute="employe" method="post" novalidate="novalidate">
            <div class="row m-3">
                <div class="col-2 offset-2">Matricule</div>
                <div class="col">
                    <form:input required="required" path="matricule"/>
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le matricule est obligatoire ! 
                </div>              
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Entreprise</div>
                <div class="col">
                    <form:select id="entreprises" path="entreprise.id">
						<c:forEach var="entreprise" items="${entreprises}">
								<!--  ${entreprise.denomination} -->
								<form:option value="${entreprise.id}">${entreprise.denomination }</form:option>
						</c:forEach>
					</form:select> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le nom d'entreprise est obligatoire ! 
                </div>    
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Profil</div>
                <div class="col">

					<form:select id="profils" path="profilRemuneration.id" >
					<c:forEach var="typeProfil" items="${profils}">
					<!--  ${typeProfil.code} -->
						<form:option value="${typeProfil.id}">${typeProfil.code}</form:option>
					</c:forEach>
					</form:select> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le profil de rémunératione est attendu ici. 
                </div>    
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Grade</div>
                <div class="col">
					<form:select id="grades" path="grade.id">
					<f:setLocale value = "fr_FR"/>
					<c:forEach var="grade" items="${grades}">
						<f:formatNumber value = "${grade.tauxBase*grade.nbHeuresBase*12}" type = "currency" pattern="# ¤" var="gradeInfos" />
						<!-- ${grade.code} - ${gradeInfos} / an  -->
						<form:option value="${grade.id}">${grade.code} - ${gradeInfos} / an</form:option>
					</c:forEach>
					</form:select> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le Grade est obligatoire ! 
                </div>    
            </div>
      
            <div class="row m-3">
                <div class="offset-6">
                    <button type="submit" class="btn btn-info btn-lg">Ajouter</button>
                </div>
            </div> 
        </form:form>
    </div>

</body>
</html>