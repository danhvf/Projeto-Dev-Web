package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Turmas;

public class TurmasDAO {
    public void Inserir(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?,?,?,?,?)");
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setFloat(5, turma.getNota());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void InserirComValidacao(Turmas turma) throws Exception {
    Conexao conexao = new Conexao();
    try {
        // Verificar o número de inscritos para o código da turma
        String verificarSQL = """
            SELECT COUNT(*) AS inscritos, 
                   MAX(professor_id) AS professor_id, 
                   MAX(disciplina_id) AS disciplina_id
            FROM turmas
            WHERE codigo_turma = ?
        """;
        PreparedStatement verificarStmt = conexao.getConexao().prepareStatement(verificarSQL);
        verificarStmt.setString(1, turma.getCodigoTurma());
        ResultSet rs = verificarStmt.executeQuery();

        if (rs.next()) {
            int inscritos = rs.getInt("inscritos");
            if (inscritos >= 2) {
                // Retorna falso se não há vagas
                return;
            }

            // Obter informações do professor e da disciplina
            turma.setProfessorId(rs.getInt("professor_id"));
            turma.setDisciplinaId(rs.getInt("disciplina_id"));
        }

        // Inserir o novo aluno na turma
        String inserirSQL = """
            INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) 
            VALUES (?, ?, ?, ?, ?)
        """;
        PreparedStatement inserirStmt = conexao.getConexao().prepareStatement(inserirSQL);
        inserirStmt.setInt(1, turma.getProfessorId());
        inserirStmt.setInt(2, turma.getDisciplinaId());
        inserirStmt.setInt(3, turma.getAlunoId());
        inserirStmt.setString(4, turma.getCodigoTurma());
        inserirStmt.setFloat(5, turma.getNota());
        inserirStmt.executeUpdate();

        return; // Inscrição realizada com sucesso
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao inserir na turma: " + e.getMessage());
    } finally {
        conexao.closeConexao();
    }
}


    public Turmas getTurma(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Turmas turma = null;
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "SELECT t.id, t.codigo_turma, t.nota, " +
                "t.professor_id, t.disciplina_id, t.aluno_id, " + 
                "p.nome AS professor_nome, d.nome AS disciplina_nome, a.nome AS aluno_nome " +
                "FROM turmas t " +
                "JOIN professores p ON t.professor_id = p.id " +
                "JOIN disciplina d ON t.disciplina_id = d.id " +
                "JOIN alunos a ON t.aluno_id = a.id " +
                "WHERE t.id = ?"
            );
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                turma = new Turmas();
                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getFloat("nota"));
            }
            return turma;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Turmas getTurmaPorCod(String CodigoTurma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Turmas turma = null;
            String selectSQL = 
                    """
                    SELECT 
                        t.id, 
                        t.professor_id, 
                        t.disciplina_id, 
                        t.aluno_id, 
                        t.codigo_turma, 
                        t.nota,
                        d.nome AS disciplinaNome, 
                        p.nome AS professorNome
                    FROM 
                        turmas t
                    JOIN 
                        disciplina d ON t.disciplina_id = d.id
                    JOIN 
                        professores p ON t.professor_id = p.id
                    WHERE 
                        t.codigo_turma = ?
                    LIMIT 1
                    """;
            PreparedStatement sql = conexao.getConexao().prepareStatement(selectSQL);
            sql.setString(1, CodigoTurma);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                turma = new Turmas();
                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getFloat("nota"));
                turma.setDisciplinaNome(resultado.getString("disciplinaNome"));
                turma.setProfessorNome(resultado.getString("professorNome"));
            }
            return turma;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
    
    public ArrayList<Turmas> getHistorico(int alunoId) throws SQLException {
    ArrayList<Turmas> historico = new ArrayList<>();
    Conexao conexao = new Conexao();
    String selectSQL = 
            """
            SELECT 
                t.id, 
                t.professor_id, 
                t.disciplina_id, 
                t.aluno_id, 
                t.codigo_turma, 
                t.nota, 
                d.nome AS disciplinaNome, 
                p.nome AS professorNome
            FROM 
                turmas t
            JOIN 
                disciplina d ON t.disciplina_id = d.id
            JOIN 
                professores p ON t.professor_id = p.id
            WHERE 
                t.aluno_id = ?
            """;
    try (PreparedStatement sql = conexao.getConexao().prepareStatement(selectSQL)) {
        sql.setInt(1, alunoId);
        ResultSet resultado = sql.executeQuery();
        while (resultado.next()) {
            Turmas turma = new Turmas();
            turma.setId(resultado.getInt("id"));
            turma.setProfessorId(resultado.getInt("professor_id"));
            turma.setDisciplinaId(resultado.getInt("disciplina_id"));
            turma.setAlunoId(resultado.getInt("aluno_id"));
            turma.setCodigoTurma(resultado.getString("codigo_turma"));
            turma.setNota(resultado.getFloat("nota"));
            turma.setDisciplinaNome(resultado.getString("disciplinaNome"));
            turma.setProfessorNome(resultado.getString("professorNome"));
            historico.add(turma);
        }
    }
    System.out.println(historico);
    return historico;
}


    public void Alterar(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "UPDATE turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ? WHERE id = ?");
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setFloat(5, turma.getNota());
            sql.setInt(6, turma.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "DELETE FROM turmas WHERE id = ?");
            sql.setInt(1, turma.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turmas> ListaDeTurmas() {
       ArrayList<Turmas> minhasTurmas = new ArrayList<>();
       Conexao conexao = new Conexao();
       String selectSQL = "SELECT t.id, t.codigo_turma, t.nota, " +
                              "t.professor_id, t.disciplina_id, t.aluno_id, " + // Adicionar IDs
                              "p.nome AS professor_nome, d.nome AS disciplina_nome, a.nome AS aluno_nome " +
                              "FROM turmas t " +
                              "JOIN professores p ON t.professor_id = p.id " +
                              "JOIN disciplina d ON t.disciplina_id = d.id " +
                              "JOIN alunos a ON t.aluno_id = a.id";
       try {
           PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
           ResultSet resultado = preparedStatement.executeQuery();
           while (resultado.next()) {
               Turmas turma = new Turmas();
               turma.setId(resultado.getInt("id"));
               turma.setCodigoTurma(resultado.getString("codigo_turma"));
               turma.setNota(resultado.getFloat("nota"));
               turma.setProfessorId(resultado.getInt("professor_id")); 
               turma.setDisciplinaId(resultado.getInt("disciplina_id")); 
               turma.setAlunoId(resultado.getInt("aluno_id")); 
               minhasTurmas.add(turma);
           }
       } catch (SQLException e) {
           System.out.println("Query executada: " + selectSQL);
           System.out.println("Tamanho da lista retornada pelo DAO: " + minhasTurmas.size());
           throw new RuntimeException("Erro ao listar turmas: " + e.getMessage());
       } finally {
           conexao.closeConexao();
       }
       return minhasTurmas;
   }
    
    public ArrayList<Turmas> ListasDeTurmasCod() {
       ArrayList<Turmas> turmasUnicas = new ArrayList<>();
       Conexao conexao = new Conexao();
       String selectSQL = """
        SELECT 
            t.id, 
            t.codigo_turma, 
            t.nota, 
            t.professor_id, 
            p.nome AS professor_nome, 
            t.disciplina_id, 
            d.nome AS disciplina_nome, 
            t.aluno_id, 
            a.nome AS aluno_nome
        FROM 
            turmas t
        JOIN 
            professores p ON t.professor_id = p.id
        JOIN 
            disciplina d ON t.disciplina_id = d.id
        JOIN 
            alunos a ON t.aluno_id = a.id
        WHERE 
            t.id IN (
                SELECT MIN(t2.id)
                FROM turmas t2
                GROUP BY t2.codigo_turma
            )
    """;
       try {
           PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
           ResultSet resultado = preparedStatement.executeQuery();
           while (resultado.next()) {
               Turmas turma = new Turmas();
               turma.setId(resultado.getInt("id"));
               turma.setCodigoTurma(resultado.getString("codigo_turma"));
               turma.setNota(resultado.getFloat("nota"));
               turma.setProfessorId(resultado.getInt("professor_id")); 
               turma.setDisciplinaId(resultado.getInt("disciplina_id")); 
               turma.setAlunoId(resultado.getInt("aluno_id")); 
               turmasUnicas.add(turma);
           }
       } catch (SQLException e) {
           System.out.println("Query executada: " + selectSQL);
           System.out.println("Tamanho da lista retornada pelo DAO: " + turmasUnicas.size());
           throw new RuntimeException("Erro ao listar turmas: " + e.getMessage());
       } finally {
           conexao.closeConexao();
       }
       return turmasUnicas;
   }

    
    public ArrayList<String> ListaDeProfessores() {
        ArrayList<String> professores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT id, nome FROM professores";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                professores.add(rs.getInt("id") + " - " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar professores: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return professores;
    }

    public ArrayList<String> ListaDeDisciplinas() {
        ArrayList<String> disciplinas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT id, nome FROM disciplina";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                disciplinas.add(rs.getInt("id") + " - " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar disciplinas: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }

    public ArrayList<String> ListaDeAlunos() {
        ArrayList<String> alunos = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String sql = "SELECT id, nome FROM alunos";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alunos.add(rs.getInt("id") + " - " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }
}
