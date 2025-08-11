package com.example.Libreria.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {

    private static final HttpClient cliente = HttpClient.newHttpClient();

    public static String consultarAPI(String url)  {


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response;

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        }catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar la API", e);
        }

        String json = response.body();

        return json;
    }
}
