# Sistema Web MVC com Java Servlets

Este é uma aplicação web desenvolvida em **Java** utilizando a arquitetura **MVC** (Model-View-Controller), **Servlets** e conexão com banco de dados **MySQL** via JDBC.

## 🛠️ Tecnologias Utilizadas

* **Java** (JDK 17 ou superior)
* **Apache NetBeans** (IDE)
* **Apache Tomcat 8.5** (Servidor Web)
* **MySQL** (Banco de Dados)
* **JDBC** (Conector Java para Banco de Dados)
* **HTML/CSS** (Frontend básico)

---

## 📋 Pré-requisitos

Antes de começar, você precisará ter instalado/configurado em sua máquina:

1.  **Java JDK**: [Download aqui](https://www.oracle.com/java/technologies/downloads/).
2.  **Apache Tomcat 8.5**: [Download aqui](https://tomcat.apache.org/download-80.cgi).
3.  **MySQL Server** (ou XAMPP/WAMP): O serviço deve estar rodando na porta `3306`.

---

## ⚙️ Configuração do Banco de Dados

⚠️ **Importante:** Antes de rodar o projeto, é necessário criar o banco de dados.

1.  Abra seu gerenciador de banco de dados (MySQL Workbench, DBeaver, phpMyAdmin, etc).
2.  Localize o arquivo de script SQL na pasta do projeto.
3.  Execute o script para criar a estrutura (tabelas) e popular com os dados iniciais de admin (inserts).
4.  Insira o admin cpf= 249.252.810-38 e senha= 111

### Configuração Padrão
O sistema está configurado para conectar com as seguintes credenciais (conforme classe `Conexao.java`):

* **Database:** `dbjava`
* **Porta:** `3306`
* **Usuário:** `root`
* **Senha:** `123456`

> **Nota:** Se a senha do seu MySQL local for diferente de `123456`, você deve alterar o arquivo `Conexao.java` para refletir a sua senha local.

---

## 🚀 Instalação e Execução

### Instalar o NetBeans e Configurar o Tomcat
1.  Acesse o site oficial e instale o [Apache NetBeans](https://netbeans.apache.org/download/index.html).
2.  No NetBeans, vá em **Tools (Ferramentas) > Servers (Servidores)**.
3.  Clique em **Add Server**.
4.  Escolha **Apache Tomcat**.
5.  Aponte para a pasta onde você baixou/descompactou o **Tomcat 8.5**.

## Para executar:
Basta importar o projeto via git ou baixar o zip e abrir o projeto no NetBeans
