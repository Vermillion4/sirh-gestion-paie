<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
	<script src="fonctionsCreation.js"></script>
	<title>Creation d'employe</title>
</head>
<body>
	<nav>
		<a href="<c:url value='/lister'/>">Employés</a>
		<a href="<c:url value='/bulletin'/>">Bulletin</a>
	</nav>
	<h1>Ajouter un Employé</h1>
    <div class="container-fluid">
        <form id="principalForm" class="form-horizontal" action="">
            <div class="row m-3">
                <div class="col-2 offset-2">Matricule</div>
                <div class="col">
                    <input type="text" required/>
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le matricule est obligatoire ! 
                </div>              
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Entreprise</div>
                <div class="col">
                    <input list="entreprises">
					<datalist id="entreprises">
					<c:forTokens var="nomEntreprise" items="${entreprise.nom}" delims="">
							<option value="${nomEntreprise}">
					</c:forTokens>
					</datalist> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le nom d'entreprise est obligatoire ! 
                </div>    
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Profil</div>
                <div class="col">
                    <input list="profils">
					<datalist id="profils">
					<c:forTokens var="typeProfil" items="${profil.nom}" delims="">
							<option value="${typeProfil}">
					</c:forTokens>
					</datalist> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le profil de rémunératione est attendu ici. 
                </div>    
            </div>
            <div class="row m-3">
                <div class="col-2 offset-2">Grade</div>
                <div class="col">
                    <input list="grades">
					<datalist id="grades">
					<c:forTokens var="grade" items="${grade.code}" delims="">
							<option value="${grade}">
					</c:forTokens>
					</datalist> 
                </div>
                <div class="alert alert-danger" style="display:none">
                    Le Grade est obligatoire ! 
                </div>    
            </div>
      
            <div class="row m-3">
                <div class="offset-6">
                    <!-- Trigger the modal with a button -->
                    <button type="submit" class="btn btn-info btn-lg">Ajouter</button>
                </div>
            </div> 
        </form>
    </div>

</body>
</html>