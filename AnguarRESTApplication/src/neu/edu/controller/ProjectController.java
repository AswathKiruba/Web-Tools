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

import neu.edu.bean.UserAccountBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.output.OutputUserProject;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;
import neu.edu.service.ProjectService;

@Controller
@Path("/user/{id}/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@GET
	@PermitAll
	public Response getAllProject(@PathParam("id") String id){
		
		List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(id));
		
		
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
	@Path("/insert")
	@PermitAll
	public Response addCategory(@PathParam("id") int id,UserProjectBean userProjectBean){
		System.out.println("add project method called");
		
		OutputUserProject userProjectBean1 = projectService.addProject(userProjectBean,id);
		
		return  Response.ok().status(200).entity(userProjectBean1).build();
		
		
	}
	

}
