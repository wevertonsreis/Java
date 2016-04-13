package br.com.alura.gerenciador.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Tarefa {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("usuarioLogado");
		return "/WEB-INF/paginas/logout.html";
	}

}
