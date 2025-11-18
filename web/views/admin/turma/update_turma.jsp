<%-- 
    Document   : UpdateAdm
    Created on : 14 de dez. de 2024, 16:28:00
    Author     : Daniel Fontoura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,entidade.*" %>
<%@ page import="java.util.ArrayList" %>
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
            ArrayList<String> professores = (ArrayList<String>) request.getAttribute("professores");
            ArrayList<String> disciplinas = (ArrayList<String>) request.getAttribute("disciplinas");
            ArrayList<String> alunos = (ArrayList<String>) request.getAttribute("alunos");
            Turmas turma = (Turmas) request.getAttribute("turma");
        %>
        <div class="container-sm">
        <a  class="btn btn-outline-primary" href="TurmasController?acao=listar_todos">Listar Professores</a>
            <form action="TurmasController?acao=editar" method="POST">
                <input type="hidden" name="id" value="<%= turma != null ? turma.getId() : 0 %>">
                <h3>Editar Turma</h3>

                <label for="professor_id">Professor:</label>
                <select name="professor_id" id="professor_id" required>
                    <%
                        if (professores != null) {
                            for (String professor : professores) {
                                String[] partes = professor.split(" - ");
                                String id = partes[0];
                                String nome = partes[1];
                    %>
                                <option value="<%= id %>" <%= turma != null && turma.getProfessorId() == Integer.parseInt(id) ? "selected" : "" %>><%= nome %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label for="disciplina_id">Disciplina:</label>
                <select name="disciplina_id" id="disciplina_id" required>
                    <%
                        if (disciplinas != null) {
                            for (String disciplina : disciplinas) {
                                String[] partes = disciplina.split(" - ");
                                String id = partes[0];
                                String nome = partes[1];
                    %>
                                <option value="<%= id %>" <%= turma != null && turma.getDisciplinaId() == Integer.parseInt(id) ? "selected" : "" %>><%= nome %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label for="aluno_id">Aluno:</label>
                <select name="aluno_id" id="aluno_id" required>
                    <%
                        if (alunos != null) {
                            for (String aluno : alunos) {
                                String[] partes = aluno.split(" - ");
                                String id = partes[0];
                                String nome = partes[1];
                    %>
                                <option value="<%= id %>" <%= turma != null && turma.getAlunoId() == Integer.parseInt(id) ? "selected" : "" %>><%= nome %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label for="codigo_turma">Código da Turma:</label>
                <input type="text" name="codigo_turma" id="codigo_turma" value="<%= turma != null ? turma.getCodigoTurma() : "" %>" required>

                <label for="nota">Nota:</label>
                <input type="number" name="nota" id="nota" value="<%= turma != null ? turma.getNota() : 0 %>" step="0.01" required>

                <input type="submit" value="Salvar" class="btn btn-primary">
            </form>
        </div>
        </div>    
     <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>     
    </body>
</html>
