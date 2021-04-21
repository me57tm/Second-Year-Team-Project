package physics;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Map
{
	public final int TILE_HEIGHT = 32;
	public final int TILE_WIDTH = 32;
	
	public final int MAP_HEIGHT_IN_TILES = 24;
	public final int MAP_WIDTH_IN_TILES = 36;
	
	ArrayList<Layer> map;
	
	public Map() {
		map = new ArrayList<Layer>();
	}
	
	public void renderMap(GraphicsContext context) {
		context.save();

		//going through the map, adding one layer at a time
		for(int i=0; i<map.size(); i++) {
			//going through a layer
			for(int widthIndex=0; widthIndex<map.get(i).widthInTiles; widthIndex++) {
				for(int heightIndex=0; heightIndex<map.get(i).heightInTiles; heightIndex++) {
					
					Layer temp = map.get(i);
					Tile tempTile = temp.getTile(widthIndex,heightIndex);
					
					//the try-catch block is here because some layers might have empty cells and should keep iterating until a nonempty cell is found
					try {
						//the tile's image is drawn at the given coords in pixels(tile index*tile size)
						context.drawImage((tempTile.getTileImage()), widthIndex*tempTile.TILE_SIZE_IN_PIXELS, heightIndex*tempTile.TILE_SIZE_IN_PIXELS);
						
					} catch(NullPointerException e) {
						continue;
					}
					
				}
			}
		}
        
        context.restore();
	}
	
	public void addLayer(Layer layer) {
		map.add(layer);
	}
	
	public int getSize() {
		return map.size();
	}
	
	public Layer getLayer(int i) {
		return map.get(i);
	}
}
