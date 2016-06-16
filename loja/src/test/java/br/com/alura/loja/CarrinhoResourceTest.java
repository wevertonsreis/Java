package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

/**
 * 
 * @author Weverton Reis
 *
 */
public class CarrinhoResourceTest {

	@Before
	public void startServidor() {
		Servidor.start();
	}
	
	@After
	public void stopServidor() {
		Servidor.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos").request().get(String.class);

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

		System.out.println(carrinho);

		Assert.assertTrue(carrinho.getRua().equals("Rua Vergueiro 3185"));
	}
}
