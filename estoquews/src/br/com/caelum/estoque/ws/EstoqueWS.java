package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;

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
	
	@WebMethod(exclude = true)
	public void outroMetodo() {
		// Nao vai fazer parte do WSDL
	}
	
}
