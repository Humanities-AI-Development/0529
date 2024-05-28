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


@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String link = "";

		try {
			Book book = new Book();//データ格納のためにフィールド準備
			BookDAO objDao = new BookDAO();//DB関連のメソッドを使うためにインスタンス化
			// フォームからのデータ取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String priceStr = request.getParameter("price");

			// 入力チェック
			if (isbn == null || isbn.isEmpty()) {
				error= "ISBNが未入力の為、書籍登録処理は行えませんでした。";

				link = request.getContextPath() + "/list";  

				request.setAttribute("error", error);
				request.setAttribute("link", link);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}else if(title == null || title.isEmpty()){
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				link = request.getContextPath() + "/list";  

				request.setAttribute("error", error);
				request.setAttribute("link", link);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}else if (priceStr == null || priceStr.isEmpty()) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				link = request.getContextPath() + "/list";  

				request.setAttribute("error", error);
				request.setAttribute("link", link);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}else {//価格が数値以外の時
				try {
					int price = Integer.parseInt(priceStr);
					// 数値への変換が成功した場合の処理
				} catch (NumberFormatException e) {
					// 数値への変換が失敗した場合の処理
					error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
					link = request.getContextPath() + "/list";  

					request.setAttribute("error", error);
					request.setAttribute("link", link);

					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}

			ArrayList<Book> bookList = objDao.selectAll();
			for(Book b :bookList) {
				if(b.getIsbn().equals(isbn)) {
					error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
					link = request.getContextPath() + "/list";  

					request.setAttribute("error", error);
					request.setAttribute("link", link);

					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}

			// データをセット
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(priceStr));

			

			int count = objDao.insert(book);//DB登録完了（一応、件数を変数に格納しておく）

			// エラーがない場合、ListServletにフォワード
			request.setAttribute("count",count);//登録件数をスコープに登録しておく
			request.getRequestDispatcher("/list").forward(request, response);


		}catch(IllegalStateException e) {
			error = "DB接続エラーのため登録できませんでした。";
		}

	}

}