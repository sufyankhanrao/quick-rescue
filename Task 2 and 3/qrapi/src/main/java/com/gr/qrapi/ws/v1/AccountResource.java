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

import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.service.GeneralService;
import com.gr.qrapi.core.service.GeneralServiceLocal;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

	GeneralServiceLocal genericService = GeneralService.getService();
	@Context
	UriInfo info;

	@GET
	@Path("/all")
	public List<Account> getAllAccounts() {
		return genericService.getAllAccounts();
	}

	@POST
	@Path("/add")
	public String addAccount(Account account) {
		return genericService.addAccount(account);
	}

	@DELETE
	@Path("/delete/{accountID}")
	public String deleteAccount(@PathParam("accountID") int accountID) {
		return genericService.deleteAccount(accountID);
	}

	@PUT
	@Path("/update/{id}/{name}/{emaildomain}/{timezonecity}/{username}/{password}")
	public String updateAccount(@PathParam("id") int id, @PathParam("name") String name,
			@PathParam("emaildomain") String emailDomain, @PathParam("timezonecity") String timezoneCity,
			@PathParam("username") String username, @PathParam("password") String password) {
		return genericService.updateAccount(id, new Account(name, emailDomain, timezoneCity, username, password));
	}

	@GET
	@Path("/search")
	public Account searchAccountByID(@Context UriInfo info) {
		int accountID = Integer.parseInt(info.getQueryParameters().getFirst("accountID"));
		return genericService.searchAccountByID(accountID);
	}

	@POST
	@Path("/login")
	public String getLogin(Account account) {
		return genericService.getLogin(account.getUsername(), account.getPassword());
	}

}
