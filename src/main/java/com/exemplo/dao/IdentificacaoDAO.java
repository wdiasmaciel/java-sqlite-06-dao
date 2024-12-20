package com.exemplo.dao;

import com.exemplo.model.Identificacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdentificacaoDAO {
    public void criarTabela() {
        try (Connection connection = Conexao.getConnection();
                Statement statement = connection.createStatement()) {
            String createTableSQL = """
                    -- Criação da tabela Identificacao
                    CREATE TABLE IF NOT EXISTS Identificacao (
                        id INTEGER PRIMARY KEY,
                        nome TEXT NOT NULL,
                        descricao TEXT NOT NULL,
                        FOREIGN KEY (id) REFERENCES Produto(id)
                    );
                """;
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Erro na comunicação com o banco de dados!");
            e.printStackTrace();
        }
    }

    public void inserir(Identificacao identificacao) {
        String sql = "INSERT INTO Identificacao (id, nome, descricao) VALUES (?, ?, ?)";

        try (Connection connection = Conexao.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, identificacao.getId());
            preparedStatement.setString(2, identificacao.getNome());
            preparedStatement.setString(3, identificacao.getDescricao());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir identificação.", e);
        }
    }

    public List<Identificacao> listar() {
        String sql = "SELECT * FROM Identificacao";
        List<Identificacao> identificacoes = new ArrayList<Identificacao>();

        try (Connection connection = Conexao.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Identificacao identificacao = new Identificacao();
                identificacao.setId(rs.getInt("id"));
                identificacao.setNome(rs.getString("nome"));
                identificacao.setDescricao(rs.getString("descricao"));

                identificacoes.add(identificacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar identificações.", e);
        }

        return identificacoes;
    }

    // Implemente os métodos para atualizar e excluir.
}
