<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import= "board.list.*" %>
<%@page import= "board.article.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%
  String pageNum = request.getParameter("pageNum");
  if (pageNum == null){
	pageNum = "1";
  }
  int currentPage = Integer.parseInt(pageNum);
  int endPage = BoardListDAO.getTotal();
  List<BoardListVO> boardList = BoardListDAO.listBoard(currentPage);
  Date dt = new Date();
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
  String root = BoardArticleDAO.getRoot();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
		<div class="container">
			<jsp:include page="Header.jsp"/>
		</div>
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
				<td>
					<%if(sf.format(dt).compareTo(sf.format(boardList.get(i).getDate()))==0) {
							out.print("<img src =\"./images/today.png\" style=\"width:30px; height:16px;\" alt=\" \">");
					}%>
					<%if (Objects.equals(boardList.get(i).getIs_private(),"Y")){
						out.print("<img src =\"./images/lock.jpg\" style=\"width:23px; height:17px;\" alt=\" \">");
						if(!Objects.equals(boardList.get(i).getWriter(),session.getAttribute("id"))&&!Objects.equals(root,session.getAttribute("id"))){ 
							out.print("<a>");
						}else { 
							out.print("<a href=\"BoardArticleForm.jsp?num="+boardList.get(i).getNum()+"\"style=\"text-decoration: none\">");
						}
					  } else {
							out.print("<a href=\"BoardArticleForm.jsp?num="+boardList.get(i).getNum()+"\"style=\"text-decoration: none\">");
					}%>
						<%=boardList.get(i).getTitle()%>
						</a></td>
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