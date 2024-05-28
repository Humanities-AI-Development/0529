package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 書籍管理システムにおける書籍一覧機能に関する処理をおこなうサーブレットクラス
 *
 * @author KandaITSchool
 *
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		try {
            // ① BookDAOをインスタンス化する
            BookDAO objDao = new BookDAO();

            // ②関連メソッドを呼び出し、戻り値としてBookオブジェクトのリストを取得する
            ArrayList<Book> bookList = objDao.selectAll();

            // ③②で取得したListをリクエストスコープに"book_list"という名前で格納する
            request.setAttribute("book_list", bookList);

            // ④list.jspにフォワード
            request.getRequestDispatcher("/view/list.jsp").forward(request, response);

        } catch (Exception e) {
            // エラーメッセージと発生場所をリクエストスコープに設定
        	String error = "";
            error = "DB接続エラーの為、一覧表示は行えませんでした。";
            request.setAttribute("error", error);  
            
            String link = request.getContextPath() + "/view/menu.jsp";  
            
            request.setAttribute("link", link);
            // エラーページにフォワード
            request.getRequestDispatcher("/view/error.jsp").forward(request, response);
        }
    }
}
