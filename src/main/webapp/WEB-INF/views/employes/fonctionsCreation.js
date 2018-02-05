/**
 * 
 */
function valider(){
	
	for(i=0;i<4;i++){
		var element=$('#principalForm')[0].getElementsByTagName("input")[i];
		
		if(element.value==""){
			document.forms[0].setAttribute("action",".?errors");
			document.forms[0].setAttribute("method","POST");
			document.forms[0].submit();
			return;
		}
	}

	var element=document.getElementsByClassName('numSecurite')[0];
	
	if( element.value.length!=15 || !element.value.match("[0-9]{15}") ){
		//routine : 555555555555555
		document.forms[0].action=".?errors";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	}

	document.forms[0].setAttribute("action","./creerCollaborateur");
	document.forms[0].setAttribute("method","POST");
	document.forms[0].submit();
			
}