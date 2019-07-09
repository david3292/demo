/**
 * 
 */

function mostrarModal(opcion) {
	if (opcion === 1)
		resetearClienteVista();

	$('.ui.small.modal').modal({
		onApprove : function() {
			let idCliente = $('#id-cliente').val();
			if (idCliente === '')
				idCliente = null;
			crearModificarCliente({
				"idCliente" : idCliente,
				"nombreCliente" : $('#nombre-cliente').val(),
				"apellidoCliente" : $('#apellido-cliente').val()
			});
		}
	}).modal('show');
}

function crearModificarCliente(cliente) {
	$.ajax({
		type : "POST",
		url : "http://localhost:8085/cliente/crear",
		data : JSON.stringify(cliente),
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

function verCliente(that) {
	let urlCliente = "http://localhost:8085/cliente/buscar/"
			+ $(that).attr('id-cliente');
	console.log(urlCliente);
	$.ajax({
		type : "GET",
		url : urlCliente,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(cliente) {
			$('#id-cliente').val(cliente.idCliente);
			$('#nombre-cliente').val(cliente.nombreCliente);
			$('#apellido-cliente').val(cliente.apellidoCliente);
			$('#modal-titulo').text('Modificar Cliente');
			$('#btn-cliente').text('Modificar');
			mostrarModal(2);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Request: " + XMLHttpRequest.toString() + "\n\nStatus: "
					+ textStatus + "\n\nError: " + errorThrown);
		}
	});
};

function eliminarCliente(that) {
	let urlCliente = "http://localhost:8085/cliente/eliminar/"
			+ $(that).attr('id-cliente');
	$.ajax({
		type : "DELETE",
		url : urlCliente,
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

function resetearClienteVista() {
	$('#id-cliente').val('');
	$('#nombre-cliente').val('');
	$('#apellido-cliente').val('');
	$('#modal-titulo').text('Crear Cliente');
	$('#btn-cliente').text('Aceptar');
};