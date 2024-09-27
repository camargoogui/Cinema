
package br.com.fiap.cinema.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OmdbService {
    /**
     * API Via Cep - http://www.omdbapi.com/
     * A OMDb API fornece informações detalhadas sobre filmes, séries e conteúdo relacionado a partir de um banco de dados baseado em títulos e identificadores.
     * Exemplo de consulta de Filme:
     *     http://www.omdbapi.com/?t=Mad+Max&apikey=325d8b5
     * Saída
     * URL: http://www.omdbapi.com/?t=Mad+Max&apikey=325d8b5
     *
     *     {
     *       "Title": "Mad Max",
     *       "Year": "1979",
     *       "Rated": "R",
     *       "Released": "21 Mar 1980",
     *       "Runtime": "88 min",
     *       "Genre": "Action, Adventure, Sci-Fi",
     *       "Director": "George Miller",
     *       "Writer": "James McCausland, George Miller, Byron Kennedy",
     *       "Actors": "Mel Gibson, Joanne Samuel, Hugh Keays-Byrne",
     *       "Plot": "In a self-destructing world, a vengeful Australian policeman sets out to stop a violent motorcycle gang.",
     *       "Language": "English",
     *       "Country": "Australia",
     *       "Awards": "6 wins & 8 nominations",
     *       "Poster": "https://m.media-amazon.com/images/M/MV5BMTM4Mjg5ODEzMV5BMl5BanBnXkFtZTcwMDc3NDk0NA@@._V1_SX300.jpg",
     *       "Ratings": [
     *         {
     *           "Source": "Internet Movie Database",
     *           "Value": "6.8/10"
     *         },
     *         {
     *           "Source": "Rotten Tomatoes",
     *           "Value": "90%"
     *         },
     *         {
     *           "Source": "Metacritic",
     *           "Value": "73/100"
     *         }
     *       ],
     *       "Metascore": "73",
     *       "imdbRating": "6.8",
     *       "imdbVotes": "233,289",
     *       "imdbID": "tt0079501",
     *       "Type": "movie",
     *       "DVD": "N/A",
     *       "BoxOffice": "$8,750,000",
     *       "Production": "N/A",
     *       "Website": "N/A",
     *       "Response": "True"
     *     }
     *
     * */


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
