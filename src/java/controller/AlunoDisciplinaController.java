/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidade.Turmas;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.TurmasDAO;

/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "AlunoDisciplinaController", urlPatterns = {"/AlunoDisciplinaController"})
public class AlunoDisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TurmasDAO turmasDAO = new TurmasDAO();
        String acao = request.getParameter("acao");
        int id = 0;
        ArrayList<Turmas> lista;

       
            switch (acao) {
                case "listar_todos": {
                    lista = turmasDAO.ListasDeTurmasCod();
                    request.setAttribute("minhasTurmas", lista);
                    request.setAttribute("professores", turmasDAO.ListaDeProfessores());
                    request.setAttribute("disciplinas", turmasDAO.ListaDeDisciplinas());
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/aluno/listar_disciplina_aluno.jsp");
                    mostrar.forward(request, response);
                    System.out.println("Tamanho da lista de turmas enviada para o JSP: " + lista.size());
                    break;
                }
        }
    }
}
