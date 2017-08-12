package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.CustomBean;
import neu.edu.bean.ReceivedMessageBean;
import neu.edu.bean.ReceivedMessageBean1;
import neu.edu.bean.UserBean;

import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserMessage;

@Service
public class ChatService {
	
	@Autowired
	private UserDAO userDAO;

	public List<UserBean> getAllUser(Integer userId) {
		// TODO Auto-generated method stub
		List<UserAccount> users = userDAO.getAllUsers(userId);
		
		List<UserBean> response = new ArrayList<>();
		
		for(UserAccount user : users){
			UserBean userBean = new UserBean();
			userBean.setUsername(user.getFirstname());
			userBean.setId(user.getId());
			response.add(userBean);
			
		}
		return response;
	
	}

	public List<ReceivedMessageBean> getAllUserReceived(Integer integer) {
		// TODO Auto-generated method stub
		List<UserMessage> usermessages = userDAO.getallReceived(integer);
		
		List<ReceivedMessageBean> response = new ArrayList<>();
		
		for(UserMessage um : usermessages){
			ReceivedMessageBean bean = new ReceivedMessageBean();
			bean.setMessageid(um.getMessageid());
			bean.setMessage(um.getMessage());
			bean.setReceivername(um.getReceivername());
			bean.setRecieveid(um.getRecieveid());
			bean.setSentid(um.getSentid());
			bean.setSentname(um.getSentname());
			response.add(bean);
			
		}
		return response;
		
		
	}

	public List<ReceivedMessageBean1> getAllUserSent(Integer integer) {
		// TODO Auto-generated method stub
		List<UserMessage> usermessages = userDAO.getallSent(integer);
		
		List<ReceivedMessageBean1> response = new ArrayList<>();
		
		for(UserMessage um : usermessages){
			ReceivedMessageBean1 bean = new ReceivedMessageBean1();
			bean.setMessageid(um.getMessageid());
			bean.setMessage(um.getMessage());
			bean.setReceivername(um.getReceivername());
			bean.setRecieveid(um.getRecieveid());
			bean.setSentid(um.getSentid());
			bean.setSentname(um.getSentname());
			response.add(bean);
			
		}
		return response;
	}

	public Boolean addSent(Integer integer, String name, String message, CustomBean customBean) {
		// TODO Auto-generated method stub
		//Boolean b = userDAO.addsent(integer,name,message,customBean);
		UserMessage um = new UserMessage();
		um.setMessage(message);
		um.setReceivername(customBean.getUsername());
		um.setRecieveid(customBean.getId());
		um.setSentid(integer);
		um.setSentname(name);
		Boolean b = userDAO.addsent(um);
		return true;
	}

}
