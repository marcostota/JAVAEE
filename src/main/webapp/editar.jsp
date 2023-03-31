<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="img/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" placeholder="" id="caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" placeholder="Nome" class="caixa1" value="<%out.print(request.getAttribute("nome")) ;%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" placeholder="Fone" class="caixa2" value="<%out.print(request.getAttribute("fone")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" placeholder="Email" class="caixa1" value="<%out.print(request.getAttribute("email")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="btn1" onclick="validar()">
		
		
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>