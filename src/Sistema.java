
public class Sistema {

	public static void main(String[] args) {
	
		String prior;
		prior = "c";
		
		int num;
		num = 0001;
		
		Senha senha1 = new Senha();
		senha1.setPrioridade(prior);
		senha1.setNumero(num);
		System.out.println("Sua senha: "+senha1.getPrioridade()+senha1.getNumero());

	}

}
