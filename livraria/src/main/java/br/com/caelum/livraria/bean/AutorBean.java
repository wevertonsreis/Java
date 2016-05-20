package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.contantes.AtributosDeSessao;
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
	
	@Inject
	private FacesContext facesContext;
	
	private List<Autor> autores;
	
	@PostConstruct
	public void init(){
		System.out.println("AutorBean esta nascendo...");
		
		System.out.println("VIEW ID AUTOR = " + FacesContext.getCurrentInstance().getViewRoot().getViewId());
		
		
		carregarAutores();
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

	@SuppressWarnings("unchecked")
	public String voltar() {
		List<Autor> autores = (List<Autor>) facesContext.getExternalContext().getSessionMap().get(AtributosDeSessao.PESQUISA_AUTOR_RESULTADO);
		
		if (autores != null && !autores.isEmpty()) {
			autores.remove(autor);
			autores.add(autor);
		}
		return "pesquisaAutor?faces-redirec=true";
	}
	
	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}
	

}
