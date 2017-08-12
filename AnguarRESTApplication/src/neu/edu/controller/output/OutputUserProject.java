package neu.edu.controller.output;
// Generated Apr 21, 2017 7:12:53 PM by Hibernate Tools 5.2.1.Final

import java.util.Date;

import neu.edu.entity.UserAccount;

/**
 * UserProject generated by hbm2java
 */
public class OutputUserProject implements java.io.Serializable {

	private Integer projectid;
	private UserAccount userAccount;
	private String title;
	private String description;
	private Date enddate;
	private String category;
	private String status;
	private Float goal;
	private Integer count;

	public OutputUserProject() {
	}

	public OutputUserProject(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public OutputUserProject(UserAccount userAccount, String title, String description, Date enddate, String category,
			String status, Float goal, Integer count) {
		this.userAccount = userAccount;
		this.title = title;
		this.description = description;
		this.enddate = enddate;
		this.category = category;
		this.status = status;
		this.goal = goal;
		this.count = count;
	}

	public Integer getProjectid() {
		return this.projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getGoal() {
		return this.goal;
	}

	public void setGoal(Float goal) {
		this.goal = goal;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}