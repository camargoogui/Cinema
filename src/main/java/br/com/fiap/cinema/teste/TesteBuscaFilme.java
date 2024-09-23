//Guilherme Paes Camargo RM555166
//Icaro Américo de Albuquerque Lima RM555131
package br.com.fiap.cinema.teste;

import br.com.fiap.cinema.json.EscritaJson;
import br.com.fiap.cinema.service.OmdbService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Scanner;

public class TesteBuscaFilme {
    public static void main(String[] args) {
        OmdbService omdbService = new OmdbService();
        EscritaJson escritaJson = new EscritaJson();

        // Usando Scanner para capturar o título do filme
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título do filme que deseja buscar: ");
        String tituloFilme = scanner.nextLine();

        try {
            String dadosFilme = omdbService.buscarFilme(tituloFilme);

            // Verificando se houve erro na busca
            if (dadosFilme.contains("\"Error\"")) {
                System.out.println("Filme não encontrado. Verifique o título e tente novamente.");
            } else {
                // Exibir informações importantes no console
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(dadosFilme, JsonObject.class);
                String titulo = jsonObject.get("Title").getAsString();
                String ano = jsonObject.get("Year").getAsString();
                String diretor = jsonObject.get("Director").getAsString();
                System.out.println("Filme: " + titulo);
                System.out.println("Ano: " + ano);
                System.out.println("Diretor: " + diretor);

                // Salvar o arquivo JSON
                escritaJson.salvarFilmeJson(dadosFilme, tituloFilme);
                System.out.println("Arquivo JSON gerado com sucesso.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar o filme: " + e.getMessage());
        }
    }
}
