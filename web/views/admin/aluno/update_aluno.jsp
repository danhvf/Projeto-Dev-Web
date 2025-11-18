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
        <title>Atualizando aluno</title>
    </head>
    <body>
        <header>
            <div class="topSite">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-dark float-right">Voltar</a>
            </div>
        </header>
         <div class="content">
        <%
            Alunos aux = (Alunos)request.getAttribute("aluno");
        %>
        <div class="container-sm">
        <a  class="btn btn-outline-primary" href="AlunosController?acao=listar_todos">Listar Alunos</a>
        <form class="card" action="AlunosController?acao=editar" method="POST">
            <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
            <h3> Editar Aluno </h3>
            <label for="nome">Nome Completo:</label>
            <input type="text" size="38" id="nome" name="<%if(aux != null){out.print("nome");}%>" value="<%if(aux != null){out.print(aux.getNome());} %>" required>
            <label for="email">Email:</label>
            <input type="text" size="38" id="email" name="<%if(aux != null){out.print("email");}%>" value="<%if(aux != null){out.print(aux.getEmail());} %>" required>
            <label for="celular">Celular:</label>
            <input type="text" size="38" id="celular" name="<%if(aux != null){out.print("celular");}%>" value="<%if(aux != null){out.print(aux.getCelular());} %>" required>
            <label for="cpf">CPF:</label>
            <input type="text" class="cpf" size="12" maxlength="14" id="cpf" name="<%if(aux != null){out.print("cpf");}%>" value="<%if(aux != null){out.print(aux.getCpf());} %>" required>
            <label for="senha">Senha:</label>
            <input type="password" class="senha" id="senha" size="12" maxlength="10" name="<%if(aux != null){out.print("senha");}%>" value="<%if(aux != null){out.print(aux.getSenha());} %>" required>
            <label for="endereco">Endereço:</label>
            <input type="text" class="endereco" size="30" id="endereco" name="<%if(aux != null){out.print("endereco");}%>" value="<%if(aux != null){out.print(aux.getEndereco());} %>" required>
            <label for="cidade">Cidade:</label>
            <input type="text" class="cidade" size="20" " id="cidade" name="<%if(aux != null){out.print("cidade");}%>" value="<%if(aux != null){out.print(aux.getCidade());} %>" required>
            <label for="bairro">Bairro:</label>
            <input type="text" class="bairro" size="20" id="bairro" name="<%if(aux != null){out.print("bairro");}%>" value="<%if(aux != null){out.print(aux.getBairro());} %>" required>
            <label for="cep">CEP:</label>
            <input type="text" class="cep" size="10" id="cep" name="<%if(aux != null){out.print("cep");}%>" value="<%if(aux != null){out.print(aux.getCep());} %>" required>
            <input class="btn btn-outline-primary" type="submit" value="Editar">
        </form>
        </div>
        </div>    
     <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>     
    </body>
</html>
