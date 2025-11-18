<%-- 
    Document   : InserirAdm
    Created on : 14 de dez. de 2024, 18:20:57
    Author     : Daniel Fontoura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,entidade.*" %>
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
    <a class="btn btn-outline-primary mb-3" href="TurmasController?acao=listar_todos">Listar Turmas</a>
    <form class="card p-4" action="TurmasController?acao=inserir" method="POST">
        <input type="hidden" name="id" value="0">
        <h3 class="mb-3">Cadastro de Nova Turma</h3>
        
        <div class="mb-3">
            <label for="codigo_turma" class="form-label">Código da Turma</label>
            <input type="text" class="form-control" id="codigo_turma" name="codigo_turma" placeholder="Ex: A1" maxlength="2" required>
        </div>
        
        <div class="mb-3">
            <label for="professor_id" class="form-label">Professor</label>
            <select class="form-select" id="professor_id" name="professor_id" required>
                <option value="" disabled selected>Selecione um professor</option>
                <% 
                    ArrayList<String> professores = (ArrayList<String>) request.getAttribute("professores");
                    if (professores != null) {
                        for (String professor : professores) {
                %>
                <option value='<%= professor.split(" - ")[0] %>'><%= professor.split(" - ")[1] %></option>
                <% 
                        }
                    }
                %>
            </select>

        </div>
        
        <div class="mb-3">
            <label for="disciplina_id" class="form-label">Disciplina</label>
            <select class="form-select" id="disciplina_id" name="disciplina_id" required>
                <option value="" disabled selected>Selecione uma disciplina</option>
                <% 
                    ArrayList<String> disciplinas  = (ArrayList<String>) request.getAttribute("disciplinas");
                    if (disciplinas != null) {
                        for (String dp : disciplinas) {
                %>
                <option value='<%= dp.split(" - ")[0] %>'><%= dp.split(" - ")[1] %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>
        
        <div class="mb-3">
            <label for="aluno_id" class="form-label">Aluno</label>
            <select class="form-select" id="aluno_id" name="aluno_id" required>
                <option value="" disabled selected>Selecione um aluno</option>
                <% 
                    ArrayList<String> alunos = (ArrayList<String>) request.getAttribute("alunos");
                    if (alunos != null) {
                        for (String aluno : alunos) {
                %>
                <option value='<%= aluno.split(" - ")[0] %>'><%= aluno.split(" - ")[1] %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>
        
        <div class="mb-3">
            <label for="nota" class="form-label">Nota</label>
            <input type="number" class="form-control" id="nota" name="nota" placeholder="Ex: 9.5" step="0.01" required>
        </div>

        <div class="d-flex justify-content-end">
            <input class="btn btn-outline-secondary me-2" type="reset" value="Limpar">
            <input class="btn btn-outline-primary" type="submit" value="Cadastrar">
        </div>
    </form>
</div>

    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
