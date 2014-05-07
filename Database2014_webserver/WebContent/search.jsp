<%@page import="graphical.helpers.TablesToHTML"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setContentType("text/html; charset=utf-8");
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
		<h1>Search into data</h1>
		<div>
			<div class="section">
				<div class="article">
					<h2>Artists</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							type="text" name="typeFilter" placeholder="Type"> <br />
						<input type="text" name="genderFilter" placeholder="Gender">
						<input type="text" name="areaFilter" placeholder="Area"> <br />
						<input style="visibility: hidden; width: 0px;" type="text"
							name="table" value="artist"><input type="submit"
							value="GO" /> <select name="outSize">
							<option value="10">10</option>
							<option value="100">100</option>
							<option value="1000">1000</option>
							<option value="max">Maximum</option>
						</select>
					</form>
				</div>
				<div class="article">
					<h2>Albums</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							type="text" name="formatFilter" placeholder="Format"> <input
							type="text" name="artistFilter" placeholder="Artist"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="album"> <input type="submit" value="GO" /> <select
							name="outSize">
							<option value="10">10</option>
							<option value="100">100</option>
							<option value="1000">1000</option>
							<option value="max">Maximum</option>
						</select>
					</form>
				</div>
			</div>
			<div class="section">
				<div class="article">
					<h2>Genre</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Name"> <input
							style="visibility: hidden; width: 0px;" type="text" name="table"
							value="genre"> <input type="submit" value="GO" /> <select
							name="outSize">
							<option value="10">10</option>
							<option value="100">100</option>
							<option value="1000">1000</option>
							<option value="max">Maximum</option>
						</select>
					</form>
				</div>
				<div class="article">
					<h2>Track</h2>
					<form action="list.jsp">
						<input type="text" name="nameFilter" placeholder="Track name">
						<input type="text" name="releaseFilter" placeholder="Album name">
						<input style="visibility: hidden; width: 0px;" type="text"
							name="table" value="track"> <input type="submit"
							value="GO" /> <select name="outSize">
							<option value="10">10</option>
							<option value="100">100</option>
							<option value="1000">1000</option>
							<option value="max">Maximum</option>
						</select>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>