package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderedItem;


/**
 * 書籍管理システムDB版で使用するデータベース関連の処理をまとめたクラス
 *
 * @author KandaITSchool
 *
 */
public class OrderedItemDAO {

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

        /**
         * DBの書籍情報を格納するbookinfoテーブルから全書籍情報を取得するメソッド
         *
         * @return 全書籍情報のリスト
         * @throws IllegalStateException メソッド内部で例外が発生した場合
         */
        public ArrayList<OrderedItem> selectAll() {//全件検索
                Connection con = null;
                Statement smt = null;
                ArrayList<OrderedItem> OrderedItemList = new ArrayList<OrderedItem>();

                try {
                        con = getConnection();//DB接続
                        smt = con.createStatement();//SQLを使えるようにしてる
//2つのテーブルの共通カラムであるisbnを条件にテーブル結合することで、本のタイトルを取得することができる。
                        String sql = sql = "SELECT o.user,b.title,o.date FROM bookinfo b INNER JOIN orderinfo o ON b.isbn=o.isbn";
                        ResultSet rs = smt.executeQuery(sql);

                        while (rs.next()) {
                        	OrderedItem list = new OrderedItem();
                        	list.setUserid(rs.getString("userid"));
                        	list.setTitle(rs.getString("title"));
                        	list.setDate(rs.getString("date"));
                        	OrderedItemList.add(list);//２つのテーブル結合データを格納
                        }

                } catch (Exception e) {
                        throw new IllegalStateException(e);
                } finally {
                        if (smt != null) {
                                try {
                                        smt.close();
                                } catch (SQLException ignore) {
                                }
                        }
                        if (con != null) {
                                try {
                                        con.close();
                                } catch (SQLException ignore) {
                                }
                        }
                }
                return OrderedItemList;//２つのテーブル結合データを格納
        }
               
               
}