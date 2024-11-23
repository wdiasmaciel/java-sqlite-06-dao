package com.exemplo.dao;

import com.exemplo.model.Filial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilialDAO {

    public void inserir(Filial filial) {
        String sql = "INSERT INTO Filial (cnpj, nome, telefone, endereco) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, filial.getCnpj());
            preparedStatement.setString(2, filial.getNome());
            preparedStatement.setString(3, filial.getTelefone());
            preparedStatement.setString(4, filial.getEndereco());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir filial.", e);
        }
    }

    public List<Filial> listar() {
        String sql = "SELECT * FROM Filial";
        List<Filial> filiais = new ArrayList<>();

        try (Connection connection = Conexao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Filial filial = new Filial();
                filial.setCnpj(resultSet.getString("cnpj"));
                filial.setNome(resultSet.getString("nome"));
                filial.setTelefone(resultSet.getString("telefone"));
                filial.setEndereco(resultSet.getString("endereco"));

                filiais.add(filial);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar filiais.", e);
        }

        return filiais;
    }

    // Implemente os métodos para atualizar e excluir.
}
