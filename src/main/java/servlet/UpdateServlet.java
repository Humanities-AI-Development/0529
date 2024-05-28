package servlet;
import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {       

//      例）request.getRequestDispatcher("/list").forward(request, response)
//      ・エラーが有る場合(異常ルート)はerror.jspにフォワードする
//      例）タイトルが未入力の場合
//      error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
//      ※メッセージを設定するのは③のエラーチェック時に行う
//      request.setAttribute("error"，error);
//      request.getRequestDispatcher("/view/error.jsp").forward(request, response);
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                String error = "";
                String link = "";
               
                try {
                        Book book = new Book();////②更新後の書籍情報を格納するBookオブジェクトを生成します。


                       
                       
                        String isbn = request.getParameter("isbn");
                        String title = request.getParameter("title");
                        String priceStr = request.getParameter("price");
                       
                       
                        if(title == null || title.isEmpty()){
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

                       
                       
                        BookDAO objDao = new BookDAO();//①BookDAOクラスのオブジェクトを生成します。
                       
                       
                        book.setIsbn(request.getParameter("isbn"));
                        book.setTitle(request.getParameter("title"));
                        book.setPrice(Integer.parseInt(request.getParameter("price")));
                       
                       
                        objDao.update(book);//⑤BookDAOクラスに定義したupdate（）メソッドを利用して、Bookオブジェクトに格納された書籍データでデータベースを更新します。
                       
                       
                        String cmd = request.getParameter("cmd");//
                       
                        request.getRequestDispatcher("/list").forward(request, response);

                       

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