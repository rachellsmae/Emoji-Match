import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Tile class that specifies how each tile is constructed on the board.
 * Each tile has a value that corresponds to a different emoji.
 * 
 */

public class Tile {

	private int x;
	private int y;
	private int result;
	
	public static final int LENGTH = 75;
	
	private BufferedImage background;
	
	public Tile(int x, int y, int result) {
		String image = "";
		this.x = x;
		this.y = y;
		
		if (result % 2 == 0 && result >= 0) {
		this.result = result;
		} else {
			throw new IllegalArgumentException("Invalid number");
		}
		
		if (result == 2) {
			image = "files/emoji1.png";
		} 
		else if (result == 4) {
			image = "files/emoji3.png";
		}
		else if (result == 8) {
			image = "files/emoji4.png";
		}
		else if (result == 16) {
			image = "files/emoji5.png";
		}
		else if (result == 32) {
			image = "files/emoji6.png";
		}
		else if (result == 64) {
			image = "files/emoji7.png";
		}
		else if (result == 128) {
			image = "files/emoji9.png";
		}
		else if (result == 256) {
			image = "files/emoji10.png";
		}
		else if (result == 512) {
			image = "files/emoji11.png";
		}
		
		try {
            background = ImageIO.read(new File(image));
        } catch (IOException e) {
        }
	}

    
	public void paint(Graphics g) {
		if (result == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(this.x, this.y, LENGTH, LENGTH);
		}
		g.drawImage(background, this.x, this.y, LENGTH, LENGTH, null);
	} 

}
