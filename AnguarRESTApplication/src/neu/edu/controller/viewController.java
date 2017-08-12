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
import neu.edu.controller.output.ResponseCheck;
import neu.edu.entity.UserProject;
import neu.edu.service.ProjectService;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class viewController {


	@Autowired
	private ProjectService projectService;
	
	@POST
	@Path("/project/{data}")
	@PermitAll
	public Response getAllProject(@PathParam("data") String data){
		System.out.println("getting the project");
		List<UserProjectBean> userProjectBeans =  projectService.getAllProjectCategory(data);
		
		
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
	
	
	@POST
	@Path("/liked/{data}")
	@PermitAll
	public Response likeProject(@PathParam("data") String data, UserProjectBean upb){
		System.out.println("getting the project");
		UserProject userProject =  projectService.likeProject(new Integer(data),upb);
		
		UserProjectBean userP = new UserProjectBean();
		
		userP.setLiked(userProject.getLiked());
		return  Response.ok().status(200).entity(userP).build();
		
	}
	
	@POST
	@Path("/dislike/{data}")
	@PermitAll
	public Response dislikeProject(@PathParam("data") String data, UserProjectBean upb){
		System.out.println("getting the project");
		UserProject userProject =  projectService.dislikeProject(new Integer(data),upb);
		
		UserProjectBean userP = new UserProjectBean();
		
		userP.setHate(userProject.getHate());
		return  Response.ok().status(200).entity(userP).build();
		
	}
	
	@POST
	@Path("/cart/{data}")
	@PermitAll
	public Response cartProject(@PathParam("data") String data){
		System.out.println("getting the project");
		boolean userProject =  projectService.cartProject(new Integer(data));
		
		UserProjectBean userP = new UserProjectBean();
		
		userP.setHate(0);
		return  Response.ok().status(200).entity(userP).build();
		
	}
	
	
	@GET
	@Path("/project/cart/service")
	@PermitAll
	public Response getAllProjectCartService(){
		
		List<UserProjectBean> userProjectBeans =  projectService.getAllProjectCartService();
		
		
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
