document.getElementById("submit-button").addEventListener("click", 
    function() {
        let elem = document.getElementById("inputId").value;
       let text = parseInt(elem);
       if (Number.isNaN(text) || elem.length!==8){
           alert("Por favor, ingrese una cédula de identidad valida.")
        } 
    }
);