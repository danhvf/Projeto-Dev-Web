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
        
    </body>
    <div class="container-fluid">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Código da Turma</th>
                        <th scope="col">Professor</th>
                        <th scope="col">Disciplina</th>
                        <th scope="col">Aluno</th>
                        <th scope="col">Nota</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                    <tbody>
                        <%
                            // Obtenha as listas de professores, disciplinas e alunos do request
                            ArrayList<String> professores = (ArrayList<String>) request.getAttribute("professores");
                            ArrayList<String> disciplinas = (ArrayList<String>) request.getAttribute("disciplinas");
                            ArrayList<String> alunos = (ArrayList<String>) request.getAttribute("alunos");
                            ArrayList<Turmas> turmas = (ArrayList<Turmas>) request.getAttribute("minhasTurmas");

                            // Iterar sobre as turmas
                            if (turmas != null) {
                                for (Turmas turma : turmas) {
                                    // Obter nomes a partir dos IDs
                                    String nomeProfessor = "Não encontrado";
                                    String nomeDisciplina = "Não encontrado";
                                    String nomeAluno = "Não encontrado";

                                    if (professores != null) {
                                        for (String prof : professores) {
                                            String[] partes = prof.split(" - ");
                                            if (Integer.parseInt(partes[0]) == turma.getProfessorId()) {
                                                nomeProfessor = partes[1];
                                                break;
                                            }
                                        }
                                    }

                                    if (disciplinas != null) {
                                        for (String disc : disciplinas) {
                                            String[] partes = disc.split(" - ");
                                            if (Integer.parseInt(partes[0]) == turma.getDisciplinaId()) {
                                                nomeDisciplina = partes[1];
                                                break;
                                            }
                                        }
                                    }

                                    if (alunos != null) {
                                        for (String aluno : alunos) {
                                            String[] partes = aluno.split(" - ");
                                            if (Integer.parseInt(partes[0]) == turma.getAlunoId()) {
                                                nomeAluno = partes[1];
                                                break;
                                            }
                                        }
                                    }
                        %>
                            <tr>
                                <td><%= turma.getCodigoTurma() %></td>
                                <td><%= nomeProfessor %></td>
                                <td><%= nomeDisciplina %></td>
                                <td><%= nomeAluno %></td>
                                <td><%= turma.getNota() %></td>
                                <td>
                                    <a href="TurmasController?acao=editar&id=<%= turma.getId() %>" class="btn btn-dark float-right">Editar</a>
                                    <a href="TurmasController?acao=excluir&id=<%= turma.getId() %>" class="btn btn-danger float-right">Excluir</a>
                                </td>
                            </tr>
                        <%
                                }
                            }
                        %>
                </tbody>
            </table>
            <a href="TurmasController?acao=inserir" class="btn btn-info float-right">Inserir nova turma</a>
        </div>
    </div>        
</html>
