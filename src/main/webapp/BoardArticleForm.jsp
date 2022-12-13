<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.Boolean" %>
<%
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){ // 클릭한게 없으면 1번 페이지
		pageNum = "1";
	}
	String num = request.getParameter("num");
	BoardArticleDAO boardArticle = new BoardArticleDAO();
	BoardArticleVO board = boardArticle.readArticle(num);
	System.out.println(board.getWriter() + " " + session.getAttribute("user_id"));
	//String str = board.get(0).getIs_private();
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
					<%
					if(Objects.equals(board.getIs_private(),"N")) { 
					%>
					<td>작성자</td>
					<td><input type="text" name="writer" size="30" value="<%=board.getWriter()%>" readonly> 
					&nbsp;&nbsp;공개여부&nbsp;<%=board.getIs_private() %></td>
					</tr>
					<tr>
					<td>제목</td>
					<td><input type="text" name="title" style="width: 500px" value="<%=board.getTitle() %>" readonly/></td>
					</tr>
					<tr>
					<td>내용</td>
					<td><input type="text" name="content" style="width: 500px; height: 200px;" value="<%=board.getContent()%>" readonly/></td>
					</tr>
					<%
					} else if(Objects.equals(board.getIs_private(),"Y")){ 
						if(Objects.equals(board.getWriter(),session.getAttribute("user_id"))) {
							%>
							<td>작성자</td>
							<td><input type="text" name="writer" size="30" value="<%=board.getWriter()%>" readonly> 
							&nbsp;&nbsp;공개여부&nbsp;<%=board.getIs_private() %></td>
							</tr>
							<tr>
							<td>제목</td>
							<td><input type="text" name="title" style="width: 500px" value="<%=board.getTitle() %>" readonly/></td>
							</tr>
							<tr>
							<td>내용</td>
							<td><input type="text" name="content" style="width: 500px; height: 200px;" value="<%=board.getContent()%>" readonly/></td>
							</tr>
							<%
						} else {
							out.println("<script>alert(\"열람권한이 없습니다.\");history.back();</script>"); 
						}
					}
						%>
				</tr>
			</table>
				<input type="button" value="수정" onclick="location.href='BoardArticleUpdateForm.jsp?num=<%=num%>'" />
				<input type="button" value="삭제" onclick="location.href='BoardArticleDeleteForm.jsp?num=<%=num%>'"/>
				<input type="button" value="뒤로" onclick="location.href='http://localhost:8080/OnlyList/BoardListForm.jsp';"/> 
				<input type="hidden" name="command" value="addBoard" />
			</form>
	</body>
</html>