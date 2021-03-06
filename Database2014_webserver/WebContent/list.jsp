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
<script type="text/javascript">
 function eraseEntry(id, table) {
	if (confirm('Are you sure you want to delete this row?')) {
		window.location = "confirm.jsp?type=delete&table="+table+"&id="+id;
	}
 }
</script>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="header">
		<div class="article">
			<%
				int tableSize = 100;
				if (null != request.getParameter("outSize")) {
					if (request.getParameter("outSize").equals("max")) {
						tableSize = Integer.MAX_VALUE;
					} else if (request.getParameter("outSize").equals("1000")) {
						tableSize = 1000;
					} else if (request.getParameter("outSize").equals("10")) {
						tableSize = 10;
					}
				}

				if (null == request.getParameter("table")) {
					out.println("<h1>Wow, you such a hacker!</h1>");
				} else if (request.getParameter("table").equals("artist")) {
					out.println("<h1>Browsing artists</h1>");
					if (null != request.getParameter("genreFilter")) {
						ResultSet rs = OracleDatabase.SINGLE.filterArtists(request
								.getParameter("genreFilter"));
						out.println(TablesToHTML.artistsGenreResultSetToHTML(rs,
								tableSize));
					} else {
						ResultSet rs = OracleDatabase.SINGLE.filterArtists(
								request.getParameter("nameFilter"),
								request.getParameter("typeFilter"),
								request.getParameter("genderFilter"),
								request.getParameter("areaFilter"));
						out.println(TablesToHTML.artistsResultSetToHTML(rs,
								tableSize));
					}
				} else if (request.getParameter("table").equals("genre")) {
					out.println("<h1>Browsing genres</h1>");
					ResultSet rs = OracleDatabase.SINGLE.filterGenre(request
							.getParameter("nameFilter"));
					out.println(TablesToHTML.genreResultSetToHTML(rs, tableSize));
				} else if (request.getParameter("table").equals("album")) {
					out.println("<h1>Browsing albums</h1>");
					ResultSet rs = OracleDatabase.SINGLE.filterAlbum(
							request.getParameter("nameFilter"),
							request.getParameter("artistFilter"),
							request.getParameter("formatFilter"));
					out.println(TablesToHTML.albumResultSetToHTML(rs, tableSize));
				} else if (request.getParameter("table").equals("track")) {
					out.println("<h1>Browsing tracks</h1>");
					ResultSet rs = OracleDatabase.SINGLE.filterTrack(
							request.getParameter("nameFilter"),
							request.getParameter("releaseFilter"));
					out.println(TablesToHTML.trackResultSetToHTML(rs, tableSize));
				}
			%>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>