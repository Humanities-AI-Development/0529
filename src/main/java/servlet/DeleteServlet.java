package servlet;
import java.io.IOException;

import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
               
//              ⑤「ListServlet」へフォワードします

                String error = "";//エラー文を格納するため
                String link = "";//エラー処理のためのリンク
               
                try {
                        String isbn = request.getParameter("isbn");//?(クエリパラメータで受け取ったisbn)
                       
                        BookDAO objDao = new BookDAO();//DB関連のメソッドを使うためにインスタンス化
                       
                        objDao.delete(isbn);//削除メソッド呼び出し
                       
                        request.getRequestDispatcher("/list").forward(request, response);



                }catch(IllegalStateException e) {
                        error = "DB接続エラーの為、書籍削除処理は行えませんでした。";
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