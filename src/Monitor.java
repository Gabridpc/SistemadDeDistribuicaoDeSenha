
public class Monitor {
	

	static String nomeiaSenha(String prior, int num) {
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
}
