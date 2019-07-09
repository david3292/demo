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

import com.evaluacion.model.Articulo;
import com.evaluacion.service.IArticuloService;
import com.evaluacion.util.Respuesta;

@RestController
@RequestMapping(value = "/articulo")
public class ArticuloApi {

	@Autowired
	private IArticuloService servicio;
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> crearCliente(@RequestBody Articulo articulo){
		Respuesta respuesta = new Respuesta();
		try {
			this.servicio.create(articulo);
			respuesta.setCodigo(Respuesta.CORRECTO);
			respuesta.setMensaje("Articulo guardado correctamente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar guardar el artículo");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Articulo>> buscarTodos(){
		try {			
			List<Articulo> articulos = servicio.findAll();
			return new ResponseEntity<List<Articulo>>(articulos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Articulo>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/buscar/{idArticulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Articulo> buscarPorId(@PathVariable("idArticulo")Integer idArticulo){
		try {
			Articulo articulo = new Articulo();
			articulo.setIdArticulo(idArticulo);
			articulo = servicio.findById(articulo);
			return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Articulo>(new Articulo(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/eliminar/{idArticulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> eliminarCliente(@PathVariable("idArticulo")Integer idArticulo){
		Respuesta respuesta = new Respuesta();
		try {
			Articulo articulo = new Articulo();
			articulo.setIdArticulo(idArticulo);
			this.servicio.delete(articulo);
			respuesta.setCodigo(Respuesta.CORRECTO);
			respuesta.setMensaje("Articulo eliminado correctamente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar eliminar el artículo");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
