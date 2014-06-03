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
	<div id="body">
		<div>
			<div class="section">
				<div class="article" >
					<h2>Queries</h2>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=H">Query H</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=I">Query I</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=J">Query J</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=K">Query K</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=L">Query L</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=M">Query M</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=N">Query N</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=O">Query O</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=P">Query P</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=Q">Query Q</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=R">Query R</a>
					</h3>
					<h3 style="text-align: left;">
						<a href="tops.jsp?query=S">Query S</a>
					</h3>
				</div>
			</div>
			<%
				if (null != request.getParameter("query")) {
					out.println("<div class=\"section\"><div class=\"article\" style=\"padding=20px\">");
					out.println("<h2>Results</h2>");
					String query = request.getParameter("query");
					if (query.equalsIgnoreCase("H")) {
						out.println(OracleDatabase.SINGLE.queryH());
					} else if (query.equalsIgnoreCase("I")) {
						out.println(OracleDatabase.SINGLE.queryI());
					} else if (query.equalsIgnoreCase("J")) {
						out.println(OracleDatabase.SINGLE.queryJ());
					} else if (query.equalsIgnoreCase("K")) {
						out.println(OracleDatabase.SINGLE.queryK());
					} else if (query.equalsIgnoreCase("L")) {
						out.println(OracleDatabase.SINGLE.queryL());
					} else if (query.equalsIgnoreCase("M")) {
						out.println(OracleDatabase.SINGLE.queryM());
					} else if (query.equalsIgnoreCase("N")) {
						//out.println(OracleDatabase.SINGLE.queryN());
					} else if (query.equalsIgnoreCase("O")) {
						out.println(OracleDatabase.SINGLE.queryO());
					} else if (query.equalsIgnoreCase("P")) {
						out.println(OracleDatabase.SINGLE.queryP());
					} else if (query.equalsIgnoreCase("Q")) {
						out.println(OracleDatabase.SINGLE.queryQ());
					} else if (query.equalsIgnoreCase("R")) {
						out.println(OracleDatabase.SINGLE.queryR());
					} else if (query.equalsIgnoreCase("S")) {
						out.println(OracleDatabase.SINGLE.queryS());
					} else {
						out.println("<h3>Unknown query</h3>");
					}
					out.println("</div></div>");
				}
			%>

		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>