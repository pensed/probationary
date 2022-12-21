<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){
		pageNum = "1";
	}
	String num = request.getParameter("num");
	BoardArticleDAO boardArticle = new BoardArticleDAO();
	BoardArticleVO board = boardArticle.readArticle(num);
	String root = boardArticle.getRoot();
	if(!Objects.equals(board.getWriter(),session.getAttribute("id"))&&!Objects.equals(root,session.getAttribute("id"))){	
		out.println("<script>alert('작성자 혹은 관리자가 아닙니다.'); history.back();</script>");
	} else {
		boardArticle.deleteArticle(num);
	}
	

%>
<script>
	alert("삭제 완료");
	location.href="BoardListForm.jsp";
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>

</body>
</html>