import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

public class ArtistSongCreator2 {

	static HashMap<String, String> mastaMap(File file) {
		double i = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			boolean first = true;
			while ((strLine = br.readLine()) != null) {
				if (first) {
					first = false;
				} else {
					String[] split = strLine.split("\t");

					map.put(split[0], split[1] + "\t" + split[2]);
					i++;
					if (i % 1000000 == 0) {
						System.out.println("PLOP!");
					}
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

		File track = new File("/Users/johngaspoz/Desktop/track.csv");
		File artTrack = new File("/Users/johngaspoz/Desktop/artist_track.csv");
		File artSong = new File("/Users/johngaspoz/Desktop/artist_song.csv");
		HashMap<String, String> mapmap = mastaMap(track);
		// if file doesnt exists, then create it
		if (!artSong.exists()) {
			try {
				artSong.createNewFile();
				FileOutputStream f = new FileOutputStream(artSong);
				PrintStream ps = new PrintStream(f);

				FileInputStream fstream = new FileInputStream(artTrack);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strLine;
				String outLine = "";
				boolean first = true;
				boolean yolol = true;
				while ((strLine = br.readLine()) != null) {
					if (first) {
						ps.println("ArtistID\tRecordingID\tMediumID");
						first = false;
					} else {
						String[] split = strLine.split("\t");
						// outLine = mapmap.get(split[0]) + "\t" + split[1] +
						// "\t"
						// + split[2];
						outLine = split[0] + "\t" + mapmap.get(split[1]);
						if (yolol) {
							System.err.println(outLine);
							yolol = false;
						}
						ps.println(outLine);

					}
				}
				System.out.println("FIN!");

				in.close();
				ps.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
