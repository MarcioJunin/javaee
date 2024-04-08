<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.JavaBeans"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
/*for (int i=0; i<lista.size(); i++) {
	out.println(lista.get(i).getIdcon());
	out.println(lista.get(i).getNome());
	out.println(lista.get(i).getFone());
	out.println(lista.get(i).getEmail());
}
*/
%>
<!DOCTYPE html>
<html lang="ptbr">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="icon" href="/Agenda/src/main/webapp/Imagens/favicon.png">
</head>
<body>
	<header>
		<h1>Agenda de contatos</h1>
	</header>
	<a href="novo.html" class="botao1">Novo contato</a>
	<a href="report" class="botao2">Relatórios</a>
	<table>
		<thead>
			<tr>
			<table id="tabela" >
				<th>id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdcon() %> </td>
				 <td><%=lista.get(i).getNome() %> </td>
				 <td><%=lista.get(i).getFone() %> </td>
				 <td><%=lista.get(i).getEmail() %> </td>
				 <td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class=botao1>Editar</a>
				 <a href="javascript:confirmar(<%=lista.get(i).getIdcon() %>)" class="botao2">Excluir</a>
				 </td>
				<%
 }
 %>
		</tbody>
	</table>
	
	<script type="text/javascript" src="scripts/Confirmador.js"></script>
</body>
</html>