package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Disciplina;


/**
 *
 * @author Daniel Fontoura
 */

public class DisciplinaDAO {

    public void Inserir(Disciplina dp) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO disciplina (nome, requisito, ementa, carga_horaria)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, dp.getNome());
            sql.setString(2, dp.getRequisito());
            sql.setString(3, dp.getEmenta());
            sql.setInt(4, dp.getCH());            
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Disciplina getDisciplina(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Disciplina dp = new Disciplina();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM disciplina WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    dp.setId(Integer.parseInt(resultado.getString("ID")));
                    dp.setNome(resultado.getString("NOME"));
                    dp.setRequisito(resultado.getString("REQUISITO"));
                    dp.setEmenta(resultado.getString("EMENTA"));
                    dp.setCH(Integer.parseInt(resultado.getString("CARGA_HORARIA")));
                }
            }
            return dp;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Disciplina dp) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ?  WHERE ID = ? ");
            sql.setString(1, dp.getNome());
            sql.setString(2, dp.getRequisito());
            sql.setString(3, dp.getEmenta());
            sql.setInt(4, dp.getCH());
            sql.setInt(5, dp.getId());
            sql.executeUpdate();
            System.out.println("executando query:" + sql);

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Disciplina dp) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM disciplina WHERE ID = ? ");
            sql.setInt(1, dp.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Disciplina> ListaDeDisciplinas() {
        ArrayList<Disciplina> minhasDisciplinas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM disciplina order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina dp = new Disciplina(resultado.getString("NOME"),
                            resultado.getString("REQUISITO"),
                            resultado.getString("EMENTA"),
                            Integer.parseInt(resultado.getString("CARGA_HORARIA")));
                    dp.setId(Integer.parseInt(resultado.getString("id")));
                    minhasDisciplinas.add(dp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeDisciplinas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasDisciplinas;
    }

}
