package br.com.caelum.livraria.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Livro livro;
	
	private Integer quantidade;
	
	/**
	 * 
	 */
	public Venda() {
		super();
	}

	/**
	 * 
	 * @param livro
	 * @param quantidade
	 */
	public Venda(Livro livro, Integer quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
