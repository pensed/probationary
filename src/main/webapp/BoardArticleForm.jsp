<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){ // 클릭한게 없으면 1번 페이지
		pageNum = "1";
	}
	String num = request.getParameter("num");
	BoardArticleDAO boardArticle = new BoardArticleDAO();
	List<BoardArticleVO> board = boardArticle.readArticle(num);

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>게시글 상세</title>
	</head>

	<body>
		<form >
			<table>
				<th>게시글 상세</th>
				<tr>
					<% for(int i=0; i < board.size(); i++) { %>
			<tr>
				<td><%=board.get(i).getWriter() %><br></td>
				<td><%=board.get(i).getTitle() %><br></td>
				<td><%=board.get(i).getIs_private()%><br></td>
				<td><%=board.get(i).getContent() %><br></td>
			</tr>
			<% } %>
				</tr>
			</table>
			<input type="button" value="수정" onclick="location.href='BoardArticleUpdateForm.jsp?num=<%=num%>'" />
			<input type="button" value="삭제" onclick="location.href='BoardArticleDeleteForm.jsp?num=<%=num%>'">
			<input type="button" value="취소" onclick="location.href='http://localhost:8080/OnlyList/BoardListForm.jsp';"/> 
			<input type="hidden" name="command" value="addBoard" />
		</form>
	</body>
</html>