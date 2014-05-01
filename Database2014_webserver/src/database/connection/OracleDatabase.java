package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

;

public enum OracleDatabase {
	SINGLE;

	private Connection mConnection;

	public String getFirstTenElementOfALBUM() throws SQLException {
		if (null == mConnection) {
			return null;
		}

		String result = "";

		Statement stmt = null;
		String query = "select ALBUMID, FORMAT, RELEASENAME from ALBUM";

		try {
			stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 0;
			while (rs.next() && count < 10) {
				int albumID = rs.getInt("ALBUMID");
				String format = rs.getString("FORMAT");
				String releaseName = rs.getString("RELEASENAME");
				result = result + albumID + ", " + format + ", " + releaseName
						+ "<br/>";
				count++;
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception:\n--- " + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
	}

	public ResultSet filterArtists(String nameFilter, String typeFilter,
			String genderFilter, String areaFilter, String genreFilter) {

		inputSanitization(nameFilter);
		inputSanitization(typeFilter);
		inputSanitization(genderFilter);
		inputSanitization(areaFilter);
		inputSanitization(genreFilter);
		

		/* QUERY GENERATION */
		String query = "SELECT Artist.name, Artist.type, Artist.gender, Area.name, Genre.name"
				+ "FROM Artist,Area,ArtistGenre,Genre " + "WHERE ";
		if (null != nameFilter)
			query += "Artist.name=" + nameFilter + " AND ";
		if (null != typeFilter)
			query += "Artist.type=" + typeFilter + " AND ";
		if (null != genderFilter)
			query += "Artist.gender=" + genderFilter + " AND ";
		if (null != areaFilter)
			query += "Artist.areaID=Area.areaID AND ";
		query += "Area.name=" + areaFilter + " AND ";
		if (null != genreFilter)
			query += "Artist.artistID=ArtistGenre.artistID AND ";
		query += "Genre.genreID=ArtistGenre.genreID AND ";
		query += "Genre.name=" + genreFilter + " AND ";
		query.substring(0, query.length() - 5); // remove last 'AND'
		query += ";";

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Could not execute query (on artists): "
					+ e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Could not close statement: "
							+ e.getMessage());
				}
			}
		}

		return rs;
	}

	public ResultSet filterGenre(String nameFilter) {
		
		inputSanitization(nameFilter);

		/* QUERY GENERATION */
		String query = "SELECT Genre.name" + "FROM Genre";
		if (null != nameFilter) {
			query += " Genre.name=" + nameFilter + ";";
		} else {
			query += ";";
		}

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = mConnection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Could not execute query (on artists): "
					+ e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Could not close statement: "
							+ e.getMessage());
				}
			}
		}

		return rs;
	}

	private OracleDatabase() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
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
			System.err.println("Could not connect to DBMS:\n--- "
					+ e.getMessage());
			mConnection = null;
		}
	}

	private String inputSanitization(String value) {
		value.replaceAll("\"", "");
		value.replaceAll("'", "");
		value.replaceAll(";", "");
		value.replaceAll("--", "");
		return value;
	}
}
