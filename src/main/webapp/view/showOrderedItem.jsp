<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import="util.MyFormat"%>
<%@page import="bean.OrderedItem"%>
<%
ArrayList<OrderedItem> ordered_list = (ArrayList<OrderedItem>)request.getAttribute("ordered_list");//検索にマッチした配列
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
        <div style="margin-bottom: 250px">


                <table style="margin: auto">
                        <tr>
                                <th style="background-color: #6666ff; width: 200px">ユーザー</th>
                                <th style="background-color: #6666ff; width: 200px">TITLE</th>
                                <th style="background-color: #6666ff; width: 200px">注文日</th>
                        </tr>
                        <%
                        int total = 0;
                        if (ordered_list != null) {//booksにカート一覧が格納
                                for (OrderedItem list : ordered_list) {
                        %>



                        <tr>
                                <td style="text-align: center; width: 200px"><%=list.getUserid()%></td>
                               
                                <td style="text-align: center; width: 200px"><%=list.getTitle()%></td>

                                <td style="text-align: center; width: 200px"><%=list.getDate()%>
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