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

import neu.edu.bean.PaymentInfoBean;
import neu.edu.bean.StatBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.service.AdminService;

@Controller
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GET
	@Path("/project")
	@PermitAll
	public Response getAllProject(){
		
	List<UserProjectBean> userProjectBeans =  adminService.getAllProject();
	
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
	@Path("/project/disable/{data}")
	@PermitAll
	public Response disableProject(@PathParam("data") String id){
		System.out.println("check project method called");
		
		UserProjectBean bean =  adminService.disableProject(new Integer(id));
		

		return  Response.ok().status(200).entity(bean).build();

		
	}
	
	@GET
	@Path("/city")
	@PermitAll
	public Response getAlltheCity(){
		
	List<PaymentInfoBean> beans =  adminService.getAllCity();
	
		ResponseCheck ch = new ResponseCheck();
		if(beans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(beans).build();
		}
	
	
	}
	
	@GET
	@Path("/stat")
	@PermitAll
	public Response getAlltheStat(){
		
		StatBean bean = adminService.getStat();
		
		return  Response.ok().status(200).entity(bean).build();
	}

}
