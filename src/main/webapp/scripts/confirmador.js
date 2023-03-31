	/**
 * 
 */

 function confirmar(idcon){
	 let resposta = confirm("Confirma a exclusao deste documento?")
		if(resposta === true) {
		 window.location.href="delete?idcon=" +idcon
		
	}
 }