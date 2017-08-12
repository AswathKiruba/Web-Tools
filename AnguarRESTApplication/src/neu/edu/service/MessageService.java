package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.CategoryBean;
import neu.edu.bean.CommentBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Category;

import neu.edu.entity.UserComment;
import neu.edu.entity.UserProject;

@Service
public class MessageService {
	
	@Autowired
	private UserDAO userDAO;
	

	public List<CommentBean> getAllComments(int projectId) {
		// TODO Auto-generated method stub
		
		//UserProject project = userDAO.validateProject(projectId);
		
		List<UserComment> comments = userDAO.getAllComments(projectId);
		
		List<CommentBean> response = new ArrayList<>();
		for(UserComment comment:comments){
			CommentBean commentBean = new CommentBean();
			commentBean.setMessage(comment.getMessage());
			commentBean.setUsername(comment.getUsername());
			response.add(commentBean);
		}
		return response;
		
		
	}


	public Boolean addComments(int id,CommentBean comment) {
		// TODO Auto-generated method stub
		UserComment usercomment = new UserComment();
		usercomment.setUsername(comment.getUsername());
		usercomment.setMessage(comment.getMessage());
	  UserProject userp = userDAO.validateProject(id);
	  usercomment.setUserProject(userp);
	  
	  userDAO.addComments(usercomment);
	  
	  
		return true;
	}

}
