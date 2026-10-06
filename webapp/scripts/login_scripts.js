document.getElementById("ok").addEventListener("click", (event) => {
    let field1 = document.getElementById("contrasena");
    let field2 = document.getElementById("confirmacion");
    let textfield_ci = document.getElementById("documento");
	let textfield_fecha = document.getElementById("fechaNacimiento");
	let especialidades = document.querySelectorAll('input[name="especialidades"]');
	let especialidadesMarcadas = document.querySelectorAll('input[name="especialidades"]:checked');
	let textfield_porcentaje = document.getElementById("porcentajeAdicional");
	
    if (field1.value !== field2.value) {
        event.preventDefault();
        alert("Las contraseñas no coinciden");
    }
    if (textfield_ci && textfield_ci.value.length !== 8){
        event.preventDefault();
        alert("Por favor, ingrese una cedula válida");
    }
	if (textfield_fecha && textfield_fecha.value !== ""){
		let fechaNacimiento = new Date(textfield_fecha.value);
		let hoy = new Date();
		if (fechaNacimiento > hoy){
			event.preventDefault();
			alert("La fecha de nacimiento no puede ser futura");
		}
	}
	if (especialidades.length > 0 && especialidadesMarcadas.length === 0){
		event.preventDefault();
		alert("Debe seleccional al menos una especialidad");
	}
	if (textfield_porcentaje){
		let porcentaje = Number(textfield_porcentaje.value);
		if (textfield_porcentaje.value === "" || porcentaje < 0 || porcentaje > 100){
			event.preventDefault();
			alert("El porcentaje adicional debe estar entre 0 y 100");
		}
	}
}); 
