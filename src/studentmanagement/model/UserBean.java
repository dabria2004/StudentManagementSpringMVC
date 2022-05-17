package studentmanagement.model;

import javax.validation.constraints.NotEmpty;

public class UserBean {
	@NotEmpty
	private String user_id;
	@NotEmpty
	private String user_name;
	@NotEmpty
	private String user_password;
	@NotEmpty
	private String con_password;
	
	public String getCon_password() {
		return con_password;
	}
	public void setCon_password(String con_password) {
		this.con_password = con_password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
}
