package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;


public class OrderDAO {

	/**
	 * JDBCドライバ内部のDriverクラスパス
	 */
	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	/**
	 * 接続するMySQLデータベースパス
	 */
	private static final String URL = "jdbc:mariadb://localhost/mybookdb";
	/**
	 * データベースのユーザー名
	 */
	private static final String USER = "root";
	/**
	 * データベースのパスワード
	 */
	private static final String PASSWD = "NEW_PASSWORD";

	/**
	 * フィールド変数の情報を基に、DB接続をおこなうメソッド
	 *
	 * @return データベース接続情報
	 * @throws IllegalStateException メソッド内部で例外が発生した場合
	 */
	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(RDB_DRIVE);//ドライバ読み込み
			con = DriverManager.getConnection(URL, USER, PASSWD);//データベース接続を確立
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//買い物リストに追加するメソッド（insert）----------------------------------------------------
	public void insert(Order order){
		Connection con = null;
		Statement smt = null;


		//リストに追加するSQL文
		//＊カラムのordernoはオートインクリメント属性のため、「NULL」を設定することで自動で番号が割り振られる。
		//*カラムのdateはSQL関数の「CURDATE()」を利用することで、このSQL文を発行した時の日付を自動で取得できる。
		String sql = sql = "INSERT INTO orderinfo VALUES(NULL,'"+ order.getUserid() + "','"+ order.getIsbn() +"',"
				+ order.getQuantity() +",CURDATE())";     

		try{
			con = getConnection();//DB接続
			smt = con.createStatement();//SQLを使えるようにしてる
			smt.executeUpdate(sql);//SQL実行


		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}
			if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
	}

}

