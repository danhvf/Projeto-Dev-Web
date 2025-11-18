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
   <% Turmas turma = (Turmas) request.getAttribute("turmaDados"); %>

<!DOCTYPE html>
<html>
<head>
    <title>Inscrição na Turma</title>
    <link rel="stylesheet" href="path/to/bootstrap.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Inscrição na Turma</h1>
        <div class="mb-3">
                <input type="hidden" id="id" name="id" value="<%= turma.getId() %>" readonly>
            </div>

            <div class="mb-3">
                <label for="codigoTurma" class="form-label">Código da Turma</label>
                <input type="text" class="form-control" id="codigoTurma" name="codigo_turma" value="<%= turma.getCodigoTurma() %>" readonly>
            </div>

            <div class="mb-3">
                <label for="professorId" class="form-label">Nome do Professor</label>
                <input type="text" class="form-control" id="professorId" name="professor_id" value="<%= turma.getProfessorNome() %>" readonly>
            </div>

            <div class="mb-3">
                <label for="disciplinaId" class="form-label">Nome da Disciplina</label>
                <input type="text" class="form-control" id="disciplinaId" name="disciplina_id" value="<%= turma.getDisciplinaNome() %>" readonly>
            </div>

            <div class="mb-3">
                <label for="alunoId" class="form-label">Digite seu ID de Aluno</label>
                <input type="text" class="form-control" id="alunoId" name="aluno_id">
            </div>

            <div class="mb-3">
                <input type="hidden" class="form-control" id="nota" name="nota" value="0">
            </div>
            <form action="InscricaoController" method="POST">
                <input type="hidden" name="acao" value="inserir">
                <input type="hidden" id="codigoTurma" name="codigo_turma" value="<%= turma.getCodigoTurma() %>">
                <input type="hidden" id="professorId" name="professor_id" value="<%= turma.getProfessorId() %>">
                <input type="hidden" id="disciplinaId" name="disciplina_id" value="<%= turma.getDisciplinaId() %>">
                <input type="hidden" id="alunoId" name="aluno_id" value="<%= turma.getAlunoId() %>">
                <input type="hidden" id="nota" name="nota" value="0">

                <button type="submit" class="btn btn-primary">Confirmar Inscrição</button>
            </form>
    </div>
</body>
</html>