package br.com.ufba.atendimento;

import java.io.Serializable;
import java.util.Date;

import br.com.ufba.config.APIConfig;

public class Atendimento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String guiche;
	private Senha senha;
	private Date dataAtendimento;
	private Date dataRegistro;

	public Atendimento(int id, Senha senha) {
		this.id = id;
		this.senha = senha;
		this.dataRegistro = new Date();
		this.guiche = APIConfig.getGuiche();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getGuiche() {
		return guiche;
	}

	public void setGuiche(String guiche) {
		this.guiche = guiche;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	@Override
	public String toString() {
		String padLeft = this.id < 10 ? "000" : this.id >= 10 && this.id < 100 ? "00" : "0";

		return String.format("%s%s%d", this.getSenha().getTipo(), padLeft, this.id);
	}
}
