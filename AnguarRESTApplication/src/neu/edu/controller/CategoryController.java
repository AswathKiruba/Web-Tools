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

import neu.edu.bean.CategoryBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.output.CategoryBeanOutput;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.entity.Category;
import neu.edu.service.CategoryService;
import neu.edu.service.ProjectService;

@Controller
@Path("/user/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@GET
	@PermitAll
	public Response getCategory(){
		
		List<CategoryBean> categoryBeans =  categoryService.getAllCategories();
		return  Response.ok().status(200).entity(categoryBeans).build();
		
	}
	
	@GET
	@Path("/check")
	@PermitAll
	public Response getCategoryCheck(){
		
		List<CategoryBean> categoryBeans =  categoryService.getAllCategoriesCheck();
		return  Response.ok().status(200).entity(categoryBeans).build();
		
	}
	
	@POST
	@Path("/add/{data}")
	@PermitAll
	public Response addCategory(@PathParam("data") String name){
		System.out.println("add category method called");
		
		Category cat =  categoryService.addCategory(name);
		
		
			return  Response.ok().status(200).entity(cat).build();
		
		
	}
	
	@POST
	@Path("/add/{data}/check")
	@PermitAll
	public Response check(@PathParam("data") String name){
		System.out.println("check category method called");
		
		List<CategoryBeanOutput> categoryBeans =  categoryService.checkAllCategories(name);
		ResponseCheck ch = new ResponseCheck();
		if(categoryBeans.isEmpty()){
			ch.setMessage("no");
			return  Response.ok().status(200).entity(ch).build();
		}
		else{
			ch.setMessage("yes");
			return  Response.ok().status(200).entity(ch).build();
		}
		
		
	}
	
	@POST
	@Path("/add/{data}/check/disable")
	@PermitAll
	public Response disableCategory(@PathParam("data") String name){
		System.out.println("check category method called");
		
		CategoryBeanOutput categoryBean1 =  categoryService.disableCategory(name);
		

		return  Response.ok().status(200).entity(categoryBean1).build();

		
	}

	@POST
	@Path("/add/{data}/check/activate")
	@PermitAll
	public Response activateCategory(@PathParam("data") String name){
		System.out.println("check category method called");
		
		CategoryBeanOutput categoryBean1 =  categoryService.activateCategory(name);

		return  Response.ok().status(200).entity(categoryBean1).build();

		
		
	}
	
	@POST
	@Path("/add/{data}/check/delete")
	@PermitAll
	public Response deleteCategory(@PathParam("data") String name){
		System.out.println("check category method called");
		
		Boolean categoryBean1 =  categoryService.deleteCategory(name);
		
		ResponseCheck res = new ResponseCheck();
		
		if(categoryBean1==true){
			res.setMessage("deleted");
			
		}
		
		return  Response.ok().status(200).entity(res).build();

		

		
		
	}

}
