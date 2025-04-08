package com.example.Act3Gasolineras.model;

import lombok.Data;

@Data
public class Gasolinera {
    // Datos de ubicación y administrativos
    private String provincia;
    private String municipio;
    private String localidad;
    private String codigoPostal;
    private String direccion;
    private String margen;
    private double longitud;
    private double latitud;
    private String tomaDatos;

    // Precios de carburantes
    private Double precioGasolina95E5;
    private Double precioGasolina95E10;
    private Double precioGasolina95E5Premium;
    private Double precioGasolina98E5;
    private Double precioGasolina98E10;
    private Double precioGasoleoA;
    private Double precioGasoleoPremium;
    private Double precioGasoleoB;
    private Double precioGasoleoC;
    private Double precioBioetanol;
    private Double porcentajeBioalcohol;
    private Double precioBiodiesel;
    private Double porcentajeEsterMetilico;
    private Double precioGasesLicuados;
    private Double precioGasNaturalComprimido;
    private Double precioGasNaturalLicuado;
    private Double precioHidrogeno;

    // Otros campos
    private String rotulo;
    private String tipoVenta;
    private String rem;
    private String horario;
    private String tipoServicio;

    public String getProvincia() {
        return provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMargen() {
        return margen;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public String getTomaDatos() {
        return tomaDatos;
    }

    public Double getPrecioGasolina95E5() {
        return precioGasolina95E5;
    }

    public Double getPrecioGasolina95E10() {
        return precioGasolina95E10;
    }

    public Double getPrecioGasolina95E5Premium() {
        return precioGasolina95E5Premium;
    }

    public Double getPrecioGasolina98E5() {
        return precioGasolina98E5;
    }

    public Double getPrecioGasolina98E10() {
        return precioGasolina98E10;
    }

    public Double getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public Double getPrecioGasoleoPremium() {
        return precioGasoleoPremium;
    }

    public Double getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public Double getPrecioGasoleoC() {
        return precioGasoleoC;
    }

    public Double getPrecioBioetanol() {
        return precioBioetanol;
    }

    public Double getPorcentajeBioalcohol() {
        return porcentajeBioalcohol;
    }

    public Double getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public Double getPorcentajeEsterMetilico() {
        return porcentajeEsterMetilico;
    }

    public Double getPrecioGasesLicuados() {
        return precioGasesLicuados;
    }

    public Double getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public Double getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public Double getPrecioHidrogeno() {
        return precioHidrogeno;
    }

    public String getRotulo() {
        return rotulo;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public String getRem() {
        return rem;
    }

    public String getHorario() {
        return horario;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }
}
