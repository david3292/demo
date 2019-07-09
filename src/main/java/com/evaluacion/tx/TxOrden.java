package com.evaluacion.tx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evaluacion.model.Articulo;
import com.evaluacion.model.Orden;
import com.evaluacion.service.IArticuloService;
import com.evaluacion.service.IOrdenService;
import com.evaluacion.util.Respuesta;

@Component
public class TxOrden {

	@Autowired
	private IOrdenService ordenServicio;
	
	@Autowired
	private IArticuloService articuloServicio;
	
	@Transactional
	public Respuesta txOrden(Orden orden) {
			Respuesta respuesta = new Respuesta();
			try {
				orden.setFechaCompra(LocalDate.now());
				List<Articulo> listaArticulos = new ArrayList<>();
				orden.getDetallesOrden().forEach(x -> listaArticulos.add(x.getArticulo()));
				listaArticulos.parallelStream().forEach(x -> x.setCantidadDisponible(x.getCantidadDisponible() - 1));
				
				ordenServicio.create(orden);
				listaArticulos.forEach(x -> articuloServicio.update(x));
				respuesta.setCodigo(Respuesta.CORRECTO);
				respuesta.setMensaje("Orden guardada correctamente");
				
			} catch (Exception e) {
				respuesta.setCodigo(Respuesta.ERROR);
				respuesta.setMensaje("No se ha podido guardar la orden");
			}
			return respuesta;
	}
}
