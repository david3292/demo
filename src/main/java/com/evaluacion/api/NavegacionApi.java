package com.evaluacion.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.evaluacion.model.Articulo;
import com.evaluacion.model.Cliente;
import com.evaluacion.model.Orden;
import com.evaluacion.service.IArticuloService;
import com.evaluacion.service.IClienteService;
import com.evaluacion.service.IOrdenService;

@Controller
public class NavegacionApi {
	
	@Autowired
	private IClienteService clienteServicio;
	
	@Autowired
	private IArticuloService articuloService;
	
	@Autowired
	private IOrdenService ordenServicio;

	@RequestMapping(value = { "/", "/cliente**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		List<Cliente> clientes = clienteServicio.findAll();
		
		ModelAndView model = new ModelAndView();
		model.addObject("clientes", clientes);
		model.setViewName("cliente");
		return model;

	}
	
	@RequestMapping(value = { "/articulo"}, method = RequestMethod.GET)
	public ModelAndView articuloPage() {

		List<Articulo> articulos = articuloService.findAll();
		
		ModelAndView model = new ModelAndView();
		model.addObject("articulos", articulos);
		model.setViewName("articulo");
		return model;

	}
	
	@RequestMapping(value = { "/orden"}, method = RequestMethod.GET)
	public ModelAndView ordenPage() {

		List<Orden> ordenes = ordenServicio.findAll();
		
		ModelAndView model = new ModelAndView();
		model.addObject("ordenes", ordenes);
		model.setViewName("orden");
		return model;

	}
	
}
