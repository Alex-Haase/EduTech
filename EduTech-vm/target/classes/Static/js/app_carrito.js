const carrito = (() => {
    const API = "/api/v1/carrito";
    
    async function listarCarrito() {
        try {
            const response = await fetch(API); 
            const cursos = await response.json();

            const tbody = document.querySelector("#tablaCarrito tbody"); // Obtener el tbody de la tabla
            const totalSpan = document.getElementById("totalCarrito"); // Obtener el span del total
            const totalPrecio = document.getElementById("totalPrecio"); // Obtener el span del total precio
            tbody.innerHTML = ""; // Limpiar el tbody antes de agregar nuevos elementos
            totalSpan.textContent = cursos.length; // Mostrar la cantidad de cursos en el carrito

            let sumaTotal = 0;// Inicializar sumaTotal
            
            cursos.forEach(curso => { // Iterar sobre cada curso en el carrito
                sumaTotal += curso.precio ?? 0;// Asumiendo que cada curso tiene un precio
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.titulo}</td>
                        <td>${curso.autor}</td>
                        <td> 
                            <button class="btn btn-sm btn-danger" onclick="carrito.eliminarCurso(${curso.id})">🗑️</button> 
                        </td> 
                    </tr>
                `;
                tbody.innerHTML += fila; // Agregar la fila al tbody
            });
            totalPrecio.textContent = sumaTotal; // Mostrar el total en el span

        } catch (err) {
            console.error("Error al cargar carrito", err);
        }
    }
    // Funciones para agregar, eliminar y vaciar el carrito
    async function agregarCurso(id) {
        try {
            await fetch(`${API}/agregar/${id}`, { method: "POST" });
            alert("Libro agregado al carrito");
            listarCarrito();
        } catch (err) {
            console.error("Error al agregar al carrito", err);
        }
    }

    async function eliminarCurso(id) {
        try {
            await fetch(`${API}/eliminar/${id}`, { method: "DELETE" });
            alert("Curso eliminado del carrito");
            listarCarrito();
        } catch (err) {
            console.error("Error al eliminar del carrito", err);
        }
    }

    async function vaciarCarrito() {
        if (confirm("¿Estás seguro de vaciar el carrito?")) {
            await fetch(`${API}/vaciar`, { method: "DELETE" });
            alert("Carrito vaciado");
            listarCarrito();
        }
    }
    // Función para confirmar la compra
    // Se asume que el precio total se obtiene de la API o se calcula en el frontend
    async function confirmarCompra() {
        const total = document.getElementById("totalPrecio").textContent;
        if (parseInt(total) === 0) {
            alert("El carrito está vacío.");
            return;
        }

        if (confirm(`¿Deseas confirmar tu compra por $${total}?`)) {
            await fetch(`${API}/vaciar`, { method: "DELETE" });
            alert("¡Gracias por tu compra/reserva!");
            listarCarrito();
        }
    }

    return { listarCarrito, agregarCurso, eliminarCurso, vaciarCarrito, confirmarCompra };
})();

// Cargar carrito al iniciar
document.addEventListener("DOMContentLoaded", () => {
    app.listarCursos();        // del módulo anterior
    carrito.listarCarrito();   // nuevo módulo
});
