package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

/**
 * 
 * @author Weverton Reis
 *
 */
public class VendaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private DAO<Venda> dao;

	@PostConstruct
	public void init() {
		dao = new DAO<Venda>(Venda.class, manager);
	}

	/**
	 * 
	 * @return
	 */
	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

}