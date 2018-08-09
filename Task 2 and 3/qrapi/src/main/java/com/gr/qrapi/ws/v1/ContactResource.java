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

import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {
	GeneralServiceLocal genericService = GeneralService.getService();
	@Context
	UriInfo info;

	@GET
	@Path("/contacts/all")
	public List<Contact> getAllContactsofAccount(@Context UriInfo info) {
		int accountID = Integer.parseInt(info.getQueryParameters().getFirst("accountID"));
		return genericService.getAllContactsofAccount(accountID);
	}

	@GET
	@Path("/contacts/addresses/all")
	public List<Address> getAllAddressesofContacts(@Context UriInfo info) {
		int accountID = Integer.parseInt(info.getQueryParameters().getFirst("accountID"));
		return genericService.getAllAddressesofContacts(accountID);
	}
	

	@POST
	@Path("/account/{accountID}/contacts/add")
	public String addContact(@PathParam("accountID") int accountID, Contact contact) {
		String result = genericService.addContact(accountID, contact);
		List<AlertProfile> profileList = genericService.getProfilesByAccountID(accountID);
		if(result.equalsIgnoreCase("{\"output\":\"success\"}")){
		for (AlertProfile profile : profileList) {
			if (contact.getAddress().getCity().equalsIgnoreCase(profile.getLocation().getCity())
					| contact.getAddress().getCountry().equalsIgnoreCase(profile.getLocation().getCountry())) {
				return "{\"alert\":\"true\" , \"output\":\"success\"}";
			}
		}
		}else {
			return "{\"alert\":\"none\" , \"output\":\"failure\"}";
		}
		return "{\"alert\":\"false\" , \"output\":\"success\"}";
	}

	@DELETE
	@Path("contacts/delete/{contactID}")
	public String deleteContact(@PathParam("contactID") int contactID) {
		return genericService.deleteContact(contactID);
	}

	@PUT
	@Path("contacts/update/{contactID}/{fname}/{lname}/{emailID}/{gender}/{phoneNo}/{status}/{street}/{city}/{state}/{country}")
	public String updateContact(@PathParam("contactID") int contactID, @PathParam("fname") String fname,
			@PathParam("lname") String lname, @PathParam("emailID") String emailID, @PathParam("gender") String gender,
			@PathParam("phoneNo") String phoneNo, @PathParam("status") String status,
			@PathParam("street") String street, @PathParam("city") String city, @PathParam("state") String state,
			@PathParam("country") String country) {
		return genericService.updateContact(contactID,
				new Contact(fname, lname, emailID, gender, phoneNo, status, new Address(street, city, state, country)));
	}

	@GET
	@Path("/contacts/search")
	public Contact searchContactByID(@Context UriInfo info) {
		int contactID = Integer.parseInt(info.getQueryParameters().getFirst("contactID"));
		return genericService.searchContactByID(contactID);
	}

}
