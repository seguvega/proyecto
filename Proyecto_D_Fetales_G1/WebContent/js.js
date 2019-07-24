function validar(){
	var email;
	email = document.getElementById("txtCorreo").value;
	expresion = /\w+@\w+\.+[a-z]/;
	
	if(!expresion.test(email)){
		alert("El correo no es válido");
		return;
	}
}