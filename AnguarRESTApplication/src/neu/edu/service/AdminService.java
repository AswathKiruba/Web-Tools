package neu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.PaymentInfoBean;
import neu.edu.bean.StatBean;
import neu.edu.bean.UserProjectBean;

import neu.edu.dao.UserDAO;
import neu.edu.entity.Category;
import neu.edu.entity.PaymentInfo;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;

@Service
public class AdminService {
	
	@Autowired
	private UserDAO userDAO;

	public List<UserProjectBean> getAllProject() {
		// TODO Auto-generated method stub
		System.out.println("Getting the service method");
		
		
		List<UserProject> userProjects = userDAO.getAllProjectsAdmin();
		
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
	
	public UserProjectBean disableProject(Integer integer) {
		// TODO Auto-generated method stub
		UserProject proj = userDAO.validateProject(integer);
		
		proj.setStatus("disable");
		
		userDAO.updateProject(proj);
		UserProjectBean bean = new UserProjectBean();
		bean.setTitle(proj.getTitle());
	
		return bean;
	}

	public List<PaymentInfoBean> getAllCity() {
		// TODO Auto-generated method stub
		List<PaymentInfo> paymentServices = userDAO.getallCity();
		
		
		List<PaymentInfoBean> beans = new ArrayList<>();
		for(PaymentInfo pay : paymentServices){
			PaymentInfoBean bean = new PaymentInfoBean();
			bean.setAmount(pay.getAmount());
			bean.setBaddress(pay.getBaddress());
			bean.setCardno(pay.getCardno());
			bean.setCity(pay.getCity());
			bean.setCvv(pay.getCvv());
			beans.add(bean);
			
		}
		
		return beans;
	}

	public StatBean getStat() {
		// TODO Auto-generated method stub
		List<PaymentInfo> payment = userDAO.getallCity();
		List<UserAccount> user = userDAO.getUsers();
		List<UserProject> proj = userDAO.getAllProjectsAdmin();
		List<Category> cat = userDAO.getAllCategories();
		
		StatBean statBean = new StatBean();
		
		statBean.setCategory(cat.size());
		statBean.setPayment(payment.size());
		statBean.setProjects(proj.size());
		statBean.setUsers(user.size());
		
		
		return statBean;
	}

}
