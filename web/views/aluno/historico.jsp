<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,entidade.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Histórico de Turmas do Aluno</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <div class="topSite">
                <a href="${pageContext.request.contextPath}/indexaluno.jsp" class="btn btn-dark float-right">Voltar</a>
            </div>
        </header>
        <br><br>
        <h3>Histórico do Aluno</h3>
        <%
                    Alunos alunoLogado = (Alunos) session.getAttribute("aluno");
                    out.println("<h3>Aluno: " + alunoLogado.getNome() + "</h3>");
                %>
        <div class="container">
            <%
                ArrayList<Turmas> historico = (ArrayList<Turmas>) request.getAttribute("historico");
                if (historico == null || historico.isEmpty()) {
            %>
                <div class="alert alert-info" role="alert">
                    Nenhum histórico encontrado para o ID fornecido.
                </div>
            <%
                } else {
            %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Disciplina</th>
                        <th>Código da Turma</th>
                        <th>Professor</th>
                        <th>Nota</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Turmas turma : historico) {
                    %>
                    <tr>
                        <td><%= turma.getDisciplinaNome() %></td>
                        <td><%= turma.getCodigoTurma() %></td>
                        <td><%= turma.getProfessorNome() %></td>
                        <td><%= turma.getNota() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                }
            %>
        </div>
    </body>
</html>
