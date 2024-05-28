<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>

<%
String error = (String) request.getAttribute("error");
Book book = (Book) request.getAttribute("book");//Detailサーブレット経由だから使える
%>

<html>
<head>
<title>update</title>
<style>
.all {
	display: flex;
	justify-content: center; /* 水平方向の中央揃え */
	gap: 50px;
}

.item {
	padding: 10px;
	border: 1px solid #000;
}

.submit-container {
	margin-top: 20px; /* ボタンを少し下に移動 */
	justify-content: center; /* 水平方向の中央揃え */
	display: flex;
}
</style>
</head>
<body>
	<!-- -------------ここからがヘッダー ---------------------------------------------------------------->
	<!-- ヘッダー開始 -->
	<jsp:include page="../common/header.jsp" flush="true" />
	<!-- ヘッダー終了 -->
	<!-- -------------ここまでがヘッダー ---------------------------------------------------------------->
	<br>
	<br>
	<br>
	<div class="all">


		<div class="left">
			<table style="margin: 0 auto;">
				<p>＜＜変更前情報＞＞</p>
				<tr>

					<th style="background-color: #6666ff; width: 150">ISBN</th>
					<td style="background-color: #30F9B2; width: 150"><%=book.getIsbn()%></td>
				</tr>

				<tr>
					<th style="background-color: #6666ff; width: 150">TITLE</th>
					<td style="background-color: #30F9B2; width: 150"><%=book.getTitle()%></td>
				</tr>

				<tr>
					<th style="background-color: #6666ff; width: 150">価格</th>
					<td style="background-color: #30F9B2; width: 150"><%=book.getPrice()%></td>
				</tr>



			</table>
		</div>


		<div class="right">
			<form action="<%=request.getContextPath()%>/update">

				<table style="margin: 0 auto">
					<p>＜＜変更後情報＞＞</p>
					<tr>
						<input type="hidden" name="isbn" value=<%=book.getIsbn()%>><%=book.getIsbn()%>
						</input>
					</tr>

					<tr>

						<td><input type=text size="45" name="title"></input></td>
					</tr>

					<tr>
						<td><input type=text size="45" name="price"></input></td>
					</tr>
				</table>
		</div>
	</div>
	<div class="submit-container">
		<input type="submit" value="更新">
	</div>
	</form>
	






	<!-- -------------ここまからフッター ---------------------------------------------------------------->


	<!-- フッター開始 -->
	<jsp:include page="../common/footer.jsp" flush="true" />
	<!-- フッター終了 -->
</body>
</html>