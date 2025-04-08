package com.example.Act3Gasolineras.client;

import com.example.Act3Gasolineras.model.Gasolinera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GasolinerasApiClient {

    @Autowired
    private RestTemplate restTemplate;

    // Reemplaza la URL según la documentación de la API externa
    private final String API_URL = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres.json";

    public List<Gasolinera> getGasolineras() {
        ResponseEntity<Gasolinera[]> response = restTemplate.getForEntity(API_URL, Gasolinera[].class);
        return Arrays.asList(response.getBody());
    }
}
