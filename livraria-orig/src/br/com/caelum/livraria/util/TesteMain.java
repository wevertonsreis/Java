package br.com.caelum.livraria.util;

public class TesteMain {

	
	public static void main(String[] args) {
		MinhaThreadThread minhaThreadThread = new MinhaThreadThread();
		minhaThreadThread.start();
		
		MinhaSuperThread minhaSuperThread = new MinhaSuperThread();
		Thread thread = new Thread(minhaSuperThread);
		thread.start();
		
	}

}
