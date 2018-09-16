package com.luigui.jpa_example.controller;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luigui.jpa_example.model.Company;
import com.luigui.jpa_example.repository.CompanyRepository;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyController {

	@Inject
	CompanyRepository companyRepository; 
	
	@GET
	public Response getAll() {
		return Response.ok().entity(companyRepository.getAll()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getCompany(@PathParam("id") Long id) {
		return Response.ok().entity(companyRepository.getCompany(id)).build();
	}
	
	@POST
	public Response insertCompany(Company company) {
		return Response.ok().entity(companyRepository.insertCompany(company)).build();
	}
	
	@PUT
	public Response updateCompany(Company company) {
		return Response.ok().entity(companyRepository.updateCompany(company)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCompany(@PathParam("id") Long id) {
		return Response.ok().entity(companyRepository.deleteCompany(id)).build();
	}
}
