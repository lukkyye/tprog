

document.getElementById("form--submit").addEventListener("click", 
    function() {
        let elem = document.getElementById("form--id--textfield").value;
       let text = parseInt(elem);
       if (Number.isNaN(text) || elem.length!==8){
           alert("Por favor, ingrese una cédula de identidad valida.")
        } 
    }
);