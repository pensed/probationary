<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.Boolean" %>
<%
    Object obj = session.getAttribute("isLogon");
    if(obj!=null){
     Boolean isLogin = (Boolean)obj;
	 	if(!isLogin ) {
			out.println("<script>alert('로그인 페이지로 이동합니다.'); location.href='BoardLoginForm.jsp';</script>");
		}
    }else{
    	out.println("<script>alert('로그인 페이지로 이동합니다.'); location.href='BoardLoginForm.jsp';</script>");
    }
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>게시글 작성</title>
		<script type="text/javascript">
			function fnSendBoard() {
				var frmBoardReigster = document.frmBoardReigster;
				var writer = frmBoardReigster.writer.value;
				var title = frmBoardReigster.title.value;
				var content = frmBoardReigster.content.value;
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
					frmBoardReigster.method = "post";
					frmBoardReigster.action = "boardregister";
					frmBoardReigster.submit();
				}
			}
		</script>
	</head>

	<body>
		<form name="frmBoardReigster">
			<table>
				<th>게시글 작성</th>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" size="30" value="<%=session.getAttribute("id")%>" readonly/> 비공개 
						<input type="checkbox" name="inputCheck" value="Y" id="inputCheck" />
						<input type="hidden" name="inputCheck" value="N" id="inputCheckHidden" />
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
			<input type="button" value="저장" onclick="fnSendBoard();" /> 
			<input type="button" value="뒤로" onclick="location.href='http://localhost:8080/BoardListForm.jsp';"/> 
			<input type="hidden" name="command" value="addBoard" />
		</form>
	</body>
</html>
