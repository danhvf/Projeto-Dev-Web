/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entidade.Turmas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TurmasDAO;

/**
 *
 * @author Daniel Fontoura
 */
@WebServlet(name = "InscricaoController", urlPatterns = {"/InscricaoController"})
public class InscricaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TurmasDAO turmasDAO = new TurmasDAO();
        String acao = request.getParameter("acao");
        System.out.println("Acao recebida: " + acao);
        int id = 0;
        ArrayList<Turmas> lista;
        ArrayList<Turmas> historico;
        ArrayList<Turmas> dados;

        try {
            switch (acao) {

                case "inserir": {
                    String ProfID = request.getParameter("professor_id");  
                    String AlunoID = request.getParameter("aluno_id");
                    String DisciplaID = request.getParameter("disciplina_id");      
                    String CodTurma = request.getParameter("codigoTurma");
                    
                    if (ProfID == null || AlunoID == null || DisciplaID == null || CodTurma == null) {
                    System.out.println("Um dos parâmetros está nulo");
                    }
                    
                    Turmas turma = new Turmas();
                    turma.setCodigoTurma(CodTurma);
                    turma.setNota(0);
                    turma.setAlunoId(Integer.parseInt(AlunoID));
                    turma.setProfessorId(Integer.parseInt(ProfID));
                    turma.setDisciplinaId(Integer.parseInt(DisciplaID));
                    // Adicione as listas de referência para seleção no JSP
                    
                    request.setAttribute("turma", turma);

                    RequestDispatcher inserir = getServletContext().getRequestDispatcher("/views/aluno/inscrever.jsp");
                    inserir.forward(request, response);
                    break;
                }
                
                case "retornaDados": {
                    String codigoTurma = request.getParameter("codigoTurma");
                    System.out.println("Código da Turma recebido: " + codigoTurma);

                    if (codigoTurma != null && !codigoTurma.isEmpty()) {
                        try {
                            // Busca a turma com base no código
                            Turmas turmaDados = turmasDAO.getTurmaPorCod(codigoTurma);
                                
                            if (turmaDados != null) {
                                // Define o resultado como atributo da requisição
                                request.setAttribute("turmaDados", turmaDados);
                                System.out.println("Dados Recebidos: " + turmaDados.getDisciplinaNome());
                                System.out.println("Dados Recebidos: " + turmaDados.getProfessorNome());
                                // Redireciona para a página JSP para exibir os resultados
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/inscrever.jsp");
                                rd.forward(request, response);
                            } else {
                                // Se não encontrar nenhuma turma
                                System.err.println("Nenhuma turma encontrada para o código: " + codigoTurma);
                                request.setAttribute("erro", "Nenhuma turma encontrada para o código fornecido.");
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/listar_disciplina_aluno.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception e) {
                            System.err.println("Erro ao buscar os dados da turma: " + e.getMessage());
                            request.setAttribute("erro", "Erro ao buscar os dados da turma.");
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/listar_disciplina_aluno.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        System.err.println("Nenhum código de turma foi recebido.");
                        request.setAttribute("erro", "Código da turma não informado.");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/erro.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                
                case "cancelar": {
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

            // Validação dos parâmetros recebidos
            
            String professorIdStr = request.getParameter("professor_id");
            String disciplinaIdStr = request.getParameter("disciplina_id");
            String alunoIdStr = request.getParameter("aluno_id");
            String codigoTurma = request.getParameter("codigo_turma");
            String notaStr = request.getParameter("nota");

            if (professorIdStr == null || disciplinaIdStr == null || alunoIdStr == null || codigoTurma == null || notaStr == null) {
                throw new IllegalArgumentException("Parâmetro ausente no formulário.");
            }

            // Conversões seguras
            
            turma.setProfessorId(Integer.parseInt(professorIdStr));
            turma.setDisciplinaId(Integer.parseInt(disciplinaIdStr));
            turma.setAlunoId(Integer.parseInt(alunoIdStr));
            turma.setCodigoTurma(codigoTurma);
            turma.setNota(Float.parseFloat(notaStr));

            // Processa inserção ou atualização
            if (turma.getId() > 0) {
                turmasDAO.Alterar(turma);
                mensagem = "Turma atualizada com sucesso!";
                alert = "alert-info";
            } else {
                turmasDAO.InserirComValidacao(turma);
                mensagem = "Turma inserida com sucesso!";
                alert = "alert-info";
            }

            // Atualiza lista e encaminha resposta
            ArrayList<Turmas> lista = turmasDAO.ListaDeTurmas();
            request.setAttribute("minhasTurmas", lista);
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            mensagem = "Erro ao processar os dados: formato inválido.";
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/indexaluno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao processar os dados: " + e.getMessage();
            alert = "alert-danger";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("alert", alert);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/aluno/indexaluno.jsp");
            rd.forward(request, response);
        }
    }


}
