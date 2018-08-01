package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

	GeneralServiceLocal genericService = GeneralService.getService();

	@GET
	@Path("/all")
	public List<Account> getAllAccounts() {
		return genericService.getAllAccounts();
	}

	@PUT
	@Path("/add/{name}/{emaildomain}/{timezonecity}")
	public String addAccount(@PathParam("name") String name, @PathParam("emaildomain") String emailDomain,
			@PathParam("timezonecity") String timezoneCity) {
		return genericService.addAccount(new Account(name, emailDomain, timezoneCity));
	}

	@DELETE
	@Path("/delete/{accountID}")
	public Account deleteAccount(@PathParam("accountID") int accountID) {
		return genericService.deleteAccount(accountID);
	}
	@PUT
	@Path("/update/{id}/{name}/{emaildomain}/{timezonecity}")
	public Account updateAccount(@PathParam("id") int id,@PathParam("name") String name, @PathParam("emaildomain") String emailDomain,
			@PathParam("timezonecity") String timezoneCity) {
		return genericService.updateAccount(id,new Account(name, emailDomain, timezoneCity));
	}
	
	
}
