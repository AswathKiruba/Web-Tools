package neu.edu.service;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.bean.UserAccountBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.bean.UserSessionInfo;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	
	public UserAccountBean validateUser(String username,String password){
		System.out.println(" validating auth Service is called Called");
		
		//UserAccount user1 = userDao.validateUserName(username);
		
		UserAccount user = null;
		String enc = null;
		
		try 
        {
            
			 String text = password;
	            String key = "Bar12345Bar12345"; // 128 bit key
	            // Create key and cipher
	            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	            Cipher cipher = Cipher.getInstance("AES");
	            // encrypt the text
	            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	            byte[] encrypted = cipher.doFinal(text.getBytes());

	            StringBuilder sb = new StringBuilder();
	            for (byte b: encrypted) {
	                sb.append((char)b);
	            }

	            // the encrypted String
	             enc = sb.toString();
	            System.out.println("encrypted:" + enc);

	            
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		
		 user = userDao.validateUser(username, enc);
		

		if (user == null) {
			System.out.println("User Not Found");
			return null;
		} else {
			UserAccountBean bean = new UserAccountBean();
			bean.setId(user.getId());
			bean.setFirstName(user.getFirstname());
			bean.setRole(user.getRole());
			System.out.println("User  Found");
			return bean;
		}
	}

	public UserSessionInfo fetchUserAccountDetails(Integer userId) {
		// TODO Auto-generated method stub
		UserSessionInfo userSessionInfo=null;
		
		UserAccount userAccount = userDao.fetchUserAccount(userId);
		if(userAccount!=null){
			userSessionInfo = new UserSessionInfo(userAccount.getId());
			userSessionInfo.setName(userAccount.getFirstname());
			userSessionInfo.setRole(userAccount.getRole());
		}
		
		//System.out.println(userAccount.getName());
		for(UserProject userProject:userAccount.getUserProjects()){
			UserProjectBean userProjectBean = new UserProjectBean(userProject.getTitle(), userProject.getDescription());
			userSessionInfo.getUserProjectBeans().add(userProjectBean);
			System.out.println(userProject.getTitle());
		}
		return userSessionInfo;
	}

	

	
	

}
