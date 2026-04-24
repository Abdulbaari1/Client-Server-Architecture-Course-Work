/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;

import com.mycompany.coursework.exception.SensorUnavailableException;
import com.mycompany.coursework.models.Sensor;
import com.mycompany.coursework.models.SensorReading;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SensorReadingResource {

    private String sensorId;

    public static Map<String, List<SensorReading>> readings = new HashMap<>();

    public SensorReadingResource() {
    }

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> returnReadings() {
        if (readings.containsKey(sensorId)) {
            return readings.get(sensorId);
        }
        return new ArrayList<>();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReading addReading(SensorReading sentreading) {
        Sensor sensor = SensorResource.sensors.get(sensorId);
        if (sensor == null) {
            return null;
        }
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException(
                    "Sensor is currently in maintenance and cannot accept any new readings."
            );
        }
        if (!readings.containsKey(sensorId)) {
            readings.put(sensorId, new ArrayList<>());
        }
        readings.get(sensorId).add(sentreading);

        sensor.setCurrentValue(sentreading.getValue());

        return sentreading;

    }
}
