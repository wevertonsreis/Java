package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

import com.thoughtworks.xstream.XStream;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		return new ProjetoDAO().busca(id).toXML();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) throws Exception {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI location = new URI("/projetos/" + projeto.getId());
		return Response.created(location).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removeProjeto(@PathParam("id")long id) throws Exception {
		new ProjetoDAO().remove(id);
		return Response.ok().build();
	}
	
}
