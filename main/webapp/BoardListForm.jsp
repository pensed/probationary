<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.list.*" %>
<%@page import="java.util.*" %>
<%
// List<BoardListVO> boardList = (List)request.getAttribute("boardList");
// System.out.println(boardList);
  List<BoardListVO> boardList = BoardListDAO.listBoard(1);

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
		<table board=1>
			<tr align='center' bgcolor='lightgray'>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<% for(int i=0; i < boardList.size(); i++) { %>
			<tr>
				<td><%=boardList.get(i).getNum() %></td>
				<td><%=boardList.get(i).getWriter() %></td>
				<td><%=boardList.get(i).getTitle() %></td>
				<td><%=boardList.get(i).getDate() %></td>
				<td><%=boardList.get(i).getCnt() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>