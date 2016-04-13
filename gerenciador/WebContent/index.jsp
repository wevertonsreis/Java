<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1>Bem vindo ao nosso gerenciador de empresas!</h1>
		
		<c:if test="${usuarioLogado != null}">
			<h3>Você está logado como ${usuarioLogado.email}</h3>
		</c:if>
		
		<form action="executa" method="post">
			<input type="hidden" name="tarefa" value="NovaEmpresa">
			<table>
				<tr>
					<td>Nome:</td>
					<td><input type="text" name="nome"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Enviar"></td>
				</tr>
			</table>	
		</form>
		
		<form action="executa" method="get">
			<input type="hidden" name="tarefa" value="BuscaEmpresa">
			<table>
				<tr>
					<td>Nome:</td>
					<td><input type="text" name="filtro"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Buscar"></td>
				</tr>
			</table>	
		</form>
		
		<form action="executa" method="post">
			<input type="hidden" name="tarefa" value="Login">
			<table>
				<tr>
					<td>E-mail:</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" name="senha"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form>
		
		<form action="executa" method="post">
			<input type="hidden" name="tarefa" value="Logout">
			<input type="submit" value="Logout">
		</form>
	</body>
</html>