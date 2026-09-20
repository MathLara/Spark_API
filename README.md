# 📘 Spark Pessoa API

### API REST em Java com Spark Framework — CRUD de Pessoa com banco H2 em memória

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spark Java](https://img.shields.io/badge/Spark_Java-2.9.4-black?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2_Database-in--memory-blue?style=for-the-badge)
![Gson](https://img.shields.io/badge/Gson-2.11.0-4285F4?style=for-the-badge&logo=google&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## 🎯 Sobre o projeto

Este projeto é o backend de uma API para **cadastro de Pessoas**, implementando as cinco operações clássicas de um CRUD: **Create, Read, Update e Delete**.

A aplicação foi desenvolvida em **Java 17**, utilizando o **Spark Java 2.9.4** como micro framework web, **Gson 2.11.0** para conversão entre objetos Java e JSON, **JDBC puro** para acesso ao banco e **H2 em memória** para armazenamento dos dados.

> ⚠️ **Spark Java não é Apache Spark.** O Spark Java utilizado neste projeto é um micro framework web para criação de aplicações e APIs REST. O Apache Spark é uma plataforma diferente, voltada principalmente ao processamento distribuído de grandes volumes de dados.

## 🧩 Principais características

- API REST para gerenciamento de pessoas.
- CRUD completo.
- Arquitetura em camadas: Model, Repository, Service e Controller.
- Java 17.
- Spark Java 2.9.4.
- Jetty embutido como servidor web.
- Gson para serialização e desserialização JSON.
- JDBC puro, sem JPA/Hibernate.
- Banco H2 em memória.
- Validações realizadas manualmente na camada Service.
- Uso de `PreparedStatement`, evitando concatenação direta dos valores no SQL.
- CORS habilitado na inicialização da aplicação.
- Maven para gerenciamento das dependências.

## 🏗️ Arquitetura

O fluxo principal da aplicação é:

**Cliente/Postman → Jetty → Spark → Controller → Service → Repository → H2**

Cada camada possui uma responsabilidade:

| Camada | Responsabilidade |
|---|---|
| **Model** | Representa a entidade `Pessoa`. |
| **Repository** | Executa o acesso ao banco usando JDBC e SQL. |
| **Service** | Contém validações e regras de negócio. |
| **Controller** | Define as rotas HTTP, recebe JSON e produz as respostas. |

## 📂 Estrutura do projeto

```text
spark-pessoa-api/
├── pom.xml
├── README.md
└── src/main/java/br/edu/utfpr/pb/pw/spark/
    ├── App.java
    ├── config/
    │   └── DatabaseConfig.java
    ├── model/
    │   └── Pessoa.java
    ├── repository/
    │   └── PessoaRepository.java
    ├── service/
    │   └── PessoaService.java
    └── controller/
        └── PessoaController.java
```

## 🔌 Endpoints

Todas as requisições e respostas utilizam `application/json`.

| Método | Rota | Descrição | Sucesso |
|---|---|---|---|
| GET | `/pessoas` | Lista todas as pessoas | 200 |
| GET | `/pessoas/:id` | Busca uma pessoa pelo ID | 200 / 404 |
| POST | `/pessoas` | Cadastra uma pessoa | 201 / 400 |
| PUT | `/pessoas/:id` | Atualiza uma pessoa | 200 / 404 |
| DELETE | `/pessoas/:id` | Remove uma pessoa | 204 / 404 |

### Exemplo de POST

```json
{
    "nome": "João da Silva",
    "cpf": "123.456.789-00",
    "telefone": "(42) 99999-1234",
    "cidade": "Ponta Grossa",
    "estado": "PR"
}
```

## 🗄️ Banco de dados

O projeto utiliza o **H2 Database em modo in-memory**.

- URL JDBC: `jdbc:h2:mem:pessoaDB;DB_CLOSE_DELAY=-1`
- Usuário: `sa`
- Senha: vazia
- Persistência: os dados existem somente enquanto a aplicação estiver em execução.

A tabela `pessoa` é criada automaticamente durante a inicialização.

## ▶️ Como executar

### Pré-requisitos

- Java 17 ou superior.
- Maven 3.9 ou superior.
- IntelliJ IDEA ou outra IDE compatível com projetos Maven.
- Postman para testar a API.

### Execução

1. Abra o projeto na IDE.
2. Importe/recarregue o projeto Maven.
3. Aguarde o download das dependências.
4. Execute `App.java`.
5. O servidor será iniciado na porta **4567**.
6. Teste os endpoints pelo Postman.

URL base:

`http://localhost:4567`

