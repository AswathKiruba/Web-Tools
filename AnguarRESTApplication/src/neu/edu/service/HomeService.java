package neu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.UserProjectBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserProject;

@Service
public class HomeService {
	
	@Autowired
	private UserDAO userDAO;

	public List<UserProjectBean> getAllProject() {
		// TODO Auto-generated method stub
		
		Date today = new Date();
List<UserProject> userProjects = userDAO.geteveryProjects(today);
		
		
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

	public List<UserProjectBean> getAllProjects() {
		// TODO Auto-generated method stub
		
		List<UserProject> userProjects = userDAO.getalltProjects();
				
				
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
