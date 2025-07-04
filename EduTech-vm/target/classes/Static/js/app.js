const API_URL = "http://localhost:8080/api/v1/cursos";

function listarCursos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(cursos => {
            const tbody = document.querySelector("#tablaCursos tbody");
            tbody.innerHTML = "";
            cursos.forEach(curso => {
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.titulo}</td>
                        <td>${curso.descripcion}</td>
                        <td>${curso.fechaInicio}</td>
                        <td>${curso.fechaTermino}</td>
                        <td>${curso.capacidad}</td>
                        <td>${curso.profesor}</td>
                        <td>$${parseFloat(curso.precio).toFixed(2)}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${curso.id})">🗑️ Eliminar</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarCurso(${curso.id})">✏️ Editar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        })
        .catch(e => {
            alert("Error al cargar cursos: " + e);
            console.error(e);
        });
}

function agregarCurso() {
    const titulo = document.getElementById("titulo").value.trim();
    const descripcion = document.getElementById("descripcion").value.trim();
    const fechaInicio = document.getElementById("fechaInicio").value;
    const fechaTermino = document.getElementById("fechaTermino").value;
    const capacidad = parseInt(document.getElementById("capacidad").value);
    const profesor = document.getElementById("profesor").value.trim();
    const precio = parseFloat(document.getElementById("precio").value);

    if (!titulo || !descripcion || !fechaInicio || !fechaTermino || isNaN(capacidad) || !profesor || isNaN(precio)) {
        alert("Por favor completa todos los campos correctamente.");
        return;
    }

    const nuevoCurso = {
        titulo,
        descripcion,
        fechaInicio,
        fechaTermino,
        capacidad,
        profesor,
        precio
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoCurso)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al agregar curso");
        return res.json();
    })
    .then(data => {
        alert("Curso agregado exitosamente");
        listarCursos();
        limpiarFormulario();
    })
    .catch(e => {
        alert("Error al agregar curso: " + e);
        console.error(e);
    });
}

function eliminarCurso(id) {
    if (!confirm("¿Seguro quieres eliminar este curso?")) return;

    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(res => {
            if (!res.ok) throw new Error("Error al eliminar curso");
            alert("Curso eliminado exitosamente");
            listarCursos();
        })
        .catch(e => {
            alert("Error al eliminar curso: " + e);
            console.error(e);
        });
}

let cursoEnEdicionId = null;

function buscarCurso(id) {
    fetch(`${API_URL}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error("Curso no encontrado");
            return res.json();
        })
        .then(curso => {
            document.getElementById("titulo").value = curso.titulo;
            document.getElementById("descripcion").value = curso.descripcion;
            document.getElementById("fechaInicio").value = curso.fechaInicio;
            document.getElementById("fechaTermino").value = curso.fechaTermino;
            document.getElementById("capacidad").value = curso.capacidad;
            document.getElementById("profesor").value = curso.profesor;
            document.getElementById("precio").value = curso.precio;

            cursoEnEdicionId = curso.id;

            const boton = document.getElementById("botonFormulario");
            boton.textContent = "Actualizar Curso";
            boton.onclick = () => actualizarCurso(curso.id);
        })
        .catch(e => {
            alert("Error al cargar curso: " + e);
            console.error(e);
        });
}

function actualizarCurso(id) {
    const titulo = document.getElementById("titulo").value.trim();
    const descripcion = document.getElementById("descripcion").value.trim();
    const fechaInicio = document.getElementById("fechaInicio").value;
    const fechaTermino = document.getElementById("fechaTermino").value;
    const capacidad = parseInt(document.getElementById("capacidad").value);
    const profesor = document.getElementById("profesor").value.trim();
    const precio = parseFloat(document.getElementById("precio").value);

    if (!titulo || !descripcion || !fechaInicio || !fechaTermino || isNaN(capacidad) || !profesor || isNaN(precio)) {
        alert("Por favor completa todos los campos correctamente.");
        return;
    }

    const cursoActualizado = {
        id,
        titulo,
        descripcion,
        fechaInicio,
        fechaTermino,
        capacidad,
        profesor,
        precio
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cursoActualizado)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al actualizar curso");
        return res.json();
    })
    .then(data => {
        alert("Curso actualizado exitosamente");
        listarCursos();
        limpiarFormulario();
    })
    .catch(e => {
        alert("Error al actualizar curso: " + e);
        console.error(e);
    });
}

function limpiarFormulario() {
    document.getElementById("titulo").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("fechaInicio").value = "";
    document.getElementById("fechaTermino").value = "";
    document.getElementById("capacidad").value = "";
    document.getElementById("profesor").value = "";
    document.getElementById("precio").value = "";

    cursoEnEdicionId = null;

    const boton = document.getElementById("botonFormulario");
    boton.textContent = "Agregar Curso";
    boton.onclick = agregarCurso;
}

// Iniciar cargando cursos al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    listarCursos();
});
