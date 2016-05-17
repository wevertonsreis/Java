package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();

	@Inject
	private AutorDao autorDao;
	
	private List<Autor> autores;
	
	@PostConstruct
	public void init(){
		System.out.println("AutorBean esta nascendo...");
		
		System.out.println("VIEW ID AUTOR = " + FacesContext.getCurrentInstance().getViewRoot().getViewId());
		
		
		carregarAutores();
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("AutorBean esta morrendo...");
	}
	
	private void carregarAutores() {
		autores = autorDao.listaTodos();
	}
	
	public void carregarPelaId() {
		Integer id = autor.getId();
		if (id != null) {
			autor = autorDao.buscaPorId(id);			
		}
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if (autor.getId() != null) {
			autorDao.atualiza(this.autor);
		}else {
			autorDao.adiciona(this.autor);
		}
		carregarAutores();
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}
	

}
