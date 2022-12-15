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
	BoardArticleVO board = boardArticle.readArticle(num);	
	if(Objects.equals(board.getWriter(),session.getAttribute("id"))){
		boardArticle.deleteArticle(num);	
	} else {
		out.println("<script>alert('작성자 혹은 관리자가 아닙니다.'); history.back();</script>");
	}
	

%>
<script>
	alert("삭제 완료");
	location.href="http://localhost:8080/BoardListForm.jsp";
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