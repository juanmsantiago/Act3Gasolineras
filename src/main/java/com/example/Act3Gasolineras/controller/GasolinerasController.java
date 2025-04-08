package com.example.Act3Gasolineras.controller;

import com.example.Act3Gasolineras.model.Gasolinera;
import com.example.Act3Gasolineras.service.GasolinerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GasolinerasController {
    @Autowired
    private GasolinerasService gasolinerasService;

    @GetMapping("/gasolineras")
    public ResponseEntity<List<Gasolinera>> getGasolineras(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String carburante) {
        List<Gasolinera> resultado = gasolinerasService.obtenerGasolinerasFiltradas(lat, lon, empresa, carburante);
        return ResponseEntity.ok(resultado);
    }
}
