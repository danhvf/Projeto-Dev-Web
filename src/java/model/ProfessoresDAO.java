package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Professores;


/**
 *
 * @author Daniel Fontoura
 * 
 */

public class ProfessoresDAO {

    public boolean Inserir(Professores prof) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO professores (nome, email, cpf, senha)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, prof.getNome());
            sql.setString(2, prof.getEmail());
            sql.setString(3, prof.getCpf());
            sql.setString(4, prof.getSenha());            
            sql.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Professores getProfessor(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Professores prof = new Professores();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM professores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) { 
                    prof.setId(Integer.parseInt(resultado.getString("ID")));
                    prof.setNome(resultado.getString("NOME"));
                    prof.setEmail(resultado.getString("EMAIL"));
                    prof.setCpf(resultado.getString("CPF"));
                    prof.setSenha(resultado.getString("SENHA"));
                }
            }
            return prof;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public boolean Alterar(Professores prof) throws Exception {
        Conexao conexao = new Conexao();
        try {
            System.out.println("ID recebido para alterar no DAO: " + prof.getId());
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE professores SET nome = ?, email = ?, cpf = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, prof.getNome());
            sql.setString(2, prof.getEmail());
            sql.setString(3, prof.getCpf());
            sql.setString(4, prof.getSenha());
            sql.setInt(5, prof.getId());
            sql.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Professores prof) throws Exception {
        Conexao conexao = new Conexao();
        try {
            System.out.println("ID recebido para exclusão no DAO: " + prof.getId()); // Log para depuração
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM professores WHERE ID = ? ");
            sql.setInt(1, prof.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }


    public ArrayList<Professores> ListaDeProfessores() {
        ArrayList<Professores> lista = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM professores";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Professores professor = new Professores(resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"));
                    professor.setId(Integer.parseInt(resultado.getString("id")));
                    lista.add(professor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeProfessores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return lista;
    }
    
        public Professores getProfessorPorCpf(String codigo) {
            
        Professores prof = null;
        PreparedStatement ps;
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM professores WHERE cpf = ?";
            System.out.println("Executando SQL: " + sql); // Log para depuração
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, codigo);
            System.out.println("Parâmetro CPF: " + codigo); // Log para depuração
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                prof = new Professores();
                prof.setId(rs.getInt("id"));
                prof.setNome(rs.getString("nome"));
                prof.setEmail(rs.getString("email"));
                prof.setCpf(rs.getString("cpf"));
                prof.setSenha(rs.getString("senha"));
            } else {
                System.out.println("Nenhum resultado encontrado para o CPF: " + codigo);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
         System.out.println("Resultado GetAdmin por CPF " + prof);
        return prof;
    }
    public Professores Logar(Professores prof) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM professores WHERE cpf = ? AND senha = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, prof.getCpf());
            ps.setString(2, prof.getSenha());
            System.out.println("Query de logar executada: " + ps);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prof = new Professores();
                prof.setId(rs.getInt("id"));
                prof.setNome(rs.getString("nome"));
                prof.setEmail(rs.getString("email"));
                prof.setCpf(rs.getString("cpf"));
                prof.setSenha(rs.getString("senha"));
                return prof;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao logar professor: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return new Professores(); 
    }    
        

}
