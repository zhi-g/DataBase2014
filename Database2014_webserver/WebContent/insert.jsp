<%@page
	import="com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException"%>
<%@page import="graphical.helpers.TablesToHTML"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DB2014 - Group 5</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="header">
		<div class="article" style="text-align: center;">
			<%
				String query = request.getParameter("query");
				if (null != query) {

					out.println("<h1>Query status</h1>");
					out.println(OracleDatabase.SINGLE.runInsertion(query));
				}
			%>
			<h1>Insertion query</h1>
			<form action="insert.jsp" method="POST">
				<textarea rows="10" cols="80" name="query"
					placeholder="Type your insertion query here."></textarea>
				<br />
				<button type="submit" value="Submit">Submit</button>
			</form>
			<br />
			<br />
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>