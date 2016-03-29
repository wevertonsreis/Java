package br.com.caelum.estoque.cliente.teste;

import br.com.caelum.estoque.cliente.EstoqueWS;
import br.com.caelum.estoque.cliente.EstoqueWSService;
import br.com.caelum.estoque.cliente.Filtro;
import br.com.caelum.estoque.cliente.Filtros;
import br.com.caelum.estoque.cliente.ListaItens;
import br.com.caelum.estoque.cliente.TipoItem;

public class TesteClienteSoap {

	public static void main(String[] args) {
		
		EstoqueWS estoqueWS = new EstoqueWSService().getEstoqueWSPort();
		
		Filtro filtro = new Filtro();
		filtro.setNome("IPhone");
		filtro.setTipo(TipoItem.CELULAR);

		Filtros filtros = new Filtros();
		filtros.getFiltro().add(filtro);

		ListaItens lista = estoqueWS.todosOsItens(filtros);

		System.out.println("Resposta do serviço: " + lista);
		
	}

}
