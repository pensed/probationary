<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.list.*" %>
<%@page import="java.util.*" %>
<%
  String pageNum = request.getParameter("pageNum");
  if (pageNum == null){ // 클릭한게 없으면 1번 페이지
	pageNum = "1";
  }
  int currentPage = Integer.parseInt(pageNum);
  int endPage = BoardListDAO.getTotal();
  List<BoardListVO> boardList = BoardListDAO.listBoard(currentPage);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="글작성" onclick="location.href='BoardRegisterForm.jsp'">
			</td>
		</tr>
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
				<td><a href="BoardArticleForm.jsp?num=<%=boardList.get(i).getNum()%>"><%=boardList.get(i).getTitle()%></a></td>
				<td><%=boardList.get(i).getDate() %></td>
				<td><%=boardList.get(i).getCnt() %></td>
			</tr>
			<% } %>
		</table>
		<%for(int i=1;i<=endPage;i++){ %>
		<a href="BoardListForm.jsp?pageNum=<%=i%>">[<%=i  %>]</a>
		<% } %>
	</body>
</html>