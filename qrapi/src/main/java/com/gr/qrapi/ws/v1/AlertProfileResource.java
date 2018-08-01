package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Location;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertProfileResource {
	GeneralServiceLocal genericService = GeneralService.getService();

	@GET
	@Path("/all")
	public List<AlertProfile> getAllProfiles() {
		return genericService.getAllProfiles();
	}

	@SuppressWarnings("null")
	@POST
	@Path("/add/{accountID}/{name}/{city}/{country}")
	public String addprofile(@FormParam("accountID") int accountID, @FormParam("name") String name,
			@FormParam("city") String city, @FormParam("country") String country) {
		List<Location> location = null;
		location.add(new Location(city, country));
		return genericService.addProfile(accountID, new AlertProfile(name, location));
	}

	@DELETE
	@Path("/delete/{profileID}")
	public AlertProfile deleteProfile(@PathParam("profileID") int profileID) {
		return genericService.deleteProfile(profileID);
	}

	@SuppressWarnings("null")
	@POST
	@Path("/update/{id}/{name}/{city}/{country}")
	public AlertProfile updateProfile(@FormParam("id") int profileID, @FormParam("name") String name,
			@FormParam("city") String city, @FormParam("country") String country) {
		List<Location> location = null;
		location.add(new Location(city, country));
		return genericService.updateProfile(profileID, new AlertProfile(name, location));
	}
}
