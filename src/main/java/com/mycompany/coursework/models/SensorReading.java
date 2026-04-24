/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.models;

/**
 *
 * @author user
 */
public class SensorReading {
    private String id; // Unique reading event ID (UUID recommended )
 private long timestamp ; // Epoch time (ms) when the reading was captured
private double value; // The actual metric value recorded by the hardware

// Constructors, getters , setters ...

    public SensorReading() {
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
