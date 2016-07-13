package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;

import com.thoughtworks.xstream.XStream;

public class ProjetoResourceTest {
	
	private Client client;
	
	@Before
	public void startServidor() {
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		this.client = ClientBuilder.newClient(config);
		
		Servidor.start();
	}
	
	@After
	public void stopServidor() {
		Servidor.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		WebTarget target = client.target("http://localhost:8080");
		
		String conteudo = target.path("/projetos/1").request().get(String.class);

		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);

		Assert.assertTrue(projeto.getNome().contains("Minha loja"));
	}
	
	@Test
	public void testaAdicionaProjeto() {
		WebTarget target = client.target("http://localhost:8080");
		
		Projeto projeto = new Projeto();
		
		String xml = projeto.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/projetos").request().post(entity);

		Assert.assertTrue(response.getStatus() == 201);
	}
}
