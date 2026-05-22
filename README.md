# 🎸 Band Registration System

Sistema de cadastro de bandas desenvolvido em Java com JDBC puro, aplicando arquitetura em camadas e boas práticas de acesso a banco de dados relacional para estudo de CRUD e JDBC

---

## 📋 Sobre o Projeto

Este projeto implementa um CRUD completo para gerenciamento de bandas e líderes de bandas, utilizando **JDBC** para comunicação direta com o banco de dados MySQL. O foco está na separação de responsabilidades por meio de camadas bem definidas (Repository, Service e Factory), além do controle explícito de transações.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia     | Versão  | Finalidade                          |
|----------------|---------|-------------------------------------|
| Java           | 17+     | Linguagem principal                 |
| JDBC           | —       | Acesso ao banco de dados            |
| MySQL          | 8+      | Banco de dados relacional           |
| Maven          | 3.8+    | Gerenciamento de dependências       |
| Lombok         | 1.18+   | Redução de boilerplate (getters, construtores) |
| Log4j2         | 2.x     | Logging e rastreamento de execução  |

---

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas**, com responsabilidades bem separadas:

```
src/
  └── java/
        └── com/BancoDeDadosBandas/
            ├── model/          # Entidades (Band, etc.)
            ├── repository/     # Acesso ao banco via JDBC (SQL puro)
            ├── service/        # Regras de negócio e validações
            ├── factory/        # Criação e gerenciamento de conexões
            └── main/           # Ponto de entrada da aplicação
```

### Responsabilidade de cada camada

- **Model** — representa os dados (entidades POJO com Lombok)
- **Repository** — executa as queries SQL diretamente via JDBC e realiza os commits
- **Service** — orquestra as operações, lógica de negócio e validações
- **Factory** — centraliza a criação de conexões com o banco de dados e os rollbacks

---

## ⚙️ Como Executar

### Pré-requisitos

- Java 17 ou superior
- MySQL 8 ou superior
- Maven 3.8 ou superior

### 1. Clone o repositório

```bash
git clone https://github.com/jonathanleao/JDBC-Band-Registration-DataBase-Project.git
cd JDBC-Band-Registration-DataBase-Project
```

### 2. Configure o banco de dados

Execute o script SQL disponivel no arquivo do projeto pelo nome "schema.sql" para criar o banco no MySQL


### 3. Configure a conexão

Ajuste as credenciais do banco no arquivo de configuração da `ConnectionFactory`:

```java
private static final String url    = "jdbc:mysql://localhost:3306/cadastro_banda";
private static final String username   = "seu_usuario";
private static final String password   = "sua_senha";
```

### 4. Compile e execute

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bandas.main.Main"
```

---

## 🔑 Funcionalidades
- ✅ relacionamento entre líderes de banda e bandas, onde cada banda tem um líder
- ✅ cadastro de líderes de bandas
- ✅ Cadastro de bandas
- ✅ Busca por nome
- ✅ Atualização de dados
- ✅ Remoção de bandas
- ✅ Controle de transações com `commit` e `rollback`
- ✅ Logging de operações com Log4j2

---

## 📚 Conceitos Aplicados

- **JDBC** — conexão, `PreparedStatement`, `ResultSet`, fechamento de recursos
- **Arquitetura em camadas** — separação entre acesso a dados, regras de negócio e apresentação
- **Padrão Factory** — centralização da criação de conexões
- **Transações** — controle manual de `autoCommit`, `commit` e `rollback`
- **Lombok** — `@toString`, `@Builder`, `@Value`
- **Log4j2** — rastreamento de fluxo e erros e informações

---

## 👨‍💻 Autor

**Jonathan Leão**  
[github.com/jonathanleao](https://github.com/jonathanleao)
