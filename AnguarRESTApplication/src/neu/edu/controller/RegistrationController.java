package neu.edu.controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserAccountBean;
import neu.edu.entity.Category;
import neu.edu.entity.UserAccount;
import neu.edu.service.CategoryService;
import neu.edu.service.RegisterService;

@Controller
@Path("/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RegistrationController {

	@Autowired
	private RegisterService registerService;
	
	@POST
	@PermitAll
	public Response addCategory(UserAccountBean userAccountBean){
		System.out.println("add category method called");
		
		UserAccount userAccount1 = registerService.createUser(userAccountBean);
		
		
			return  Response.ok().status(200).entity(userAccount1).build();
		
		
	}
}
