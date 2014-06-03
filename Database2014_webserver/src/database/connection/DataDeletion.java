package database.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDeletion {
	public static boolean removeArtist(int id) {
		boolean success = false;
		System.out.print("Deleting artist with id " + id + " ... ");

		Connection connection = OracleDatabase.SINGLE.getConnection();
		if (null != connection) {
			/* remove relation between all genres and this artist */
			String query = "DELETE FROM Artist_Genre WHERE Artist_Genre.artistid = "
					+ id;
			try {
				connection.createStatement().executeQuery(query);

				success = true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}

			/* remove relation between all tracks and this artist */
			query = "DELETE FROM Artist_Song WHERE Artist_Song.artistid = "
					+ id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}

			/* remove this artist */
			query = "DELETE FROM Artist WHERE Artist.id = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
		}

		printEndOfLine(success);
		return success;
	}

	public static boolean removeGenre(int id) {
		boolean success = false;
		System.out.print("Deleting genre with id " + id + " ... ");

		Connection connection = OracleDatabase.SINGLE.getConnection();
		if (null != connection) {
			/* remove relation between all artists and this genre */
			String query = "DELETE FROM Artist_Genre WHERE Artist_Genre.genreid = "
					+ id;
			try {
				connection.createStatement().executeQuery(query);

				success = true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}

			/* remove this genre */
			query = "DELETE FROM Genre WHERE Genre.id = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
		}

		printEndOfLine(success);
		return success;
	}

	public static boolean removeAlbum(int id) {
		boolean success = false;
		System.out.print("Deleting album with id " + id + " ... ");

		Connection connection = OracleDatabase.SINGLE.getConnection();
		if (null != connection) {
			/* remove all tracks in Artist_Song that are related to this album */
			String query = "SELECT Track.id FROM Track WHERE Track.mediumid = " + id;
			try {
				ResultSet rs = connection.createStatement().executeQuery(query);
				
				while (rs.next()) {
					int track_id = rs.getInt(1);
					query = "DELETE FROM Artist_Song WHERE Artist_Song.trackid = " + track_id;
					connection.createStatement().executeQuery(query);
				}

				success = true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
			
			/* remove relation between all songs and this album */
			query = "DELETE FROM Track WHERE Track.mediumid = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}

			/* remove relation between all songs and this album */
			query = "DELETE FROM Album WHERE Album.id = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
		}

		printEndOfLine(success);
		return success;
	}

	public static boolean removeTrack(int id) {
		boolean success = false;
		System.out.print("Deleting track with id " + id + " ... ");

		Connection connection = OracleDatabase.SINGLE.getConnection();
		if (null != connection) {
			/* remove all tracks in Artist_Song that are related to this album */
			String query = "SELECT Track.id FROM Track WHERE Track.recordingid = " + id;
			try {
				ResultSet rs = connection.createStatement().executeQuery(query);
				
				while (rs.next()) {
					int track_id = rs.getInt(1);
					query = "DELETE FROM Artist_Song WHERE Artist_Song.trackid = " + track_id;
					connection.createStatement().executeQuery(query);
				}

				success = true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
			
			/* remove relation between all songs and this album */
			query = "DELETE FROM Track WHERE Track.recordingid = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}

			/* remove relation between all songs and this album */
			query = "DELETE FROM Song WHERE Song.id = " + id;
			try {
				connection.createStatement().executeQuery(query);

				success = success && true;
			} catch (SQLException e) {
				success = false;
				SQLHelper.printSQLException(e);
			}
		}

		printEndOfLine(success);
		return success;
	}

	private static void printEndOfLine(boolean status) {
		if (status) {
			System.out.println("done!");
		} else {
			System.out.println("failure!");
		}
	}
}
