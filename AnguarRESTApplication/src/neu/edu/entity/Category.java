package neu.edu.entity;
// Generated Apr 27, 2017 1:00:33 AM by Hibernate Tools 5.2.1.Final

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Integer categoryid;
	private String categoryname;
	private String status;

	public Category() {
	}

	public Category(String categoryname, String status) {
		this.categoryname = categoryname;
		this.status = status;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
