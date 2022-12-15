<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%
	String num = request.getParameter("num");
	BoardArticleDAO boardArticle = new BoardArticleDAO();
	BoardArticleVO board = boardArticle.readArticle(num);	
	if(!Objects.equals(board.getWriter(),session.getAttribute("id"))){
			out.println("<script>alert('작성자가 아닙니다.'); history.back();</script>");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>게시글 작성</title>
		<script type="text/javascript">
			function fnSendBoard() {
				var frmBoardUpdate = document.frmBoardUpdate;
				console.log(frmBoardUpdate);
				var writer = frmBoardUpdate.writer.value;
				var num = frmBoardUpdate.boardNum.value;
				var title = frmBoardUpdate.title.value;
				var content = frmBoardUpdate.content.value;
				if (document.getElementById("inputCheck").checked) {
					document.getElementById("inputCheckHidden").disabled = true;
				}
				if (title.length == 0 || title == "") {
					alert("제목을 작성 후 등록 바랍니다.");
				} else if (content.length == 0 || content == "") {
					alert("내용을 작성 후 등록 바랍니다.");
				} else if (title.length > 20) {
					alert("최대 제목용량 초과");
				} else if (content.length > 2000) {
					alert("최대 내용용량 초과");
				} else {
					frmBoardUpdate.method = "post";
					frmBoardUpdate.action = "boardupdate";
					frmBoardUpdate.submit();
				}
			}
		</script>
	</head>

	<body>
		<form name="frmBoardUpdate">
			<table>
				<th>게시글 수정</th>
				<tr>
					<td>작성자</td>
					<td><input type="text" id='writer' name="writer" size="30" value="<%=session.getAttribute("id")%>" readonly/> 비공개 
						<input type="checkbox" name="input_check" value="Y" id="inputCheck" />
						<input type="hidden" name="input_check" value="N" id="inputCheckHidden" />
						<input type="hidden" name="num" value="<%=num %>"  id="inputCheckHidden" /> 
				</tr>
				<tr>
					<td>번호</td>
					<td><input type="text" id='boardNum' name="boardNum" value="<%=num %>" readonly/></td>	
				</tr>
				<tr>		
					<td>제목</td>
					<td><input type="text" id='title' name="title" style="width: 500px" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" id='content' name="content" style="width: 500px; height: 200px;" /></td>
				</tr>
			</table>
			<input type="button" value="저장" onclick="fnSendBoard()" /> 
			<input type="button" value="뒤로" onclick="location.href='http://localhost:8080/BoardListForm.jsp';"/> 
			<input type="hidden" name="command" value="updateBoard" />
		</form>
	</body>
</html>
