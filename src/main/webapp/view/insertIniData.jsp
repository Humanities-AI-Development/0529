<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import="util.MyFormat"%>
<%@page import="bean.OrderedItem"%>
<%
ArrayList<Book> book_list = (ArrayList<Book>)request.getAttribute("book_list");//検索にマッチした配列
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


<br><br><br><br>

<h2 style="text-align:center;">初期データとして以下のデータを登録しました。</h2>
<br><br><br><br>
        <div style="margin-bottom: 250px">


                <table style="margin: auto">
                        <tr>
                                <th style="background-color: #6666ff; width: 200px">ISBN</th>
                                <th style="background-color: #6666ff; width: 200px">TITLE</th>
                                <th style="background-color: #6666ff; width: 200px">価格</th>
                        </tr>
                        <%
                        int total = 0;
                        if (book_list != null) {//booksにカート一覧が格納
                                for (Book list : book_list) {
                        %>



                        <tr>
                                <td style="text-align: center; width: 200px"><%=list.getIsbn()%></td>
                               
                                <td style="text-align: center; width: 200px"><%=list.getTitle()%></td>

                                <td style="text-align: center; width: 200px"><%=list.getPrice()%>
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
               
        </div>


        <!-- -------------ここまからフッター ---------------------------------------------------------------->


        <!-- フッター開始 -->
        <jsp:include page="../common/footer.jsp" flush="true" />
        <!-- フッター終了 -->
</body>
</html>