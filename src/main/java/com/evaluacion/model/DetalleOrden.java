package com.evaluacion.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "detalleorden")
@Table(name = "detalle_orden")
public class DetalleOrden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_orden", nullable = false)
	private Integer idDetalleOrden;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_articulo", nullable = false, updatable = false)
	private Articulo articulo;
		
	public Integer getIdDetalleOrden() {
		return idDetalleOrden;
	}
	
	public void setIdDetalleOrden(Integer idDetalleOrden) {
		this.idDetalleOrden = idDetalleOrden;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
}
