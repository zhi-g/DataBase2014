<%@page import="graphical.helpers.TablesToHTML"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<%
		if(request.getParameter("table")=="Artist"){
			ResultSet rs = OracleDatabase.SINGLE.filterArtists(
					request.getParameter("nameFilter"),
					request.getParameter("typeFilter"),
					request.getParameter("genderFilter"),
					request.getParameter("areaFilter"),
					request.getParameter("genreFilter"));
			out.println(TablesToHTML.artistsResultSetToHTML(rs));
		}
	%>
	<ul>
		<li><p>
				<b>Search request:</b>
				<%=request.getParameter("value")%>
			</p></li>
		<li><p>
				<b>In table:</b>
				<%=request.getParameter("table")%>
			</p></li>
	</ul>
	<%@ include file="footer.jsp"%>
</body>
</html>