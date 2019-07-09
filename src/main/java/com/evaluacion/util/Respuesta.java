package com.evaluacion.util;

public class Respuesta {
	
	public static final String CORRECTO = "CORRECTO";
	public static final String ERROR = "ERROR";

	private String codigo;
	
	private String mensaje;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
