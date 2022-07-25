package com.epower.crud.CustomerManagement.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.epower.crud.CustomerManagement.services.CustomerManagementServices;


@Path("/customer")
public class CustomerManagementResource {

CustomerManagementServices services = new CustomerManagementServices();
	
	//View all customers
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewCustomerInfo(){
		return services.viewCustomerDetails();
	}

	//Insert new Customer
	@POST
	@Path("/addCustomerData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomerInfo(@FormParam("c_name") String c_name, @FormParam("account_no") String account_no,
								@FormParam("email") String email) 
	{
		
		String output = services.insertCustomerDetails(c_name, account_no,email) ;
		 return output;
	}
	
	//update customer
	
	
	@PUT
	@Path("/updateCustomerData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomerInfo(@FormParam("c_name") String c_name, @FormParam("account_no") String account_no,
			@FormParam("email") String email, @FormParam("c_id") String c_id) {
			
		String output = services.updateCustomerDetails(c_id, c_name, account_no , email);
		
		return output;
	}
	
	
	//Delete Customer
	@DELETE
	@Path("/deleteCustomerData")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomerInfo(String cus)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(cus, "", Parser.xmlParser());
		
		//Read the value from the element customer id
		String c_id = doc.select("c_id").text();
		String output = services.deleteCustomerDetails(c_id);
	
	return output;
	}
	
	//Get customer by ID
	@GET
	@Path("/{c_id}")
	@Produces(MediaType.TEXT_HTML)
	public String getCustomerInfoById(@PathParam("c_id") int id){
		return services.getCustomerDetailsById(id);
	}
}
