package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

//// 注文登録・メール送信用サーブレット
@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {//カート追加用のサーブレット
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
               
//              ①セッションから"user"を取得する。(セッション切れの場合はerror.jspに遷移する)
//              例) User user = (User)session.getAttribute("user");
//              ②セッションから"order_list"を取得する。(カートの中身がない場合はerror.jspに遷移する)
//              例) ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");
//              ③②BookDAOとOrderDAOをインスタンス化し、関連メソッドをorder_listの(カート追加データ分）だけ呼び出す。
//              例）Book Book = objBookDAO.selectIsbn(order.getIsbn());
//              例）objOrderDao.insert(order);
//              ※このループ中にメール本文を作成する
//              ④③中に取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
//              例）list.add(Book):Listに追加する方法
//              例）request.setAttribute("book_list",list)：リクエストスコープに格納する方法
//              ⑤"order_list"の注文情報内容をメール送信する。※メールの本文内容は、右側の例を参考にして作成して下さい
//              ※メール送信サンプルソースを配布(実装する場合は講師に声をお掛け下さい)
//              ⑥セッションの"order_list"情報をクリアする。
//              例）session.setAttribute("order_list",null);
//              ⑦buyConrirm.jspにフォワードする。
//              例）request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response)
               
                String error = "";
                String link = "";
                try {
                       
//                      ①セッションから"user"を取得する。(セッション切れの場合はerror.jspに遷移する)
                        HttpSession session = request.getSession();                     // HttpServletRequestオブジェクトからセッションを取得
                        User user = (User)session.getAttribute("user");//セッションから、user属性を取得
                        if(user==null) {
                                error="セッション切れです。";
                                request.setAttribute("error", error);
                                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                        }
                       
//              ②セッションから"order_list"を取得する。(カートの中身がない場合はerror.jspに遷移する)
//                      *order_list:InsertIntoCartServletで定義したカート内一覧が格納されたセッション配列
                        ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");
                        if(order_list==null) {
                                error = "カート内になにもありません";
                                link = request.getContextPath() + "/view/menu.jsp"; 
                    request.setAttribute("error", error);
                    request.setAttribute("link", link);
                                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                        }
                       

//                      ③②BookDAOとOrderDAOをインスタンス化し、関連メソッドをorder_listの(カート追加データ分）だけ呼び出す。
//                      例）Book Book = objBookDAO.selectIsbn(order.getIsbn());
//                      例）objOrderDao.insert(order);
//                      ※このループ中にメール本文を作成する
//                      下記の手順を試してみましょう。
//                      ① 書籍情報をfor文で一つずつ取り出す。
//                      ② String型変数textに格納し、メール用のメソッドの引数にtextを指定する。
                        BookDAO objBookDao = new BookDAO();
                        OrderDAO objOrderDao = new OrderDAO();
                       
                        ArrayList<Book> list = new ArrayList<Book>();//書籍情報を格納するフィールドを作成しておく
                       
                        for(Order order:order_list) {//拡張for文を使って該当データをbookに格納
                                list.add(objBookDao.selectByIsbn(order.getIsbn()));//① 書籍情報をfor文で一つずつ取り出す
                        }
//                      ④③中に取得した各BookをListに追加し、リクエストスコープに"book_list"という名前で格納する。
                        session.setAttribute("book_list",list);//セッションスコープに購入書籍一覧を格納

//                      ⑤"order_list"の注文情報内容をメール送信する。※メールの本文内容は、右側の例を参考にして作成して下さい
                        String username = user.getUserid();//ログインしているユーザ名
                        String email = user.getEmail();
                        String BodyContents="本のご購入ありがとうざいます。\r\n"       + "以下内容でご注文を受け付けましたので、ご連絡致します。";//本文
                        String ClosingWords = "またのご利用よろしくお願いします。";//締めの言葉
                        int sum = 0;
                        int i = 0;
                       
                        String txt = null;//購入データを格納
                       
                        txt = username+"様"+"\r\n";
                        txt += "\r\n"+BodyContents+"\r\n\r\n\r\n";
                        for(Book li :list) {
                                String isbn =li.getIsbn();
                                String title =li.getTitle();
                                int price = li.getPrice();
                                        txt += isbn+"      "+title+"      "+price+"\r\n";
                                        sum+=price;
                                        }
                        txt +="\r\n"+"合計："+sum+"円";
                       
                        txt += "\r\n\r\n\r\n"+ClosingWords;
//---------------------------------------------------------------------------------------------------------
                        SendMail MailObj = new SendMail(txt,email);
                        MailObj.second();//メール送信
                       
                       
//                      ⑥セッションの"order_list"情報をクリアする。
                        session.setAttribute("order_list",null);
                       
                       
//                      ⑦buyConrirm.jspにフォワードする。
                        request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
                                               
                       


                       
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