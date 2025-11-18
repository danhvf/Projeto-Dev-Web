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
import entidade.Alunos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.AlunosDAO;
/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "AlunosController", urlPatterns = {"/AlunosController"})
public class AlunosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlunosDAO alunosDAO = new AlunosDAO();
        String acao = request.getParameter("acao");
        int id = 0;
        ArrayList<Alunos> lista;

        try {
            switch (acao) {
                case "listar_todos": {
                    lista = alunosDAO.ListaDeAlunos();
                    request.setAttribute("meusAlunos", lista);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
                    mostrar.forward(request, response);
                    break;
                }

                case "inserir": {
                    Alunos aluno = new Alunos();
                    aluno.setId(0);
                    aluno.setNome("");
                    aluno.setEmail("");
                    aluno.setCpf("");
                    aluno.setCelular("");
                    aluno.setEndereco("");
                    aluno.setCidade("");
                    aluno.setBairro("");
                    aluno.setCep("");

                    request.setAttribute("aluno", aluno);
                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/admin/aluno/insert_aluno.jsp");
                    inserir.forward(request, response);
                    break;
                }

                case "editar": {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        System.out.println("Chamou editar e recebeu id:" + id);
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Alunos aluno = alunosDAO.getAluno(id);
                    System.out.println("Recebeu aluno: " + id);
                    if (aluno != null && aluno.getId() > 0) {
                        System.out.println("entrou no if");
                        request.setAttribute("aluno", aluno);
                        RequestDispatcher rs = request.getRequestDispatcher("/views/admin/aluno/update_aluno.jsp");
                        rs.forward(request, response);
                    } else {
                        System.out.println("Aluno não encontrado");
                        String mensagem = "Aluno não encontrado!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
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
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Alunos aluno = new Alunos();
                    aluno.setId(id);
                    alunosDAO.Excluir(aluno);

                    lista = alunosDAO.ListaDeAlunos();
                    request.setAttribute("meusAlunos", lista);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
                    aposexcluir.forward(request, response);
                    break;
                }

                default:
                    String mensagem = "Ação inválida!";
                    String alert = "alert-danger";
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("alert", alert);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Ocorreu um erro inesperado!";
            String alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem = "";
        String alert = "";
        AlunosDAO alunosDAO = new AlunosDAO();

        try {
            Alunos aluno = new Alunos();
            aluno.setId(Integer.parseInt(request.getParameter("id")));
            aluno.setNome(request.getParameter("nome"));
            aluno.setEmail(request.getParameter("email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setSenha(request.getParameter("senha"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));
            System.out.println("Chamou do Post: " + aluno.getId());
            if (aluno.getId() > 0) {
                alunosDAO.Alterar(aluno);
                mensagem = "Aluno atualizado com sucesso!";
                alert = "alert-info";
            } else {
                alunosDAO.Inserir(aluno);
                mensagem = "Aluno inserido com sucesso!";
                alert = "alert-info";
            }

            ArrayList<Alunos> lista = alunosDAO.ListaDeAlunos();
            request.setAttribute("meusAlunos", lista);
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/aluno/listar_aluno.jsp");
            rd.forward(request, response);
        }
    }
}
