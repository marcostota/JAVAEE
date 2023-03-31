<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="img/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="btn1">Novo Contato</a>
	<a href="report" class="btn2"> Relatorio</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opcao</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i< lista.size(); i++){ %>
			<tr>
				<td><%=lista.get(i).getIdcon()%> </td>
				<td><%=lista.get(i).getNome()%> </td>
				<td><%=lista.get(i).getFone()%> </td>
				<td><%=lista.get(i).getEmail()%> </td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" 
				class="btn1">Editar</a>
				<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" 
				class="btn2">Excluir</a>
				</td>
			</tr>
			<%}%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>