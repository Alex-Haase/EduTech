// Módulo carrito: gestiona agregar, eliminar, vaciar y confirmar cursos en el carrito
const carrito = (() => {
    const API = "/api/v1/carrito";  // Endpoint base del backend para el carrito

    // Cargar y mostrar los cursos que están actualmente en el carrito
    async function listarCarrito() {
        try {
            const response = await fetch(API); 
            const cursos = await response.json();

            // Elementos del DOM donde se mostrará la info del carrito
            const tbody = document.querySelector("#tablaCarrito tbody");
            const totalSpan = document.getElementById("totalCarrito");
            const totalPrecio = document.getElementById("totalPrecio");

            // Limpiar la tabla actual
            tbody.innerHTML = "";
            totalSpan.textContent = cursos.length; // Mostrar cantidad total de cursos

            let sumaTotal = 0; // Para calcular el total en dinero

            // Agregar cada curso a la tabla
            cursos.forEach(curso => {
                sumaTotal += curso.precio ?? 0;

                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.titulo}</td>
                        <td>${curso.profesor}</td>
                        <td> 
                            <button class="btn btn-sm btn-danger" onclick="carrito.eliminarCurso(${curso.id})">
                                🗑️ Eliminar
                            </button> 
                        </td> 
                    </tr>
                `;
                tbody.innerHTML += fila;
            });

            // Mostrar el total en pantalla
            totalPrecio.textContent = sumaTotal;

        } catch (err) {
            console.error("❌ Error al cargar el carrito:", err);
        }
    }

    // Agrega un curso al carrito
    async function agregarCurso(id) {
        try {
            await fetch(`${API}/agregar/${id}`, { method: "POST" });
            alert("✅ Curso agregado al carrito");
            listarCarrito(); // Actualizar vista
        } catch (err) {
            console.error("❌ Error al agregar curso al carrito:", err);
        }
    }

    // Elimina un curso específico del carrito
    async function eliminarCurso(id) {
        try {
            await fetch(`${API}/eliminar/${id}`, { method: "DELETE" });
            alert("🗑️ Curso eliminado del carrito");
            listarCarrito();
        } catch (err) {
            console.error("❌ Error al eliminar curso del carrito:", err);
        }
    }

    // Vacía completamente el carrito
    async function vaciarCarrito() {
        if (confirm("⚠️ ¿Estás seguro de vaciar el carrito?")) {
            await fetch(`${API}/vaciar`, { method: "DELETE" });
            alert("🧹 Carrito vaciado");
            listarCarrito();
        }
    }

    // Confirma la compra y limpia el carrito
    async function confirmarCompra() {
        const total = document.getElementById("totalPrecio").textContent;
        if (parseInt(total) === 0) {
            alert("El carrito está vacío.");
            return;
        }

        if (confirm(`💸 ¿Deseas confirmar tu compra por $${total}?`)) {
            await fetch(`${API}/vaciar`, { method: "DELETE" });
            alert("✅ ¡Gracias por tu compra/reserva!");
            listarCarrito();
        }
    }

    // Expone funciones públicas del carrito
    return { listarCarrito, agregarCurso, eliminarCurso, vaciarCarrito, confirmarCompra };
})();


// Cuando el documento esté listo, cargar cursos y el carrito
document.addEventListener("DOMContentLoaded", () => {
    listarCursos();        // Este viene de tu módulo de cursos (debe estar definido)
    carrito.listarCarrito();   // Mostrar cursos actuales del carrito
});
