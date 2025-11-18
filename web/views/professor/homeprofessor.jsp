<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Professores" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Área Restrita</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="/views/professor/menuprofessor.jsp" />
            <div class="mt-5">

                <h1>Portal do Professor</h1><br>
                <%
                    Professores profObtido = (Professores) session.getAttribute("professores");
                    out.println("<h3>Bem vindo Professor(a) " + profObtido.getNome() + "</h3>");
                %>


            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
