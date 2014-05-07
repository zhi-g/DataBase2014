package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

;

public enum OracleDatabase {
	SINGLE;

	private Connection mConnection;

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
		} catch (SQLException e) {
			System.err.println("Could not get B1");
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
		} catch (SQLException e) {
			System.err.println("Could not get B1");
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
		} catch (SQLException e) {
			System.err.println("Could not get B1");
			SQLHelper.printSQLException(e);
		}
		return result;
	}

	/**
	 * to filter according to genre
	 */
	public ResultSet filterArtists(String genreFilter) {

		// SQLHelper.inputSanitization(nameFilter);
		// SQLHelper.inputSanitization(typeFilter);
		// SQLHelper.inputSanitization(genderFilter);
		// SQLHelper.inputSanitization(areaFilter);
		// SQLHelper.inputSanitization(genreFilter);

		/* QUERY GENERATION */
		String query = "SELECT Artist.name, Artist.type, Artist.gender, Area.name, Genre.name"
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

		// SQLHelper.inputSanitization(nameFilter);
		// SQLHelper.inputSanitization(typeFilter);
		// SQLHelper.inputSanitization(genderFilter);
		// SQLHelper.inputSanitization(areaFilter);
		// SQLHelper.inputSanitization(genreFilter);

		/* QUERY GENERATION */
		String query = "SELECT Artist.name, Artist.type, Artist.gender, Area.name"
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

		System.out.println("Query: " + query);

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.prepareStatement(query);

			/* Replacing the '?' */
			int index = 1;
			if (!nameFilter.equals("")) {
				stmt.setString(index, "%" + nameFilter + "%");
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

		/* QUERY GENERATION */
		String query = "SELECT Genre.name FROM Genre";
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
