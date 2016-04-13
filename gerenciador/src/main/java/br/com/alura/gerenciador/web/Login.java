package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

public class Login implements Tarefa {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		PrintWriter writer;
		try {
			writer = response.getWriter();
			Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
			if(usuario == null) {
				writer.println("<html><body>Usuário ou senha inválida</body></html>");
			} else {
				request.getSession().setAttribute("usuarioLogado", usuario);
				writer.println("<html><body>Usuário logado: " + email + "</body></html>");
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		return null;
	}

}
