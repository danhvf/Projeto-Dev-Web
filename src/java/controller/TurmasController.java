/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entidade.TurmaInfo;
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
@WebServlet(name = "TurmasController", urlPatterns = {"/TurmasController"})
public class TurmasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TurmasDAO turmasDAO = new TurmasDAO();
        String acao = request.getParameter("acao");
        int id = 0;
        ArrayList<Turmas> lista;
        ArrayList<Turmas> historico;

        try {
            switch (acao) {
                case "listar_todos": {
                    lista = turmasDAO.ListaDeTurmas();
                    request.setAttribute("minhasTurmas", lista);
                    request.setAttribute("professores", turmasDAO.ListaDeProfessores());
                    request.setAttribute("disciplinas", turmasDAO.ListaDeDisciplinas());
                    request.setAttribute("alunos", turmasDAO.ListaDeAlunos());
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
                    mostrar.forward(request, response);
                    System.out.println("Tamanho da lista de turmas enviada para o JSP: " + lista.size());
                    break;
                }
                

                case "inserir": {
                    Turmas turma = new Turmas();
                    turma.setId(0);
                    turma.setCodigoTurma("");
                    turma.setNota(0);

                    // Adicione as listas de referência para seleção no JSP
                    request.setAttribute("professores", turmasDAO.ListaDeProfessores());
                    request.setAttribute("disciplinas", turmasDAO.ListaDeDisciplinas());
                    request.setAttribute("alunos", turmasDAO.ListaDeAlunos());
                    request.setAttribute("turma", turma);

                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/admin/turma/insert_turma.jsp");
                    inserir.forward(request, response);
                    break;
                }
                
                case "buscandoID": {
                    System.out.println("Entrou no case buscandoID");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/views/aluno/buscandoID.jsp");
                    dispatcher.forward(request, response);

                    break;
                }

                
                case "historico": {
                    System.out.println("Entrou no switch caso historico");
                    String alunoIdParam = request.getParameter("alunoId");
                    System.out.println("alunoId= " + alunoIdParam);

                    // Verifica se o parâmetro está nulo ou vazio
                    if (alunoIdParam == null || alunoIdParam.trim().isEmpty()) {
                        System.err.println("Parâmetro alunoId está nulo ou vazio.");
                        request.setAttribute("error", "Insira um ID válido.");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/buscandoID.jsp");
                        rd.forward(request, response);
                        break;
                    }

                    try {
                        System.out.println("Entrou no try catch de historico");
                        int alunoId = Integer.parseInt(alunoIdParam);
                        historico = turmasDAO.getHistorico(alunoId);
                        System.out.println("Histórico recuperado: " + historico);
                        request.setAttribute("historico", historico);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/historico.jsp");
                        rd.forward(request, response);
                    } catch (NumberFormatException e) {
                        System.err.println("ID inválido, por favor insira um número inteiro.");
                        request.setAttribute("error", "O ID deve ser um número inteiro.");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/buscandoID.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }




                case "editar": {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Turmas turma = turmasDAO.getTurma(id);
                    if (turma != null && turma.getId() > 0) {
                        request.setAttribute("professores", turmasDAO.ListaDeProfessores());
                        request.setAttribute("disciplinas", turmasDAO.ListaDeDisciplinas());
                        request.setAttribute("alunos", turmasDAO.ListaDeAlunos());
                        request.setAttribute("turma", turma);

                        RequestDispatcher rs = request.getRequestDispatcher("/views/admin/turma/update_turma.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Turma não encontrada!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }

                case "excluir": {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido para exclusão!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Turmas turma = new Turmas();
                    turma.setId(id);
                    turmasDAO.Excluir(turma);

                    lista = turmasDAO.ListaDeTurmas();
                    request.setAttribute("minhasTurmas", lista);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/comum/showMessage.jsp");
                    aposexcluir.forward(request, response);
                    break;
                }

                default: {
                    String mensagem = "Ação inválida!";
                    String alert = "alert-danger";
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("alert", alert);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
                    rd.forward(request, response);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Ocorreu um erro inesperado!";
            String alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem = "";
        String alert = "";
        TurmasDAO turmasDAO = new TurmasDAO();

        try {
            Turmas turma = new Turmas();
            turma.setId(Integer.parseInt(request.getParameter("id")));
            turma.setProfessorId(Integer.parseInt(request.getParameter("professor_id")));
            turma.setDisciplinaId(Integer.parseInt(request.getParameter("disciplina_id")));
            turma.setAlunoId(Integer.parseInt(request.getParameter("aluno_id")));
            turma.setCodigoTurma(request.getParameter("codigo_turma"));
            turma.setNota(Float.parseFloat(request.getParameter("nota")));

            if (turma.getId() > 0) {
                turmasDAO.Alterar(turma);
                mensagem = "Turma atualizada com sucesso!";
                alert = "alert-info";
            } 
                       
            else {
                turmasDAO.Inserir(turma);
                mensagem = "Turma inserida com sucesso!";
                alert = "alert-info";
            } 

            ArrayList<Turmas> lista = turmasDAO.ListaDeTurmas();
            request.setAttribute("minhasTurmas", lista);
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/turma/listar_turma.jsp");
            rd.forward(request, response);
        }
    }
}

