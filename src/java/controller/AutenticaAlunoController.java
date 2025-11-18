package controller;

import entidade.Alunos;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AlunosDAO;

/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "AutenticaAlunoController", urlPatterns = {"/AutenticaAlunoController"})
    public class AutenticaAlunoController extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/views/aluno/formLogin.jsp");
            rd.forward(request, response);

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // Pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        System.out.println("Login: " + cpf_user + "\nSenha: " + senha_user);

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // Dados não foram preenchidos, retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha não podem estar vazios.");
            rd = request.getRequestDispatcher("/views/aluno/formLogin.jsp");
            rd.forward(request, response);
            return;
        }

        Alunos alunoObtido = null; // Aluno obtido do banco de dados
        Alunos aluno = new Alunos();
        aluno.setCpf(cpf_user);
        aluno.setSenha(senha_user);

        AlunosDAO alunosDAO = new AlunosDAO();

        try {
            // Chamando o método de autenticação
            alunoObtido = alunosDAO.Logar(aluno);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("msgError", "Erro ao tentar autenticar. Tente novamente mais tarde.");
            rd = request.getRequestDispatcher("/views/aluno/formLogin.jsp");
            rd.forward(request, response);
            return;
        }

        if (alunoObtido != null && alunoObtido.getId() != 0) {
            // Usuário autenticado, criar sessão e redirecionar para o dashboard
            HttpSession session = request.getSession();
            session.setAttribute("aluno", alunoObtido);
            session.setAttribute("ID", aluno.getId());
            rd = request.getRequestDispatcher("/aluno/dashboard");
            rd.forward(request, response);
        } else {
            // CPF ou senha inválidos
            request.setAttribute("msgError", "CPF ou senha incorretos.");
            rd = request.getRequestDispatcher("/views/aluno/formLogin.jsp");
            rd.forward(request, response);
        }
    }



}
