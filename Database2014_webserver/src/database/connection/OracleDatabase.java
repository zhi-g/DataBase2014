package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public enum OracleDatabase {
	SINGLE;

	private Connection mConnection;

	public Connection getConnection() {
		return mConnection;
	}

	public String queryA() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "SELECT a.name " + "FROM artist a, area r "
				+ "WHERE a.areaid = r.id AND r.name = 'Switzerland'";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			while (rs.next() && count < 10) {
				String name = rs.getString("name");
				result = result + name + "<br/>";
				count++;
			}
			System.out.println("Done query A");
		} catch (SQLException e) {
			System.err.println("Could not get A");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	/**
	 * Groups
	 */
	public String queryB1() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select name, countArtists from area r, "
				+ "(select t.areaid, count(*) as countArtists "
				+ "from artist t where t.type = 'Group' "
				+ "and t.areaid is not null group by t.areaid "
				+ "order by countArtists DESC) where r.id = areaid "
				+ "and rownum <=1";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String name = rs.getString("name");
				String count = rs.getString("countArtists");
				result = result + name + " - " + count + "<br/>";
			}
			System.out.println("Done query B1");
		} catch (SQLException e) {
			System.err.println("Could not get B1");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	/**
	 * Males
	 */
	public String queryB2() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select name, countArtists  from area r,("
				+ "select t.areaid,  count(*) as countArtists "
				+ "from artist t "
				+ "where t.areaid is not null and t.gender = 'Male' "
				+ "group by t.areaid "
				+ "order by countArtists DESC) where r.id = areaid and rownum <=1";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String name = rs.getString("name");
				String count = rs.getString("countArtists");
				result = result + name + " - " + count + "<br/>";
			}
			System.out.println("Done query B2");
		} catch (SQLException e) {
			System.err.println("Could not get B2");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	/**
	 * Females
	 */
	public String queryB3() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select name, countArtists  from area r,("
				+ "select t.areaid,  count(*) as countArtists "
				+ "from artist t "
				+ "where t.areaid is not null and t.gender = 'Female' "
				+ "group by t.areaid "
				+ "order by countArtists DESC) where r.id = areaid and rownum <=1";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String name = rs.getString("name");
				String count = rs.getString("countArtists");
				result = result + name + " - " + count + "<br/>";
			}
			System.out.println("Done query B3");
		} catch (SQLException e) {
			System.err.println("Could not get B3");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	/**
	 * names of 10 groups with the most recorded tracks
	 */
	public String queryC() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select * from (select name from "
				+ "(select t.artistid, count(*) as countRecordedTracks "
				+ "from artist_song t group by t.artistid "
				+ "order by countRecordedTracks desc"
				+ "), artist r where r.id = artistid and r.type = 'Group'"
				+ ") where rownum <=10";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			while (rs.next() && count < 10) {
				String name = rs.getString("name");
				result = result + name + "<br/>";
				count++;
			}
			System.out.println("Done query C");
		} catch (SQLException e) {
			System.err.println("Could not get B1");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	public String queryD() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select * from ("
				+ " select a.name from"
				+ " (select art.artistid, count(distinct a1.releasename) as countRecordings"
				+ " from album a1, track t, artist_song art"
				+ " where art.trackid = t.id and t.recordingid = a1.id"
				+ " group by art.artistid"
				+ " order by countRecordings desc) res, artist a"
				+ " where a.id =res.artistid and a.type = 'Group'"
				+ ") where rownum<=10";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			while (rs.next() && count < 10) {
				String name = rs.getString("name");
				result = result + name + "<br/>";
				count++;
			}
			System.out.println("Done query D");
		} catch (SQLException e) {
			System.err.println("Could not get D");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	public String queryE() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;

		String query = "Select name "
				+ " From(Select art.name, (count(DISTINCT Genre.name)) AS countGenre "
				+ " From Artist Art,Artist_genre AG, Genre Genre "
				+ " WHERE art.gender = 'Female' AND art.id=ag.artistid AND ag.genreID=genre.id "
				+ " group BY art.name ORDER BY countGenre DESC)"
				+ " where rownum<=1";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			result = rs.getString("name");
			System.out.println("Done query E");
		} catch (SQLException e) {
			System.err.println("Could not get E");
			SQLHelper.printSQLException(e);
		}
		return result;

	}

	public String queryF() throws SQLException {
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "";

		Statement stmt = null;

		String query = "select r1.name from (Select Area.name, area.id, count(DISTINCT Artist.name) AS countFemale "
				+ "	From Artist, Area "
				+ " WHERE Artist.gender='Female' AND Artist.areaID=Area.ID "
				+ " group BY area.name, area.id) r1,"
				+ " (Select Area.name, area.id, count(DISTINCT Artist.name) AS countMale"
				+ " From Artist, Area "
				+ " WHERE Artist.gender='Male'"
				+ " AND Artist.areaID=Area.ID "
				+ " group BY area.name, area.id) r2"
				+ " where countFemale > countMale and r1.id = r2.id";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("name");
				result = result + name + "<br/>";
			}
			System.out.println("Done query F");
		} catch (SQLException e) {
			System.err.println("Could not get F");
			SQLHelper.printSQLException(e);
		}
		return result;

	}

	public String queryG() throws SQLException {
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "";

		Statement stmt = null;

		String query = "select album.* from ("
				+ " select mediumid, count(t.id)" + " from track t"
				+ " group by t.mediumid"
				+ " having  count(t.id) >=  (select max(count(t1.id))"
				+ " from track t1"
				+ " group by t1.mediumid)), album where mediumid = album.id";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("releasename");
				result = result + name + ", " + rs.getString("format")
						+ ", (id: " + rs.getString("id") + ")" + "<br/>";
			}
			System.out.println("Done query G");
		} catch (SQLException e) {
			System.err.println("Could not get G");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	public String queryH() throws SQLException {
		System.out.println("Begin query H");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p><i>Area - Name</i><br/>";

		Statement stmt = null;

		try {
			// top female
			String query = "SELECT m1.name, m2.name FROM (SELECT b.name, a.areaid, a.crt, rank() over (partition by a.areaid order by a.crt desc) rnk FROM artist b, (select artid, areaid, crt from (select t1.artistid as artid, count(*) as crt from artist_song t1 group by t1.artistid order by crt DESC), artist where artid = id ) a WHERE b.gender = 'Female' and a.artid = b.id) m1, area m2 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in (select distinct areaid from (select artist.areaid as areaid, count(distinct artist.name) as cnt from artist where artist.areaid is not null group by artist.areaid) where cnt > 29) ORDER BY m2.name";
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			result += "</p><h3>Females</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				String area = rs.getString(2);
				result = result + area + " - " + name + "<br/>";
			}

			// top male
			query = "SELECT m1.name, m2.name FROM (SELECT b.name, a.areaid, a.crt, rank() over (partition by a.areaid order by a.crt desc) rnk FROM artist b, (select artid, areaid, crt from (select t1.artistid as artid, count(*) as crt from artist_song t1 group by t1.artistid order by crt DESC), artist where artid = id ) a WHERE b.gender = 'Male' and a.artid = b.id) m1, area m2 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in (select distinct areaid from (select artist.areaid as areaid, count(distinct artist.name) as cnt from artist where artist.areaid is not null group by artist.areaid) where cnt > 29) ORDER BY m2.name";
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
			result += "</p><h3>Males</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				String area = rs.getString(2);
				result = result + area + " - " + name + "<br/>";
			}

			// top group
			query = "SELECT m1.name, m2.name FROM (SELECT b.name, a.areaid, a.crt, rank() over (partition by a.areaid order by a.crt desc) rnk FROM artist b, (select artid, areaid, crt from (select t1.artistid as artid, count(*) as crt from artist_song t1 group by t1.artistid order by crt DESC), artist where artid = id ) a WHERE b.type = 'Other' and a.artid = b.id) m1, area m2 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in (select distinct areaid from (select artist.areaid as areaid, count(distinct artist.name) as cnt from artist where artist.areaid is not null group by artist.areaid) where cnt > 29) ORDER BY m2.name";
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
			result += "</p><h3>Groups</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				String area = rs.getString(2);
				result = result + area + " - " + name + "<br/>";
			}
			result += "</p>";
			System.out.println("Done query H");
		} catch (SQLException e) {
			System.err.println("Could not get H");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryI() throws SQLException {
		System.out.println("Begin query I");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "select name from (Select distinct song.name , count(*) as counter from track, song, artist, artist_song ass, album where ass.artistid = artist.id and ass.trackid = track.id and artist.name = 'Metallica' and track.mediumid = album.id and track.recordingid = song.id group by song.name order by counter desc) where rownum <=25";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int count = 0;
			while (rs.next() && count < 25) {
				String name = rs.getString(1);
				result = result + name + "<br/>";
				count++;
			}
			
			result += "</p>";
			System.out.println("Done query I");
		} catch (SQLException e) {
			System.err.println("Could not get I");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryJ() throws SQLException {
		System.out.println("Begin query J");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p><i>Genre: Artist name</i><br/>";

		Statement stmt = null;

		try {
			String query = "select t2.name, t1.name from ( select gid, name, rank() over(partition by gid order by nrt desc) rnk from (select ag.genreid as gid, ag.artistid as aid, tr.crt as nrt, art.name as name from (SELECT genreid FROM (SELECT genreid, COUNT(DISTINCT artistid) AS cnt FROM artist_genre GROUP BY genreid ORDER BY cnt DESC) WHERE rownum<11) g, artist_genre ag, artist art, (select artistid, count(*) as crt from artist_song group by artistid) tr where art.gender = 'Female' and tr.artistid = art.id and g.genreid = ag.genreid and ag.artistid = art.id)) t1, genre t2 where t1.rnk = 1 and t2.id = gid";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int count = 0;
			while (rs.next() && count < 10) {
				String t1 = rs.getString(1);
				String t2 = rs.getString(2);
				result = result + t1 + ": " + t2 + "<br/>";
				count++;
			}
			
			result += "</p>";
			System.out.println("Done query J");
		} catch (SQLException e) {
			System.err.println("Could not get J");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	public String queryK() throws SQLException {
		System.out.println("Begin query K");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			// no female
			String query = "select distinct g1.name from genre g1 where g1.id not in (select distinct g.id from genre G , artist A, artist_genre AG where a.id = ag.artistid and ag.genreid = g.id and a.gender = 'Female')";
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			result += "</p><h3>No females</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				result = result + "- " + name + "<br/>";
			}

			// no male
			query = "select distinct g1.name from genre g1 where g1.id not in (select distinct g.id from genre G , artist A, artist_genre AG where a.id = ag.artistid and ag.genreid = g.id and a.gender = 'Male')";
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
			result += "</p><h3>No males</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				result = result + "- " + name + "<br/>";
			}

			// no group
			query = "select distinct g1.name from genre g1 where g1.id not in (select distinct g.id from genre G , artist A, artist_genre AG where a.id = ag.artistid and ag.genreid = g.id and a.type = 'Group')";
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
			result += "</p><h3>No groups</h3><p><br/>";
			while (rs.next()) {
				String name = rs.getString(1);
				result = result + "- " + name + "<br/>";
			}
			result += "</p>";
			System.out.println("Done query K");
		} catch (SQLException e) {
			System.err.println("Could not get K");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	public String queryL() throws SQLException {
		System.out.println("Begin query L");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "SELECT t4.name, t3.name, t3.countRecordedTracks FROM (SELECT t2.areaid, t2.id, t2.name, t2.countRecordedTracks, row_number() over(partition BY t2.areaid order by t2.countRecordedTracks DESC) rnk FROM (SELECT id, r.name, areaid, countRecordedTracks FROM (SELECT t.artistid , COUNT(*) AS countRecordedTracks FROM artist_song t GROUP BY t.artistid) t1, artist r WHERE r.id   = t1.artistid AND r.gender = 'Male') t2) t3, area t4 WHERE t3.rnk  <=5 and t4.id = t3.areaid AND t3.areaid IN (SELECT DISTINCT area.id FROM area, (SELECT artist.areaid, COUNT(DISTINCT artist.name) AS cnt FROM artist WHERE artist.areaid IS NOT NULL AND artist.type = 'Group' GROUP BY artist.areaid ORDER BY cnt DESC) t WHERE area.id = t.areaid AND t.cnt >= 10)";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			String currentArea = "";
			while (rs.next()) {
				String area = rs.getString(1);
				String name = rs.getString(2);
				int count = rs.getInt(3);
				if (!currentArea.equals(area)) {
					result += "</p><h3>" + area + "</h3><p>";
					currentArea = area;
				}
				result = result + name + " - " + count + "<br/>";
			}
			
			result += "</p>";
			System.out.println("Done query L");
		} catch (SQLException e) {
			System.err.println("Could not get L");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryM() throws SQLException {
		System.out.println("Begin query M");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "select distinct(1) from (select g.id, count(distinct meds.trackid) as cnt from artist g, (select s1.artistid, s1.trackid from artist_song s1, artist_song s2, track t1, track t2 where s1.artistid != s2.artistid and t1.id = s1.trackid and t2.id = s2.trackid and t1.mediumid = t2.mediumid) meds where g.type = 'Group' and g.id = meds.artistid group by g.id order by cnt DESC) where rownum<11";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int count = 0;
			while (rs.next() && count < 10) {
				String groupName = rs.getString(1);
				result = result + groupName + "<br/>";
				count++;
			}
			
			result += "</p>";
			System.out.println("Done query M");
		} catch (SQLException e) {
			System.err.println("Could not get M");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryO() throws SQLException {
		System.out.println("Begin query O");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "select distinct releasename from (select t1.releaseid from (select releaseid,count(*) as counter from album group by album.releaseid order by counter desc) t1 where counter = (select max(counter) from (select releaseid,count(*) as counter from album where releaseid is not null group by album.releaseid))) fat1, album where fat1.releaseid = album.releaseid";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString(1);
				result = result + name + "<br/>";
			}
			
			result += "</p>";
			System.out.println("Done query O");
		} catch (SQLException e) {
			System.err.println("Could not get O");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryP() throws SQLException {
		System.out.println("Begin query P");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "select genre.name from genre, (select * from (select fat2.id, count(*) as cnt from (select  genre.name,genre.id from genre, artist_genre ag,artist, (select artist.name,artist.id, table1.counter from artist, (select A1.id,count(*) as counter from artist A1, artist_genre AG, genre G where A1.id = AG.artistid and Ag.genreid = G.id group by A1.id order by counter desc) table1 where artist.id = table1.id) fat1 where artist.id = fat1.id and artist.id = ag.artistid and ag.genreid = genre.id) fat2 group by fat2.id order by cnt desc) where rownum <=1) fat3 where genre.id = fat3.id";

			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String name = rs.getString(1);
				result = result + name + "<br/>";
			}
			
			result += "</p>";
			System.out.println("Done query P");
		} catch (SQLException e) {
			System.err.println("Could not get P");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryQ() throws SQLException {
		System.out.println("Begin query Q");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p>";

		Statement stmt = null;

		try {
			String query = "select name, cnt from(select s.name, count(distinct s.id) as cnt from song s group by s.name order by cnt DESC) where rownum<=5";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int count = 0;
			while (rs.next() && count < 5) {
				String songName = rs.getString(1);
				int songCount = rs.getInt(2);
				result = result + songName + " - " + songCount + "<br/>";
				count++;
			}
			
			result += "</p>";
			System.out.println("Done query Q");
		} catch (SQLException e) {
			System.err.println("Could not get Q");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryR() throws SQLException {
		System.out.println("Begin query R");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p><i>Artist - tracks - mediums</i><br/>";

		Statement stmt = null;

		try {
			String query = "select * from (select  t3.artistid, count(distinct trackid) as cnt, count(distinct mediumid) as cnm from track t2, artist_song t3 where t3.trackid = t2.id group by t3.artistid order by (cnt/cnm) DESC) where rownum<11";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int count = 0;
			while (rs.next() && count < 10) {
				String artist = rs.getString(1);
				int trackCount = rs.getInt(2);
				int mediumCount = rs.getInt(3);
				result = result + artist + " - " + trackCount  + " - " + mediumCount + "<br/>";
				count++;
			}
			
			result += "</p>";
			System.out.println("Done query R");
		} catch (SQLException e) {
			System.err.println("Could not get R");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	public String queryS() throws SQLException {
		System.out.println("Begin query S");
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}

		String result = "<p><i>Hit artist - Hit ability</i>";

		Statement stmt = null;

		try {
			String query = "select id, avs FROM (SELECT t2.id, COUNT(t1.crs) AS cns, AVG(t1.crs)   AS avs FROM (SELECT * FROM (SELECT recordingid, COUNT(DISTINCT mediumid) AS crs FROM track GROUP BY recordingid) WHERE crs>=100) t1, artist t2, track t3, artist_song t4 WHERE t1.recordingid = t3.recordingid AND t3.id = t4.trackid AND t4.artistid = t2.id GROUP BY t2.id) WHERE cns>=10";
			
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int artistID = rs.getInt(1);
				int avg = rs.getInt(2);
				result = result + artistID + " - " + avg + "<br/>";
			}
			
			result += "</p>";
			System.out.println("Done query S");
		} catch (SQLException e) {
			System.err.println("Could not get S");
			result = "<p>An error occured.</p>";
			SQLHelper.printSQLException(e);
		}
		return result;
	}
	
	/**
	 * to filter according to genre
	 */
	public ResultSet filterArtists(String genreFilter) {
		if (null == mConnection || null == genreFilter) {
			return null;
		}

		// SQLHelper.inputSanitization(nameFilter);
		// SQLHelper.inputSanitization(typeFilter);
		// SQLHelper.inputSanitization(genderFilter);
		// SQLHelper.inputSanitization(areaFilter);
		// SQLHelper.inputSanitization(genreFilter);

		/* QUERY GENERATION */
		String query = "SELECT Artist.name, Artist.type, Artist.gender, Area.name, Genre.name, Artist.id"
				+ " FROM Artist,Area,Artist_Genre,Genre" + " WHERE ";

		query += "Artist.ID=Artist_Genre.artistID AND ";
		query += "Genre.ID=Artist_Genre.genreID AND ";
		query += "Artist.areaID=Area.id AND ";

		if (!genreFilter.equals(""))
			query += "Genre.name like ? AND ";

		query = query.substring(0, query.length() - 5); // remove last 'AND'
		query += " ORDER BY Artist.name";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);

			/* Replacing the '?' */
			if (!genreFilter.equals("")) {
				stmt.setString(1, "%" + genreFilter + "%");
			}

			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("Could not execute query (on artists)");
			SQLHelper.printSQLException(e);
		}

		return rs;
	}

	public ResultSet filterArtists(String nameFilter, String typeFilter,
			String genderFilter, String areaFilter) {
		if (null == mConnection || null == nameFilter || null == typeFilter
				|| null == genderFilter || null == areaFilter) {
			return null;
		}

		// SQLHelper.inputSanitization(nameFilter);
		// SQLHelper.inputSanitization(typeFilter);
		// SQLHelper.inputSanitization(genderFilter);
		// SQLHelper.inputSanitization(areaFilter);
		// SQLHelper.inputSanitization(genreFilter);

		/* QUERY GENERATION */
		String query = "SELECT Artist.name, Artist.type, Artist.gender, Area.name, Artist.id"
				+ " FROM Artist,Area" + " WHERE ";

		query += "Artist.areaID=Area.id AND ";

		if (!nameFilter.equals(""))
			query += "Artist.name like ? AND ";
		if (!typeFilter.equals(""))
			query += "Artist.type like ? AND ";
		if (!genderFilter.equals(""))
			query += "Artist.gender like ? AND ";
		if (!areaFilter.equals(""))
			query += "Area.name like ? AND ";

		query = query.substring(0, query.length() - 5); // remove last 'AND'
		query += " ORDER BY Artist.name";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);

			/* Replacing the '?' */
			int index = 1;
			if (!nameFilter.equals("")) {
				stmt.setString(index, "%" + nameFilter + "%");
				System.out.println("name in the query: " + nameFilter);
				index++;
			}
			if (!typeFilter.equals("")) {
				stmt.setString(index, "%" + typeFilter + "%");
				index++;
			}
			if (!genderFilter.equals("")) {
				stmt.setString(index, "%" + genderFilter + "%");
				index++;
			}
			if (!areaFilter.equals("")) {
				stmt.setString(index, "%" + areaFilter + "%");
				index++;
			}

			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("Could not execute query (on artists)");
			SQLHelper.printSQLException(e);
		}

		return rs;
	}

	public ResultSet filterGenre(String nameFilter) {
		if (null == mConnection || null == nameFilter) {
			return null;
		}
		if (null == mConnection) {
			return null;
		}

		/* QUERY GENERATION */
		String query = "SELECT Genre.name, Genre.id FROM Genre";
		if (!nameFilter.equals("")) {
			query += " WHERE Genre.name like ?";
		}
		query += "ORDER BY Genre.name";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);
			stmt.setString(1, "%" + nameFilter + "%");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("Could not execute query (on genre)");
			SQLHelper.printSQLException(e);
		}

		return rs;
	}

	public ResultSet filterAlbum(String Alb_name, String Artist_name,
			String Format_name) {
		if (null == mConnection || null == Alb_name || null == Artist_name
				|| null == Format_name) {
			return null;
		}

		/* QUERY GENERATION */
		String query = "SELECT UNIQUE album.releasename, artist.name, album.format, album.id FROM Album, artist_song,artist,track WHERE ";

		query += "Artist_song.artistid=Artist.ID AND ";
		query += "artist_song.trackid=track.ID AND ";
		query += "track.mediumID=album.id AND ";

		if (!Alb_name.equals(""))
			query += "Album.releasename like ? AND ";
		if (!Format_name.equals(""))
			query += "Album.format like ? AND ";
		if (!Artist_name.equals(""))
			query += "Artist.name = ? AND ";
		query = query.substring(0, query.length() - 5); // remove last 'AND'
		query += " ORDER BY Album.releasename";
		System.out.println("Query: " + query);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);

			/* Replacing the '?' */
			int index = 1;
			if (!Alb_name.equals("")) {
				stmt.setString(index, "%" + Alb_name + "%");
				index++;
			}
			if (!Artist_name.equals("")) {
				stmt.setString(index, Artist_name);
				index++;
			}
			if (!Format_name.equals("")) {
				stmt.setString(index, "%" + Format_name + "%");
				index++;
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("Could not execute query (on album)");
			SQLHelper.printSQLException(e);
		}
		return rs;
	}

	public ResultSet filterTrack(String trackName, String albumName) {
		if (null == mConnection || null == trackName || null == albumName) {
			return null;
		}

		/* QUERY GENERATION */// TODO incorrect
		String query = "SELECT UNIQUE Song.name, Song.length, Album.releasename, Track.position, Song.id"
				+ " FROM Song, Track, Album "
				+ "WHERE album.id = track.mediumid "
				+ "AND song.id = track.recordingid AND ";

		if (!trackName.equals("")) {
			query += "Song.name like ? AND ";
		}
		if (!albumName.equals("")) {
			query += "Album.releasename = ? AND ";
		}
		query = query.substring(0, query.length() - 5); // remove last 'AND'
		query += "ORDER BY Album.releasename, Track.position";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);

			int index = 1;
			if (!trackName.equals("")) {
				stmt.setString(index, "%" + trackName + "%");
				index++;
			}
			if (!albumName.equals("")) {
				stmt.setString(index, albumName);
			}

			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.err.println("Could not execute query (on tracks)");
			SQLHelper.printSQLException(e);
		}

		return rs;
	}
	
	public String runInsertion(String query) {
		if (null == mConnection) {
			return "<p>An error occured.</p>";
		}
		if (query.toUpperCase().contains("DROP")
				|| query.toUpperCase().contains("ALTER")
				|| !query.toUpperCase().contains("INSERT")) {
			return "<p>You must insert something in the tables!</p>";
		}

		try {
			mConnection.createStatement().executeQuery(query);
			
			System.out.println("Insertion done");
		} catch (SQLException e) {
			System.err.println("Could not insert");
			return "</p><p>" + e.getErrorCode() + " - " + e.getMessage() + "</p>";
		}
		
		return "<p>Insertion complete.</p>";
	}

	/* Insert queries */
	/*
	 * public Status insertIntoGenre(int id, String name){ if (null ==
	 * mConnection) { return Status.ConnectionProblem; } name =
	 * name.replace("'", "''"); // sanitize the name Statement stmt = null;
	 * String query = "select * from (" +
	 * "insert into genre (id, name) values ("+ id + "," +name + ")";
	 * 
	 * try { stmt = mConnection.createStatement(); int rs =
	 * stmt.executeUpdate(query);
	 * 
	 * } catch (SQLException e) { System.err.println("Could not get B1");
	 * SQLHelper.printSQLException(e);
	 * 
	 * }
	 * 
	 * return Status.OK; }
	 */

	private OracleDatabase() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is the Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle driver registered!");

		try {
			mConnection = DriverManager.getConnection(
					"jdbc:oracle:thin:@icoracle.epfl.ch:1521/srso4.epfl.ch",
					"db2014_g05", "elfenlied");
			System.out.println("Connected to database!");
		} catch (SQLException e) {
			System.err.println("Could not connect to DBMS");
			SQLHelper.printSQLException(e);
			mConnection = null;
		}
	}
}

// a changer!
enum Status {
	OK, ConnectionProblem, InsertProblem
}