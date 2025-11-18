<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Alunos" %>
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
                        Alunos alunoLogado = (Alunos) session.getAttribute("aluno");
                        if (alunoLogado != null) { %>
                            <a class="nav-link" href="TurmasController?acao=historico">Histórico</a>
                            <a class="nav-link" href="AlunoDisciplinaController?acao=listar_todos">Inscrição</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  } else { %>
                
     
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login Administrador</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaAlunoController?acao=Login">Login Aluno</a>
                            <a class="nav-link" href="/AindaSemImplementar">Login Professor</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
