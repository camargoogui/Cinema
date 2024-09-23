package br.com.fiap.cinema.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritaJson {
    public void salvarFilmeJson(String dadosJson, String titulo) {
        try {
            // Criar um objeto Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(dadosJson, JsonObject.class);

            // Criar o diretório de saída, se não existir
            File dir = new File("output");
            if (!dir.exists()) {
                dir.mkdir(); // Cria o diretório de saída
            }

            // Salvar o JSON no diretório de saída
            try (FileWriter file = new FileWriter("output/" + titulo + ".json")) {
                gson.toJson(jsonObject, file);
                System.out.println("Dados do filme salvos no arquivo: output/" + titulo + ".json");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
