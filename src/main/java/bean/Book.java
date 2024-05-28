package bean;

/**
 * 書籍情報（ISBN、タイトル、価格）を一つのオブジェクトとしてまとめるためのDTOクラス
 *
 * @author KandaITSchool
 *
 */

public class Book {

	/**
	 * 書籍のISBN
	 */
	private String isbn;
	/**
	 * 書籍のタイトル
	 */
	private String title;
	/**
	 * 書籍の価格
	 */
	private int price;

	/**
	 * コンストラクタ<br>
	 * 書籍情報（ISBN、タイトル、価格）の初期設定をおこなう
	 */
	public Book() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
	}

	/**
	 * 書籍のISBNを取得する
	 *
	 * @return 書籍のISBN
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * 書籍のISBNを設定する
	 *
	 * @param isbn 設定する書籍のISBN
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * 書籍のタイトルを取得する
	 *
	 * @return 書籍のタイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 書籍のタイトルを設定する
	 *
	 * @param title 設定する書籍のタイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 書籍の価格を取得する
	 *
	 * @return 書籍の価格
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 書籍の価格を設定する
	 *
	 * @param price 設定する書籍の価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}


}
