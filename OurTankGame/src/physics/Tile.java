package physics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Tile
{
	String source;
	boolean passable;
	int x;
	int y;

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
	
	public Tile(int xCoord, int yCoord) {
		this.x=xCoord;
		this.y=yCoord;
	}
	
	public int getTileX() {
		return this.x;
	}
	
	public int getTileY() {
		return this.y;
	}
	
	public void setTileX(int newX) {
		this.x=newX;
	}
	
	public void setTileY(int newY) {
		this.y = newY;
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
	
	//returns the rectangle of a given tile, useful for collision
		public Rectangle getTileRectangle() {
			return new Rectangle(this.getTileX(),this.getTileY(),this.TILE_SIZE_IN_PIXELS,this.TILE_SIZE_IN_PIXELS);
		}
}
