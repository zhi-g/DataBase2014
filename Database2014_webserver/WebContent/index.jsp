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
			<img src="images/music_logo.jpg" alt="">
			<h1>Music as you never explored it.</h1>
		</div>
	</div>
	<div id="body">
		<div>
			<div class="section">
				<div class="article">
					<h2>Swiss artists</h2>
					<p>
						Ten random artists from Switzerland. <a href="#">See all of
							them</a>
					</p>
					<p>
						<%
							out.println(OracleDatabase.SINGLE.getTenRandomSwissArtists());
						%>
					</p>
					<!--
					<table>
						<tr>
							<th>Name</th>
							<th>Type</th>
							<th>Area</th>
						</tr>
						<tr>
						 	<td>Eluveite</td>
							<td>Group</td>
							<td>Zürich</td>
	  					</tr>
					</table>
					-->
				</div>
				<div class="article">
					<h2>top tens</h2>
					<p>Top ten groups with most records and top ten groups with
						most releases.</p>
					<p>Et Epigonus quidem amictu tenus philosophus, ut apparuit,
						prece frustra temptata, sulcatis lateribus mortisque metu admoto
						turpi confessione cogitatorum socium, quae nulla erant, fuisse
						firmavit cum nec vidisset quicquam nec audisset penitus expers
						forensium rerum; Eusebius vero obiecta fidentius negans, suspensus
						in eodem gradu constantiae stetit latrocinium illud esse, non
						iudicium clamans. Sed si ille hac tam eximia fortuna propter
						utilitatem rei publicae frui non properat, ut omnia illa
						conficiat, quid ego, senator, facere debeo, quem, etiamsi ille
						aliud vellet, rei publicae consulere oporteret?</p>
				</div>
				<div class="article">
					<h2>track-boulimic</h2>
					<p>Album with highest number of tracks</p>
					<p>Et Epigonus quidem amictu tenus philosophus, ut apparuit,
						prece frustra temptata, sulcatis lateribus mortisque metu admoto
						turpi confessione cogitatorum socium, quae nulla erant, fuisse
						firmavit cum nec vidisset quicquam nec audisset penitus expers
						forensium rerum; Eusebius vero obiecta fidentius negans, suspensus
						in eodem gradu constantiae stetit latrocinium illud esse, non
						iudicium clamans. Sed si ille hac tam eximia fortuna propter
						utilitatem rei publicae frui non properat, ut omnia illa
						conficiat, quid ego, senator, facere debeo, quem, etiamsi ille
						aliud vellet, rei publicae consulere oporteret?</p>
				</div>
			</div>
			<div class="section">
				<div class="article">
					<h2>top areas</h2>
					<h3>Areas with most males, females and groups, with artist
						counts per type for each.</h3>
					<p>Et Epigonus quidem amictu tenus philosophus, ut apparuit,
						prece frustra temptata, sulcatis lateribus mortisque metu admoto
						turpi confessione cogitatorum socium, quae nulla erant, fuisse
						firmavit cum nec vidisset quicquam nec audisset penitus expers
						forensium rerum; Eusebius vero obiecta fidentius negans, suspensus
						in eodem gradu constantiae stetit latrocinium illud esse, non
						iudicium clamans. Sed si ille hac tam eximia fortuna propter
						utilitatem rei publicae frui non properat, ut omnia illa
						conficiat, quid ego, senator, facere debeo, quem, etiamsi ille
						aliud vellet, rei publicae consulere oporteret?</p>
				</div>
				<div class="article">
					<h2>genre-wide woman</h2>
					<p>Female artist associated with most genres.</p>
					<p>Et Epigonus quidem amictu tenus philosophus, ut apparuit,
						prece frustra temptata, sulcatis lateribus mortisque metu admoto
						turpi confessione cogitatorum socium, quae nulla erant, fuisse
						firmavit cum nec vidisset quicquam nec audisset penitus expers
						forensium rerum; Eusebius vero obiecta fidentius negans, suspensus
						in eodem gradu constantiae stetit latrocinium illud esse, non
						iudicium clamans. Sed si ille hac tam eximia fortuna propter
						utilitatem rei publicae frui non properat, ut omnia illa
						conficiat, quid ego, senator, facere debeo, quem, etiamsi ille
						aliud vellet, rei publicae consulere oporteret?</p>
				</div>
				<div class="article">
					<h2>Women's cities</h2>
					<p>
						Ten random cities with more women than men. <a href="#">See
							all cities</a>
					</p>
					<p>Et Epigonus quidem amictu tenus philosophus, ut apparuit,
						prece frustra temptata, sulcatis lateribus mortisque metu admoto
						turpi confessione cogitatorum socium, quae nulla erant, fuisse
						firmavit cum nec vidisset quicquam nec audisset penitus expers
						forensium rerum; Eusebius vero obiecta fidentius negans, suspensus
						in eodem gradu constantiae stetit latrocinium illud esse, non
						iudicium clamans. Sed si ille hac tam eximia fortuna propter
						utilitatem rei publicae frui non properat, ut omnia illa
						conficiat, quid ego, senator, facere debeo, quem, etiamsi ille
						aliud vellet, rei publicae consulere oporteret?</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>