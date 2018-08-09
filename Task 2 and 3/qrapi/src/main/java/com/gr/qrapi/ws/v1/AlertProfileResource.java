package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Location;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertProfileResource {
	GeneralServiceLocal genericService = GeneralService.getService();
	@Context
	UriInfo info;

	@GET
	@Path("/profiles/all")
	public List<AlertProfile> getAllProfiles() {
		return genericService.getAllProfiles();
	}

	@POST
	@Path("/account/{accountID}/profiles/add")
	public String addProfile(@PathParam("accountID") int accountID, AlertProfile alertProfile) {
		return genericService.addProfile(accountID, alertProfile);
	}

	@DELETE
	@Path("/profiles/delete/{profileID}")
	public String deleteProfile(@PathParam("profileID") int profileID) {
		return genericService.deleteProfile(profileID);
	}

	@PUT
	@Path("/profiles/update/{id}/{name}/{city}/{country}")
	public String updateProfile(@PathParam("id") int profileID, @PathParam("name") String name,
			@PathParam("city") String city, @PathParam("country") String country) {
		return genericService.updateProfile(profileID, new AlertProfile(name, new Location(city, country)));
	}

	@GET
	@Path("/profiles/search")
	public AlertProfile searchProfileByID(@Context UriInfo info) {
		int profileID = Integer.parseInt(info.getQueryParameters().getFirst("profileID"));
		return genericService.searchProfileByID(profileID);
	}

	@GET
	@Path("/profiles/all")
	public List<AlertProfile> getAllContactsofAccount(@Context UriInfo info) {
		int accountID = Integer.parseInt(info.getQueryParameters().getFirst("accountID"));
		return genericService.getProfilesByAccountID(accountID);
	}

}
