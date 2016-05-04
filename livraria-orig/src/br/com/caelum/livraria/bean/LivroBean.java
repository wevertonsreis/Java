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

	public void carregaPelaId() {
		Integer id = livro.getId();
		livro = new DAO<Livro>(Livro.class).buscaPorId(id);
	}

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

		if (livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}

		livro = new Livro();
		return null;
	}

	/**
	 * 
	 * @param livro
	 * @return
	 */
	public String remover(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
		return null;
	}

	/**
	 * 
	 * @param livro
	 * @return
	 */
	public String carregar(Livro livro) {
		System.out.println("Carregando o livro = " + livro);
		this.livro = livro;
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

	/**
	 * 
	 * @param autor
	 * @return
	 */
	public String removerAutorDoLivro(Autor autor) {
		livro.removerAutor(autor);
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
