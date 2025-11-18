package controller;

import entidade.Administrador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        System.out.println("Login: " +cpf_user + "\nSenha: "+senha_user);

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha não podem estar vazios.");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } else {
            Administrador administradorObtido;
            Administrador administrador = new Administrador(cpf_user, senha_user);
            AdministradorDAO administradorDAO = new AdministradorDAO();

            try {
                administradorObtido = administradorDAO.Logar(administrador);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (administradorObtido.getId() != 0) {
                if ("S".equalsIgnoreCase(administradorObtido.getAprovado())) {
                    // Usuário aprovado, criar sessão e redirecionar
                    HttpSession session = request.getSession();
                    session.setAttribute("administrador", administradorObtido);
                    rd = request.getRequestDispatcher("/admin/dashboard");
                    rd.forward(request, response);
                } else {
                    // Usuário não aprovado
                    request.setAttribute("msgError", "Usuário não está aprovado para acesso.");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                    rd.forward(request, response);
                }
            } else {
                // CPF ou senha inválidos
                request.setAttribute("msgError", "Usuário e/ou senha incorreto.");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);
            }
        }
    }


}
