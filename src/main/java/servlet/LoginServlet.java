package servlet;

import java.io.IOException;

import bean.User;//パッケージが違うからインポートが必要
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {





	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String link = "";
		try {
			
//			①userid, password入力パラメータを取得する。
//			・jsp画面(Web)からのフォームデータを取得する
//			String userid = request.getParameter("userid");//フォームからのデータ取得
//			String password = request.getParameter("password");
			
			String userid = request.getParameter("userid");
	        String password = request.getParameter("password");
			

			
//			②UserDAOをインスタンス化し、関連メソッドを呼び出す。
//			例）User user = userDaoObj.selectByUser(userid,password);
//			・データベースのuserinfoより引数のuserデータを取得するメソッド
			UserDAO objDao = new UserDAO();//ユーザ関連のDBメソッドを使うためにインスタンス化
			
			User userdata = objDao.selectByUser(userid, password);//ユーザデータを取得

//			③User情報取得の有無でフォワード先を呼び別ける。
//			User情報がある場合
			//userdataだけだと、存在してしまうのでnullだけじゃなくなっちゃう
			if(userdata.getUserid()!=null){
				//取得したUserオブジェクトをセッションスコープに"user"という名前で登録する。
				HttpSession session = request.getSession();
	            session.setAttribute("user",userdata );
	            
	            //クッキーに入力情報のuseridとpasswordを登録する。（期間は5日間）
	            //→menu.jspにフォワードする
	            Cookie userIdCookie = new Cookie("userId", userid);
	            userIdCookie.setMaxAge(5 * 24 * 60 * 60); // 5 days
	            response.addCookie(userIdCookie);

	            Cookie passwordCookie = new Cookie("password", password);
	            passwordCookie.setMaxAge(5 * 24 * 60 * 60); // 5 days
	            response.addCookie(passwordCookie);
	            
	            request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

	         //User情報がない場合
			}else {
				//リクエストスコープにメッセージを登録
				request.setAttribute("message", "入力データが間違っています！");
				
				//login.jspにフォワードする
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}

			

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