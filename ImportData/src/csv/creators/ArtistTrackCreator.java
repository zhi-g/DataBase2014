/**
 * @author Zhi
 *
 */
package csv.creators;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

public class ArtistTrackCreator {
	public final static int HASH_MAP_SIZE = 1650000;

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
			int i = 0;
			boolean first = true;
			while ((strLine = br.readLine()) != null) {
				String[] split = strLine.split("\\t");
				map.put(split[0], split[1] + "\t" + split[2]);
				++i;
				if (i == HASH_MAP_SIZE) {
					System.out.println(i);
					updateFile(map, first);
					first=false;
					i = 0;
				}
			}

			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return map;
	}

	public static void main(String[] args) {
		File fileTrack = new File("track.csv");
		 mastaMap(fileTrack);

	}

	public static void updateFile(HashMap<String, String> mapmap, boolean first) {
		File fileOut = new File("artist_medium_release.csv");
		File fileArtistTrack = new File("artist_track.csv");

		// if file doesnt exists, then create it
		if (!fileOut.exists()) {
			try {
				fileOut.createNewFile();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
		try {
			FileOutputStream f = new FileOutputStream(fileOut);
			PrintStream ps = new PrintStream(f);

			FileInputStream fstream;

			fstream = new FileInputStream(fileArtistTrack);

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			br.readLine();
			if(first) ps.println("ArtistID\tRecordingID\tMediumID" );
			
			while ((strLine = br.readLine()) != null) {
				String[] split = strLine.split("\\t");
				StringBuilder outLine = new StringBuilder(split[0]);
				outLine.append(mapmap.get(split[1]));
				
				ps.println(outLine.toString());

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		}
	}
}