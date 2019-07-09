/**
 * 
 */
let articulosLista = new Array();
let articulosOrden = new Array();
let idCliente;

$(document).ready(function() {
	$('#contenedor-orden').hide();
});

function mostrarModalArticulos() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8085/articulo/buscar/",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(articulos) {
			$('#lista-articulos tbody').empty();
			articulosLista = articulos.slice(0);
			articulos.forEach(function(articulo) {
				let opcionArticulo = `<tr><td>${articulo.idArticulo}</td><td>${articulo.nombreArticulo}</td><td><a class="ui small basic icon button" id-articulo="${articulo.idArticulo}" onclick="agregarArticuloOrden(this);"><i class="icon plus circle"></i></a></td>`;
				$('#lista-articulos tbody').append(opcionArticulo);
			});
			$('.ui.small.modal').modal('show');
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Request: " + XMLHttpRequest.toString() + "\n\nStatus: "
					+ textStatus + "\n\nError: " + errorThrown);
		}
	});
};

function nuevaOrden() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8085/cliente/buscar/",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(clientes) {
			let opcionDefault = `<option value="0">-Seleccione un cliente-</option>`;
			$('select').empty().append(opcionDefault);
			clientes.forEach(function(cliente) {
				let opcionCliente = `<option value="${cliente.idCliente}">${cliente.nombreCliente}</option>`;
				$('select').append(opcionCliente);
			});
			$('#contenedor-lista-ordenes').fadeOut('fast',function(){
				$('#contenedor-orden').fadeIn();
			});
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Request: " + XMLHttpRequest.toString() + "\n\nStatus: "
					+ textStatus + "\n\nError: " + errorThrown);
		}
	});
};

$(document).on('change','select', function() {
	idCliente = $(this).children("option:selected").val();
	if(idCliente === '0')
		$('#btn-modal-articulos').addClass('disabled');
	else 
		$('#btn-modal-articulos').removeClass('disabled');
});

function agregarArticuloOrden(that) {
	let idArticulo = $(that).attr('id-articulo')	
	let articuloEncontrado = articulosLista.find(articulo => {return articulo.idArticulo === parseInt(idArticulo);});
	articulosOrden.push(articuloEncontrado);
	verDetalleOrden();
};

function verDetalleOrden() {
	$('#detalle-orden tbody').empty();
	articulosOrden.forEach(articulo => {
		let registro = `<tr><td>${articulo.idArticulo}</td><td>${articulo.nombreArticulo}</td><td>${articulo.precioUnitario}</td>`;
		$('#detalle-orden tbody').append(registro);
	});
};

function crearOrden() {
	let cliente = new Object();
	cliente.idCliente = idCliente;
	
	let detallesOrden = new Array();
	articulosOrden.forEach(articulo => {
		let detalleOrden = new Object();
		detalleOrden.articulo = articulo;
		detallesOrden.push(detalleOrden);
	});
	
	let orden = new Object();
	orden.detallesOrden = detallesOrden;
	orden.cliente = cliente;
	console.log(JSON.stringify(orden));
	$.ajax({
		type : "POST",
		url : "http://localhost:8085/orden/crear",
		data : JSON.stringify(orden),
		async : true,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(models) {
			window.location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Request: " + XMLHttpRequest.toString() + "\n\nStatus: "
					+ textStatus + "\n\nError: " + errorThrown);
		}
	});
};

function cancelarOrden(){
	$('#contenedor-orden').fadeOut('fast',function(){
		$('#contenedor-lista-ordenes').fadeIn();
	});
};