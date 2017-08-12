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

import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;

import neu.edu.bean.CategoryBean;
import neu.edu.bean.CommentBean;
import neu.edu.bean.MessageID;
import neu.edu.service.CategoryService;
import neu.edu.service.MessageService;

@Controller
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageController {
	
	
	@Autowired
	private MessageService messageService;
	
	@GET
	@Path("/message/{data}")
	@PermitAll
	public Response getMessage(@PathParam("data") String name){
		
		List<CommentBean> commentBeans =  messageService.getAllComments(new Integer(name));
		return  Response.ok().status(200).entity(commentBeans).build();
		
	}
	
	@POST
	@Path("/message/add/{data1}")
	@PermitAll
	public Response addMessage(@PathParam("data1") String id,CommentBean comment){
		
		Boolean commentBeans =  messageService.addComments(new Integer(id),comment);
		MessageID proj = new MessageID();
		if(commentBeans==true){
		
		
		proj.setProjectID(new Integer(id));
		}
		return  Response.ok().status(200).entity(proj).build();
		
	}

}
