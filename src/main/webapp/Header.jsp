<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Object obj = session.getAttribute("isLogon");
%>
<%
	if(obj!=null) {
		Boolean isLogin = (Boolean)obj;
		if(isLogin) {
			%>
			<button onclick="location.href='Logout.jsp'">로그아웃</button>
			<%
		}
	} else {
		%>
		<button onclick="location.href='BoardLoginForm.jsp'">로그인</button>
		<%
	}
%>
</body>
</html>