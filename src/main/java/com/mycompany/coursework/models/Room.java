/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Room {

    private String id;   // Unique identifier , e.g., "LIB -301"
    private String name; // Human-readable name, e.g., "Library Quiet Study"
    private int capacity; //Maximum occupancy for safety
    private List<String> sensorIds = new ArrayList<>(); //Collection of IDs of sensor deployed in this room

// Constructors, getters , setters ...
    public Room() {
    }

    public Room(String id, String name, int capacity, List<String> sensorIds) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.sensorIds = sensorIds;
    }

    public String getId() {  //Getter for ID
        return id;
    }

    public void setId(String id) { //Setter for ID
        this.id = id;
    }

    public String getName() {  //Getter for Name
        return name;
    }

    public void setName(String name) { //Setter for name
        this.name = name;
    }

    public int getCapacity() { //Getter for Capacity
        return capacity;
    }

    public void setCapacity(int capacity) { //Setter for Capacity
        this.capacity = capacity;
    }

    public List<String> getSensorIds() { //Getter for SensorIds
        return sensorIds;
    }

    public void setSensorIds(List<String> sensorIds) {//Setter for SensorIds
        this.sensorIds = sensorIds;
    }

}
