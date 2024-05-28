package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//欲しいものをカートに追加するサーブレット（データベースに追加するのではなくあくまでもセッション配列として）
@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {//カート追加用のサーブレット
//      ①セッションから"user"のUserオブジェクトを取得する(セッション切れの場合はエラー)。
//      例) User user = (User)session.getAttribute("user");
//      ※取得したuser変数自身がnullならセッション切れになっていると判断できる
//      ②isbnのパラメータを取得する。
//      例）String isbn = request.getParameter("isbn");
//      ・jsp画面(Web)からのフォームデータを取得する
//      ③BookDAOをインスタンス化し、関連メソッドを呼び出す。
//      例）Book Book = BookDaoObj.selectByIsbn(isbn);
//      ・データベースのbookinfoより引数のISBNデータを検索するメソッド
//      ④③で取得したBookオブジェクトをリクエストスコープに"Book"という名前で格納する。
//      例）request.setAttribute("Book",Book);
//      ⑤Orderのインスタンスを生成し、各setterメソッドを利用し、isbn、userid(ログイン者)、数量(1固定)を設定する。
//      例）Order order = new Order();
//       order.setIsbn(isbn);
//      ⑥セッションから"order_list"のList配列を取得する。(取得出来なかった場合はArrayList<Order>配列を新規で作成する)
//      例) list = (ArrayList<Order>)session.getAttribute("order_list");
//      ※取得出来なかった場合とはorder_listがnullの場合
//      if(list == null){
//      list = new ArrayList<Order>();
//      }
//      ⑦OrderオブジェクトをList配列に追加し、セッションスコープに"order_list"という名前で登録する。
//      例）list.add(order);
//      session.setAttribute("order_list",list)
       
//      ⑧エラーの有無でフォワード先を呼び別ける。
//      ・エラーが無い場合(正常ルート)はスコープデータに②で取得したBookをinsertIntoCart.jspにフォワードする
//      例）request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response)
//      ・エラーが有る場合(異常ルート)はerror.jspにフォワードする
//      例）request.getRequestDispatcher("/view/error.jsp").forward(request, response)


        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                String error = "";
                String link = "";
                try {
                       
//                      ①セッションから"user"のUserオブジェクトを取得する(セッション切れの場合はエラー)。
                        // HttpServletRequestオブジェクトからセッションを取得
                        HttpSession session = request.getSession();
                       
                        //セッションから、user属性を取得
                        User user = (User)session.getAttribute("user");
                       

//                      ②isbnのパラメータを取得する。
//                      ・jsp画面(Web)からのフォームデータを取得する
                        String isbn = request.getParameter("isbn");
                       
//                      ③BookDAOをインスタンス化し、関連メソッドを呼び出す。
                        BookDAO BookobjDao = new BookDAO();//DB関連のメソッドを使うためにインスタンス化
                        Book book = new Book();//データ格納のためにフィールド準備
                        book = BookobjDao.selectByIsbn(isbn);//isbnと等しいデータがbookに格納される
                       
                       
//                      ④③で取得したBookオブジェクトをリクエストスコープに"Book"という名前で格納する。
                        request.setAttribute("Book",book);
                       
//                      ⑤Orderのインスタンスを生成
//                      各setterメソッドを利用し、isbn、userid(ログイン者)、数量(1固定)を設定する。               
                        Order order = new Order();//データ格納のためにフィールド準備
                        order.setIsbn(book.getIsbn());//isbn情報をorderにセット
                       
                        order.setUserid(user.getUserid());//セッションスコープからユーザIDをセット
                       
                       
                       
                        order.setQuantity(1);//数量をセット
                        request.setAttribute("order", order);//リクエストスコープにorderを登録
                       
                        OrderDAO OrderobjDao= new OrderDAO();//DB関連のメソッドを使うためにインスタンス化
                        OrderobjDao.insert(order);//セットした情報をDBに保存                      
                       
//                      ⑥セッションから"order_list"のList配列を取得する。(取得出来なかった場合はArrayList<Order>配列を新規で作成する)                       
                        //買い物リストが格納されたlistをセッションから取得
                        ArrayList<Order> list = (ArrayList<Order>)session.getAttribute("order_list");
                        //取得出来なかった場合はArrayList<Order>配列を新規で作成する
                        if(list==null) {
                                list = new ArrayList<Order>();
                        }
                       
                       
//                      ⑦OrderオブジェクトをList配列に追加し、セッションスコープに"order_list"という名前で登録する。
                        list.add(order);
                        session.setAttribute("order_list",list);
                       
//                      ⑧エラーの有無でフォワード先を呼び別ける。
//                      ・エラーが無い場合(正常ルート)はスコープデータに②で取得したBookをinsertIntoCart.jspにフォワードする
                        request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);                   
                       
                       
                }catch(IllegalStateException e) {
                        error = "DB接続エラーの為、書籍詳細は表示できませんでした。";
                        link = request.getContextPath() + "/view/menu.jsp"; 
           
            request.setAttribute("error", error);
            request.setAttribute("link", link);
           
                        request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                        
                }catch(Exception e) {
                        error = "予期せぬエラーが発生しました。<br>"+e;
                        link = request.getContextPath() + "/view/menu.jsp"; 
           
            request.setAttribute("error", error);
            request.setAttribute("link", link);
           
                        request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                }
        }
}