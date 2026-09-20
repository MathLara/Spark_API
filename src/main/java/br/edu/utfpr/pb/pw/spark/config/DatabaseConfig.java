package br.edu.utfpr.pb.pw.spark.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String URL = "jdbc:h2:mem:pessoaDB;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void inicializarBanco() {
        String sql = """
                CREATE TABLE IF NOT EXISTS pessoa (
                    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                    nome        VARCHAR(255) NOT NULL,
                    cpf         VARCHAR(14)  NOT NULL UNIQUE,
                    telefone    VARCHAR(20),
                    rua         VARCHAR(255),
                    numero      VARCHAR(20),
                    complemento VARCHAR(255),
                    bairro      VARCHAR(255),
                    cep         VARCHAR(10),
                    cidade      VARCHAR(255),
                    estado      VARCHAR(2)
                );
                """;

        try (Connection conn = getConnection();
             Statement s = conn.createStatement()) {
            s.execute(sql);
            System.out.println("[DB] Tabela 'pessoa' criada/verificada com sucesso.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao criar tabela: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
