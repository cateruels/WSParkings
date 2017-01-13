/**
 * El servicio parking
 */
package com.prueba.parking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.JResponse;
import java.text.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author k-lagan
 */
@Path("/")
public class ParkingService {

    ParkingDao parkingDao = new ParkingDao();

    /**
     * Llamada GET que obtiene la lista de parkings precargados en memoria
     *
     * @return Devuelve la lista de parkings precargados en memoria
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Path("/parkings")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse obtenerParkings() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.obtenerParkings());
        return JResponse.ok(response).build();
    }

    /**
     * Llamada GET que obtiene la lista de parkings abiertos en el momento
     * actual
     *
     * @return Devuelve la lista de parkings abiertos
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Path("/parkingsAbiertos")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse obtenerParkingsAbiertos() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.mostrarAbiertos());
        return JResponse.ok(response).build();
    }

    /**
     * Llamada GET que obtiene la lista de parkings abiertos en el momento
     * actual con plazas libres
     *
     * @return Devuelve la lista de parkings abiertos
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Path("/parkingsAbiertosLibres")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse obtenerParkingsAbiertosLibres() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.mostrarAbiertosLibres());
        return JResponse.ok(response).build();
    }

    /**
     * Llamada GET que muestra los parking abiertos para una fecha y hora en
     * concreto
     *
     * @param fechaFormato Fecha en formato DD.MM.YYYY#hh
     * @return Muestra los parkings abiertos para una hora y fecha especificos
     * @throws java.text.ParseException
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @GET
    @Path("/mostrarAbiertosFecha/{fechaFormato}")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse mostrarAbiertosFecha(@PathParam("fechaFormato") String fechaFormato) throws ParseException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.mostrarAbiertosFecha(fechaFormato));
        return JResponse.ok(response).build();
    }

    /**
     * Llamada POST que añade un parking a la lista de parkings
     *
     * @param nombre Nombre del parking
     * @param hApertura Hora de apertura del parking
     * @param hCierre Hora de cierre del parking
     * @param pLibres Plazas libres del parking
     * @param pTotales Plazas totales del parking
     * @param diasApertura Dias de apertura del parking
     * @param latitud Latitud del parking
     * @param longitud Longitud del parking
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @POST
    @Path("/anadirParking/{nombre}/{hApertura}/{hCierre}/{pLibres}/{pTotales}/{diasApertura}/{latitud}/{longitud}")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse anadirParking(@QueryParam("nombre") String nombre, @QueryParam("hApertura") int hApertura, @QueryParam("hCierre") int hCierre, @QueryParam("pLibres") int pLibres, @QueryParam("pTotales") int pTotales, @QueryParam("diasApertura") String diasApertura, @QueryParam("latitud") long latitud, @QueryParam("longitud") long longitud) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.anadirParking(nombre, hApertura, hCierre, pLibres, pTotales, diasApertura, latitud, longitud));
        return JResponse.ok(response).build();
    }

    /**
     * Llamada POST que modifica un parking
     *
     * @param id Id del parking a ser modificado
     * @param nombre Nombre del parking
     * @param hApertura Hora de apertura
     * @param hCierre Hora de cierre
     * @param pLibres Plazas libres
     * @param pTotales Plazas totales
     * @param diasApertura Dias de apertura
     * @param latitud Latitud del parking
     * @param longitud Longitud del parking
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @POST
    @Path("/modificarParking/{id}/{nombre}/{hApertura}/{hCierre}/{pLibres}/{pTotales}/{diasApertura}/{latitud}/{longitud}")
    @Produces(MediaType.APPLICATION_JSON)
    public JResponse modificarParking(@QueryParam("id") int id, @QueryParam("nombre") String nombre, @QueryParam("hApertura") int hApertura, @QueryParam("hCierre") int hCierre, @QueryParam("pLibres") int pLibres, @QueryParam("pTotales") int pTotales, @QueryParam("diasApertura") String diasApertura, @QueryParam("latitud") long latitud, @QueryParam("longitud") long longitud) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingDao.modificarParking(id, nombre, hApertura, hCierre, pLibres, pTotales, diasApertura, latitud, longitud));
        return JResponse.ok(response).build();
    }

}
