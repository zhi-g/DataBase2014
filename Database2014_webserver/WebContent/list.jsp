<%@page import="graphical.helpers.TablesToHTML"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB2014 - Group 5</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="header">
		<div class="article">
			<%
				int tableSize = 100;
				if (request.getParameter("outSize").equals("max")) {
					tableSize = Integer.MAX_VALUE;
				} else if (request.getParameter("outSize").equals("1000")) {
					tableSize = 1000;
				} else if (request.getParameter("outSize").equals("10")) {
					tableSize = 10;
				}

				if (request.getParameter("table").equals("artist")) {
					out.println("<h1>Browsing artists</h1>");
					ResultSet rs = OracleDatabase.SINGLE.filterArtists(
							request.getParameter("nameFilter"),
							request.getParameter("typeFilter"),
							request.getParameter("genderFilter"),
							request.getParameter("areaFilter"),
							request.getParameter("genreFilter"));
					out.println(TablesToHTML.artistsResultSetToHTML(rs, tableSize));
				}
				if (request.getParameter("table").equals("genre")) {
					out.println("<h1>Browsing genres</h1>");
					ResultSet rs = OracleDatabase.SINGLE.filterGenre(request
							.getParameter("nameFilter"));
					out.println(TablesToHTML.genreResultSetToHTML(rs, tableSize));
				}
			%>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>