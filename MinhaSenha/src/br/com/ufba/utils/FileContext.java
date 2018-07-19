package br.com.ufba.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FileContext {

	private String fileName;
	private String ip;

	public FileContext(String fileName) {
		this.fileName = fileName;
	}

	public FileContext(String ip, String fileName) {
		this.fileName = fileName;
		this.ip = ip;
	}

	public void saveFile(Object object) throws Exception {
		ObjectOutputStream obStream;

		try {
			if (ip != null) {
				Socket socket = new Socket("//" + ip + fileName, 1000);// Descobrir Como usar
				obStream = new ObjectOutputStream(socket.getOutputStream());
			} else {
				obStream = new ObjectOutputStream(new FileOutputStream(this.fileName));
			}
			
			obStream.writeObject(object);
		} catch (FileNotFoundException e) {
			new Exception("Não foi possível encontrar o caminho.");
		} catch (IOException e) {
			new Exception("Erro ao gravar o arquivo.");
		}
	}

	@SuppressWarnings("resource")
	public Object readFile() throws Exception {
		ObjectInputStream obStream;

		try {
			if (ip != null) {
				Socket socket = new Socket("//" + ip + fileName, 1000);// Descobrir Como usar
				obStream = new ObjectInputStream(socket.getInputStream());
			} else {
				obStream = new ObjectInputStream(new FileInputStream(this.fileName));
			}
			
			return obStream.readObject();
		} catch (IOException e) {
			new Exception("Não foi possível encontrar o arquivo.");
		}

		return null;
	}
}
