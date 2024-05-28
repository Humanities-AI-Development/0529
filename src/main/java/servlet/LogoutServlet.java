package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
//	①セッション情報をクリアする。
//	例） HttpSession session = request.getSession();
//	 session.invalidate()
//	②login.jspにフォワードする。
//	例）request.getRequestDispatcher("/view/login.jsp").forward(request, response)

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String link = "";
		try {
//			①セッション情報をクリアする。
			HttpSession session = request.getSession();
			session.invalidate();
			
//			②login.jspにフォワードする。
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
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