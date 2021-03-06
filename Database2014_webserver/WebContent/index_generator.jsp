<%@page import="database.connection.OracleDatabase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
			<img src="images/music_logo.jpg" alt="" style="margin: 0 50px 0 0;">
			<h1>Music as you never explored it.</h1>
		</div>
	</div>
	<div id="body">
		<div>
			<div class="section">
				<div class="article">
					<h2>Swiss artists</h2>
					<h4>
						Ten artists from Switzerland. <a
							href="list.jsp?nameFilter=&typeFilter=&genderFilter=&areaFilter=Switzerland&genreFilter=&table=artist&outSize=max">See
							all of them</a>
					</h4>
					<p>
						<%
							out.println(OracleDatabase.SINGLE.queryA());
						%>
					</p>
				</div>
				<div class="article">
					<h2>top tens</h2>
					<h4>Top ten groups with most tracks and top ten groups with
						most releases.</h4>
					<table>
						<tr>
							<th><p>Most tracks</p></th>
							<th><p>Most releases</p></th>
						</tr>
						<tr>
							<td>
								<p>
									<%
										out.println(OracleDatabase.SINGLE.queryC());
									%>
								</p>
							</td>
							<td>
								<p>
									<%
										out.println(OracleDatabase.SINGLE.queryD());
									%>
								</p>
							</td>
						</tr>

					</table>
				</div>
				<div class="article">
					<h2>track-boulimic</h2>
					<h4>Albums with highest number of tracks</h4>
					<p><%out.println(OracleDatabase.SINGLE.queryG()); %></p>
				</div>
			</div>
			<div class="section">
				<div class="article">
					<h2>top areas</h2>
					<h4>Areas with most males, females and groups, with artist
						counts per type for each.</h4>
					<p>
						<b>Groups: </b>
						<%
							out.println(OracleDatabase.SINGLE.queryB1());
						%><br /> <b>Females: </b>
						<%
							out.println(OracleDatabase.SINGLE.queryB2());
						%>
						<br /> <b>Males: </b>
						<%
							out.println(OracleDatabase.SINGLE.queryB3());
						%>
					</p>
				</div>
				<div class="article">
					<h2>genre-wide woman</h2>
					<h4>Female artist associated with most genres.</h4>
					<p><% out.println(OracleDatabase.SINGLE.queryE()); %></p>
				</div>
				<div class="article">
					<h2>Women's cities</h2>
					<h4>
						Areas with more women than men.
					</h4>
					<p><% out.println(OracleDatabase.SINGLE.queryF()); %></p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>