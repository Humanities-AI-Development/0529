<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
<%@page import="util.MyFormat"%>
<%
//ArrayList<Book> book_list = (ArrayList<Book>)request.getAttribute("book_list");//検索にマッチした配列
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
	<div style="margin-bottom: 250px">


		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">isbn</th>
				<th style="background-color: #6666ff; width: 200px">title</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
				<th style="background-color: #6666ff; width: 250px;" colspan="4">削除</th>
			</tr>
			<%
			//デフォルト
			int total = 0;
			ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
			if (list != null) {//booksにカート一覧が格納
				for (int i = 0; i < list.size(); i++) {
					Book books = (Book) list.get(i);
					total += books.getPrice();//カートの合計金額
			%>


			<tr>
				<td style="text-align: center; width: 200px"><a
					href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
				<td style="text-align: center; width: 200px"><%=books.getTitle()%></td>


				<%
				MyFormat format = new MyFormat();
				%>
				<td style="text-align: center; width: 200px"><%=format.moneyFormat(books.getPrice())%>
				</td>


				<td style="text-align: center; width: 125px"><a
					href="<%=request.getContextPath()%>/showCart?delno=<%=i%>">削除</a></td>

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
		
		<form action="<%=request.getContextPath()%>/buyConfirm">
			<table style="margin: 0 auto">
				<tr>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
					<td colspan=2 style="text-align: center; padding-top: 60px;"><input
						type="submit" value="購入"></td>
				</tr>
			</table>
		</form>

	</div>


	<!-- -------------ここまからフッター ---------------------------------------------------------------->


	<!-- フッター開始 -->
	<jsp:include page="../common/footer.jsp" flush="true" />
	<!-- フッター終了 -->
</body>
</html>