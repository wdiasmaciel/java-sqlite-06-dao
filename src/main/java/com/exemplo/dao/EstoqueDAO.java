package com.exemplo.dao;

import com.exemplo.model.Estoque;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    public void inserir(Estoque estoque) {
        String sql = "INSERT INTO Estoque (id_produto, cnpj_filial, quantidade) VALUES (?, ?, ?)";

        try (Connection connection = Conexao.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, estoque.getIdProduto());
            preparedStatement.setString(2, estoque.getCnpjFilial());
            preparedStatement.setInt(3, estoque.getQuantidade());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir estoque.", e);
        }
    }

    public List<Estoque> listar() {
        String sql = "SELECT * FROM Estoque";
        List<Estoque> estoques = new ArrayList<>();

        try (Connection connection = Conexao.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Estoque estoque = new Estoque();
                estoque.setIdProduto(resultSet.getInt("id_produto"));
                estoque.setCnpjFilial(resultSet.getString("cnpj_filial"));
                estoque.setQuantidade(resultSet.getInt("quantidade"));

                estoques.add(estoque);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar estoques.", e);
        }

        return estoques;
    }

    // Implemente os métodos para atualizar e excluir.
}
