package neu.edu.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserProjectBean;
import neu.edu.controller.output.CategoryBeanOutput;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.service.HomeService;

@Controller
@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@GET
	@Path("/project")
	@PermitAll
	public Response getAllProject(){
		
	List<UserProjectBean> userProjectBeans =  homeService.getAllProject();
	
		ResponseCheck ch = new ResponseCheck();
		if(userProjectBeans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(userProjectBeans).build();
		}
	}
		
		
		@GET
		@Path("/project/all")
		@PermitAll
		public Response getAlltheProject(){
			
		List<UserProjectBean> userProjectBeans =  homeService.getAllProjects();
		
			ResponseCheck ch = new ResponseCheck();
			if(userProjectBeans.isEmpty()){
				ch.setMessage("no");
				return  Response.ok().status(422).entity(ch).build();
			}
			else{
				ch.setMessage("yes");
				return  Response.ok().status(200).entity(userProjectBeans).build();
			}
		
		
		}
	
		

		

}
