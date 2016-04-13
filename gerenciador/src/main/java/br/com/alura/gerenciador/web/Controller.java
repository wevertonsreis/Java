package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/executa")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String tarefa = request.getParameter("tarefa");
		
		if (tarefa == null)
			throw new IllegalArgumentException("A tarefa é necessaria!");
				
		try {
			String nomeDaClasse = "br.com.alura.gerenciador.web." + tarefa;
			Class<?> tipo = Class.forName(nomeDaClasse);
			Tarefa instancia = (Tarefa) tipo.newInstance();
			
			String resultado = instancia.executa(request, response);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(resultado);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}