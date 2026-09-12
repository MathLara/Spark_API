package br.edu.utfpr.pb.pw.spark.controller;

import br.edu.utfpr.pb.pw.spark.model.Pessoa;
import br.edu.utfpr.pb.pw.spark.service.PessoaService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static spark.Spark.*;

public class PessoaController {

    private final PessoaService service;
    private final Gson gson;

    public PessoaController() {
        this.service = new PessoaService();
        this.gson = new Gson();
    }

    public void registrarRotas() {

        get("/pessoas", (request, response) -> {
            response.type("application/json");
            return gson.toJson(service.listarTodas());
        });

        get("/pessoas/:id", (request, response) -> {
            response.type("application/json");

            Long id = Long.parseLong(request.params(":id"));
            Optional<Pessoa> pessoa = service.buscarPorId(id);

            if (pessoa.isPresent()) {
                return gson.toJson(pessoa.get());
            } else {
                response.status(404);
                return gson.toJson(mensagemErro("Pessoa não encontrada com ID: " + id));
            }
        });

        post("/pessoas", (request, response) -> {
            response.type("application/json");

            try {
                Pessoa pessoa = gson.fromJson(request.body(), Pessoa.class);
                Pessoa salva = service.cadastrar(pessoa);
                response.status(201);
                return gson.toJson(salva);
            } catch (IllegalArgumentException e) {
                response.status(400);
                return gson.toJson(mensagemErro(e.getMessage()));
            } catch (Exception e) {
                response.status(500);
                return gson.toJson(mensagemErro("Erro ao cadastrar pessoa: " + e.getMessage()));
            }
        });

        put("/pessoas/:id", (request, response) -> {
            response.type("application/json");

            try {
                Long id = Long.parseLong(request.params(":id"));
                Pessoa pessoa = gson.fromJson(request.body(), Pessoa.class);
                Optional<Pessoa> atualizada = service.atualizar(id, pessoa);

                if (atualizada.isPresent()) {
                    return gson.toJson(atualizada.get());
                } else {
                    response.status(404);
                    return gson.toJson(mensagemErro("Pessoa não encontrada com ID: " + id));
                }
            } catch (IllegalArgumentException e) {
                response.status(400);
                return gson.toJson(mensagemErro(e.getMessage()));
            }
        });

        delete("/pessoas/:id", (request, response) -> {
            response.type("application/json");

            Long id = Long.parseLong(request.params(":id"));
            boolean removido = service.remover(id);

            if (removido) {
                response.status(204);
                return "";
            } else {
                response.status(404);
                return gson.toJson(mensagemErro("Pessoa não encontrada com ID: " + id));
            }
        });
    }

    private Map<String, String> mensagemErro(String mensagem) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", mensagem);
        return erro;
    }
}
