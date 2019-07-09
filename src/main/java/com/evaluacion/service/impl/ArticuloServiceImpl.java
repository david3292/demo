package com.evaluacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.model.Articulo;
import com.evaluacion.repository.IArticuloRepo;
import com.evaluacion.service.IArticuloService;

@Service
public class ArticuloServiceImpl implements IArticuloService{

	@Autowired
	private IArticuloRepo repository;

	@Override
	public List<Articulo> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Articulo create(Articulo articulo) {
		return this.repository.save(articulo);
	}

	@Override
	public Articulo findById(Articulo articulo) {
		return this.repository.findById(articulo.getIdArticulo()).orElse(new Articulo());
	}

	@Override
	public Articulo update(Articulo articulo) {
		return this.repository.save(articulo);
	}

	@Override
	public void delete(Articulo articulo) {
		this.repository.delete(articulo);
	}
	
}
