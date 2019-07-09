package com.evaluacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.model.Orden;
import com.evaluacion.repository.IOrdenRepo;
import com.evaluacion.service.IOrdenService;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenRepo repository;
	
	@Override
	public List<Orden> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Orden create(Orden orden) {
		return this.repository.save(orden);
	}

	@Override
	public Orden findById(Orden orden) {
		return this.repository.findById(orden.getIdOrden()).orElse(new Orden());
	}

	@Override
	public Orden update(Orden orden) {
		return this.repository.save(orden);
	}

	@Override
	public void delete(Orden orden) {
		this.repository.delete(orden);
		
	}

}
