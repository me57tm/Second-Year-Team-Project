package physics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Tile
{
	String source;
	boolean passable;

	Image image;
	public final int TILE_SIZE_IN_PIXELS = 32;
	
	public Tile(String filename, boolean passability) {
		passable=passability;
		source = filename;
		try
		{
			image = new Image(new FileInputStream(source));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSource() {
		return this.source;
	}
	
	public boolean isPassable() {
		return this.passable;
	}
	
	public void setPassability(boolean passability) {
		passable=passability;
	}
	
	public Image getTileImage() {
		return this.image;
	}
	
	public void setTileImage(Image newImage) {
		this.image=newImage;
	}
}
