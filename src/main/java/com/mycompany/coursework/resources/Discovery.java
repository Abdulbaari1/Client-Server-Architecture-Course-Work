/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Discovery  {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getDiscovery(){
        HashMap<String, Object> response = new HashMap<>();
        response.put("version", "v1");
        response.put("admin", "admin@westminster.ac.uk");
        response.put("rooms", "/api/v1/rooms");
        
        return response;
    }
    
}
