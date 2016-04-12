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
		
		<form action="novaEmpresa" method="post">
			Nome: <input type="text" name="nome">
			<input type="submit" value="Enviar">	
		</form>
		
		<form action="login" method="post">
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
		
		<form action="logout" method="post">
			<input type="submit" value="Logout">
		</form>
	</body>
</html>