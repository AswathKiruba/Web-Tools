package neu.edu.controller;

import java.util.List;

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

import neu.edu.bean.CustomBean;
import neu.edu.bean.ReceivedMessageBean;
import neu.edu.bean.ReceivedMessageBean1;
import neu.edu.bean.UserBean;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.service.ChatService;

@Controller
@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@POST
	@Path("/user/{data}")
	@PermitAll
	public Response getAllUser(@PathParam("data") String data){
		
		List<UserBean> userBeans =  chatService.getAllUser(new Integer(data));
		
		
		ResponseCheck ch = new ResponseCheck();
		if(userBeans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(userBeans).build();
		}
		
		
	}
	
	@POST
	@Path("/user/received/{data}")
	@PermitAll
	public Response getAllUserRecieved(@PathParam("data") String data){
		
		List<ReceivedMessageBean> msBeans =  chatService.getAllUserReceived(new Integer(data));
		
		
		ResponseCheck ch = new ResponseCheck();
		if(msBeans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(msBeans).build();
		}
		
		
	}
	
	
	@POST
	@Path("/user/sent/{data}")
	@PermitAll
	public Response getAllUserSent(@PathParam("data") String data){
		
		List<ReceivedMessageBean1> msBeans =  chatService.getAllUserSent(new Integer(data));
		
		
		ResponseCheck ch = new ResponseCheck();
		if(msBeans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(msBeans).build();
		}
		
		
	}
	
	@POST
	@Path("/user/put/{id}/{name}/{message}")
	@PermitAll
	public Response addSent(@PathParam("id") String id,@PathParam("name") String name,
			@PathParam("message") String message,CustomBean customBean){
		
		Boolean msBeans =  chatService.addSent(new Integer(id),name,message,customBean);
		
		
		ResponseCheck ch = new ResponseCheck();
		if(msBeans){
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(ch).build();
		}
		else{
			ch.setMessage("no");
			return  Response.ok().status(422).entity(ch).build();
		}
		
		
	}

}
