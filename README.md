# 🎸 Band Registration System

Sistema de cadastro de bandas desenvolvido em Java com JDBC puro, aplicando arquitetura em camadas e boas práticas de acesso a banco de dados relacional.

---

## 📋 Sobre o Projeto

Este projeto implementa um CRUD completo para gerenciamento de bandas, utilizando **JDBC** para comunicação direta com o banco de dados MySQL. O foco está na separação de responsabilidades por meio de camadas bem definidas (Repository, Service e Factory), além do controle explícito de transações.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia     | Versão  | Finalidade                          |
|----------------|---------|-------------------------------------|
| Java           | 17+     | Linguagem principal                 |
| JDBC           | —       | Acesso ao banco de dados            |
| MySQL          | 8+      | Banco de dados relacional           |
| Maven          | 3.8+    | Gerenciamento de dependências       |
| Lombok         | 1.18+   | Redução de boilerplate (getters, setters, construtores) |
| Log4j2         | 2.x     | Logging e rastreamento de execução  |

---

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas**, com responsabilidades bem separadas:

```
src/
└── main/
    └── java/
        └── com/bandas/
            ├── model/          # Entidades (Band, etc.)
            ├── repository/     # Acesso ao banco via JDBC (SQL puro)
            ├── service/        # Regras de negócio e controle de transações
            ├── factory/        # Criação e gerenciamento de conexões
            └── main/           # Ponto de entrada da aplicação
```

### Responsabilidade de cada camada

- **Model** — representa os dados (entidades POJO com Lombok)
- **Repository** — executa as queries SQL diretamente via JDBC
- **Service** — orquestra as operações e controla o ciclo de transações (`commit`/`rollback`)
- **Factory** — centraliza a criação de conexões com o banco de dados

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

Execute o script SQL abaixo no seu MySQL para criar o banco e a tabela:

```sql
CREATE DATABASE IF NOT EXISTS bandas_db;
USE bandas_db;

CREATE TABLE IF NOT EXISTS bands (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    members INT
);
```

### 3. Configure a conexão

Ajuste as credenciais do banco no arquivo de configuração da `ConnectionFactory`:

```java
private static final String URL    = "jdbc:mysql://localhost:3306/bandas_db";
private static final String USER   = "seu_usuario";
private static final String PASS   = "sua_senha";
```

### 4. Compile e execute

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bandas.main.Main"
```

---

## 🔑 Funcionalidades

- ✅ Cadastro de bandas
- ✅ Listagem de todas as bandas
- ✅ Busca por ID
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
- **Lombok** — `@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`
- **Log4j2** — rastreamento de fluxo e erros

---

## 👨‍💻 Autor

**Jonathan Leão**  
[github.com/jonathanleao](https://github.com/jonathanleao)
