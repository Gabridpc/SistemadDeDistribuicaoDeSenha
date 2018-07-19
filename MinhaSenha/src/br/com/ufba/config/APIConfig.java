package br.com.ufba.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIConfig {
	private static Properties prop = new Properties();
	private static InputStream input = null;

	public static String getGuiche() {
		try {
			input = APIConfig.class.getResourceAsStream("/application.properties");
			prop.load(input);

			return prop.getProperty("nome.guiche");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}