package com.example.Act3Gasolineras.service;

import com.example.Act3Gasolineras.client.GasolinerasApiClient;
import com.example.Act3Gasolineras.model.Gasolinera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GasolinerasService {

    @Autowired
    private GasolinerasApiClient apiClient;

    public List<Gasolinera> obtenerGasolinerasFiltradas(Double lat, Double lon, String empresa, String carburante) {
        // Se obtiene la lista completa desde el cliente REST
        List<Gasolinera> gasolineras = apiClient.getGasolineras();

        // Filtro por empresa (se utiliza el campo 'rotulo' como identificador de la marca)
        if (empresa != null && !empresa.trim().isEmpty()) {
            gasolineras = gasolineras.stream()
                    .filter(g -> g.getRotulo() != null && g.getRotulo().toLowerCase().contains(empresa.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filtro por tipo de carburante
        if (carburante != null && !carburante.trim().isEmpty()) {
            gasolineras = gasolineras.stream()
                    .filter(g -> {
                        // Para "gasolina", se verifican los campos de precios relacionados a gasolina.
                        if ("gasolina".equalsIgnoreCase(carburante)) {
                            return (g.getPrecioGasolina95E5() != null && g.getPrecioGasolina95E5() > 0) ||
                                    (g.getPrecioGasolina95E10() != null && g.getPrecioGasolina95E10() > 0) ||
                                    (g.getPrecioGasolina95E5Premium() != null && g.getPrecioGasolina95E5Premium() > 0) ||
                                    (g.getPrecioGasolina98E5() != null && g.getPrecioGasolina98E5() > 0) ||
                                    (g.getPrecioGasolina98E10() != null && g.getPrecioGasolina98E10() > 0);
                        }
                        // Para "gasoleo", se verifican los campos correspondientes a gasóleo.
                        if ("gasoleo".equalsIgnoreCase(carburante)) {
                            return (g.getPrecioGasoleoA() != null && g.getPrecioGasoleoA() > 0) ||
                                    (g.getPrecioGasoleoB() != null && g.getPrecioGasoleoB() > 0) ||
                                    (g.getPrecioGasoleoPremium() != null && g.getPrecioGasoleoPremium() > 0);
                        }
                        // Se pueden añadir otros tipos de carburante si fuera necesario.
                        return true;
                    })
                    .collect(Collectors.toList());
        }

        // Ordenar por proximidad si se han proporcionado las coordenadas del usuario
        if (lat != null && lon != null) {
            gasolineras.sort((g1, g2) -> {
                double distancia1 = calcularDistancia(lat, lon, g1.getLatitud(), g1.getLongitud());
                double distancia2 = calcularDistancia(lat, lon, g2.getLatitud(), g2.getLongitud());
                return Double.compare(distancia1, distancia2);
            });
        }
        return gasolineras;
    }


    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en kilómetros
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
