package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.exception.AutorizacaoException;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	
	private ItemDao itemDao = new ItemDao();
	
	@WebMethod(operationName = "todosOsItens")
	@ResponseWrapper(localName = "itens")
	@WebResult(name = "item")
	@RequestWrapper(localName = "listaItens")
	public List<Item> getItens() {
		System.out.println("Chamando todosItens()");
		return itemDao.todosItens();
	}
	
	@WebMethod(operationName = "CadastrarItem")
	public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario tokenUsuario,  
			@WebParam(name = "item") Item item) throws AutorizacaoException {
		System.out.println("Cadastrando = " + item);
		
		if (!new TokenDao().ehValido(tokenUsuario)) {
			throw new AutorizacaoException("Autorização falhou");
		}
		
		System.out.println("Token = " + tokenUsuario.getDataValidade());
		
		new ItemValidador(item).validate();
		
		this.itemDao.cadastrar(item);
		return item;
	}
	
	@WebMethod(exclude = true)
	public void outroMetodo() {
		// Nao vai fazer parte do WSDL
	}
	
}
