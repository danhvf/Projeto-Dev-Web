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
import entidade.Disciplina;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.DisciplinaDAO;
/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "DisciplinaController", urlPatterns = {"/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        String acao = request.getParameter("acao");
        int id = 0;
        ArrayList<Disciplina> lista;

        try {
            switch (acao) {
                case "listar_todos": {
                    lista = disciplinaDAO.ListaDeDisciplinas();
                    request.setAttribute("minhasDisciplinas", lista);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
                    mostrar.forward(request, response);
                    break;
                }

                case "inserir": {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(0);
                    disciplina.setNome("");
                    disciplina.setRequisito("");
                    disciplina.setEmenta("");
                    disciplina.setCH(0);

                    request.setAttribute("disciplina", disciplina);
                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/admin/disciplina/insert_disciplina.jsp");
                    inserir.forward(request, response);
                    break;
                }

                case "editar": {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        System.out.println("DEBUG: Controller Editar ID recebido: " + id);
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Disciplina disciplina = disciplinaDAO.getDisciplina(id);
                    if (disciplina != null && disciplina.getId() > 0) {
                        System.out.println("DEBUG: Disciplina encontrada dentro do IF: " + disciplina.getId());
                        request.setAttribute("disciplina", disciplina);
                        RequestDispatcher rs = request.getRequestDispatcher("/views/admin/disciplina/update_disciplina.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Disciplina não encontrada!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
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
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(id);
                    disciplinaDAO.Excluir(disciplina);

                    lista = disciplinaDAO.ListaDeDisciplinas();
                    request.setAttribute("minhasDisciplinas", lista);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
                    aposexcluir.forward(request, response);
                    break;
                }

                default: {
                    String mensagem = "Ação inválida!";
                    String alert = "alert-danger";
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("alert", alert);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
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
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem = "";
        String alert = "";
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        try {
            Disciplina disciplina = new Disciplina();
            disciplina.setId(Integer.parseInt(request.getParameter("id")));
            disciplina.setNome(request.getParameter("nome"));
            disciplina.setRequisito(request.getParameter("requisito"));
            disciplina.setEmenta(request.getParameter("ementa"));
            disciplina.setCH(Integer.parseInt(request.getParameter("carga_horaria")));

            if (disciplina.getId() > 0) {
                disciplinaDAO.Alterar(disciplina);
                mensagem = "Disciplina atualizada com sucesso!";
                alert = "alert-info";
            } else {
                disciplinaDAO.Inserir(disciplina);
                mensagem = "Disciplina inserida com sucesso!";
                alert = "alert-info";
            }

            ArrayList<Disciplina> lista = disciplinaDAO.ListaDeDisciplinas();
            request.setAttribute("minhasDisciplinas", lista);
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/disciplina/listar_disciplina.jsp");
            rd.forward(request, response);
        }
    }
}
