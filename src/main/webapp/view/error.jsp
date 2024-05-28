<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%
String error = (String)request.getAttribute("error");
String link = (String)request.getAttribute("link");
%>

<html>
        <head>
                <title>error</title>
        </head>
        <body>
<!-- -------------ここからがヘッダー ---------------------------------------------------------------->

<!-- ヘッダー開始 -->
<jsp:include page="../common/header.jsp" flush="true" />
<!-- ヘッダー終了 -->

<!-- -------------ここまでがヘッダー ---------------------------------------------------------------->

                <div style="text-align:center;margin-top:80px;">
                <h2>●●エラー●●</h2>
                <br><br><br><br>
                <% if (error != null && !error.isEmpty()) { %>
            <%= error %>
            <br><br><br><br><br>
               
                <a href="<%=link %>">戻る</a>
         <% } %>

        </div>
               
               
<!-- -------------ここまからフッター ---------------------------------------------------------------->


                <!-- フッター開始 -->
<jsp:include page="../common/footer.jsp" flush="true" />
<!-- フッター終了 -->
        </body>
</html>