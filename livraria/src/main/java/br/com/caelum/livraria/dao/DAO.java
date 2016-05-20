package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * 
 * @author Weverton Reis
 *
 * @param <T>
 */
public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Class<T> classe;
	
	private EntityManager manager;

	/**
	 * 
	 * @param classe
	 * @param manager
	 */
	public DAO(Class<T> classe, EntityManager manager) {
		super();
		this.classe = classe;
		this.manager = manager;
	}

	/**
	 * 
	 * @param t
	 */
	public void adiciona(T t) {
		manager.persist(t);
	}

	/**
	 * 
	 * @param t
	 */
	public void remove(T t) {
		manager.remove(manager.merge(t));
	}

	/**
	 * 
	 * @param t
	 */
	public void atualiza(T t) {
		manager.merge(t);
	}

	/**
	 * 
	 * @return
	 */
	public List<T> listaTodos() {
		
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		return lista;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public T buscaPorId(Integer id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	/**
	 * 
	 * @return
	 */
	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from livro n").getSingleResult();
		return (int) result;
	}

	/**
	 * 
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = manager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		return lista;
	}

}
