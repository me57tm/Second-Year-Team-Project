package physics;

public class Layer
{
	String layerName;
	Tile[][] layer;
	public int heightInTiles;
	public int widthInTiles;
	
	//the layer is initialized according to its size in tiles
	public Layer(String name, int w, int h) {
		layerName = name;
		layer = new Tile[w][h]; 
		heightInTiles=h;
		widthInTiles=w;
	}
	
	public void addTile(Tile tile,int i, int j) {
		layer[i][j]=tile;
	}
	
	public Tile getTile(int i, int j) {
		return this.layer[i][j];
	}
}
