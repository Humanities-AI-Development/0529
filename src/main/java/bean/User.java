package bean;

//ユーザー情報のDTOクラス

public class User {
	
	private String userid; 
	private String password;
	private String email;
	private String authority;

//コンストラクタ(初期設定をおこなう------------------
	public User() {
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}

//GETメソッド----------------------------------------
	public String getUserid() {
		return userid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAuthority() {
		return authority;
	}
//SETメソッド------------------------------------------

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}


	
}
