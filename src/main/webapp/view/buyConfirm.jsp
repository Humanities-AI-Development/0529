<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import="util.MyFormat"%>
<%
ArrayList<Book> book_list = (ArrayList<Book>)session.getAttribute("book_list");//検索にマッチした配列
%>
<html>
<head>
<title>list</title>
</head>
<body>
        <!-- -------------ここからがヘッダー ---------------------------------------------------------------->
        <!-- ヘッダー開始 -->
        <jsp:include page="../common/header.jsp" flush="true" />
        <!-- ヘッダー終了 -->
        <!-- -------------ここまでがヘッダー ---------------------------------------------------------------->

<br><br><br><br><br>
<h2 style="text-align:center;">下記の商品を購入しました。<br>
ご利用ありがとうございました。</h2>
<br><br><br><br>
        <div style="margin-bottom: 250px">


                <table style="margin: auto">
                        <tr>
                                <th style="background-color: #6666ff; width: 200px">isbn</th>
                                <th style="background-color: #6666ff; width: 200px">title</th>
                                <th style="background-color: #6666ff; width: 200px">価格</th>
                        </tr>
                        <%
                        int total = 0;
                        if (book_list != null) {//booksにカート一覧が格納
                                for (Book list : book_list) {
                               
                                        total += list.getPrice();//カートの合計金額
                        %>



                        <tr>
                                <td style="text-align: center; width: 200px"><%=list.getIsbn()%></td>
                               
                                <td style="text-align: center; width: 200px"><%=list.getTitle()%></td>

                                <%MyFormat format = new MyFormat();%>
                                <td style="text-align: center; width: 200px"><%=format.moneyFormat(list.getPrice())%>
                                </td>



                        </tr>
                        <%
                        }
                        } else {
                        %>
                        <tr>
                                <td style="text-align: center; width: 200px">&nbsp;</td>
                                <td style="text-align: center; width: 200px">&nbsp;</td>
                                <td style="text-align: center; width: 200px">&nbsp;</td>
                                <td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
                        </tr>
                        <%
                        }
                        %>

                </table>
                <hr
                        style="border: 0; border-top: 3px double black; height: 0; margin: 20px 0;">

                <table style="margin: auto">
                        <tr>
                                <th style="background-color: #6666ff; width: 200px">合計</th>
                                <%
                                MyFormat format = new MyFormat();
                                %>
                                <td style="text-align: center; width: 200px"><%=format.moneyFormat(total)%></td>
                        </tr>
                </table>

                <br> <br> <br> <br>
               
        </div>


        <!-- -------------ここまからフッター ---------------------------------------------------------------->


        <!-- フッター開始 -->
        <jsp:include page="../common/footer.jsp" flush="true" />
        <!-- フッター終了 -->
</body>
</html>