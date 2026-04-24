/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.exception;

import com.mycompany.coursework.models.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException>{

    
    @Override
    public Response toResponse(SensorUnavailableException exception) {
    ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
        403,
                "Sensor must be active to accept any readings."
        
        );
        
        return Response.status(403)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
