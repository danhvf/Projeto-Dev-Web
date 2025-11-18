/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author Daniel Fontoura
 */

/* -- Estrutura para tabela `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `requisito` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ementa` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `carga_horaria` smallint(5) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; */

public class Disciplina {
    
    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private int carga_horaria;
    
    public Disciplina (String nome, String requisito, String ementa, int carga_horaria){
    
        this.nome = nome;
        this.requisito = requisito;
        this.ementa = ementa;
        this.carga_horaria = carga_horaria;
    
    }
    
    public Disciplina (){
        
        this.id = 0;
        this.nome = "";
        this.requisito = "";
        this.ementa = "";
        this.carga_horaria = 0;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getCH() {
        return carga_horaria;
    }

    public void setCH(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
        
}
