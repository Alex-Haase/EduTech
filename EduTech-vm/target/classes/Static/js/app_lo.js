API_URL = "http://localhost:8080/api/v1/usuarios/login";

function login() {
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },// Establece el tipo de contenido a JSON
        // Envía el cuerpo de la solicitud como JSON
        body: JSON.stringify({
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => response.json())
    .then(data => {// Maneja la respuesta del servidor
        if (data.result === "OK") {
            // Guarda el nombre en sessionStorage
            sessionStorage.setItem("nombreUsuario", data.nombre);
            // Redirige al index sin pasar parámetros por URL
            window.location.href = "/index.html";
        } else {
            alert("Acceso denegado");
        }
    });
}
