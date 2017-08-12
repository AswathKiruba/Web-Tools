package neu.edu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.CustomBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.bean.UserRegistrationBean;
import neu.edu.controller.output.OutputUserProject;
import neu.edu.entity.Category;
import neu.edu.entity.PaymentInfo;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserComment;
import neu.edu.entity.UserMessage;
import neu.edu.entity.UserProject;
import neu.edu.service.PaymentService;



@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public UserAccount validateUser(String username, String password) {
		System.out.println("val dao called");
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserAccount where userName=:username and password=:password ");
		query.setString("username", username);
		query.setString("password", password);

		UserAccount user = (UserAccount) query.uniqueResult();

		return user;

	}
	
	@Transactional
	public UserAccount createUser(UserAccount userAccount) {
		Session session = sessionFactory.openSession();
		session.save(userAccount);
		return userAccount;
	}

	public UserAccount fetchUserAccount(Integer userId) {
		Session session = sessionFactory.openSession();
		return session.load(UserAccount.class, userId);
	}

	@Transactional
	public UserProject addProject(UserProject userProject) {
		
		// TODO Auto-generated method stub
		System.out.println("im am here");
		Session session = sessionFactory.openSession();
		session.save(userProject);
		session.flush();
		return userProject;
	}

	@Transactional
	public boolean updateProject(UserProject userProject) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
	session.saveOrUpdate(userProject);
		session.flush();

		return true;
	}
	
	public UserAccount getForiegn(Integer userID){
		Session session = sessionFactory.openSession();
		UserAccount userAccount = session.get(UserAccount.class, userID);
	return userAccount;
	}
	
	@Transactional
	public List<UserProject> getAllProjects(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject where userid = :userid")
			    .setParameter("userid", userId)
			    
			    .list();

	}
	
	@Transactional
	public List<UserProject> getAllProjectsCategory(String name,Date date) {
		// TODO Auto-generated method stub
		System.out.println("getting to project dao");
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject where category = :name and status = :status and enddate>= :date ")
			    .setString("name", name)
			    .setDate("date", date)
			    .setString("status", "active")
			    .list();

	}
	
	@Transactional
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Category")
			    .list();

	}
	
	@Transactional
	public List<Category> getAllCategoriesCheck() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Category where status =:name")
				.setString("name", "active")
			    .list();

	}
	
	@Transactional
	public Category updateCategory(Category cat) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(cat);
		session.flush();

		return cat;
	}
	
	@Transactional
	public Boolean deleteCategory(Category cat) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.delete(cat);
		session.flush();

		return true;
	}
	
	
	public List<Category> checkAllCategories(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Category where categoryname =:name")
				.setString("name", name)
			    .list();

	}
	
	public Category validate(String name) {
		// TODO Auto-generated method stub
		System.out.println("validate called");
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(" from Category where categoryname =:name");
				query.setString("name", name);
				Category cat1 = (Category) query.uniqueResult();	
				return cat1;
//		return (Category) session.createQuery(" from Category where categoryname =:name")
//				.setString("name", name)
//			    .uniqueResult();

	}
	
	@Transactional
	public Category addCategory(Category category) {
		Session session = sessionFactory.openSession();
		session.save(category);
		return category;
	}
	
	
	
	
	public UserAccount validateUser(Integer id){
		System.out.println("Gone into validate");
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserAccount where id=:id ");
		query.setInteger("id", id);
		UserAccount user3 = (UserAccount)query.uniqueResult();
		session.close();
		return user3;
	}
	
	//validate projectID
	public UserProject validateProject(Integer projectID){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserProject where projectid=:projectID ");
		query.setInteger("projectID", projectID);
		UserProject user3 = (UserProject)query.uniqueResult();
		session.close();
		
		return user3;
		
	}
	
	//updatelike
	@Transactional
	public UserProject updateLike(UserProject pro) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(pro);
		session.flush();
		session.close();

		return pro;
	}

	public List<UserComment> getAllComments(int projectId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserComment where projectid=:projectId")
				.setInteger("projectId", projectId)
			    .list();
	}

	@Transactional
	public UserComment addComments(UserComment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.save(comment);
		
		return comment;
	}

	@Transactional
	public Boolean addAmount(PaymentInfo payment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.save(payment);
		session.flush();
		session.close();
		
		return true;
	}

	public Boolean updateProjectAmount(UserProject proj) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(proj);
		session.flush();
		session.close();
		
		return true;
	}

	public List<UserProject> geteveryProjects(Date date) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject where status = :status and enddate>= :date")
			    .setString("status", "active")
			    .setDate("date", date)
			    .list();
	}

	public List<UserProject> getAllProjectsAdmin() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject")
			 
			    .list();
		
	}

	public UserAccount validateUserName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return (UserAccount) session.createQuery(" from UserAccount where userName = :username ")
				.setString("username", username)
			 .uniqueResult();
		
	}

	public List<UserProject> getAllProjectsCartService() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject where cart = :cart")
				.setString("cart", "yes")
			    .list();
	}

	public List<UserProject> getalltProjects() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserProject")
				
			    .list();
	}

	public List<UserAccount> getAllUsers(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserAccount")
				
			    .list();
		
		
	}

	public List<UserMessage> getallReceived(Integer integer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserMessage where recieveid =:integer")
				.setInteger("integer", integer)
			    .list();
	}

	public List<UserMessage> getallSent(Integer integer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserMessage where sentid =:integer")
				.setInteger("integer", integer)
			    .list();
	}

	public Boolean addsent(UserMessage um) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.save(um);
		session.flush();
		session.close();
		
		return true;
	}

	public List<PaymentInfo> getallCity() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from PaymentInfo")
				.list();
	}

	public List<UserAccount> getUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		return session.createQuery(" from UserAccount")
				.list();
	}

	


}
