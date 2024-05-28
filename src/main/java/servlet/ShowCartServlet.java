package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//カートの中身を一覧にするサーブレット(ShowCartServlet)
@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		①delnoの入力パラメータを取得する。
//		例）String delno = request.getParameter("delno");
//		※delnoとは削除対象の配列要素番号である
//		②セッションから"user"を取得する。(セッション切れの場合はerror.jspに遷移する)
//		例) User user = (User)session.getAttribute("user");
//		※取得したuser変数自身がnullならセッション切れになっていると判断できる
//		③セッションから"order_list"を取得する。
//		例)ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");
//		④delnoが「null」でない場合order_listから該当の書籍情報を削除する。
//		例）order_list.remove(Integer.parseInt(delno)); Listからint数値を引数に渡して、該当データを削除する方法
//		⑤BookDAOをインスタンス化し、関連メソッドをorder_list(カートデータ)分だけ呼び出す。
//		例）Book Book = BookDaoObj.selectByIsbn(order.getIsbn());
//		・データベースのbookinfoより引数のISBNデータを検索するメソッド
//		⑥取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
//		例）list.add(Book):Listに追加する方法
//		例）request.setAttribute("book_list",list)：リクエストスコープに格納する方法
//		⑦showCart.jspにフォワードする。
//		例）request.getRequestDispatcher("/view/showCart.jsp").forward(request, response)
//

		


		
		
		String error = "";
		String link = "";

		try {
//			①delnoの入力パラメータを取得する。
//			※delnoとは削除対象の配列要素番号である
			//delnoはshowCart.jspの削除リンクで送信される
			String delno = request.getParameter("delno");
			
			
//			②セッションから"user"を取得する。(セッション切れの場合はerror.jspに遷移する)
//			※取得したuser変数自身がnullならセッション切れになっていると判断できる
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user==null) {
				error="セッション切れです。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			
//			③セッションから"order_list"を取得する。
//			*order_list:InsertIntoCartServletで定義したカート内一覧が見れる配列
			ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");
			
			
//			④delnoが「null」でない場合order_listから該当の書籍情報を削除する。
			if(delno!=null) {
				order_list.remove(Integer.parseInt(delno));//Listからint数値を引数に渡して、該当データを削除する方法
			}
			
//			⑤BookDAOをインスタンス化し、関連メソッドをorder_list(カートデータ)分だけ呼び出す。
//			⑥取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
			ArrayList<Book> list = new ArrayList<Book>();
			
			BookDAO objDao = new BookDAO();
			for(Order order:order_list) {//拡張for文を使って該当データをbookに格納
				list.add(objDao.selectByIsbn(order.getIsbn()));
			}
			request.setAttribute("book_list",list);//リクエストスコープにカートリスト一覧を格納

//			⑦showCart.jspにフォワードする。
			request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);

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