package com.plangenerator.services;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.plangenerator.Payload;
import com.plangenerator.businesslogics.Calculations;

@Path("/")
public class LoansService {
	
	@POST
	@Path("/generate-plan")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Payload payload) throws ParseException {
		System.out.println("service...");
		Calculations calculations = new Calculations(payload);
		calculations.calculatePlan();
		//"Payload saved" 
		String result = calculations.toString();
		return Response.status(201).entity(result).build();
	}
}
