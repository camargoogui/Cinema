package br.com.fiap.cinema.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection obterConexao(){
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    "rm555166", "061204");
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return conexao;
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            FileInputStream fileConfig = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileConfig);

            String url = properties.getProperty("URL");
            String user = properties.getProperty("USERNAME");
            String password = properties.getProperty("PASSWORD");

            return DriverManager.getConnection(url, user, password);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Config file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading config file", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
