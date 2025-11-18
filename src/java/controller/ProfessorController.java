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
import entidade.Professores;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.ProfessoresDAO;
/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "ProfessorController", urlPatterns = {"/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfessoresDAO profdao = new ProfessoresDAO();
        String acao = (String) request.getParameter("acao");
        int id = 0;
        ArrayList<Professores> lista;

        Professores prof = new Professores();
        try {
            switch (acao) {
                case "listar_todos": {
                    lista = profdao.ListaDeProfessores();
                    request.setAttribute("meusProfessores", lista);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/admin/professores/listar_prof.jsp");
                    mostrar.forward(request, response);
                    break;
                }
                

                case "inserir": {
                    prof.setId(0);
                    prof.setNome("");
                    prof.setEmail("");
                    prof.setCpf("");
                    prof.setSenha("");

                    // Define o objeto 'professores' como atributo para ser acessado no JSP
                    request.setAttribute("professores", prof);

                    // Realiza o encaminhamento para o JSP
                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/admin/professores/insert_prof.jsp");
                    inserir.forward(request, response);
                    break;
                }

                case "editar": {
                    // Tratamento para garantir que o id seja um número válido
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        System.out.println("Ação: editar");
                        System.out.println("ID recebido: " + id);
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/bug1.jsp");
                        rd.forward(request, response);
                        return;  
                    }

                    // Buscar professor no banco
                    prof = profdao.getProfessor(id);
                    if (prof != null && prof.getId() > 0) {
                        request.setAttribute("professores", prof);
                        RequestDispatcher rs = request.getRequestDispatcher("/views/admin/professores/update_prof.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Professor não encontrado!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/bug2.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }

                

                case "excluir": {
                    System.out.println("Ação: excluir");
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        System.out.println("ID recebido para exclusão: " + id);
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido para exclusão!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/listar_prof.jsp");
                        rd.forward(request, response);
                        return;
                    }

                    Professores professor = new Professores();
                    professor.setId(id);
                    profdao.Excluir(professor);

                    lista = profdao.ListaDeProfessores();
                    request.setAttribute("meusProfessores", lista);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/admin/professores/listar_prof.jsp");
                    aposexcluir.forward(request, response);
                    break;
                }

            }
        } catch (Exception e) {
            // Aqui você pode tratar outras exceções não previstas
            e.printStackTrace();
            String mensagem = "Ocorreu um erro inesperado!";
            String alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/listar_prof.jsp");
            rd.forward(request, response);
        }
    }
    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem = "";
        String alert = "";
        try {
            Professores prof = new Professores();

            int id = Integer.parseInt(request.getParameter("id"));
            prof.setId(id);
            prof.setNome(request.getParameter("nome"));
            prof.setEmail(request.getParameter("email"));
            prof.setCpf(request.getParameter("cpf"));
            prof.setSenha(request.getParameter("senha"));
           
            ProfessoresDAO profdao = new ProfessoresDAO();         
            
            if (id > 0) { 
                
                Professores existente = profdao.getProfessor(id);
                if (existente != null) {
                    // Atualiza o professor
                    if (profdao.Alterar(prof)) {
                        mensagem = "Professor atualizado com sucesso!";
                        alert = "alert-info";
                    } else {
                        mensagem = "Erro ao atualizar professor!";
                        alert = "alert-danger";
                    }
                } else {
                    mensagem = "Professor não encontrado!";
                    alert = "alert-danger";
                }
                
                // Redirecionamento após edição
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("alert", alert);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/update_prof.jsp");
                rd.forward(request, response);
            } else { 
                // Inserção
                Professores isExistente = profdao.getProfessorPorCpf(prof.getCpf());
                System.out.println("isExistente" + isExistente);
                if (isExistente == null) {
                    if (profdao.Inserir(prof)) {
                        mensagem = "Professor inserido com sucesso!";
                        alert = "alert-info";
                    } else {
                        mensagem = "Erro ao gravar professor!";
                        alert = "alert-danger";
                    }
                } else {
                    mensagem = "Esse CPF já está cadastrado!";
                    alert = "alert-danger";
                }
                // Redirecionamento após inserir
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("alert", alert);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/insert_prof.jsp");
                rd.forward(request, response);
                
            }

            

        } catch (IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/professores/listar_prof.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
