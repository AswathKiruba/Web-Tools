package neu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.PaymentInfoBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.PaymentInfo;
import neu.edu.entity.UserAccount;
import neu.edu.entity.UserProject;

@Service
public class PaymentService {
	
	@Autowired
	private UserDAO userDAO;

	public Boolean addAmount(Integer pid, Integer uid, PaymentInfoBean pay) {
		// TODO Auto-generated method stub
		PaymentInfo payment = new PaymentInfo();
		payment.setAmount(pay.getAmount());
		payment.setBaddress(pay.getBaddress());
		payment.setCardno(pay.getCardno());
		payment.setCity(pay.getCity());
		payment.setCvv(pay.getCvv());
		
		UserProject proj = userDAO.validateProject(pid);
		payment.setUserProject(proj);
		
		UserAccount user = userDAO.validateUser(uid);
		payment.setUserAccount(user);
		
		Boolean bo = userDAO.addAmount(payment);
		
		int a=0;
		int b=0;
		
		a=proj.getCount();
		b=pay.getAmount();
		
		int c=0;
		c=a+b;
		
		proj.setCount(c);
		proj.setCart("no");
		
		Boolean b1 = userDAO.updateProjectAmount(proj);
		
		return true;
	}

}
