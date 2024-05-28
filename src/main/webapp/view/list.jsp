<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import = "util.MyFormat" %>
<%
//ArrayList<Book> book_list = (ArrayList<Book>)request.getAttribute("book_list");//検索にマッチした配列(後で書いたからコメントアウト)
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


                <div style="margin-bottom:250px">
                <table style="margin:auto">
                                <tr>
                                        <td>
                                                <form action="<%=request.getContextPath()%>/search">
                                                        isbn：<input type=text size="30" name="isbn"></input>
                                                        title：<input type=text size="30" name="title"></input>
                                                        価格：<input type=text size="30" name="price"></input>
                                                        <input type="submit" name="search" value="検索"></input>
                                                </form>
                                        </td>
                                        <td>
                                                <form action="<%=request.getContextPath()%>/list">
                                                        <input type="submit" name="searchall" value="全件表示"></input>
                                                </form>
                                        </td>
                                </tr>
                        </table>

                        <table style="margin:auto">
                                <tr>
                                        <th style="background-color:#6666ff; width:200px">isbn</th>
                                        <th style="background-color:#6666ff; width:200px">title</th>
                                        <th style="background-color:#6666ff; width:200px">価格</th>
                                        <th style="background-color:#6666ff; width:250px" colspan="4">変更/削除/カートに入れる</th>
                                </tr>
                                <%//デフォルト
                                ArrayList<Book> list =(ArrayList<Book>)request.getAttribute("book_list");
                                if(list != null){
                                        for(int i=0;i<list.size();i++){
                                                Book books = (Book)list.get(i);
                                %>
                               
                               
                                <tr>
                                        <td style="text-align:center; width:200px"><a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
                                        <td style="text-align:center; width:200px"><%=books.getTitle()%></td>                                  
                                       
                                       
                                        <% MyFormat format = new MyFormat();%>
                                        <td style="text-align:center; width:200px"><%=format.moneyFormat(books.getPrice())%></td>
                                       
                                        <td style="text-align:center; width:125px">
                                                <a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=update">変更</a>
                                        </td>
                                        <td style="text-align:left; width:125px">
                                                <a href="<%=request.getContextPath()%>/delete?isbn=<%=books.getIsbn()%>">削除</a>
                                        </td>
                                        <td style="text-align:left; width:125px">
                                                <a href="<%=request.getContextPath()%>/insertIntoCart?isbn=<%=books.getIsbn()%>">カートに入れる</a>
                                        </td>
                                </tr>
                                <%
                                        }
                                }else{
                                %>
                                <tr>
                                        <td style="text-align:center; width:200px">&nbsp;</td>
                                        <td style="text-align:center; width:200px">&nbsp;</td>
                                        <td style="text-align:center; width:200px">&nbsp;</td>
                                        <td style="text-align:center; width:250px" colspan="2">&nbsp;</td>
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