<%-- 
    Document   : UpdateAdm
    Created on : 14 de dez. de 2024, 16:28:00
    Author     : Daniel Fontoura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,entidade.*" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="views/admin/estilo.css">
        <title>Atualizando professor</title>
    </head>
    <body>
        <header>
            <div class="topSite">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-dark float-right">Voltar</a>
            </div>
        </header>
         <div class="content">
        <% Disciplina aux = (Disciplina) request.getAttribute("disciplina"); %>

        <div class="container-sm">
        <a  class="btn btn-outline-primary" href="DisciplinaController?acao=listar_todos">Listar Disciplinas</a>
        <form class="card" action="DisciplinaController?acao=editar" method="POST">
            <input type="hidden" class="form-control" name="id" value="<% if (aux != null) { out.print(aux.getId()); } %>">
            <h3> Editar Disciplina </h3>
            <label for="nome">Nome:</label>
            <input type="text" size="38" id="nome" name="nome" value="<% if (aux != null) { out.print(aux.getNome()); } %>" required>
            <label for="requisito">Requisitos:</label>
            <input type="text" size="30" id="requisito" name="requisito" value="<% if (aux != null) { out.print(aux.getRequisito()); } %>" required>
            <label for="ementa">Ementa:</label>
            <input type="text" class="ementa" size="30" id="ementa" name="ementa" value="<% if (aux != null) { out.print(aux.getEmenta()); } %>" required>
            <label for="carga_horaria">Carga Horária</label>
            <input type="number" size="10" maxlength="5" id="carga_horaria" name="carga_horaria" value="<% if (aux != null) { out.print(aux.getCH()); } %>" required>
            <input class="btn btn-outline-primary" type="submit" value="Editar">
        </form>
        </div>
        </div>    
     <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>     
    </body>
</html>
