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





@WebServlet("/search")
public class SearchServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                String error = "";
                String link = "";
                try {
                        //①BookDAOクラスのオブジェクトを生成します
                        BookDAO objDao = new BookDAO();//DB関連のメソッドを使うためにインスタンス化


                        //②画面から送信される検索条件を受け取るためのエンコードを設定します。
                        //③画面から送信される検索条件を受け取ります。
                        String isbn = request.getParameter("isbn");//フォームからのデータ取得
                        String title = request.getParameter("title");
                        String price = request.getParameter("price");
                       
                        String cmd = request.getParameter("cmd");

                        Book book = new Book();//データ格納のためにフィールド準備
                       
                        //④BookDAOクラスに定義したsearch（）メソッドを利用して書籍情報を取得します。
                        ArrayList<Book> book_list = objDao.search(isbn,title,price);
                       
                        //⑤取得した書籍情報を「book_list」という名前でリクエストスコープに登録します。
                        request.setAttribute("book_list", book_list);
                       
                        //⑥「list.jsp」へフォワードします。
                        request.getRequestDispatcher("/view/list.jsp").forward(request, response);


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