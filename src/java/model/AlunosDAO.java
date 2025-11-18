package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Alunos;
import model.Conexao;

/**
 *
 * @author Daniel Fontoura
 * 
 CREATE TABLE IF NOT EXISTS `alunos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `celular` char(14) CHARACTER SET utf8 NOT NULL,
  `cpf` varchar(14) CHARACTER SET utf8 NOT NULL,
  `senha` varchar(255) CHARACTER SET utf8 NOT NULL,
  `endereco` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `cidade` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `bairro` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `cep` varchar(9) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */

public class AlunosDAO {

    public void Inserir(Alunos aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Alunos getAluno(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Alunos aluno = new Alunos();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    aluno.setNome(resultado.getString("NOME"));
                    aluno.setEmail(resultado.getString("EMAIL"));
                    aluno.setCelular(resultado.getString("CELULAR"));
                    aluno.setCpf(resultado.getString("CPF"));
                    aluno.setSenha(resultado.getString("SENHA"));
                    aluno.setCpf(resultado.getString("CPF"));
                    aluno.setEndereco(resultado.getString("ENDERECO"));
                    aluno.setCidade(resultado.getString("CIDADE"));
                    aluno.setBairro(resultado.getString("BAIRRO"));
                    aluno.setCep(resultado.getString("CEP"));
                    
                }
            }
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Alunos aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ?  WHERE ID = ? ");
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            sql.setInt(10, aluno.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Alunos aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM alunos WHERE ID = ? ");
            sql.setInt(1, aluno.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Alunos> ListaDeAlunos() {
        ArrayList<Alunos> meusAlunos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM alunos order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Alunos alunos = new Alunos(resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CELULAR"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("CIDADE"),
                            resultado.getString("BAIRRO"),
                            resultado.getString("CEP"));
                    alunos.setId(Integer.parseInt(resultado.getString("id")));
                    meusAlunos.add(alunos);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAlunos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAlunos;
    }
    
    public Alunos Logar(Alunos aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM alunos WHERE cpf = ? AND senha = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aluno.getCpf());
            ps.setString(2, aluno.getSenha());
            System.out.println("Query de logar executada: " + ps);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                aluno = new Alunos();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCelular(rs.getString("celular"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCep(rs.getString("cep"));
                return aluno;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao logar aluno: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return new Alunos(); 
    }
    
    public Alunos getAlunoPorCpf(String codigo) {
            
        Alunos aluno = null;
        PreparedStatement ps;
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM alunos WHERE cpf = ?";
            System.out.println("Executando SQL: " + sql); // Log para depuração
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, codigo);
            System.out.println("Parâmetro CPF: " + codigo); // Log para depuração
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                aluno = new Alunos();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCelular(rs.getString("celular"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEndereco(rs.getString("endereco"));                
                aluno.setCidade(rs.getString("cidade"));
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCep(rs.getString("cep"));
            } else {
                System.out.println("Nenhum resultado encontrado para o CPF: " + codigo);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
         System.out.println("Resultado GetAdmin por CPF " + aluno);
        return aluno;
    }


}
