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
	boardArticle.deleteArticle(num);

%>
<script>
	alert("삭제 완료");
	location.href="http://localhost:8080/OnlyList/BoardListForm.jsp";
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