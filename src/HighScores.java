import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * HighScores class that keeps track of the highscores 
 * 
 */

public final class HighScores {
    
	public static String getHighScores() {
		StringBuilder result = new StringBuilder();
		try {
			List<String> highScores = new ArrayList<String>();
			BufferedReader in;
			
			try {
				FileReader file = new FileReader("files/HighScores2.txt");
				in = new BufferedReader(file);
			} catch (java.io.FileNotFoundException e) {
				return "";
			}

			while (in.ready()) {
				highScores.add(in.readLine());
			}

			Collections.sort(highScores);
			Collections.reverse(highScores);
			
			for (int i = 0; i < 5 && i < highScores.size(); i++) {
				result.append(highScores.get(i)).append("\n");
			}
			
			in.close();
		} catch (IOException e) {
		}
		
		return result.toString();
	}
    
	public static void addScore(String score, String name) {
		try {
			if (name == null) {
				name = "";
			}

			PrintWriter out = new PrintWriter(new FileWriter("files/HighScores2.txt", true));
			out.println(score + " -> " + name);
			out.close();
		} catch (IOException e) {
		}
	}
}
