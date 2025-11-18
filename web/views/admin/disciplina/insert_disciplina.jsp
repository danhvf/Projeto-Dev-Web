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
        <a  class="btn btn-outline-primary" href="DisciplinaController?acao=listar_todos">Listar Disciplinas</a>
        <form class="card" action="DisciplinaController?acao=inserir" method="POST">
            <input type="hidden" name="id" value="0">
            <h3> Cadastro de nova disciplina:</h3>
            <label for="nome">Nome:</label>
            <input type="text" size="38" id="nome" name="nome" required>
            <label for="requisito">Requisito</label>
            <input type="text" class="requisito" placeholder="Requisitos da matéria" size="30" maxlength="50" id="requisito" name="requisito" required>
            <label for="ementa">Ementa</label>
            <input type="text" class="ementa" placeholder="Ementa da disciplina" size="30" maxlength="50" id="ementa" name="ementa" required>
            <label for="carga_horaria">Carga horária da disciplina</label>
            <input type="number" size="10" maxlength="5" name="carga_horaria" id="carga_horaria" required>

            

            <input class="btn btn-outline-primary" type="reset">
            <input class="btn btn-outline-primary" type="submit" value="Cadastrar">
        </form>
        
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
