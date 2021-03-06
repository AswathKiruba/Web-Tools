package neu.edu.entity;
// Generated Apr 27, 2017 1:00:33 AM by Hibernate Tools 5.2.1.Final

/**
 * UserMessage generated by hbm2java
 */
public class UserMessage implements java.io.Serializable {

	private Integer messageid;
	private Integer sentid;
	private String sentname;
	private Integer recieveid;
	private String receivername;
	private String message;

	public UserMessage() {
	}

	public UserMessage(Integer sentid, String sentname, Integer recieveid, String receivername, String message) {
		this.sentid = sentid;
		this.sentname = sentname;
		this.recieveid = recieveid;
		this.receivername = receivername;
		this.message = message;
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
