package bean;

//ユーザー情報のDTOクラス

public class Order {
	
	private int orderno; //注文No
	private String userid; //ユーザID
	private String isbn; //ISBN
	private int quantity; //数量
	private String date; //購入日付


//コンストラクタ(初期設定をおこなう------------------
	public Order() {
		this.orderno = 0;
		this.userid = null;
		this.isbn = null;
		this.quantity = 0;
		this.date = null;

	}

//GETメソッド----------------------------------------

	public int getOrderno() {
		return orderno;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getDate() {
		return date;
	}
	
//SETメソッド------------------------------------------

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
