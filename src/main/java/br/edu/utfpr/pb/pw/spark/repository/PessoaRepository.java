package br.edu.utfpr.pb.pw.spark.repository;

import br.edu.utfpr.pb.pw.spark.config.DatabaseConfig;
import br.edu.utfpr.pb.pw.spark.model.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepository {

    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa ORDER BY id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pessoas.add(mapRowToPessoa(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoas: " + e.getMessage(), e);
        }

        return pessoas;
    }

    public Optional<Pessoa> findById(Long id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRowToPessoa(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa por ID: " + e.getMessage(), e);
        }

        return Optional.empty();
    }

    public Pessoa save(Pessoa pessoa) {
        String sql = """
                INSERT INTO pessoa (nome, cpf, telefone, rua, numero, complemento, bairro, cep, cidade, estado)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preencherParametros(ps, pessoa);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                pessoa.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + e.getMessage(), e);
        }

        return pessoa;
    }

    public Pessoa update(Pessoa pessoa) {
        String sql = """
                UPDATE pessoa
                SET nome = ?, cpf = ?, telefone = ?, rua = ?, numero = ?,
                    complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            preencherParametros(ps, pessoa);
            ps.setLong(11, pessoa.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pessoa: " + e.getMessage(), e);
        }

        return pessoa;
    }

    public boolean deleteById(Long id) {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pessoa: " + e.getMessage(), e);
        }
    }

    private Pessoa mapRowToPessoa(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getLong("id"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setCpf(rs.getString("cpf"));
        pessoa.setTelefone(rs.getString("telefone"));
        pessoa.setRua(rs.getString("rua"));
        pessoa.setNumero(rs.getString("numero"));
        pessoa.setComplemento(rs.getString("complemento"));
        pessoa.setBairro(rs.getString("bairro"));
        pessoa.setCep(rs.getString("cep"));
        pessoa.setCidade(rs.getString("cidade"));
        pessoa.setEstado(rs.getString("estado"));
        return pessoa;
    }

    private void preencherParametros(PreparedStatement ps, Pessoa pessoa) throws SQLException {
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getCpf());
        ps.setString(3, pessoa.getTelefone());
        ps.setString(4, pessoa.getRua());
        ps.setString(5, pessoa.getNumero());
        ps.setString(6, pessoa.getComplemento());
        ps.setString(7, pessoa.getBairro());
        ps.setString(8, pessoa.getCep());
        ps.setString(9, pessoa.getCidade());
        ps.setString(10, pessoa.getEstado());
    }
}
