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
import util.FileIn;
//カートの中身を一覧にするサーブレット(ShowCartServlet)
@WebServlet("/insertIniData")
public class InsertIniDataServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
               
//              ①BookDAOをインスタンス化し、関連メソッドを呼び出す。
//              例）ArrayList<Book> list = BookDaoObj.selectAll();
//              ②①の戻り値として、Bookオブジェクトのリスト（List）を取得する。
//              ③②のListに１件でも書籍データがあればerror.jspにフォワードする。無ければ④以降の処理を行う。
//              ④初期データ用CSVファイルよりデータを取得する。
//              ファイルのパスはString path = getServletContext().getRealPath("file\\initial_data.csv");で取得する。
//              ⑤Bookのオブジェクトを生成し、setterを利用して①データのisbn, title, priceを設定する。(初期データの数分)
//              例）Book book = new Book();
//               book.setIsbn(isbn);
//              ⑥取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
//              例）list.add(Book):Listに追加する方法
//              例）request.setAttribute("book_list",list)：リクエストスコープに格納する方法
//              ⑦②で設定した各Bookのオブジェクトを引数として、BookDAOオブジェクトを生成し、関連メソッドを呼び出す。
//              例）BookDaoobj.insert（Book）;
//              ・データベースのbookinfoテーブルに引数の情報を新規登録するメソッド（初期ファイルデータをデータの数だけ登録する
//              ⑧insertIniData.jspにフォワードする。
//              例）request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response)

               
                String error = "";
                String link = "";

                try {
//                      ①BookDAOをインスタンス化し、関連メソッドを呼び出す。
                        BookDAO BookDaoObj = new BookDAO();
//                      ②①の戻り値として、Bookオブジェクトのリスト（List）を取得する。
                        ArrayList<Book> list = BookDaoObj.selectAll();
//                      ③②のListに１件でも書籍データがあればerror.jspにフォワードする。無ければ④以降の処理を行う。
                        if(list!=null) {
                                error="セッション切れです。";
                                request.setAttribute("error", error);
                                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                        }
//                      ④初期データ用CSVファイルよりデータを取得する。
//                      ファイルのパスはString path = getServletContext().getRealPath("file\\initial_data.csv");で取得する。         
                        String path = getServletContext().getRealPath("file\\initial_data.csv");
                        FileIn fileObj = new FileIn();
                        fileObj.open(path);//ファイルを開く
                        ArrayList<Book> List = new ArrayList<Book>();
                        while(true) {
                                String data = fileObj.readLine();//1行格納
                                String[] split_data = data.split(".");
//                              ⑤Bookのオブジェクトを生成し、setterを利用して①データのisbn, title, priceを設定する。(初期データの数分)
                                Book book = new Book();
                                book.setIsbn(split_data[0]);
                                book.setTitle(split_data[1]);
                                book.setPrice(Integer.parseInt(split_data[2]));
//                              ⑦②で設定した各Bookのオブジェクトを引数として、BookDAOオブジェクトを生成し、関連メソッドを呼び出す。
//                              例）BookDaoobj.insert（Book）;
//                              ・データベースのbookinfoテーブルに引数の情報を新規登録するメソッド（初期ファイルデータをデータの数だけ登録する
                                BookDAO BookDaoobj = new BookDAO();
                                BookDaoobj.insert(book);
                                List.add(book);//Listに格納
                                if(data.equals("")) {
                                        break;
                                }
                               
                        }
//                      ⑥取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
                       
                        request.setAttribute("book_list",List);//リクエストスコープに格納する方法

                       
//                      ⑧insertIniData.jspにフォワードする。
                        request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);
                       
                       
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