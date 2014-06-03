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
				+ "<th>Genre</th>" + "<th>Actions</th>" + "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String type = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String area = resultSet.getString(4);
				String genre = resultSet.getString(5);
				int id = resultSet.getInt(6);
				String row = "<tr>"
						+ "<td><a href=\"list.jsp?nameFilter=&formatFilter=&artistFilter="
						+ urlSanitization(name)
						+ "&table=album&outSize=max\">"
						+ name
						+ "</td>"
						+ "<td>"
						+ type
						+ "</td>"
						+ "<td>"
						+ gender
						+ "</td>"
						+ "<td>"
						+ area
						+ "</td>"
						+ "<td>"
						+ genre
						+ "</td>"
						+ "<td>"
						+ "<img src='images/delete.png' onclick='eraseEntry("
						+ id
						+ ",\"artist\")'/>"
						+ "<img src='images/id.png' onclick='alert(\"ID of this row: "
						+ id + "\")'/>" + "</td>" + "</tr>";
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
				+ "<th>Actions</th>" + "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String type = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String area = resultSet.getString(4);
				int id = resultSet.getInt(5);
				String row = "<tr>"
						+ "<td><a href=\"list.jsp?nameFilter=&formatFilter=&artistFilter="
						+ urlSanitization(name)
						+ "&table=album&outSize=max\">"
						+ name
						+ "</a>"
						+ "</td>"
						+ "<td>"
						+ type
						+ "</td>"
						+ "<td>"
						+ gender
						+ "</td>"
						+ "<td>"
						+ area
						+ "</td>"
						+ "<td>"
						+ "<img src='images/delete.png' onclick='eraseEntry("
						+ id
						+ ",\"artist\")'/>"
						+ "<img src='images/id.png' onclick='alert(\"ID of this row: "
						+ id + "\")'/>" + "</td>" + "</tr>";
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
				+ "<th>Actions</th>" + "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String genre = resultSet.getString(1);
				int id = resultSet.getInt(2);
				String row = "<tr><td><a href=\"list.jsp?genreFilter="
						+ urlSanitization(genre)
						+ "&table=artist\">"
						+ genre
						+ "</a></td>"
						+ "<td>"
						+ "<img src='images/delete.png' onclick='eraseEntry("
						+ id
						+ ",\"genre\")'/>"
						+ "<img src='images/id.png' onclick='alert(\"ID of this row: "
						+ id + "\")'/>" + "</td>" + "</tr>";
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
				+ "<th>Artist Name</th>" + "<th>Album Format</th>"
				+ "<th>Actions</th>" + "</tr>";
		int count = 0;
		try {
			String oldName = "";
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString(1);
				String artist_name = resultSet.getString(2);
				String format = resultSet.getString(3);
				int id = resultSet.getInt(4);
				String row = "<tr>"
						+ "<td><a href=\"list.jsp?nameFilter=&releaseFilter="
						+ urlSanitization(name)
						+ "&table=track&outSize=max\">"
						+ name
						+ "</a></td>"
						+ "<td>"
						+ artist_name
						+ "</td>"
						+ "<td>"
						+ format
						+ "</td>"
						+ "<td>"
						+ "<img src='images/delete.png' onclick='eraseEntry("
						+ id
						+ ",\"album\")'/>"
						+ "<img src='images/id.png' onclick='alert(\"ID of this row: "
						+ id + "\")'/>" + "</td>" + "</tr>";
				if (!oldName.equals(name)) {
					result += row;					
				}
				oldName = name;
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
				+ "<th>Actions</th>" + "</tr>";

		int count = 0;
		try {
			while (resultSet.next() && count < tableSize) {
				String name = resultSet.getString("name");
				int length = Math.round(resultSet.getInt("length") / 1000);
				String album = resultSet.getString("releasename");
				int position = resultSet.getInt("position");
				int id = resultSet.getInt("id");
				String row = "<tr>" + "<td><a href=https://www.youtube.com/results?search_query="+urlSanitization(name)+">"
						+ name
						+ "</a></td>"
						+ "<td>"
						+ (int) Math.floor(length / 60)
						+ ":"
						+ length % 60
						+ "</td>"
						+ "<td>"
						+ album
						+ "</td>"
						+ "<td>"
						+ position
						+ "</td>"
						+ "<td>"
						+ "<img src='images/delete.png' onclick='eraseEntry("
						+ id
						+ ",\"track\")'/>"
						+ "<img src='images/id.png' onclick='alert(\"ID of this row: "
						+ id + "\")'/>" + "</td>" + "</tr>";
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

	public static String urlSanitization(String input) {
		String output = input;
		output = output.replace("%", "%25");

		// ! # $ & ' ( ) * + , / : ; = ? @ [ ]
		// %21 %23 %24 %26 %27 %28 %29 %2A %2B %2C %2F %3A %3B %3D %3F %40 %5B
		// %5D
		output = output.replace("!", "%21");
		output = output.replace("#", "%23");
		output = output.replace("$", "%24");
		output = output.replace("&", "%26");
		output = output.replace("'", "%27");
		output = output.replace("(", "%28");
		output = output.replace(")", "%29");
		output = output.replace("*", "%2A");
		output = output.replace("+", "%2B");
		output = output.replace(",", "%2C");
		output = output.replace("/", "%2F");
		output = output.replace(":", "%3A");
		output = output.replace(";", "%3B");
		output = output.replace("=", "%3D");
		output = output.replace("?", "%3F");
		output = output.replace("@", "%40");
		output = output.replace("[", "%5B");
		output = output.replace("]", "%5D");
		
		// space " - . < > \ ^ _ ` { | } ~
		// %20 %22 %2D %2E %3C %3E %5C %5E %5F %60 %7B %7C %7D %7E
		output = output.replace(" ", "%20");
		output = output.replace("\"", "%22");
		output = output.replace("-", "%2D");
		output = output.replace(".", "%2E");
		output = output.replace("<", "%3C");
		output = output.replace(">", "%3E");
		output = output.replace("\\", "%5C");
		output = output.replace("^", "%5E");
		output = output.replace("_", "%5F");
		output = output.replace("`", "%60");
		output = output.replace("{", "%7B");
		output = output.replace("|", "%7C");
		output = output.replace("}", "%7D");
		output = output.replace("~", "%7E");

		System.out.println("URL sanitization: "+input+"->"+output);
		return output;
	}
}
