package neu.edu.controller;

import java.util.Date;
import java.util.Random;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserAccountBean;
import neu.edu.bean.UserSessionInfo;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.UserLoginBean;
import neu.edu.service.UserService;
import neu.edu.util.JWTUtil;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@Autowired
	private UserService userService;

	@POST
	@Path("/auth")
	@PermitAll
	public Response validateUser(UserLoginBean loginBean) {

		UserAccountBean user = userService.validateUser(loginBean.getUsername(), loginBean.getPassword());

		if (user == null) {
			System.out.println("user not found");
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("user-not-found");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
			
			
		} else {
			String key = generateKey();
			UserSessionInfo userSessionInfo = userService.fetchUserAccountDetails(user.getId());

			return Response.ok().status(200).entity(userSessionInfo)
					.header(JWTUtil.AUTHORIZATION_PROPERTY, JWTUtil.generateToken(String.valueOf(user.getId()),new String[] {user.getRole()}))
					.build();
					
					
		}

	}

	private String generateKey() {
		Random rand = new Random();

		int n = rand.nextInt(50) + 1;

		return n + (new Date().toString());
	}

}
