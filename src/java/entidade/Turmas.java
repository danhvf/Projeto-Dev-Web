/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author Daniel Fontoura
 */

/* -- Estrutura para tabela `turmas`
--

DROP TABLE IF EXISTS `turmas`;
CREATE TABLE IF NOT EXISTS `turmas` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `professor_id` int(11) NOT NULL,
  `disciplina_id` int(10) UNSIGNED NOT NULL,
  `aluno_id` int(11) NOT NULL,
  `codigo_turma` varchar(2) NOT NULL,
  `nota` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `turmas_FKIndex1` (`disciplina_id`),
  KEY `professor_FKIndex2` (`professor_id`),
  KEY `aluno_fk` (`aluno_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- */
public class Turmas {
    
    private int id;
    private int professor_id;
    private int disciplina_id;
    private int aluno_id;
    private String codigo_turma;
    private float nota;
    private String disciplinaNome;
    private String professorNome;
    
    public Turmas (int professor_id, int disciplina_id, int aluno_id, String codigo_turma, float nota){
        
        this.professor_id = professor_id;
        this.disciplina_id = disciplina_id;
        this.aluno_id = aluno_id;
        this.codigo_turma = codigo_turma;
        this.nota = nota;
    }
    
    public Turmas (){
        
        this.professor_id = 0;
        this.disciplina_id = 0;
        this.aluno_id = 0;
        this.codigo_turma = "";
        this.nota = 0.0f;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessorId() {
        return professor_id;
    }

    public void setProfessorId(int professor_id) {
        this.professor_id = professor_id;
    }

    public int getDisciplinaId() {
        return disciplina_id;
    }

    public void setDisciplinaId(int disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public int getAlunoId() {
        return aluno_id;
    }

    public void setAlunoId(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public String getCodigoTurma() {
        return codigo_turma;
    }

    public void setCodigoTurma(String codigo_turma) {
        this.codigo_turma = codigo_turma;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }
    
   
}
