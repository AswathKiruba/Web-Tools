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
import neu.edu.controller.output.ResponseCheck;
import neu.edu.service.PaymentService;

@Controller
@Path("/donate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@POST
	@Path("/project/{pid}/{uid}")
	@PermitAll
	public Response addAmount(@PathParam("pid") String pid,@PathParam("uid") String uid,PaymentInfoBean pay){
		
		Boolean bean = paymentService.addAmount(new Integer(pid),new Integer(uid),pay);
		ResponseCheck res = new ResponseCheck();
		if(bean){
			res.setMessage("done");
		}
		
		return  Response.ok().status(200).entity(res).build();
		
		
	}

}
