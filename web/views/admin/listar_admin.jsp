<%-- 
    Document   : listar_admin
    Created on : 17 de dez. de 2024, 14:04:36
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
    </body>
    <div class="container-fluid">
    <div class="table-responsive">
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Aprovado?</th>
                    <th scope="col"><div class="float-right">Ações</div></th>
                </tr>
            </thead> 
            <tbody>
                <%
                    ArrayList<Administrador> ListaDeAdministrador = (ArrayList<Administrador>) request.getAttribute("meusAdministradores");
                    for (int i = 0; i < ListaDeAdministrador.size(); i++) {
                        Administrador aux = ListaDeAdministrador.get(i);
                        String link_editar = "AdministradorController?acao=editar&id="+aux.getId();
                        String link_excluir = "AdministradorController?acao=excluir&id="+aux.getId();
                %>
                <tr>
                    <td><%=aux.getNome()%></td>
                    <td><%=aux.getCpf()%></td>
                    <td><%=aux.getAprovado()%></td>
                    <td>
                        <a href="<%=link_editar%>" class="btn btn-dark float-right">Editar</a>
                        <a href="<%=link_excluir%>" class="btn btn-danger float-right">Excluir</a>                       
                    </td> 
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <a href="AdministradorController?acao=inserir" class="btn btn-info float-right">Inserir novo admin</a>  
    </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </div>
</html>
