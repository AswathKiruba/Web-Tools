package neu.edu.bean;


public class ReceivedMessageBean {

	private Integer messageid;
	private Integer sentid;
	private String sentname;
	private Integer recieveid;
	private String receivername;
	private String message;

	public ReceivedMessageBean() {
	}

	
	public Integer getMessageid() {
		return this.messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

	public Integer getSentid() {
		return this.sentid;
	}

	public void setSentid(Integer sentid) {
		this.sentid = sentid;
	}

	public String getSentname() {
		return this.sentname;
	}

	public void setSentname(String sentname) {
		this.sentname = sentname;
	}

	public Integer getRecieveid() {
		return this.recieveid;
	}

	public void setRecieveid(Integer recieveid) {
		this.recieveid = recieveid;
	}

	public String getReceivername() {
		return this.receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
