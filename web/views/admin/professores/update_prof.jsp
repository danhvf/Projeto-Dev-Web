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
        <%
            Professores aux = (Professores)request.getAttribute("professores");
        %>
        <div class="container-sm">
        <a  class="btn btn-outline-primary" href="ProfessorController?acao=listar_todos">Listar Professores</a>
        <form class="card" action="ProfessorController?acao=editar" method="POST">
            <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
            <h3> Editar Professor </h3>
            <label for="nome">Nome Completo:</label>
            <input type="text" size="38" id="nome" name="<%if(aux != null){out.print("nome");}%>" value="<%if(aux != null){out.print(aux.getNome());} %>" required>
            <label for="email">Email:</label>
            <input type="text" size="38" id="email" name="<%if(aux != null){out.print("email");}%>" value="<%if(aux != null){out.print(aux.getEmail());} %>" required>
            <label for="cpf">CPF:</label>
            <input type="text" class="cpf" size="12" maxlength="14" id="cpf" name="<%if(aux != null){out.print("cpf");}%>" value="<%if(aux != null){out.print(aux.getCpf());} %>" required>
            <label for="senha">Senha:</label>
            <input type="password" size="12" maxlength="10" name="<%if(aux != null){out.print("senha");}%>" value="<%if(aux != null){out.print(aux.getSenha());} %>" required>
            <input class="btn btn-outline-primary" type="submit" value="Editar">
        </form>
        </div>
        </div>    
     <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>     
    </body>
</html>
