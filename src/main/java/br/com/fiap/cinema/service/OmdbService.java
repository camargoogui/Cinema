
package br.com.fiap.cinema.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OmdbService {
    private static final String API_KEY = "325d8b5";
    private static final String BASE_URL = "http://www.omdbapi.com/";

    public String buscarFilme(String titulo) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?apikey=" + API_KEY + "&t=" + titulo;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Código inesperado " + response);
            return response.body().string();
        }
    }
}
