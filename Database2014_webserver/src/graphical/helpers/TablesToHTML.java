package graphical.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TablesToHTML {
	
	public static String artistsResultSetToHTML(ResultSet resultSet) {
		if (null==resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		
		String result = "<tbale><tr>"
				+ "<th>Name</th>"
				+ "<th>Type</th>"
				+ "<th>Gender</th>"
				+ "<th>Area</th>"
				+ "<th>Genre</th>"
				+ "</tr>";
		
		int count = 0;
		try {
			while (resultSet.next() && count < 100) {
				String name = resultSet.getString("Artist.name");
				String type = resultSet.getString("Artist.type");
				String gender = resultSet.getString("Artist.gender");
				String area = resultSet.getString("Area.name");
				String genre = resultSet.getString("Genre.name");
				String row = "<tr>"
						+ "<td>" + name +"</td>"
						+ "<td>" + type + "</td>"
						+ "<td>" + gender + "</td>"
						+ "<td>" + area + "</td>"
						+ "<td>" + genre + "</td>"
						+ "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element: "+e.getMessage());
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}
	
	public static String genreResultSetToHTML(ResultSet resultSet) {
		if (null==resultSet) {
			return "<h3>Error while computing query</h3>";
		}
		
		String result = "<tbale><tr>"
				+ "<th>Name</th>"
				+ "</tr>";
		
		int count = 0;
		try {
			while (resultSet.next() && count < 100) {
				String genre = resultSet.getString("Genre.name");
				String row = "<tr>"
						+ "<td>" + genre + "</td>"
						+ "</tr>";
				result += row;
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Error while retrieving an element: "+e.getMessage());
			return "<h3>Error while computing query</h3>";
		}

		result += "</table>";
		return result;
	}
}
