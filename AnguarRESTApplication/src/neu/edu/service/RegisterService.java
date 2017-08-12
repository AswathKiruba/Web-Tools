package neu.edu.service;

import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.UserAccountBean;
import neu.edu.bean.UserRegistrationBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;

@Service
public class RegisterService {

	@Autowired
	private UserDAO userDao;

	public UserAccount createUser(UserAccountBean userAccountBean) {

		UserAccount userAccount = new UserAccount();
		//userAccount.setName(userRegistrationBean.getName());
		System.out.println(userAccountBean.getFirstName());
		userAccount.setUserName(userAccountBean.getUsername());
		//userAccount.setPassword(userAccountBean.getPassword());
		
		
		try 
        {
            String text = userAccountBean.getPassword() ;
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
            String enc = sb.toString();
            System.out.println("encrypted:" + enc);
            userAccount.setPassword(enc);

            
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }

		
		
		
		userAccount.setFirstname(userAccountBean.getFirstName());
		userAccount.setLastname(userAccountBean.getLastName());
		userAccount.setRole(userAccountBean.getRole());
		userAccount.setEmail(userAccountBean.getEmail());
		
	
		userAccount = userDao.createUser(userAccount);

		return userAccount;
	}

}
