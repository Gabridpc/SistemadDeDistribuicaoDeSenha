import java.util.LinkedList;
import java.util.Queue;

public class Sistema {
	
	
	public static void main(String[] args) {
	
		String prioridade;
		prioridade = "P";
		
		int numero;
		numero = 1;
		
		Senha senha1 = new Senha(prioridade, numero);
		
		boolean filaCheck = false;
		Queue<String> filaNormal= new LinkedList<String>();
		Queue<String> filaPrioridade= new LinkedList<String>();
		
		String sSenha = Monitor.nomeiaSenha(senha1.getPrioridade(),senha1.getNumero());
		
		if(senha1.getPrioridade() == "P") {
			filaPrioridade.add(sSenha);
			filaCheck = true;
		}
		else {
			filaNormal.add(sSenha);
		}
		
		if(prioridade.isEmpty()) {
			filaCheck = false;
		}
		
		if(filaCheck == true) {		
			System.out.println("Sua senha : "+ filaPrioridade.peek());
			filaPrioridade.poll();
		}
		else {
			System.out.println("Sua senha : "+ filaNormal.peek());
			filaNormal.poll();
		}
		
		//System.out.println("Sua senha (Aritmética): "+senha1.getPrioridade()+senha1.getNumero());

	}

}