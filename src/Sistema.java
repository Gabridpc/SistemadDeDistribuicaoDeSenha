
public class Sistema {
	
	private static String nomeiaSenha(String prior, int num) {
		String sSenha = "";
		if(num<1000) {
			sSenha = "0";
			if(num<100) {
				sSenha = sSenha + "0";
				if(num<10) {
					sSenha = sSenha + "0";
				}
			}
		}
		sSenha = prior + sSenha + Integer.toString(num);
		return sSenha;
	}

	public static void main(String[] args) {
	
		String prior;
		prior = "A";
		
		int num;
		num = 1;
		
		Senha senha1 = new Senha(prior, num);
				
		System.out.println("Sua senha : "+Sistema.nomeiaSenha(senha1.getPrioridade(),senha1.getNumero()));
		
		System.out.println("Sua senha (Aritmética): "+senha1.getPrioridade()+senha1.getNumero());

	}

}
