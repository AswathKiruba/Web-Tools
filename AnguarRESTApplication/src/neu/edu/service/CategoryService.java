package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.CategoryBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.output.CategoryBeanOutput;
import neu.edu.controller.output.ResponseCheck;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Category;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;

@Service
public class CategoryService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public Category addCategory(String categoryName){
		//return userDAO.addProject(categoryBean, userId);
		
		System.out.println("It is getting into insert page");
		Category category = new Category();
		
		//UserAccount ua = userDAO.validateUser(userId);
		category.setCategoryname(categoryName);
		category.setStatus("active");
		System.out.println("categoryName");
		//category.setUserAccount(ua);
		
		Category cat = userDAO.addCategory(category);
		System.out.println("returned to add category");
		return cat;
	}
	

	@Transactional
	public List<CategoryBean> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = userDAO.getAllCategories();
		
		List<CategoryBean> response = new ArrayList<>();
		for(Category category:categories){
			CategoryBean categoryBean = new CategoryBean();
			categoryBean.setCategoryName(category.getCategoryname());
			response.add(categoryBean);
		}
		return response;
	}
	
	@Transactional
	public List<CategoryBean> getAllCategoriesCheck() {
		// TODO Auto-generated method stub
		List<Category> categories = userDAO.getAllCategoriesCheck();
		
		List<CategoryBean> response = new ArrayList<>();
		for(Category category:categories){
			CategoryBean categoryBean = new CategoryBean();
			categoryBean.setCategoryName(category.getCategoryname());
			response.add(categoryBean);
		}
		return response;
	}
	
	public List<CategoryBeanOutput> checkAllCategories(String name){
		System.out.println("It is getting into category check page");
		
		List<Category> categories = userDAO.checkAllCategories(name);
		
		List<CategoryBeanOutput> response1 = new ArrayList<>();
		for(Category category:categories){
			CategoryBeanOutput categoryBean = new CategoryBeanOutput();
			categoryBean.setCategoryname(category.getCategoryname());
			response1.add(categoryBean);
		}
		return response1;
		

}
	
	public CategoryBeanOutput disableCategory(String name){
		System.out.println("It is getting into category disable service page");
		
		Category cat = userDAO.validate(name);
		System.out.println("Validate is done successfully");
		
		cat.setStatus("disable");
		Category at1 = userDAO.updateCategory(cat);
		
		CategoryBeanOutput b1 =  new CategoryBeanOutput();
		b1.setCategoryname(at1.getCategoryname());
		
		return b1;
		
		
		
		
	}
	public CategoryBeanOutput activateCategory(String name){
		System.out.println("It is getting into category disable service page");
		
		Category cat = userDAO.validate(name);
		System.out.println("Validate is done successfully");
		
		cat.setStatus("active");
		Category at1 = userDAO.updateCategory(cat);
		
		CategoryBeanOutput b1 =  new CategoryBeanOutput();
		b1.setCategoryname(at1.getCategoryname());
		
		return b1;
		
		
		
		
	}
	
	public Boolean deleteCategory(String name){
		System.out.println("It is getting into category delete service page");
		
		Category cat = userDAO.validate(name);
		System.out.println("Validate is done successfully");
		
		
		Boolean at1 = userDAO.deleteCategory(cat);
		
		
		
		return at1;
		
		
		
		
	}
	
}
