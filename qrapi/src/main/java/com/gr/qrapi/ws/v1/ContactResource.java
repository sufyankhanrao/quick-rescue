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

import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {
	GeneralServiceLocal genericService = GeneralService.getService();

	@GET
	@Path("/all")
	public List<Contact> getAllAccounts() {
		return genericService.getAllContacts();
	}

	@SuppressWarnings("null")
	@POST
	@Path("/add/{accountID}/{fname}/{lname}/{emailID}/{gender}/{phoneNo}/{status}/{street}/{city}/{state}/{country}")
	public String addContact(@FormParam("accountID") int accountID, @FormParam("fname") String fname,
			@FormParam("lname") String lname, @FormParam("emailID") String emailID, @FormParam("gender") String gender,
			@FormParam("phoneNo") String phoneNo, @FormParam("status") String status,
			@FormParam("street") String street, @FormParam("city") String city, @FormParam("state") String state,
			@FormParam("country") String country) {
		List<Address> addresses = null;
		addresses.add(new Address(street, city, state, country));
		return genericService.addContact(accountID,
				new Contact(fname, lname, emailID, gender, phoneNo, status, addresses));
	}

	@DELETE
	@Path("/delete/{contactID}")
	public Contact deleteContact(@PathParam("contactID") int contactID) {
		return genericService.deleteContact(contactID);
	}

	@SuppressWarnings("null")
	@POST
	@Path("/update/{contactID}/{fname}/{lname}/{emailID}/{gender}/{phoneNo}/{status}/{street}/{city}/{state}/{country}")
	public Contact updateContact(@FormParam("contactID") int contactID, @FormParam("fname") String fname,
			@FormParam("lname") String lname, @FormParam("emailID") String emailID, @FormParam("gender") String gender,
			@FormParam("phoneNo") String phoneNo, @FormParam("status") String status,
			@FormParam("street") String street, @FormParam("city") String city, @FormParam("state") String state,
			@FormParam("country") String country) {
		List<Address> addresses = null;
		addresses.add(new Address(street, city, state, country));
		return genericService.updateContact(contactID,
				new Contact(fname, lname, emailID, gender, phoneNo, status, addresses));
	}

}
