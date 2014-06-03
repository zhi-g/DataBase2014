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
							style="color: black; text-decoration: underline;"
							href="http://localhost:8080/Database2014_webserver/list.jsp?nameFilter=&amp;typeFilter=&amp;genderFilter=&amp;areaFilter=Switzerland&amp;genreFilter=&amp;table=artist&amp;outSize=max">See
							all of them</a>
					</h4>
					<p>
						Felix Mueller<br>Adam Szijarto<br>The Kick<br>Morph<br>Liz
						Stoussi<br>Andy Scherrer<br>Martin Stadelmann<br>Erik
						Truffaz Quartet<br>Christian Käufeler<br>Guy Pelozzi<br>

					</p>
				</div>
				<div class="article">
					<h2>top tens</h2>
					<h4>Top ten groups with most tracks and top ten groups with
						most releases.</h4>
					<p></p>
					<table>
						<tbody>
							<tr>
								<th><p>Most tracks</p></th>
								<th><p>Most releases</p></th>
							</tr>
							<tr>
								<td>
									<p>
										U2<br>The Beatles<br>Grateful Dead<br>Nirvana<br>Pearl
										Jam<br>Metallica<br>Bob Marley &amp; The Wailers<br>The
										Rolling Stones<br>Pink Floyd<br>Duke Ellington &amp;
										His Orchestra<br>

									</p>
								</td>
								<td>
									<p>
										The Beatles<br>Bob Marley &amp; The Wailers<br>Duke
										Ellington &amp; His Orchestra<br>Pearl Jam<br>Depeche
										Mode<br>Grateful Dead<br>U2<br>The Who<br>The
										Beach Boys<br>The Kinks<br>

									</p>
								</td>
							</tr>

						</tbody>
					</table>
					<p></p>
				</div>
				<div class="article">
					<h2>track-boulimic</h2>
					<h4>Albums with highest number of tracks</h4>
					<p>
						Sony Sound Series: Loops &amp; Samples: On the Jazz Tip, CD, (id:
						785558)<br>
					</p>
				</div>
			</div>
			<div class="section">
				<div class="article">
					<h2>top areas</h2>
					<h4>Areas with most males, females and groups, with artist
						counts per type for each.</h4>
					<p>
						<b>Groups: </b> United States - 20084<br> <br> <b>Females:
						</b> United States - 28580<br> <br> <b>Males: </b> United
						States - 6617<br>

					</p>
				</div>
				<div class="article">
					<h2>genre-wide woman</h2>
					<h4>Female artist associated with most genres.</h4>
					<p>Cornelia Dahlgren</p>
				</div>
				<div class="article">
					<h2>Women's cities</h2>
					<h4>Areas with more women than men.</h4>
					<p>
						Utah<br>Yukon<br>Monaco<br>Munich<br>Zagreb<br>Andorra<br>Lebanon<br>Cambodia<br>San
						Jose<br>Indonesia<br>Littleton<br>Nottingham<br>St.
						John's<br>Wellington<br>Hämeenlinna<br>Indianapolis<br>Western
						Sahara<br>
					</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>