package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Administrador;
import model.Conexao;

/*
-- Estrutura da tabela `Administradors`

CREATE TABLE IF NOT EXISTS `Administrador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `aprovado` varchar(1) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class AdministradorDAO {

    public boolean Inserir(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO administrador (nome, cpf, senha, aprovado, endereco)"
                    + " VALUES (?,?,?,?,?)");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getSenha());
            sql.setString(4, Administrador.getAprovado());
            sql.setString(5, Administrador.getEndereco());
            sql.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Administrador getAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Administrador Administrador = new Administrador();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administrador WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    Administrador.setNome(resultado.getString("NOME"));
                    Administrador.setCpf(resultado.getString("CPF"));
                    Administrador.setEndereco(resultado.getString("SENHA"));
                    Administrador.setSenha(resultado.getString("APROVADO"));
                    Administrador.setSenha(resultado.getString("ENDERECO"));
                }
            }
            return Administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public boolean Alterar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE administrador SET nome = ?, cpf = ?, senha = ?, aprovado = ?, endereco = ? WHERE ID = ? ");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getSenha());
            sql.setString(4, Administrador.getAprovado());
            sql.setString(5, Administrador.getEndereco());
            sql.setInt(6, Administrador.getId());
            sql.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM administrador WHERE ID = ? ");
            sql.setInt(1, Administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Administrador> ListaDeAdministrador() {
        ArrayList<Administrador> meusAdministradores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM administrador";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador Administrador = new Administrador(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("APROVADO"),
                            resultado.getString("ENDERECO"));
                    Administrador.setId(Integer.parseInt(resultado.getString("id")));
                    meusAdministradores.add(Administrador);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAdministradores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAdministradores;
    }

    public Administrador Logar(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM administrador WHERE cpf = ? AND senha = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, administrador.getCpf());
            ps.setString(2, administrador.getSenha());
            System.out.println("Query de logar executada: " + ps);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrador adm = new Administrador();
                adm.setId(rs.getInt("id"));
                adm.setNome(rs.getString("nome"));
                adm.setCpf(rs.getString("cpf"));
                adm.setSenha(rs.getString("senha"));
                adm.setAprovado(rs.getString("aprovado"));
                adm.setEndereco(rs.getString("endereco"));
                return adm;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao logar administrador: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return new Administrador(); 
    }

    
     public Administrador getAdministradoresPorCPF(String codigo) {
        Administrador administrador = null;
        PreparedStatement ps;
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT * FROM administrador WHERE cpf = ?";
            System.out.println("Executando SQL: " + sql); // Log para depuração
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, codigo);
            System.out.println("Parâmetro CPF: " + codigo); // Log para depuração
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                administrador = new Administrador();
                administrador.setId(rs.getInt("id"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getString("cpf"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setAprovado(rs.getString("aprovado"));
                administrador.setEndereco(rs.getString("endereco"));
            } else {
                System.out.println("Nenhum resultado encontrado para o CPF: " + codigo);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
         System.out.println("Resultado GetAdmin por CPF " + administrador);
        return administrador;
    }

}
