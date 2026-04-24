/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;
import com.mycompany.coursework.exception.LinkedResourceNotFoundException;
import com.mycompany.coursework.resources.RoomResource;
import com.mycompany.coursework.models.Sensor;
import com.mycompany.coursework.models.Room;
import java.util.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;  
import javax.ws.rs.core.MediaType;


@Path ("/sensors")
public class SensorResource {
    
    static Map<String, Sensor> sensors = new HashMap<>();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Sensor createSensor(Sensor sensor){
        
        if(sensor == null || sensor.getId() == null || sensor.getRoomId() == null){
        return null;
        }
        if(!RoomResource.rooms.containsKey(sensor.getRoomId())){
        throw new LinkedResourceNotFoundException(
        "Room with ID: " + sensor.getRoomId() + " not found."
        );
        }
        sensors.put(sensor.getId(), sensor);
        Room room = RoomResource.rooms.get(sensor.getRoomId());
        room.getSensorIds().add(sensor.getId());
        return sensor;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Sensor> searchSensor(@QueryParam("type") String type){
    
        if(type == null){
        return sensors.values();
        }
        List<Sensor> filteredlist = new ArrayList<>();
        
        for(Sensor s: sensors.values()){
        if (type.equalsIgnoreCase(s.getType())){
        filteredlist.add(s);
        }
        }
        return filteredlist;
    
    }
    @GET
    @Path("/{sensorId}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Sensor getSensorById(@PathParam("sensorId") String sensorId) {
        if(sensors.containsKey(sensorId)){
        return sensors.get(sensorId);
        }
        else{
        return null;
        }
    }
    
   @Path("/{sensorId}/readings")
    public SensorReadingResource getReadingResource(@PathParam("sensorId")String sensorId){
    return new SensorReadingResource(sensorId);
    }
}

