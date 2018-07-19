package br.com.ufba.atendimento;

import java.io.Serializable;

public class Senha implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo; // P - Preferencial; N - Normal
	
	public Senha() {}
	
	public Senha(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
