let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

function guardarCarrito() {
  localStorage.setItem('carrito', JSON.stringify(carrito));
}

function agregarAlCarrito(nombre, precio) {
  carrito.push({ nombre, precio });
  guardarCarrito();
  mostrarCarrito();
}

function mostrarCarrito() {
  const carritoDiv = document.getElementById('carrito');
  carritoDiv.innerHTML = '';
  let total = 0;

  carrito.forEach((item, index) => {
    total += item.precio;
    const div = document.createElement('div');
    div.textContent = `${item.nombre} - $${item.precio}`;
    const btn = document.createElement('button');
    btn.textContent = 'Eliminar';
    btn.onclick = () => {
      carrito.splice(index, 1);
      guardarCarrito();
      mostrarCarrito();
    };
    div.appendChild(btn);
    carritoDiv.appendChild(div);
  });

  document.getElementById('total').textContent = total;
}

// Mostrar carrito al cargar la página
mostrarCarrito();