<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Conatos</title>
<link rel="icon" href="Imagens/favicon.png">
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>
	<h1>Editar Contato</h1>
	<form name="frmcontato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="Caixa3" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" class="Caixa1" name="nome"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" class="Caixa2" name="fone"
					value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" class="Caixa1" name="email"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="submit" value="Salvar" class="botao1" onclick="validar()">

	</form>

	<script type="text/javascript" src="scripts/validacao.js"></script>
</body>
</html>