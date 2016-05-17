package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	
	private List<Livro> livros;
	
	private Integer autorId;

	@PostConstruct
	public void init() {
		livros = livroDao.listaTodos();
	}
	
	public void carregaPelaId() {
		Integer id = livro.getId();
		livro = livroDao.buscaPorId(id);
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
	@Log
	public String gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return null;
		}

		if (livro.getId() == null) {
			livroDao.adiciona(this.livro);
		}else {
			livroDao.atualiza(this.livro);
		}

		livro = new Livro();
		livros = livroDao.listaTodos();
		return null;
	}

	/**
	 * 
	 * @param livro
	 * @return
	 */
	public String remover(Livro livro) {
		livros.remove(livro);
		livroDao.remove(livro);
		return null;
	}

	/**
	 * 
	 * @param livro
	 * @return
	 */
	public String carregar(Livro livro) {
		System.out.println("Carregando o livro = " + livro);
		this.livro = livroDao.buscaPorId(livro.getId());
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
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ISBN", "O ISBN deve começar com o número 1"));
		}
	}

	/**
	 * 
	 */
	public String gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		livro.adicionaAutor(autor);
		return null;
	}

	/**
	 * 
	 * @param autor
	 * @return
	 */
	public void removerAutorDoLivro(Autor autor) {
		livro.removerAutor(autor);
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}

	public List<Livro> getLivros() {
		return livros;
	}
	
}
