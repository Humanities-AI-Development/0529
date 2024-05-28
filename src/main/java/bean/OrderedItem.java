package bean;

/**
 * 書籍情報（ISBN、タイトル、価格）を一つのオブジェクトとしてまとめるためのDTOクラス
 *
 * @author KandaITSchool
 *
 */

public class OrderedItem {

	private String userid; //ユーザID
	private String title; //書籍のタイトル
	private String date; //購入日付


	public OrderedItem() {
		this.userid = null;
		this.title = null;
		this.date = null;
	}

	//GETメソッド----------------------------------------
		public String getUserid() {
			return userid;
		}
		
		public String getTitle() {
			return title;
		}
		
		public String getDate() {
			return date;
		}

		//SETメソッド------------------------------------------	
		
		public void setUserid(String userid) {
			this.userid = userid;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public void setDate(String date) {
			this.date = date;
		}
}
