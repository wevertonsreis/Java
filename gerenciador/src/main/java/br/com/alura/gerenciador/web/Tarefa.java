package br.com.alura.gerenciador.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Tarefa {
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	
}
