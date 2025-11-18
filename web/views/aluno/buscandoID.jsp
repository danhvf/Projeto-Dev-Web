<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,entidade.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Área do Aluno</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="views/admin/estilo.css">
    </head>
    <body>
    <h1>Buscar Histórico</h1>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<div class='alert alert-danger'>" + error + "</div>");
        }
    %>
    <form action="${pageContext.request.contextPath}/TurmasController" method="GET">
        <input type="hidden" name="acao" value="historico">
        <label for="alunoId">Digite seu ID:</label>
        <input type="text" id="alunoId" name="alunoId" required>
        <button type="submit">Buscar histórico</button>
    </form>
</body>
</html>