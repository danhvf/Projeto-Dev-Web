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
        <title>Atualizando admin</title>
    </head>
    <body>
        <header>
            <div class="topSite">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-dark float-right">Voltar</a>
            </div>
        </header>
         <div class="content">
        <%
            Administrador aux = (Administrador)request.getAttribute("administradores");
        %>
        <div class="container-sm">
        <a  class="btn btn-outline-primary" href="AdministradorController?acao=listar_todos">Listar Administradores</a>
        <form class="card" action="AdministradorController" method="POST">
            <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
            <h3> Editar Administrador </h3>
            <label for="nome">Nome Completo:</label>
            <input type="text" size="38" id="nome" name="<%if(aux != null){out.print("nome");}%>" value="<%if(aux != null){out.print(aux.getNome());} %>" required>
            <label for="cpf">CPF:</label>
            <input type="text" class="cpf" size="12" maxlength="11" id="cpf" name="<%if(aux != null){out.print("cpf");}%>" value="<%if(aux != null){out.print(aux.getCpf());} %>" required>
            <label for="senha">Senha:</label>
            <input type="password" size="12" maxlength="10" name="<%if(aux != null){out.print("password");}%>" value="<%if(aux != null){out.print(aux.getSenha());} %>" required>
            <label for="aprovado">Aprovado:</label>
            <input type="text" class="aprovado" id-="aprovado" size="12" maxlength="1" name="<%if(aux != null){out.print("aprovado");}%>" value="<%if(aux != null){out.print(aux.getAprovado());} %>" required>
            <label for="endereco">Endereço:</label>
            <input type="text" class="endereco" id="endereco" size="12" maxlength="11" name="<%if(aux != null){out.print("endereco");}%>" value="<%if(aux != null){out.print(aux.getEndereco());} %>" required>
            <input class="btn btn-outline-primary" type="submit" value="Editar">
        </form>
        </div>
        </div>    
     <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>     
    </body>
</html>
