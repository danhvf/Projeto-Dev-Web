package controller;

import entidade.Professores;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProfessoresDAO;

/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "AutenticaProfessorController", urlPatterns = {"/AutenticaProfessorController"})
public class AutenticaProfessorController extends HttpServlet {
    @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

                RequestDispatcher rd;
                rd = request.getRequestDispatcher("/views/professor/formLogin.jsp");
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
            Professores profObtido = null; // Prof obtido do banco de dados
            Professores prof = new Professores();
            prof.setCpf(cpf_user);
            prof.setSenha(senha_user);

            ProfessoresDAO profDAO = new ProfessoresDAO();

        try {
            // Chamando o método de autenticação
            profObtido = profDAO.Logar(prof);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("msgError", "Erro ao tentar autenticar. Tente novamente mais tarde.");
            rd = request.getRequestDispatcher("/views/aluno/formLogin.jsp");
            rd.forward(request, response);
            return;
        }

        if (profObtido != null && profObtido.getId() != 0) {
            // Usuário autenticado, criar sessão e redirecionar para o dashboard
            HttpSession session = request.getSession();
            session.setAttribute("professores", profObtido);
            session.setAttribute("ID", prof.getId());
            rd = request.getRequestDispatcher("/professor/dashboard");
            rd.forward(request, response);
        } else {
            // CPF ou senha inválidos
            request.setAttribute("msgError", "CPF ou senha incorretos.");
            rd = request.getRequestDispatcher("/views/professor/formLogin.jsp");
            rd.forward(request, response);
        }
        }
}
            
