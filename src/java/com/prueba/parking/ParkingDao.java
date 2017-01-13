/**
 * La clase ParkingDao
 * 12/09/2016
 */
package com.prueba.parking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k-lagan
 */
public class ParkingDao {

    /**
     * La lista de parkings en el sistema
     */
    List<Parking> listaParkings;
    /**
     * Variable hora al filtrar parkings por fecha
     */
    private Integer h;
    /**
     * Variable fecha al filtrar parkings por fecha
     */
    private Date date;

    /**
     * Método que obtiene la lista de los parkings dados de alta inicialmente en
     * el sistema. Intenta guardar en sesion los datos.
     *
     * @return la lista de parkings
     */
    public List<Parking> obtenerParkings() {
        inicializarDatos();
        return listaParkings;
    }

    /**
     * Método que crea una instancia de la lista si esta no ha sido instanciada
     */
    public void instanciaLista() {
        if (listaParkings == null) {
            listaParkings = new ArrayList<>();
        }
    }

    /**
     * Método que carga tres parking en la aplicación
     */
    private void inicializarDatos() {
        instanciaLista();
        if (listaParkings.size() <= 0) {
            Parking p1 = new Parking(0, "Park Blanc", 0, 22, 200, 200,
                    Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"), 40.23, 12.45);
            Parking p2 = new Parking(1, "Park Blau", 9, 22, 100, 365,
                    Arrays.asList("Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"), 14.15, 7.01121);
            Parking p3 = new Parking(2, "Park Morat", 8, 23, 150, 350,
                    Arrays.asList("Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"), 18.14, 19.412241421);

            listaParkings.add(p1);
            listaParkings.add(p2);
            listaParkings.add(p3);
        }
    }

    /**
     * Método que añade un parking nuevo a la lista de parkings
     *
     * @param nombre
     * @param hApertura
     * @param hCierre
     * @param pLibres
     * @param pTotales
     * @param diasApertura
     * @param latitud
     * @param longitud
     * @return
     */
    public int anadirParking(String nombre, int hApertura, int hCierre, int pLibres, int pTotales, String diasApertura,
            double latitud, double longitud) {
        listaParkings = obtenerParkings();
        List<String> dias = new ArrayList<>();
        StringTokenizer p = new StringTokenizer(diasApertura, ",");
        while (p.hasMoreTokens()) {
            dias.add(p.nextToken());
        }
        Parking park = new Parking(listaParkings.size(), nombre, hApertura, hCierre, pLibres, pTotales, dias, latitud,
                longitud);
        listaParkings.add(park);

        return park.getIdentificador();

    }

    /**
     * Método que añade un parking tras modificarlo
     *
     * @param identificador
     * @param nombre
     * @param hApertura
     * @param hCierre
     * @param pLibres
     * @param pTotales
     * @param diasApertura
     * @param latitud
     * @param longitud
     * @return El identificador asignado al nuevo parking
     */
    public Parking anadirParking(int identificador, String nombre, int hApertura, int hCierre, int pLibres, int pTotales,
            String diasApertura, double latitud, double longitud) {
        Parking park = null;
        listaParkings = obtenerParkings();
        List<String> dias = new ArrayList<>();
        StringTokenizer p = new StringTokenizer(diasApertura, ",");
        while (p.hasMoreTokens()) {
            dias.add(p.nextToken());
        }
        try {
            park = new Parking(identificador, nombre, hApertura, hCierre, pLibres, pTotales, dias, latitud,
                    longitud);
            listaParkings.add(park);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Se ha producido un error de formato de uno de los parámetros... ", ex);
        }
        return park;
    }

    /**
     * Método que busca un parking en la lista de parkings y lo elimina en caso
     * de encontrarlo. A continuacion lo vuelve a añadir con los cambios.
     *
     * @param identificador
     * @param nombre
     * @param hApertura
     * @param hCierre
     * @param pLibres
     * @param pTotales
     * @param diasApertura
     * @param latitud
     * @param longitud
     * @return
     */
    public boolean modificarParking(int identificador, String nombre, int hApertura, int hCierre, int pLibres,
            int pTotales, String diasApertura, double latitud, double longitud) {
        listaParkings = obtenerParkings();
        if (listaParkings != null && listaParkings.size() > 0) {
            for (Parking p : listaParkings) {
                if (p.getIdentificador() == identificador) {
                    listaParkings.remove(p);
                    anadirParking(identificador, nombre, hApertura, hCierre, pLibres, pTotales, diasApertura, latitud,
                            longitud);
                    return true;
                    // Añadir un sort con un comparator para dar la apariencia
                    // de modificacion y no de que se ha borrado y luego añadido
                    // de nuevo }
                }
            }
        }
        return false;
    }

    /**
     * Método que muestra la lista de parkings abiertos para la hora en curso
     *
     * @return devuelve la lista de parkings abiertos para la hora en curso
     */
    public List<Parking> mostrarAbiertos() {
        listaParkings = obtenerParkings();
        List<Parking> listaParkingsAbiertos = new ArrayList<>();
        String diaSemana = "";
        if (listaParkings != null && listaParkings.size() > 0) {
            Calendar calendario = Calendar.getInstance();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int dia = calendario.get(Calendar.DAY_OF_WEEK);
            diaSemana = obtenerDiaSemana(dia, diaSemana);
            for (Parking p : listaParkings) {
                if (p.getHoraApertura() <= hora && hora <= p.getHoraCierre()) {
                    for (String di : p.getDiasApertura()) {
                        if (di.equals(diaSemana)) {
                            listaParkingsAbiertos.add(p);
                            break;
                        }
                    }
                }
            }
        }
        return listaParkingsAbiertos;
    }

    /**
     * Método que muestra la lista de parkings abiertos para la hora en curso
     * con plazas libres
     *
     * @return devuelve la lista de parkings abiertos para la hora en curso con
     * plazas libres
     */
    public List<Parking> mostrarAbiertosLibres() {
        listaParkings = obtenerParkings();
        List<Parking> listaParkingsAbiertosLibres = new ArrayList<>();
        String diaSemana = "";
        if (listaParkings != null && listaParkings.size() > 0) {
            Calendar calendario = Calendar.getInstance();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int dia = calendario.get(Calendar.DAY_OF_WEEK);
            diaSemana = obtenerDiaSemana(dia, diaSemana);
            for (Parking p : listaParkings) {
                if (p.getHoraApertura() <= hora && hora <= p.getHoraCierre()) {
                    for (String di : p.getDiasApertura()) {
                        if (di.equals(diaSemana)) {
                            if (!p.isCompleto()) {
                                listaParkingsAbiertosLibres.add(p);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return listaParkingsAbiertosLibres;
    }

    /**
     * Método que muestra la lista de parkings abiertos para la hora en curso
     *
     * @param fechaFormato
     * @return devuelve la lista de parkings abiertos para la hora en curso
     * @throws java.text.ParseException
     */
    public List<Parking> mostrarAbiertosFecha(String fechaFormato) throws ParseException {
        listaParkings = obtenerParkings();
        List<Parking> listaParkingsAbiertosFecha = new ArrayList<>();
        if (fechaFormato != null && !fechaFormato.isEmpty()) {
            for (Parking p : listaParkings) {
                String fecha = "";
                String hora = "";
                if (fechaFormato.contains("#")) {
                    String[] partes = fechaFormato.split("#");
                    fecha = partes[0];
                    hora = partes[1];
                }

                if (!fecha.isEmpty() && !hora.isEmpty()) {
                    String fechaFormateada = fecha.replace(".", "-");
                    String diaSemana = "";
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        date = formato.parse(fechaFormateada);
                    } catch (ParseException ex) {
                        Logger.getLogger(getClass().getName()).log(
                                Level.SEVERE, "Se ha producido un error de formato de fecha... " + date, ex);
                        return listaParkingsAbiertosFecha;
                    }

                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(date);
                    int dia = cal.get(Calendar.DAY_OF_WEEK);

                    diaSemana = obtenerDiaSemana(dia, diaSemana);

                    try {
                        h = Integer.valueOf(hora);
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(getClass().getName()).log(
                                Level.SEVERE, "Se ha producido un error de formato de hora... " + hora, ex);
                        return listaParkingsAbiertosFecha;
                    }
                    if (h >= (p.getHoraApertura()) && (h <= p.getHoraCierre())) {
                        for (String di : p.getDiasApertura()) {
                            if (di.equals(diaSemana)) {
                                listaParkingsAbiertosFecha.add(p);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return listaParkingsAbiertosFecha;
    }

    /**
     * Metodo que tras pasarle por parametro el dia de la semana desde Calendar,
     * devuelve el dia de la semana que es (Lunes, Martes, Miércoles...)
     *
     * @param dia El dia de la semana devuelto desde Calendar
     * @param diaSemana La cadena donde se devuelve el nombre del día a partir
     * de su literal pasado desde Calendar
     * @return El textual del día de la semana
     */
    private String obtenerDiaSemana(int dia, String diaSemana) {
        if (dia == 2) {
            diaSemana = "Lunes";
        }
        if (dia == 3) {
            diaSemana = "Martes";
        }
        if (dia == 4) {
            diaSemana = "Miercoles";
        }
        if (dia == 5) {
            diaSemana = "Jueves";
        }
        if (dia == 6) {
            diaSemana = "Viernes";
        }
        if (dia == 7) {
            diaSemana = "Sabado";
        }
        if (dia == 1) {
            diaSemana = "Domingo";
        }
        return diaSemana;
    }
}
