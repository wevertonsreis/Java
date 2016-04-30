package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	
	private Integer autorId;
	
	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	/**
	 * 
	 */
	public String gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return null;
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
		livro = new Livro();
		return null;
	}
	
	/**
	 * 
	 * @param facesContext
	 * @param uiComponent
	 * @param value
	 * @throws ValidatorException
	 */
	public void comecaComDigitoUm(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
		String value = (String) object;
		if (!value.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O ISBN deve começar com o número 1"));
		}
	}
	
	/**
	 * 
	 */
	public String gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		livro.adicionaAutor(autor);
		return null;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}
	
}
