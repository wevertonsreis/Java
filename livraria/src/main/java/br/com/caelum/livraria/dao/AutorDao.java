package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Autor;

/**
 * 
 * @author Weverton
 *
 */
public class AutorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	public void init() {
		dao = new DAO<Autor>(Autor.class, manager);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public List<Autor> buscarPorNome(String nome) {
		StringBuilder builderQuery = new StringBuilder("SELECT a FROM Autor a WHERE 1 = 1 ");
		
		if (nome != null && !nome.isEmpty())
			builderQuery.append("AND a.nome LIKE :nome ");
		
		TypedQuery<Autor> query = manager.createQuery(builderQuery.toString(), Autor.class);
		
		if (nome != null && !nome.isEmpty())
			query.setParameter("nome", "%" + nome + "%");
		
		return query.getResultList();
	}

}