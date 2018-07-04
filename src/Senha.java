public class Senha {

	private String prioridade;
	private int numero;
	
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Senha(String prioridade, int numero) {
		this.prioridade = prioridade;
		this.numero = numero;
	}
		
}