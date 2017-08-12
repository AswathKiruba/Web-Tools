package neu.edu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.UserProjectBean;

import neu.edu.controller.output.OutputUserProject;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;

@Service
public class ProjectService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public OutputUserProject addProject(UserProjectBean userProjectBean,Integer userId){
		boolean a;
	UserProject userProject = new UserProject();
		userProject.setCategory(userProjectBean.getCategory());
		userProject.setDescription(userProjectBean.getDescription());
		userProject.setTitle(userProjectBean.getTitle());
		userProject.setGoal(userProjectBean.getGoal());
		userProject.setShortdesc(userProjectBean.getShortdesc());
		userProject.setStatus("active");
		userProject.setLiked(0);
		userProject.setHate(0);
		userProject.setCount(0);
		Float as =(float) 0;
		userProject.setReceived(as);
		userProject.setCart("no");
		userProject.setAmount1(userProjectBean.getAmount1());
		userProject.setAmount2(userProjectBean.getAmount2());
		userProject.setAmount3(userProjectBean.getAmount3());
		userProject.setService1(userProjectBean.getService1());
		userProject.setService2(userProjectBean.getService2());
		userProject.setService3(userProjectBean.getService3());
		
		
		// 2017-12-31
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (userProjectBean.getEndDate() != null) {
			try {
				userProject.setEnddate(sdf.parse(userProjectBean.getEndDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		UserAccount account =userDAO.getForiegn(userId);
		
		userProject.setUserAccount(account);
		
		UserProject proj = userDAO.addProject(userProject);
		OutputUserProject op = new OutputUserProject();
		op.setCategory(proj.getCategory());
		
		return op;
	}

	
	
	@Transactional
	public List<UserProjectBean> getAllProject( Integer userId) {
		// TODO Auto-generated method stub
		List<UserProject> userProjects = userDAO.getAllProjects(userId);
		
		List<UserProjectBean> response = new ArrayList<>();
		for(UserProject userProject:userProjects){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.setTitle(userProject.getTitle());
			userProjectBean.setDescription(userProject.getDescription());
			userProjectBean.setAmount1(userProject.getAmount1());
			userProjectBean.setAmount2(userProject.getAmount2());
			userProjectBean.setAmount3(userProject.getAmount3());
			userProjectBean.setCategory(userProject.getCategory());
			userProjectBean.setEndDate(String.valueOf(userProject.getEnddate()));
			userProjectBean.setGoal(userProject.getGoal());
			userProjectBean.setHate(userProject.getHate());
			userProjectBean.setLiked(userProject.getLiked());
			userProjectBean.setShortdesc(userProject.getShortdesc());
			userProjectBean.setCount(userProject.getCount());
			System.out.println(userProject.getCount());
			response.add(userProjectBean);
		}
		return response;
	}
	
	@Transactional
	public List<UserProjectBean> getAllProjectCategory( String data) {
		// TODO Auto-generated method stub
		System.out.println("Getting the service method");
		Date today = new Date();
		
		List<UserProject> userProjects = userDAO.getAllProjectsCategory(data,today);
		
		List<UserProjectBean> response = new ArrayList<>();
		for(UserProject userProject:userProjects){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.setTitle(userProject.getTitle());
			userProjectBean.setDescription(userProject.getDescription());
			userProjectBean.setShortdesc(userProject.getShortdesc());
			userProjectBean.setAmount1(userProject.getAmount1());
			userProjectBean.setAmount2(userProject.getAmount2());
			userProjectBean.setAmount3(userProject.getAmount3());
			userProjectBean.setCategory(userProject.getCategory());
			userProjectBean.setEndDate(String.valueOf(userProject.getEnddate()));
			userProjectBean.setGoal(userProject.getGoal());
			userProjectBean.setService1(userProject.getService1());
			userProjectBean.setService2(userProject.getService2());
			userProjectBean.setService3(userProject.getService3());
			userProjectBean.setProjectID(userProject.getProjectid());
			userProjectBean.setLiked(userProject.getLiked());
			userProjectBean.setHate(userProject.getHate());
			userProjectBean.setCount(userProject.getCount());
			
			
			
			response.add(userProjectBean);
		}
		return response;
	}
	
	public UserProject likeProject(Integer projectID,UserProjectBean upb){
		
		int like = upb.getLiked();
		like++;
		
		UserProject userProject = userDAO.validateProject(projectID);
		
		userProject.setLiked(like);
		
	  UserProject userP1 = userDAO.updateLike(userProject);		
		return userP1;
		
	}
	
public UserProject dislikeProject(Integer projectID,UserProjectBean upb){
		
		int dislike = upb.getHate();
		dislike++;
		
		UserProject userProject = userDAO.validateProject(projectID);
		
		userProject.setHate(dislike);
		
	  UserProject userP1 = userDAO.updateLike(userProject);		
		return userP1;
		
	}



public boolean cartProject(Integer integer) {
	// TODO Auto-generated method stub
	UserProject userProject = userDAO.validateProject(integer);
	
	userProject.setCart("yes");
	
	boolean p1 = userDAO.updateProject(userProject);
	
	return true;
}



public List<UserProjectBean> getAllProjectCartService() {
	// TODO Auto-generated method stub
	System.out.println("Getting the service method");
	
	
	List<UserProject> userProjects = userDAO.getAllProjectsCartService();
	
	List<UserProjectBean> response = new ArrayList<>();
	for(UserProject userProject:userProjects){
		UserProjectBean userProjectBean = new UserProjectBean();
		userProjectBean.setTitle(userProject.getTitle());
		userProjectBean.setDescription(userProject.getDescription());
		userProjectBean.setShortdesc(userProject.getShortdesc());
		userProjectBean.setAmount1(userProject.getAmount1());
		userProjectBean.setAmount2(userProject.getAmount2());
		userProjectBean.setAmount3(userProject.getAmount3());
		userProjectBean.setCategory(userProject.getCategory());
		userProjectBean.setEndDate(String.valueOf(userProject.getEnddate()));
		userProjectBean.setGoal(userProject.getGoal());
		userProjectBean.setService1(userProject.getService1());
		userProjectBean.setService2(userProject.getService2());
		userProjectBean.setService3(userProject.getService3());
		userProjectBean.setProjectID(userProject.getProjectid());
		userProjectBean.setLiked(userProject.getLiked());
		userProjectBean.setHate(userProject.getHate());
		userProjectBean.setCount(userProject.getCount());
		
		
		
		response.add(userProjectBean);
	}
	return response;
}




	
	

}
