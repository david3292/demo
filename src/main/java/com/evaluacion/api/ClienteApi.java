package com.evaluacion.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.model.Cliente;
import com.evaluacion.service.IClienteService;
import com.evaluacion.util.Respuesta;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteApi {

	@Autowired
	private IClienteService servicio;
	
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> crearCliente(@RequestBody Cliente cliente){
		Respuesta respuesta = new Respuesta();
		try {
			this.servicio.create(cliente);
			respuesta.setCodigo(Respuesta.CORRECTO);
			respuesta.setMensaje("Cliente guardado correctamente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar guardar al cliente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/buscar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable("idCliente")Integer idCliente){
		try {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			cliente = this.servicio.findById(cliente);			
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Cliente>(new Cliente(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> buscarTodos(){
		try {
			List<Cliente> clientes = this.servicio.findAll();
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Cliente>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/eliminar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminarCliente(@PathVariable("idCliente")Integer idCliente){
		Respuesta respuesta = new Respuesta();
		try {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			this.servicio.delete(cliente);
			respuesta.setCodigo(Respuesta.CORRECTO);
			respuesta.setMensaje("Cliente eliminado correctamente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar elimianr el cliente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
