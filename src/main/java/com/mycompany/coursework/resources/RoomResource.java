/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;

import com.mycompany.coursework.exception.RoomNotEmptyException;
import com.mycompany.coursework.models.Room;
import java.util.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rooms")
public class RoomResource {

    static Map<String, Room> rooms = new HashMap<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Room> getAllRooms() {
        return rooms;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Room createRoom(Room room){
        
        if(room == null || room.getId() == null){
        return  room;
        }
    rooms.put(room.getId(), room);
    
    return room;
    }
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("roomId") String roomId) {
        if(rooms.containsKey(roomId)){
        return rooms.get(roomId);
        }
        else{
        return null;
        }
    }
    @DELETE
    @Path("/{roomId}")
    public String deleteRoom(@PathParam("roomId")String roomId){
    Room room = rooms.get(roomId);
    
    if(room == null){
        return "Room not Found";
    }
    if (room != null && !room.getSensorIds().isEmpty()){
    throw new RoomNotEmptyException(
    "Room currently has active sensors."
    );
    }
        rooms.remove(roomId);
        
        return "Room successfully deleted.";
    }
    
}
