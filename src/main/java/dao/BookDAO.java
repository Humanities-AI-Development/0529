package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Book;


/**
 * 書籍管理システムDB版で使用するデータベース関連の処理をまとめたクラス
 *
 * @author KandaITSchool
 *
 */
public class BookDAO {

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
        public ArrayList<Book> selectAll() {//全件検索
                Connection con = null;
                Statement smt = null;
                ArrayList<Book> bookList = new ArrayList<Book>();

                try {
                        con = getConnection();//DB接続
                        smt = con.createStatement();//SQLを使えるようにしてる

                        String sql = "SELECT * FROM bookinfo ORDER BY isbn";
                        ResultSet rs = smt.executeQuery(sql);

                        while (rs.next()) {
                                Book book = new Book();
                                book.setIsbn(rs.getString("isbn"));
                                book.setTitle(rs.getString("title"));
                                book.setPrice(rs.getInt("price"));
                                bookList.add(book);
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
                return bookList;
        }

        //DB登録メソッド----------------------------------------------------
        public int insert(Book book){

                Connection con = null;
                Statement smt = null;

                int count = 0;

                //登録するためのSQL文
                String sql = "insert into bookinfo values('"
                                +book.getIsbn()+"','"
                                +book.getTitle()+"','"
                                +book.getPrice()+"')";          
                try{
                        con = getConnection();//DB接続
                        smt = con.createStatement();//SQLを使えるようにしてる
                        count = smt.executeUpdate(sql);//SQL実行


                }catch(Exception e){
                        throw new IllegalStateException(e);
                }finally{
                        if( smt != null ){
                                try{smt.close();}catch(SQLException ignore){}
                        }
                        if( con != null ){
                                try{con.close();}catch(SQLException ignore){}
                        }
                }return count;
        }
       
       
        //詳細画面表示----------------------------------------------------
        public Book selectByIsbn(String isbn){
                
                  Connection con = null;
                  Statement smt = null;
                
                  Book book = new Book();
                  String sql = "select * from bookinfo where isbn='"+isbn+"'";
                
                  try{
                          con = getConnection();//DB接続
                          smt = con.createStatement();//SQLを使えるようにしてる
                          ResultSet rs = smt.executeQuery(sql);//SQL実行
                         
                          if(rs.next()) {
                                  book.setIsbn(rs.getString("isbn"));
                                  book.setTitle(rs.getString("title"));
                                  book.setPrice(Integer.parseInt(rs.getString("price")));
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
                  }
                  return book;//1件のデータが格納されたBook型インスタンス
                }
        //削除処理----------------------------------------------------

                public void delete(String isbn){               
                          Connection con = null;
                          Statement smt = null;
                          Book book = new Book();
                          String sql = "delete from bookinfo where isbn='"+isbn+"'";//削除SQL
                          int count = 0;
                        
                          try{
                                  con = getConnection();//DB接続
                                  smt = con.createStatement();//SQLを使えるようにしてる
                                  count = smt.executeUpdate(sql);//SQL実行
                                 
                        
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
               
                //更新処理----------------------------------------------------

                public void update(Book book){         
                          Connection con = null;
                          Statement smt = null;
                         
                          String sql = "UPDATE bookinfo SET title='"+book.getTitle()+"',price="+book.getPrice()+" WHERE isbn='"+book.getIsbn()+"'";
                         
                          int count = 0;
                        
                          try{
                                  con = getConnection();//DB接続
                                  smt = con.createStatement();//SQLを使えるようにしてる
                                  count = smt.executeUpdate(sql);//SQL実行
                                 
                        
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
               
                //フォームから検索-----------------------------------------------------------
               
               
                public ArrayList <Book> search(String isbn,String title, String price){
                        
                          Connection con = null;
                          Statement smt = null;
                        
//                              ①検索した書籍情報を格納するArrayListオブジェクトを生成します。
                          ArrayList<Book> bookList = new ArrayList<Book>();
                         
//                              ②引数の情報を利用し、検索用のSQL文を文字列として定義します。※SQL文は設計書参
                          String sql = "SELECT isbn,title,price FROM bookinfo " +
                                          "WHERE isbn LIKE '%" + isbn + "%' AND title LIKE '%" + title + "%' AND price LIKE '%" + price + "%'";
                         
                         
                          try{
//                                      ③BookDAOクラスに定義したgetConnection()メソッドを利用して、Connectionオブジェクトを生成します。
                              con=getConnection();
//                                      ④ConnectionオブジェクトのcreateStatement（）メソッドを利用して、Statementオブジェクトを生成します。
                              smt=con.createStatement();
                             
//                                      ⑤Statementオブジェクトの、executeQuery（）メソッドを利用して、②のSQL文を発行し結果セットを取得します。
                              ResultSet rs = smt.executeQuery(sql);
                             
//                                      ⑥結果セットから書籍データを検索件数分全て取り出し、ArrayListオブジェクトにBookオブジェクトとして格納します。
                              while(rs.next()) {
                                    Book book = new Book();
                                    book.setIsbn(rs.getString("isbn"));
                                    book.setTitle(rs.getString("title"));
                                    book.setPrice(Integer.parseInt(rs.getString("price")));
                                    bookList.add(book);
                                   
                              }
                        
                          }catch(Exception e){
                            throw new IllegalStateException(e);
                          }finally{
                            if( smt != null ){//         ⑦statementオブジェクトをクローズします。
                              try{smt.close();}catch(SQLException ignore){}
                            }
                            if( con != null ){//         ⑧Connectionオブジェクトをクローズします。
                              try{con.close();}catch(SQLException ignore){}
                            }
                          }
                          return bookList;
                        }
               
               
}