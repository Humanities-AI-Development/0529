package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.OrderedItem;
import dao.OrderedItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//カートの中身を一覧にするサーブレット(ShowCartServlet)
@WebServlet("/showOrderedItem")
public class ShowOrderedItemServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
               
//              ①OrderedItemDAOをインスタンス化し、関連メソッドを呼び出す
//              例）ArrayList<OrderedItem> list = orderedItemDao.selectAll();
//              ・データベースのorderinfoテーブルとbookinfoテーブルを結合して購入履歴データ取得するメソッド
//              ②①の戻り値として、OrderedItemオブジェクトのリスト（List）を取得する
//              ③取得したListをリクエストスコープに"ordered_list"という名前で格納する
//              例）request.setAttribute("ordered_list",list);
//              ④showOrderedItem.jspにフォワードする
//              例）request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response)
               
                String error = "";
                String link = "";

                try {
//                      ①OrderedItemDAOをインスタンス化し、関連メソッドを呼び出す
//                      例）ArrayList<OrderedItem> list = orderedItemDao.selectAll();
//                      ・データベースのorderinfoテーブルとbookinfoテーブルを結合して購入履歴データ取得するメソッド
                        OrderedItemDAO OrdObj = new OrderedItemDAO();
//                      ②①の戻り値として、OrderedItemオブジェクトのリスト（List）を取得する
                        ArrayList<OrderedItem> list =OrdObj.selectAll();//2つのテーブルの共通カラムであるisbnを条件にテーブル結合することで、本のタイトルを取得することができる。

                       
//                      ③取得したListをリクエストスコープに"ordered_list"という名前で格納する
                        request.setAttribute("ordered_list",list);
                       
//                      ④showOrderedItem.jspにフォワードする
                        request.getRequestDispatcher("/view/showOrderdItem.jsp").forward(request, response);

                       
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