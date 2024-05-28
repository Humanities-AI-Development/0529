<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import="util.MyFormat"%>
<%
Book book = (Book)request.getAttribute("Book");
%>

<html>

<body>
	<!-- ヘッダー開始 -->
	<jsp:include page="../common/header.jsp" flush="true" />
	<!-- ヘッダー終了 -->

<form action="<%=request.getContextPath()%>/showCart">
	<table style="margin: 0 auto">

		<tr>
			<th style="background-color: #6666ff; width: 150">ISBN</th>
			<td style="background-color: #30F9B2; width: 150"><%=book.getIsbn() %></td>
		</tr>

		<tr>
			<th style="background-color: #6666ff; width: 150">TITLE</th>
			<td style="background-color: #30F9B2; width: 150"><%=book.getTitle() %></td>
		</tr>

		<tr>
			<th style="background-color: #6666ff; width: 150">価格</th>
			<%MyFormat format = new MyFormat();%>
			<td style="background-color: #30F9B2; width: 150"><%=format.moneyFormat(book.getPrice()) %></td>
		</tr>

		<tr>

			<td colspan=2 style="text-align: center; padding-top: 60px;"><input
				type="submit" value="カート確認"></td>
		</tr>

	</table>
	</form>

	<!-- フッター開始 -->
	<jsp:include page="../common/footer.jsp" flush="true" />
	<!-- フッター終了 -->
</body>
</html>

