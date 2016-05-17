package br.com.caelum.livraria.bean;

import static br.com.caelum.livraria.contantes.AtributosDeSessao.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

/**
 * 
 * @author Weverton Reis
 *
 */
@Named
@ViewScoped
public class PesquisaAutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext facesContext;
	
	private List<Autor> autores;

	private String nome;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		autores = (List<Autor>) facesContext.getExternalContext().getSessionMap().get(PESQUISA_AUTOR_RESULTADO.name());
		nome = (String) facesContext.getExternalContext().getSessionMap().get(PESQUISA_AUTOR_FILTRO.name());
		
		if (autores == null)
			autores = new ArrayList<Autor>();
	}
	
	public String pesquisar() {
		autores = autorDao.buscarPorNome(nome);
		return null;
	}
	
	public String editar(Autor autor) {
		facesContext.getExternalContext().getSessionMap().put(PESQUISA_AUTOR_RESULTADO.name(), autores);
		facesContext.getExternalContext().getSessionMap().put(PESQUISA_AUTOR_FILTRO.name(), nome);
		return "autor?faces-redirect=true&autorId=" + autor.getId();
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}