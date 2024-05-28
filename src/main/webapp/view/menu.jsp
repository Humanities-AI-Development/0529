<%@page contentType="text/html; charset=UTF-8"%>
<!-- メニュー画面の表示 -->

<html>
        <head>
                <title>書籍管理メニュー画面</title>
        </head>
        <body>
      
<!-- ヘッダー開始 -->
<jsp:include page="../common/header.jsp" flush="true" />
<!-- ヘッダー終了 -->       
              
              
                <div style="margin-bottom:350px">
                        <table  style="margin:auto; border:0;">
                                <tr><td><a href="<%=request.getContextPath() %>/list">【書籍一覧】</a><br><br></td></tr>
                                <tr><td><a href="<%=request.getContextPath() %>/view/insert.jsp">【書籍登録】</a><br><br></td></tr>
                                <tr><td><a href="<%=request.getContextPath() %>/showCart">【カート状況確認】</a><br><br></td></tr>
                                <tr><td><a href="<%=request.getContextPath() %>/showOrderedItem">【購入状況確認】</a><br><br></td></tr>
                                <tr><td><a href="<%=request.getContextPath() %>/insertIniData">【初期データ登録（データがない場合のみ）】</a><br><br></td></tr>
                               
                               
                                <tr><td><a href="<%=request.getContextPath() %>/logout">【ログアウト】</a><br><br></td></tr>
                        </table>
                </div>
              
              
              
              
                <!-- フッター開始 -->
<jsp:include page="../common/footer.jsp" flush="true" />
<!-- フッター終了 -->
        </body>
</html>