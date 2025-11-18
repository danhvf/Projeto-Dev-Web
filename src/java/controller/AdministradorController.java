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
import entidade.Administrador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.AdministradorDAO;
/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AdministradorDAO administradoresdao = new AdministradorDAO();
        String acao = (String) request.getParameter("acao");
        int id = 0;
        ArrayList<Administrador> meusAdministradores;

        Administrador administradores = new Administrador();
        try {
            switch (acao) {
                case "listar_todos" -> {
                    meusAdministradores = administradoresdao.ListaDeAdministrador();
                    request.setAttribute("meusAdministradores", meusAdministradores);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
                    mostrar.forward(request, response);
                }
                

                case "inserir" -> {
                    administradores.setId(0);
                    administradores.setNome("");
                    administradores.setCpf("");
                    administradores.setSenha("");
                    administradores.setAprovado("");          
                    administradores.setEndereco("");

                    // Define o objeto 'administradores' como atributo para ser acessado no JSP
                    request.setAttribute("administradores", administradores);

                    // Realiza o encaminhamento para o JSP
                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/admin/insert_admin.jsp");
                    inserir.forward(request, response);
                }

                case "editar" -> {
                    // Tratamento para garantir que o id seja um número válido
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        String mensagem = "ID inválido!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
                        rd.forward(request, response);
                        return;  // Retorna para não continuar executando o código
                    }

                    administradores = administradoresdao.getAdministrador(id);
                    if (administradores.getId() > 0) {
                        request.setAttribute("administradores", administradores);
                        RequestDispatcher rs = request.getRequestDispatcher("/views/admin/update_admin.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Erro ao gravar administrador!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
                        rd.forward(request, response);
                    }
                }

                case "excluir" -> {
                    // Tratamento para garantir que o id seja um número válido
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                }   catch (NumberFormatException e) {
                        String mensagem = "ID inválido para exclusão!";
                        String alert = "alert-danger";
                        request.setAttribute("mensagem", mensagem);
                        request.setAttribute("alert", alert);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/page_adm.jsp");
                        rd.forward(request, response);
                    return;
                }

                        // Criar o objeto Administrador com o ID
                        Administrador administrador = new Administrador();
                        administrador.setId(id);

                        // Passar o objeto administrador para o método Excluir
                        administradoresdao.Excluir(administrador);

                        meusAdministradores = administradoresdao.ListaDeAdministrador();
                        request.setAttribute("meusAdministradores", meusAdministradores);
                        RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
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
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
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
            Administrador administradores = new Administrador();

            int id = Integer.parseInt(request.getParameter("id"));
            administradores.setId(id);
            administradores.setNome(request.getParameter("nome"));
            administradores.setCpf(request.getParameter("cpf"));
            administradores.setSenha(request.getParameter("password"));
            administradores.setAprovado(request.getParameter("aprovado"));
            administradores.setEndereco(request.getParameter("endereco"));
           
            AdministradorDAO administradorDAO = new AdministradorDAO();         
            
            if (id > 0) { 
                
                Administrador existente = administradorDAO.getAdministrador(id);
                if (existente != null) {
                    // Atualiza o administrador
                    if (administradorDAO.Alterar(administradores)) {
                        mensagem = "Administrador atualizado com sucesso!";
                        alert = "alert-info";
                    } else {
                        mensagem = "Erro ao atualizar administrador!";
                        alert = "alert-danger";
                    }
                } else {
                    mensagem = "Administrador não encontrado!";
                    alert = "alert-danger";
                }
            } else { 
                // Inserção
                Administrador isExistente = administradorDAO.getAdministradoresPorCPF(administradores.getCpf());
                if (isExistente == null) {
                    if (administradorDAO.Inserir(administradores)) {
                        mensagem = "Administrador inserido com sucesso!";
                        alert = "alert-info";
                    } else {
                        mensagem = "Erro ao gravar administrador!";
                        alert = "alert-danger";
                    }
                } else {
                    mensagem = "Esse CPF já está cadastrado!";
                    alert = "alert-danger";
                }
            }

            // Redirecionamento após inserção ou edição
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/update_admin.jsp");
            rd.forward(request, response);

        } catch (IOException | NumberFormatException | ServletException e) {
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/admin/listar_admin.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
