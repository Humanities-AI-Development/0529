<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import = "util.MyFormat" %>
<%
Book book = (Book)request.getAttribute("book");
%>

<html>

<body>
<!-- ヘッダー開始 -->
<jsp:include page="../common/header.jsp" flush="true" />
<!-- ヘッダー終了 -->

        <div style="text-align: center; margin-top: 80px;">
                <button style="margin-right: 40px;"
                        onclick="location.href='<%=request.getContextPath() %>/detail?isbn=<%=book.getIsbn()%>&cmd=update'">変更</button>
                <button
                        onclick="location.href='<%=request.getContextPath()%>/delete?isbn=<%=book.getIsbn()%>'">削除</button>


                <br>
                <br>
                <br>
                <table style="margin: 0 auto">

                        <tr>
                                <th style="background-color: #6666ff; width: 150">ISBN</th>
                                <td style="background-color: #30F9B2; width: 150"><%=book.getIsbn() %></td>
                        </tr>

                        <tr>
                                <th style="background-color: #6666ff; width: 150">TITLE</th>
                                <td style="background-color: #30F9B2; width: 150"><%=book.      getTitle() %></td>
                        </tr>

                        <tr>
                                <th style="background-color: #6666ff; width: 150">価格</th>
                                <%MyFormat format = new MyFormat();%>
                                <td style="background-color: #30F9B2; width: 150"><%=format.moneyFormat(book.getPrice()) %></td>
                        </tr>



                </table>
                <br>
                <br>
                <br>
                <br>
                <br>



        </div>
       
<!-- フッター開始 -->
<jsp:include page="../common/footer.jsp" flush="true" />
<!-- フッター終了 -->
</body>
</html>