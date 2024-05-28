<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <h1 style="text-align: center">書籍管理システムweb版ver.1.0</h1>
        <hr
                style="text-align: center; height: 5px; background-color: blue; width: 950px">

        <table style="margin: auto; width: 850px">
                <tr>
                        <td style="text-align: center; width: 80px">[<a
                                href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
                        </td>
                        <td style="text-align: center; width: 80px">[<a
                                href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
                        </td>
                        <td style="text-align: center; width: 508px; font-size: 24px;">書籍一覧</td>
                        <td style="width: 80px">&nbsp;</td>
                        <td style="width: 80px">&nbsp;</td>
                </tr>
        </table>

        <hr
                style="text-align: center; height: 2px; background-color: black; width: 950px">
</body>
</html>