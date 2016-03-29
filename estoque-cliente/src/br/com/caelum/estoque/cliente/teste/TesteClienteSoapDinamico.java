package br.com.caelum.estoque.cliente.teste;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.caelum.estoque.cliente.EstoqueWS;
import br.com.caelum.estoque.cliente.Filtro;
import br.com.caelum.estoque.cliente.Filtros;
import br.com.caelum.estoque.cliente.Item;
import br.com.caelum.estoque.cliente.ListaItens;
import br.com.caelum.estoque.cliente.TipoItem;

public class TesteClienteSoapDinamico {

	public static void main(String[] args) throws MalformedURLException {
		
		URL url = new URL("http://localhost:8080/estoquews-web/EstoqueWS?wsdl");
        QName qname = new QName("http://ws.estoque.caelum.com.br/", "EstoqueWS");

        Service service = Service.create(url, qname);

        EstoqueWS cliente = service.getPort(EstoqueWS.class);

        Filtro filtro = new Filtro();
        filtro.setNome("IPhone");
        filtro.setTipo(TipoItem.CELULAR);;

        Filtros filtros = new Filtros();
		filtros.getFiltro().add(filtro);
        
        ListaItens lista = cliente.todosOsItens(filtros);
        
        for (Item item : lista.getItem()) {
            System.out.println(item.getNome());
        }

	}

}
