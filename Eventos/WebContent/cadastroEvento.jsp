<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Tela de cadastro de eventos</title>
	</head>
	
	<body>
		<h1>Cadastro de eventos</h1>
		<h3>Preencha o formulário abaixo</h3>
		<br>
		<h3 style="color:red;">${erro}</h3>
			<form action="cadastrarEvento" name="formul">
				Nome do evento: <input id="n" type="text" name="nome" value="${param.nome}"><br>
				Descrição: <textarea id="desc" rows="4" cols="50" name="descricao">${param.descricao}</textarea><br>
				Data do evento (DD/MM/AAAA): <input id="d" type="text" name="data" value="${param.data}"><br>
				Preço: R$ <input id="p" type="text" name="preco" value="${param.preco}"><br>
				
				<input type="hidden" name="logica" value="CadastrarEventoLogica">
				
				<input type="submit" value="Gravar no banco de dados" onclick="envia('mvc')">
				<input type="submit" value="Visualizar no navegador" onclick="envia('visualizarNavegador')">
				<input type="submit" value="Gerar pdf" onclick="envia('gerarPdf')">
				<input type="button" value="Minimo esforço" onclick="me()">
			</form>
			<br>
			<h3 id="instrucao"></h3>
			<h4 id="dadosEvento"></h4>
			<script> 
				function envia(pag){ 
   					document.formul.action= pag 
   					document.formul.submit() 
				}
				
				function me(){
					document.getElementById("instrucao").innerHTML = "Para salvar basta copiar as informações abaixo e colar no seu editor de texto preferido";
					
					var infoEvento = "Nome do evento: " + document.getElementById("n").value + "<br>";
					infoEvento += "Descricao: " + document.getElementById("desc").value + "<br>";
					infoEvento += "Data: " + document.getElementById("d").value + "<br>";
					infoEvento += "Preço: R$" + document.getElementById("p").value + ",00<br>";
					
					document.getElementById("dadosEvento").innerHTML = infoEvento;
				}
			</script>
	</body>
</html>