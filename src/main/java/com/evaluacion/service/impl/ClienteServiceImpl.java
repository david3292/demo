package com.evaluacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.model.Cliente;
import com.evaluacion.repository.IClienteRepo;
import com.evaluacion.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepo repository;

	@Override
	public List<Cliente> findAll() {		
		return this.repository.findAll();
	}

	@Override
	public Cliente create(Cliente cliente) {
		return this.repository.save(cliente);
	}

	@Override
	public Cliente findById(Cliente cliente) {
		return this.repository.findById(cliente.getIdCliente()).orElse(new Cliente());
	}

	@Override
	public Cliente update(Cliente cliente) {		
		return this.repository.save(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		this.repository.delete(cliente);
	}
	
	
	
}
