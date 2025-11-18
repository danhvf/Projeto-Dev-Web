package entidade;

public class Administrador {

    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String aprovado;
    private String endereco;
    

    public Administrador(String nome, String cpf, String senha, String aprovado, String endereco ) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.aprovado = aprovado;
        this.endereco = endereco;
        
    }

    public Administrador(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Administrador() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.aprovado = "";
        this.endereco = "";
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setAprovado (String aprovado){
        this.aprovado = aprovado;
    }
    
    public String getAprovado(){
        return aprovado;
    }

}
