/**
 * La clase Parking
 * 12/09/2016
 */
package com.prueba.parking;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author k-lagan
 */
public class Parking {

    private int identificador;
    private String nombre;
    private int horaApertura;
    private int horaCierre;
    private int plazasTotales;
    private int plazasLibres;
    private List<String> diasApertura;
    private double latitudGPS;
    private double longitudGPS;

    public int getIdentificador() {
        return identificador;
    }

    @XmlElement
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    @XmlElement
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    @XmlElement
    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    @XmlElement
    public void setHoraCierre(int horaCierre) {
        this.horaCierre = horaCierre;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }

    @XmlElement
    public void setPlazasTotales(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    @XmlElement
    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    public List<String> getDiasApertura() {
        return diasApertura;
    }

    @XmlElement
    public void setDiasApertura(List<String> diasApertura) {
        this.diasApertura = diasApertura;
    }

    public double getLatitudGPS() {
        return latitudGPS;
    }

    @XmlElement
    public void setLatitudGPS(double latitudGPS) {
        this.latitudGPS = latitudGPS;
    }

    public double getLongitudGPS() {
        return longitudGPS;
    }

    @XmlElement
    public void setLongitudGPS(double longitudGPS) {
        this.longitudGPS = longitudGPS;
    }

    public boolean isCompleto() {
        return (this.plazasLibres < this.plazasTotales);
    }

    /**
     * Constructor parametrizado de la clase Parking
     *
     * @param identificador Identificador numÃ©rico del parking
     * @param nombre Nombre del parking
     * @param horaApertura Hora de apertura del parking
     * @param horaCierre Hora de cierre del parking
     * @param plazasTotales Plazas totales del parking
     * @param plazasLibres Plazas libres del parking
     * @param diasApertura Dias de apertura del parking
     * @param latitudGPS Latitud GPS del parking
     * @param longitudGPS Longitud GPS del parking
     */
    public Parking(int identificador, String nombre, int horaApertura, int horaCierre, int plazasTotales, int plazasLibres, List<String> diasApertura, double latitudGPS, double longitudGPS) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.plazasTotales = plazasTotales;
        this.plazasLibres = plazasLibres;
        this.diasApertura = diasApertura;
        this.latitudGPS = latitudGPS;
        this.longitudGPS = longitudGPS;
    }

    /**
     * Constructor de la clase parking
     */
    public Parking() {

    }

}
