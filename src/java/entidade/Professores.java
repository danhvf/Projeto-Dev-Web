/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author Daniel Fontoura
 */


/* -- Estrutura para tabela `professores`
--

DROP TABLE IF EXISTS `professores`;
CREATE TABLE IF NOT EXISTS `professores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `cpf` varchar(14) CHARACTER SET utf8 NOT NULL,
  `senha` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; */

public class Professores {
    
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    
    public Professores (String nome, String email, String cpf, String senha){
        
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        
    }
    
    public Professores (){
        this.id = 0;
        this.nome = "";
        this.email = "";
        this.cpf = "";
        this.senha = "";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

}


