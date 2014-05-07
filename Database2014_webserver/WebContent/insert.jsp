<%@page import="graphical.helpers.TablesToHTML"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Hello ploplop -->
	<%
		/*Here we insert new data when needed*/
		if(null!=request.getParameter("table")){
			if(request.getParameter("table").equals("artist")){}
				//inserting into artists
		}
	%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB2014 - Group 5</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="body">
	<h1>Insert new data</h1>
		<div>
			<div class="section">
				<div class="article">
					<h2>Artists</h2>
					<form action="insert.jsp">
						<input type="text" name="artistID" placeholder="ID"> <input
							type="text" name="name" placeholder="Name"> <input
							type="text" name="type" placeholder="Type"> <input
							type="text" name="gender" placeholder="Gender (M/F)"> <input
							type="text" name="areaID" placeholder="AreaID"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="artist"> <input type="submit" value="GO" />
					</form>
				</div>
				<div class="article">
					<h2>Artists</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							type="text" name="typeFilter" placeholder="Type"> <input
							type="text" name="genderFilter" placeholder="Gender"> <input
							type="text" name="areaFilter" placeholder="Area"> <input
							type="text" name="genreFilter" placeholder="Genre"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="artist"> <input type="submit" value="GO" />
					</form>
				</div>
			</div>
			<div class="section">
				<div class="article">
					<h2>Genre</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="genre"> <input type="submit" value="GO" />
					</form>
				</div>
				<div class="article">
					<h2>Artists</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							type="text" name="typeFilter" placeholder="Type"> <input
							type="text" name="genderFilter" placeholder="Gender"> <input
							type="text" name="areaFilter" placeholder="Area"> <input
							type="text" name="genreFilter" placeholder="Genre"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="artist"> <input type="submit" value="GO" />
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>