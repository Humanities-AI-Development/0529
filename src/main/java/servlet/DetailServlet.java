package servlet;
import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String link = "";
		try {
			BookDAO objDao = new BookDAO();//DB関連のメソッドを使うためにインスタンス化



			String isbn = request.getParameter("isbn");//フォームからのデータ取得
			String cmd = request.getParameter("cmd");

			Book book = new Book();//データ格納のためにフィールド準備
			book = objDao.selectByIsbn(isbn);

			
			if (book.getTitle() == null || book.getTitle().isEmpty()) {
				error="表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
				link = request.getContextPath() + "/list";  
                
                request.setAttribute("error", error);
                request.setAttribute("link", link);
                
    			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			request.setAttribute("book",book);//登録件数をスコープに登録しておく

			// cmdの値によってフォワードを変える
			if (cmd.equals("detail")) {
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
			} else if ("update".equals(cmd)) {
				request.getRequestDispatcher("/view/update.jsp").forward(request, response);
			} else {
				// cmdが指定されていない場合はエラーページにフォワード
				throw new Exception("無効な操作が指定されました。");
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