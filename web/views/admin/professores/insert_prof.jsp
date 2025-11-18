<%-- 
    Document   : InserirAdm
    Created on : 14 de dez. de 2024, 18:20:57
    Author     : Daniel Fontoura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Área do Administrador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="views/admin/estilo.css">
    </head>
    <body>
        <header>
            <div class="topSite">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-dark float-right">Voltar</a>
            </div>
        </header>

    <div class="container-sm">
        <a  class="btn btn-outline-primary" href="ProfessorController?acao=listar_todos">Listar Professores</a>
        <form class="card" action="ProfessorController?acao=inserir" method="POST">
            <input type="hidden" name="id" value="0">
            <h3> Cadastro de novo professor:</h3>
            <label for="nome">Nome Completo</label>
            <input type="text" placeholder="ex: Lucas Silva Ferreira" size="38" id="nome" name="nome" required>
            <label for="email">E-mail:</label>
            <input type="text" class="email" placeholder="Digite seu email" size="30" maxlength="30" id="email" name="email" required>
            <label for="cpf">CPF</label>
            <input type="text" class="cpf" placeholder="Digite seu CPF" size="12" maxlength="14" minlength="11" id="cpf" name="cpf" required>
            <label for="senha">Senha</label>
            <input type="password" size="12" maxlength="10" name="senha" id="senha" required>

            

            <input class="btn btn-outline-primary" type="reset">
            <input class="btn btn-outline-primary" type="submit" value="Cadastrar">
        </form>
        
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
