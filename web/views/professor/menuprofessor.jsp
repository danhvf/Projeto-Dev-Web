<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Professores" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Professores profLogado = (Professores) session.getAttribute("professores");
                        if (profLogado != null) { %>
                            <a class="nav-link" href="NaoDeuPraImplementar">Lançar Nota</a>
                            <a class="nav-link" href="NaoDeuPraImplementar">Relatório dos Meus Alunos</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  } else { %>
                
     
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login Administrador</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaAlunoController?acao=Login">Login Aluno</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaProfessorController?acao=Login">Login Professor</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
