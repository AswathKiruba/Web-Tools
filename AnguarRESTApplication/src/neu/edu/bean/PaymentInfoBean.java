package neu.edu.bean;

public class PaymentInfoBean  {

	
	private String cardno;
	private Integer cvv;
	private String baddress;
	private String city;
	private Integer amount;

	public PaymentInfoBean() {
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
