package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;


public class UserDAO {

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

//ユーザデータを取得するメソッド（selectByUser）----------------------------------------------------
	public User selectByUser(String userid,String password){

		Connection con = null;
		Statement smt = null;

		//ユーザ情報を格納するためのフィールド準備
		User user = new User();

		//引数useridと同じユーザデータを取得
		String sql = "SELECT * FROM userinfo WHERE user ='"+userid+"'";
		try{
			con = getConnection();//DB接続
			smt = con.createStatement();//SQLを使えるようにしてる
			ResultSet rs = smt.executeQuery(sql);//SQL実行

			if(rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("passowrd"));
				user.setEmail(rs.getString("email"));
				user.setAuthority("authority");
			}


		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}
			if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}return user;//ユーザデータが返される

	}

}

