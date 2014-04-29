package csv.creator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

public class AlbumCreator {

	static String[] concate(String[] strArr) {
		String[] conc = new String[2];

		String strEnd = "";
		conc[0] = strArr[0];
		for (int i = 1; i < strArr.length; i++) {
			if (i == strArr.length - 1) {
				strEnd += strArr[i];
			} else {
				strEnd += strArr[i] + " ";
			}
		}
		conc[1] = strEnd;
		return conc;
	}

	static HashMap<String, String> mastaMap(File file) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			br.readLine();
			
			while ((strLine = br.readLine()) != null) {
				String[] split = strLine.split("\\t");
//				String[] conc = concate(split);
				map.put(split[0], split[1]);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return map;
	}

	public static void main(String[] args) {
		File file = new File("test.csv");
		HashMap<String, String> mapmap = mastaMap(file);
		File file2 = new File("test_result.csv");
		System.out.println(mapmap);
		
		// if file doesnt exists, then create it
		if (!file2.exists()) {
			try {
				file2.createNewFile();
				FileOutputStream f = new FileOutputStream(file2);
				PrintStream ps = new PrintStream(f);

				ps.print(mapmap.get("83657"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}
		}

	}
}
