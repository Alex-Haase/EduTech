// Este archivo contiene el código JavaScript para la gestión de libros en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
const API_URL = "http://localhost:8080/api/v1/cursos"; // URL de la API para acceder a los libros
// Función para listar los libros en la tabla
// Se utiliza la API Fetch para obtener los datos de los libros desde el servidor
function listarCursos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(Cursos => {
            const tbody = document.querySelector("#tablaCursos tbody");
            tbody.innerHTML = "";
            Cursos.forEach(curso => {
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.titulo}</td>
                        <td>${curso.descripcion}</td>
                        <td>${curso.fechaInicio}</td>
                        <td>${curso.fechaTermino}</td>
                        <td>${curso.capacidad}</td>
                        <td>${curso.profesor}</td>
                        <td>${curso.precio}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${curso.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarCurso(${curso.id})">✏️ Editar</button> 
                            <button class="btn btn-success btn-sm" onclick="carrito.agregarCurso(${curso.id})">🛒 Añadir</button>
                        </td>
                    </tr>
                `; 
                tbody.innerHTML += fila;
            });
        });
}
let Cursos = []; // Variable para almacenar la lista de cursos
// Función para agregar un curso
function agregarCurso() {
    const titulo = document.getElementById("titulo").value;
    const descripcion= document.getElementById("descripcion").value;
    const fechaInicio = document.getElementById("fechaInicio").value;
    const fechaTermino = document.getElementById("fechaTermino").value;
    const capacidad = document.getElementById("capacidad").value;
    const profesor = document.getElementById("profesor").value;
    const precio = document.getElementById("precio").value;
    
    const nuevoCurso = {
        titulo,
        descripcion,
        fechaInicio: parseInt(fechaInicio) || new Date().getFullYear(),
        fechaTermino: parseInt(fechaTermino) || new Date().getFullYear(),
        capacidad,
        profesor,
        precio: parseInt(precio) || 0 // Asegurarse de que el precio sea un número
    };
    // Enviar el nuevo curso al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoCurso)
    })// Enviar el nuevo curso al servidor
    .then(response => response.json())
    .then(data => {
        alert("Curso agregado exitosamente");
        listarCursos();// Actualizar la tabla de cursos
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un curso
function eliminarCurso(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Curso eliminado exitosamente");
                listarCursos();
            }
        });
}
// Función para buscar un curso por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del curso desde el servidor
let cursoEnEdicionId = null; // Variable para almacenar el ID del curso en edición
function buscarLibro(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(curso => {
            document.getElementById("titulo").value = curso.titulo;
            document.getElementById("descripcion").value = curso.descripcion;
            document.getElementById("fechaInicio").value = curso.fechaInicio;
            document.getElementById("fechaTermino").value = curso.fechaTermino;
            document.getElementById("capacidad").value = curso.capacidad;
            document.getElementById("profesor").value = curso.profesor;
            document.getElementById("precio").value = curso.precio;
            
            // Guardar el ID del curso en edición
             cursoEnEdicionId = curso.id;
             
            // Cambiar el botón de agregar por actualizar
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Curso";
                boton.onclick = function() {
                    actualizarCurso(curso.id);
                };
            }
        });
}
// Función para actualizar un curso
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarCurso(id) {
    const titulo = document.getElementById("titulo").value;
    const descripcion= document.getElementById("descripcion").value;
    const fechaInicio = document.getElementById("fechaInicio").value;
    const fechaTermino = document.getElementById("fechaTermino").value;
    const capacidad = document.getElementById("capacidad").value;
    const profesor = document.getElementById("profesor").value;
    const precio = document.getElementById("precio").value;

    const cursoActualizado = {
        id: id,
        titulo: titulo,
        descripcion: descripcion,
        fechaInicio: parseInt(fechaInicio) || new Date().getFullYear() ,
        fechaTermino: parseInt(fechaTermino) || new Date().getFullYear(),
        capacidad: capacidad,
        profesor,profesor,
        precio: parseInt(precio) || 0 // Asegurarse de que el precio sea un número
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cursoActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Curso actualizado exitosamente");
        listarCursos();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un libro
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("titulo").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("fechaInicio").value = "";
    document.getElementById("fechaTermino").value = "";
    document.getElementById("capacidad").value = "";
    document.getElementById("profesor").value = "";
    document.getElementById("precio").value = "";

    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Curso";
    boton.setAttribute("onclick", "agregarCurso()");

    // Resetear la variable global
    cursoEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar cursos al abrir la página

listarCursos();
