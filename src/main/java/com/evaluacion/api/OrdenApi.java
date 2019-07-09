package com.evaluacion.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.model.Orden;
import com.evaluacion.service.IOrdenService;
import com.evaluacion.tx.TxOrden;
import com.evaluacion.util.Respuesta;

@RestController
@RequestMapping(value = "/orden")
public class OrdenApi {

	@Autowired
	private IOrdenService servicio;
	
	@Autowired
	private TxOrden txOrden;
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> crearOrden(@RequestBody Orden orden){
		Respuesta respuesta = new Respuesta();
		try {
			orden.setFechaCompra(LocalDate.now());
			orden.getDetallesOrden().forEach(x -> x.getArticulo().setCantidadDisponible(x.getArticulo().getCantidadDisponible() - 1));
			this.servicio.create(orden);
			respuesta.setCodigo(Respuesta.CORRECTO);
			respuesta.setMensaje("Cliente guardado correctamente");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar guardar la orden");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/crear-tx", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta> crearOrdenTx(@RequestBody Orden orden){
		Respuesta respuesta = new Respuesta();
		try {
			respuesta = txOrden.txOrden(orden);			
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setCodigo(Respuesta.ERROR);
			respuesta.setMensaje("Ha ocurrido un error al intentar guardar la orden");
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/buscar/{idOrden}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orden> buscarPorId(@PathVariable("idOrden")Integer idOrden){
		try {
			Orden orden = new Orden();
			orden.setIdOrden(idOrden);
			orden = this.servicio.findById(orden);			
			return new ResponseEntity<Orden>(orden, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Orden>(new Orden(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
