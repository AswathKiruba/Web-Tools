package neu.edu.entity;
// Generated Apr 27, 2017 1:00:33 AM by Hibernate Tools 5.2.1.Final

/**
 * PaymentInfo generated by hbm2java
 */
public class PaymentInfo implements java.io.Serializable {

	private Integer paymentid;
	private UserAccount userAccount;
	private UserProject userProject;
	private String cardno;
	private Integer cvv;
	private String baddress;
	private String city;
	private Integer amount;

	public PaymentInfo() {
	}

	public PaymentInfo(UserAccount userAccount, UserProject userProject, String cardno, Integer cvv, String baddress,
			String city, Integer amount) {
		this.userAccount = userAccount;
		this.userProject = userProject;
		this.cardno = cardno;
		this.cvv = cvv;
		this.baddress = baddress;
		this.city = city;
		this.amount = amount;
	}

	public Integer getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserProject getUserProject() {
		return this.userProject;
	}

	public void setUserProject(UserProject userProject) {
		this.userProject = userProject;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Integer getCvv() {
		return this.cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getBaddress() {
		return this.baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}