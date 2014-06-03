<%@page import="database.connection.DataDeletion"%>
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
				if (null == request.getParameter("type")) {
					out.println("<p>You cannot access urls as you want... ;)</p>");
				} else if (request.getParameter("type").equals("delete")) {					
					if (null != request.getParameter("table")
							&& null != request.getParameter("id")) {
						out.println("<h1>Deletion</h1>");
						String table = request.getParameter("table");
						int id = Integer.parseInt(request.getParameter("id"));
						
						boolean success = false;
						if (table.equalsIgnoreCase("artist")) {
							out.println("<p>The deletion of an artist ");
							success = DataDeletion.removeArtist(id);
						} else if (table.equalsIgnoreCase("genre")) {
							out.println("<p>The deletion of a genre ");
							success = DataDeletion.removeGenre(id);
						} else if (table.equalsIgnoreCase("album")) {
							out.println("<p>The deletion of an album ");
							success = DataDeletion.removeAlbum(id);
						} else if (table.equalsIgnoreCase("track")) {
							out.println("<p>The deletion of a track ");
							success = DataDeletion.removeTrack(id);
						} else {
							out.println("<p>Deleting an element ");
						}
						
						if (success) {
							out.println("succeeded!</p>");
						} else {
							out.println("has failed!</p> <p>An error has occured, please try again later or contact the administrators.</p>");
						}
						out.println("<br/><br/><br/><p></p>");
					}
				} else if (request.getParameter("type").equals("insert")) {
					out.println("<p>Inserting...</p>");
				} else {
					out.println("<p>Unknown action.</p>");
				}
			%>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>