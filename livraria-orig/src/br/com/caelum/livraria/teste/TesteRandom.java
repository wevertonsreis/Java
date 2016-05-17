package br.com.caelum.livraria.teste;

import java.util.Random;

public class TesteRandom {

	public static void main(String[] args) {
		
		Random gerador = new Random();
		
		int resultado1 = gerador.nextInt(10);
		System.out.println(resultado1);

		int resultado2 = gerador.nextInt(10);
		System.out.println(resultado2);
		
		Random geradorBoolean = new Random();
		
		boolean resultadoBoolean1 = geradorBoolean.nextBoolean();
		System.out.println(resultadoBoolean1);
		
		boolean resultadoBoolean2 = geradorBoolean.nextBoolean();
		System.out.println(resultadoBoolean2);
	}

}
