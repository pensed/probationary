<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	List<BoardArticleVO> boardDetail = BoardArticleDAO.readArticle(num);
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
				<th>게시글 작성</th>
				<tr>
					<td>작성자</td>
					<td><%=boardDetail.getWriter() %>> 공개여부: [<%=boardDetail.getIs_private() %>] </td>
						
						
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" style="width: 500px" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" name="content" style="width: 500px; height: 200px;" /></td>
				</tr>
			</table>
			<input type="button" value="저장" onclick="fn_sendBoard()" /> 
			<input type="button" value="취소" href="#" onclick="history.go(-1);"/> 
			<input type="hidden" name="command" value="addBoard" />
		</form>
	</body>
</html>