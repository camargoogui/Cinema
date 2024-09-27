package br.com.fiap.cinema.dao;

import br.com.fiap.cinema.model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public void dropTable() {
        String sql = "DROP TABLE FILME_JAVA";

        try (Connection conexao = ConnectionFactory.obterConexao();
             Statement stmt = conexao.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabela FILME_JAVA foi excluída com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir a tabela FILME_JAVA: " + e.getMessage());
        }
    }

    public void createTable() {
        String sql = "CREATE TABLE FILME_JAVA (" +
                "TITULO VARCHAR2(100) PRIMARY KEY, " +
                "ANO VARCHAR2(4), " +
                "DIRETOR VARCHAR2(100))";

        try (Connection conexao = ConnectionFactory.obterConexao();
             Statement stmt = conexao.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabela FILME_JAVA foi criada com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar criar a tabela FILME_JAVA: " + e.getMessage());
        }
    }

    public List<Filme> listar() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM FILME_JAVA";

        try (Connection conexao = ConnectionFactory.obterConexao();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String titulo = rs.getString("TITULO");
                String ano = rs.getString("ANO");
                String diretor = rs.getString("DIRETOR");

                Filme filme = new Filme(titulo, ano, diretor);
                filmes.add(filme);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }

    public void cadastrar(Filme filme) {
        String sql = "INSERT INTO FILME_JAVA (TITULO, ANO, DIRETOR) VALUES (?, ?, ?)";

        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getAno());
            stmt.setString(3, filme.getDiretor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cadastrar(List<Filme> filmes) {
        String sql = "INSERT INTO FILME_JAVA (TITULO, ANO, DIRETOR) VALUES (?, ?, ?)";

        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            for (Filme filme : filmes) {
                stmt.setString(1, filme.getTitulo());
                stmt.setString(2, filme.getAno());
                stmt.setString(3, filme.getDiretor());
                stmt.addBatch();  // Add the batch for multiple inserts
            }

            stmt.executeBatch();  // Execute the batch

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Filme buscarPorPK(String titulo) {
        Filme filme = null;
        String sql = "SELECT * FROM FILME_JAVA WHERE TITULO = ?";

        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, titulo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String ano = rs.getString("ANO");
                    String diretor = rs.getString("DIRETOR");

                    filme = new Filme(titulo, ano, diretor);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filme;
    }

    public List<Filme> buscarPorDiretor(String diretor) {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM FILME_JAVA WHERE DIRETOR = ?";

        try (Connection conexao = ConnectionFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, diretor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String titulo = rs.getString("TITULO");
                    String ano = rs.getString("ANO");

                    Filme filme = new Filme(titulo, ano, diretor);
                    filmes.add(filme);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }
}
