package br.edu.utfpr.pb.pw.spark.service;

import br.edu.utfpr.pb.pw.spark.model.Pessoa;
import br.edu.utfpr.pb.pw.spark.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;

public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService() {

        this.repository = new PessoaRepository();
    }

    public List<Pessoa> listarTodas() {

        return repository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {

        return repository.findById(id);
    }

    public Pessoa cadastrar(Pessoa pessoa) {

        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new IllegalArgumentException("O campo 'nome' é obrigatório.");
        }
        if (pessoa.getCpf() == null || pessoa.getCpf().isBlank()) {
            throw new IllegalArgumentException("O campo 'cpf' é obrigatório.");
        }

        return repository.save(pessoa);
    }

    public Optional<Pessoa> atualizar(Long id, Pessoa pessoa) {
        Optional<Pessoa> existente = repository.findById(id);

        if (existente.isEmpty()) {
            return Optional.empty();
        }

        pessoa.setId(id);
        return Optional.of(repository.update(pessoa));
    }

    public boolean remover(Long id) {

        return repository.deleteById(id);
    }
}
