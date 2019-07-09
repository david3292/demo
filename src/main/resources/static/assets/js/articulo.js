/**
 * 
 */
$(document).ready(function() {
	$(".decimal").inputmask({
		'alias' : 'decimal',
		rightAlign : true,
		'groupSeparator' : '.',
		'autoGroup' : true
	});
});

function mostrarModal(opcion) {
	if (opcion === 1)
		resetearArticuloVista();

	$('.ui.small.modal').modal({
		onApprove : function() {
			let idArticulo = $('#id-articulo').val();
			if (idArticulo === '')
				idArticulo = null;
			crearModificarArticulo({
				"idArticulo" : idArticulo,
				"nombreArticulo" : $('#nombre-articulo').val(),
				"cantidadDisponible" : $('#cantidad-articulo').val(),
				"precioUnitario" : $('#precio-unitario').val()
			});
		}
	}).modal('show');
}

function crearModificarArticulo(articulo) {
	$.ajax({
		type : "POST",
		url : "http://localhost:8085/articulo/crear",
		data : JSON.stringify(articulo),
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

function verArticulo(that) {
	let urlArticulo = "http://localhost:8085/articulo/buscar/"
			+ $(that).attr('id-articulo');
	console.log(urlArticulo);
	$.ajax({
		type : "GET",
		url : urlArticulo,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(articulo) {
			$('#id-articulo').val(articulo.idArticulo);
			$('#nombre-articulo').val(articulo.nombreArticulo);
			$('#cantidad-articulo').val(articulo.nombreArticulo);
			$('#precio-unitario').val(articulo.precioUnitario);
			$('#modal-titulo').text('Modificar Articulo');
			$('#btn-articulo').text('Modificar');
			mostrarModal(2);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Request: " + XMLHttpRequest.toString() + "\n\nStatus: "
					+ textStatus + "\n\nError: " + errorThrown);
		}
	});
};

function eliminarArticulo(that) {
	let urlArticulo = "http://localhost:8085/articulo/eliminar/"
			+ $(that).attr('id-articulo');
	$.ajax({
		type : "DELETE",
		url : urlArticulo,
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

function resetearArticuloVista() {
	$('#id-articulo').val('');
	$('#nombre-articulo').val('');
	$('#cantidad-articulo').val('');
	$('#precio-unitario').val('');
	$('#modal-titulo').text('Crear Artículo');
	$('#btn-articulo').text('Aceptar');
};