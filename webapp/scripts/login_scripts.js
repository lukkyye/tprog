document.getElementById("ok").addEventListener("click", (event) => {
    let field1 = document.getElementById("contrasena");
    let field2 = document.getElementById("confirmacion");

    if (field1.value !== field2.value) {
        event.preventDefault();
        alert("Las contraseñas no coinciden");
    }
}); 