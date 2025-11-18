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
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Celular</th>
                    <th scope="col">Email</th>
                    <th scope="col"><div class="float-right">Ações</div></th>
                </tr>
            </thead> 
            <tbody>
                <%
                    ArrayList<Alunos> lista = (ArrayList<Alunos>) request.getAttribute("meusAlunos");
                    if (lista != null && !lista.isEmpty()) {
                        for (int i = 0; i < lista.size(); i++) {
                            Alunos aux = lista.get(i);
                %>
                <tr>
                    <td><%=aux.getNome()%></td>
                    <td><%=aux.getCpf()%></td>
                    <td><%=aux.getCelular()%></td>
                    <td><%=aux.getEmail()%></td>
                    <td>
                        <a href="AlunosController?acao=editar&id=<%=aux.getId()%>" class="btn btn-dark float-right">Editar</a>
                        <a href="AlunosController?acao=excluir&id=<%=aux.getId()%>" class="btn btn-danger float-right">Excluir</a>
                    </td> 
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">Nenhum aluno encontrado.</td>
                </tr>
                <%
                    }
                %>
            </tbody>

              
        </table>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </div>
         <a href="AlunosController?acao=inserir" class="btn btn-info float-right">Inserir novo aluno</a>
    </div>        
</html>
