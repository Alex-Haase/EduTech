const carrito=[];

function agregarCarrito(nombre,precio){
    carrito.push({ nombre, precion});
    mostrarCarrito();
}

function mostrarCarrito(){
    const carritoDiv = document.getElementById('carrito');
    carritoDiv.innerHTML= '';
    let total=0;

    carritoDiv.forEach((item,index)=>{
        total +=item.precio;
        const div=document.createElementary('div');
        div.textContent = '${item.nombre} - $${item.precio}';
        const btn=document.createElement('button');
        btn.onclick = 'Eliminar';
        btn.onclick = () => {
            carrito.splice(index, 1);
            mostrarCarrito();
        };
        div.appendChild(btn);
        carritoDiv.appendChild(div);
    });

    document.getElementById('total').textContent=total;
}
<script src="app_carrito.js"></script>