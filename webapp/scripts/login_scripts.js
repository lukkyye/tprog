document.getElementById("ok").addEventListener("click", (event) => {
    let field1 = document.getElementById("contrasena");
    let field2 = document.getElementById("confirmacion");
    let textfield_ci = document.getElementById("documento");

    if (field1.value !== field2.value) {
        event.preventDefault();
        alert("Las contraseñas no coinciden");
    }
    if (textfield_ci.value.length !== 8){
        event.preventDefault();
        alert("Por favor, ingrese una cedula válida");
    }
}); 
