package graphical.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.connection.SQLHelper;

public class TablesToHTML {

	public static String artistsGenreResultSetToHTML(ResultSet resultSet,
			int tableSize) {
		if (null == resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		if (tableSize < 0) {
			tableSize = Integer.MAX_VALUE;
		}

		String result = "<table id=\"results\"><tr>" + "<th>Name</th>"
				+ "<th>Type</th>" + "<th>Gender</th>" + "<th>Area</th>"
				+ "<th>Genre</th>" + "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String type = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String area = resultSet.getString(4);
				String genre = resultSet.getString(5);
				String row = "<tr>" + "<td>" + name + "</td>" + "<td>" + type
						+ "</td>" + "<td>" + gender + "</td>" + "<td>" + area
						+ "</td>" + "<td>" + genre + "</td>" + "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element: "
					+ e.getMessage());
			SQLHelper.printSQLException(e);
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}

	public static String artistsResultSetToHTML(ResultSet resultSet,
			int tableSize) {
		if (null == resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		if (tableSize < 0) {
			tableSize = Integer.MAX_VALUE;
		}

		String result = "<table id=\"results\"><tr>" + "<th>Name</th>"
				+ "<th>Type</th>" + "<th>Gender</th>" + "<th>Area</th>"
				+ "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String type = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String area = resultSet.getString(4);
				String row = "<tr>" + "<td>" + name + "</td>" + "<td>" + type
						+ "</td>" + "<td>" + gender + "</td>" + "<td>" + area
						+ "</td>" + "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element: "
					+ e.getMessage());
			SQLHelper.printSQLException(e);
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}

	public static String genreResultSetToHTML(ResultSet resultSet, int tableSize) {
		if (null == resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		if (tableSize < 0) {
			tableSize = Integer.MAX_VALUE;
		}

		String result = "<table id=\"results\"><tr>" + "<th>Name</th>"
				+ "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String genre = resultSet.getString("name");
				String row = "<tr><td><a href=\"list.jsp?genreFilter=" + genre
						+ "&table=artist\">" + genre + "</a></td></tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element");
			SQLHelper.printSQLException(e);
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}

	public static String albumResultSetToHTML(ResultSet resultSet, int tableSize) {
		if (null == resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		if (tableSize < 0) {
			tableSize = Integer.MAX_VALUE;
		}
		String result = "<table id=\"results\"><tr>" + "<th>Album Name</th>"
				+ "<th>Artist Name</th>" + "<th>Album Format</th>" + "</tr>";
		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String artist_name = resultSet.getString(2);
				String format = resultSet.getString(3);
				String row = "<tr>" + "<td>" + name + "</td>" + "<td>"
						+ artist_name + "</td>" + "<td>" + format + "</td>"
						+ "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element: "
					+ e.getMessage());
			SQLHelper.printSQLException(e);
			return "<h3>Error while computing query</h3>";
		}
		result += "</table>";
		return result;
	}

	public static String trackResultSetToHTML(ResultSet resultSet, int tableSize) {
		if (null == resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		if (tableSize < 0) {
			tableSize = Integer.MAX_VALUE;
		}

		String result = "<table id=\"results\"><tr>" + "<th>Name</th>"
				+ "<th>Length</th>" + "<th>Album</th>" + "<th>Position</th>"
				+ "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString("name");
				int length = Math.round(resultSet.getInt("length")/1000);
				String album = resultSet.getString("releasename");
				int position = resultSet.getInt("position");
				String row = "<tr>" 
				+ "<td>" + name + "</td>" 
				+ "<td>" + (int)Math.floor(length/60) + ":" + length%60 + "</td>" 
				+ "<td>" + album + "</td>"
				+ "<td>" + position + "</td>" + "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element");
			SQLHelper.printSQLException(e);
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}
}
