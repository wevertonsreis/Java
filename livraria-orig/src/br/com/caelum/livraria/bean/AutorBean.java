package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public void carregarPelaId() {
		Integer id = autor.getId();
		if (id != null) {
			autor = new DAO<Autor>(Autor.class).buscaPorId(id);			
		}
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if (autor.getId() != null) {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}else {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		}
	}

	public Autor getAutor() {
		return autor;
	}

}
