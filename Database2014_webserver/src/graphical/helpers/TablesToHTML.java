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
				String row = "<tr>" + "<td><a href=\"list.jsp?nameFilter=&formatFilter=&artistFilter="+ urlSanitization(name)
						+ "&table=album&outSize=max\">" + name + "</td>" + "<td>" + type
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
				String row = "<tr>" + "<td><a href=\"list.jsp?nameFilter=&formatFilter=&artistFilter="+ urlSanitization(name)
						+ "&table=album&outSize=max\">" + name + "</a>"+ "</td>" + "<td>" + type
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
				String row = "<tr><td><a href=\"list.jsp?genreFilter=" + urlSanitization(genre)
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
				String row = "<tr>"
						+ "<td><a href=\"list.jsp?nameFilter=&releaseFilter="+urlSanitization(name)+"&table=track&outSize=max\">" + name + "</a></td>"
						+ "<td>" + artist_name + "</td>"
						+ "<td>" + format + "</td>"
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
	
	public static String urlSanitization(String input) {
		String output = input;
		
		// ! 	# 	$ 	& 	' 	( 	) 	* 	+ 	, 	/ 	: 	; 	= 	? 	@ 	[ 	]
		// %21 	%23 %24	%26	%27 %28 %29 %2A %2B	%2C	%2F %3A %3B	%3D	%3F	%40	%5B %5D
		output.replace("!", "%21");
		output.replace("#", "%23");
		output.replace("$", "%24");
		output.replace("&", "%26");
		output.replace("'", "%27");
		output.replace("(", "%28");
		output.replace(")", "%29");
		output.replace("*", "%2A");
		output.replace("+", "%2B");
		output.replace(",", "%2C");
		output.replace("/", "%2F");
		output.replace(":", "%3A");
		output.replace(";", "%3B");
		output.replace("=", "%3D");
		output.replace("?", "%3F");
		output.replace("@", "%40");
		output.replace("[", "%5B");
		output.replace("]", "%5D");
		
		//space	" 	% 	- 	. 	< 	> 	\ 	^ 	_ 	` 	{ 	| 	} 	~
		//%20	%22 %25 %2D %2E %3C %3E %5C %5E %5F %60 %7B %7C %7D %7E
		output.replace(" ", "%20");
		output.replace("\"", "%22");
		output.replace("%", "%25");
		output.replace("-", "%2D");
		output.replace(".", "%2E");
		output.replace("<", "%3C");
		output.replace(">", "%3E");
		output.replace("\\", "%5C");
		output.replace("^", "%5E");
		output.replace("_", "%5F");
		output.replace("`", "%60");
		output.replace("{", "%7B");
		output.replace("|", "%7C");
		output.replace("}", "%7D");
		output.replace("~", "%7E");
		
		return output;
	}
}
