package br.edu.utfpr.pb.pw.spark;

import br.edu.utfpr.pb.pw.spark.config.DatabaseConfig;
import br.edu.utfpr.pb.pw.spark.controller.PessoaController;
import static spark.Spark.*;

//os 2 imports abaixo permitem visualizar o banco na Web (vinculado ao comando try da linha 62)
import org.h2.tools.Server;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        port(4567);

        options("/*", (request, response) -> {
            String accessControlHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlHeaders);
            }

            String accessControlMethod = request.headers("Access-Control-Request-Method");
            if (accessControlMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        DatabaseConfig.inicializarBanco();
        /* Se quiser visualizar na Web o banco, habilite o comando abaixo
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("[DB] Console H2 disponível em http://localhost:8082");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         */

        PessoaController controller = new PessoaController();
        controller.registrarRotas();

        System.out.println("============================================");
        System.out.println("  Spark API rodando em http://localhost:4567");
        System.out.println("  Endpoints disponíveis:");
        System.out.println("    GET    /pessoas");
        System.out.println("    GET    /pessoas/:id");
        System.out.println("    POST   /pessoas");
        System.out.println("    PUT    /pessoas/:id");
        System.out.println("    DELETE /pessoas/:id");
        System.out.println("============================================");
    }
}
