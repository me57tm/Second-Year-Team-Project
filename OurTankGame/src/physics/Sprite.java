package physics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite
{
	public Vector position;
	public Vector velocity;
	public double rotation; // degrees
	public Rectangle boundary;
	public javafx.scene.image.Image image;
	public int hp;
	public double elapsedTime; //seconds
	public Map map;

	public Sprite()
	{
		this.position = new Vector();
		this.velocity = new Vector();
		this.rotation = 0;
		this.boundary = new Rectangle();
		this.hp=100;
		// when we create a sprite, we set the elapsedTime to 0
		this.elapsedTime = 0;
	}

	public Sprite(String imageFileName,double x,double y)
	{
		this();
		this.position.x = x;
		this.position.y = y;
		setImage(imageFileName);
		this.boundary.setPosition(this.position.x - this.image.getWidth()/2, this.position.y - this.image.getHeight()/2);
	}

	public void setImage(String imageFileName)
	{
		this.image = new Image(imageFileName);
		this.getBoundary().setSize(this.image.getWidth(), this.image.getHeight());
	}


	public Rectangle getBoundary()
	{
		return this.boundary;
	}

	public boolean overlaps(Sprite other)
	{
		return this.getBoundary().overlaps(other.getBoundary());
	}

	public void limitFrame(double screenWidth, double screenHeight)
	{
		double halfWidth = this.image.getWidth()/2;
		double halfHeight = this.image.getHeight()/2;

		if (this.position.x >= screenWidth - halfWidth) {
			this.position.x = screenWidth - halfWidth;
		}
		if (this.position.x <= halfWidth) {
			this.position.x = halfWidth;
		}
		if (this.position.y >= screenHeight - halfHeight) {
			this.position.y = screenHeight - halfHeight;
		}
		if (this.position.y <= halfHeight) {
			this.position.y = halfHeight;
		}
	}

	public void update(double deltaTime,Map map)
	{
		this.boundary.setPosition(this.position.x - this.image.getWidth()/2, this.position.y - this.image.getHeight()/2);
		// increase elapsed time for this sprite
		this.elapsedTime += deltaTime;
		// update position according to velocity
		if (!collideMapFuture(map,deltaTime)) {
			this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
		}
		// wrap around screen
		this.limitFrame((map.MAP_WIDTH_IN_TILES-1)*map.TILE_WIDTH,(map.MAP_HEIGHT_IN_TILES-1)*map.TILE_HEIGHT);
	}

	public boolean isShot(Sprite bullet) {
		if(this.overlaps(bullet)) {
			return true;
		} else 
			return false;
	}

	public void render(GraphicsContext context)
	{
		context.save();

		//this draws the boundary for each sprite for collision purposes, will delete
		//context.strokeRect(this.boundary.x, this.boundary.y, this.getBoundary().width, this.getBoundary().height); //DEBUGGING HIBOX VIEW

		context.translate(this.position.x, this.position.y);
		context.rotate(this.rotation);

		//context.translate(-this.image.getWidth()/2, -this.image.getHeight()/ 2);

		context.drawImage(this.image, -this.image.getWidth()/2, -this.image.getHeight()/ 2);

		context.restore();
	}

	//It is expected most physics objects will overwrite this method.
	public boolean collide(Sprite s) {
		if (this.hp <= 0 || s.hp<=0){
			return false;
		}
		return overlaps(s);
	}

	//checking for collisions with map elements
	public boolean collideMap(Map map) {
		boolean collision = false;

		//checking on which tiles are the four corners of the sprite, having an idea of where is the sprite on the tilemap
		int leftTile = (int) this.boundary.x / map.TILE_WIDTH;//x1
		int rightTile = ((int) this.boundary.x + (int) this.boundary.width) / map.TILE_WIDTH;//x2
		int topTile = (int) this.boundary.y / map.TILE_HEIGHT;//y1
		int bottomTile = ((int) this.boundary.y + (int) this.boundary.height) / map.TILE_HEIGHT;//y2

		if(leftTile < 0) {
			leftTile = 0;
		}

		if(rightTile > map.MAP_WIDTH_IN_TILES) {
			rightTile = map.MAP_WIDTH_IN_TILES;
		}

		if(topTile < 0) {
			topTile = 0;
		}

		if(bottomTile > map.MAP_HEIGHT_IN_TILES) {
			bottomTile = map.MAP_HEIGHT_IN_TILES;
		}

		// System.out.println("sprite location: left:"+leftTile+" right:"+rightTile+" top:"+topTile+" bottom:"+bottomTile);

		//only checking the tiles where we know the sprite is
		for(int i = leftTile; i<=rightTile; i++) {
			for(int j= topTile; j<=bottomTile; j++) {
				Tile tile;
				try {
					tile= map.getLayer(map.getSize()-1).getTile(i, j);
					if(tile.isPassable()==false) {
						collision = true;
						//say();
					}
				} catch (NullPointerException e) {
					continue;
				}
			}

		}
		System.out.println(collision + "CollideMap");
		return collision;
	}

	public boolean collideMapFuture(Map map,double deltaTime) {
		boolean collision = false;
		int leftTile,rightTile,topTile,bottomTile;
		if (deltaTime == 0) {
			leftTile = (int) this.boundary.x / map.TILE_WIDTH;//x1
			rightTile = ((int) this.boundary.x + (int) this.boundary.width) / map.TILE_WIDTH;//x2
			topTile = (int) this.boundary.y / map.TILE_HEIGHT;//y1
			bottomTile = ((int) this.boundary.y + (int) this.boundary.height) / map.TILE_HEIGHT;//y2
		}
		else {
			leftTile = (int) (this.boundary.x+(this.velocity.x * deltaTime)) / map.TILE_WIDTH;//x1
			rightTile = ((int) (this.boundary.x+(this.velocity.x * deltaTime)) + (int) this.boundary.width) / map.TILE_WIDTH;//x2
			topTile = (int) (this.boundary.y+(this.velocity.y * deltaTime)) / map.TILE_HEIGHT;//y1
			bottomTile = ((int) (this.boundary.y+(this.velocity.y * deltaTime)) + (int) this.boundary.height) / map.TILE_HEIGHT;//y2
		}

		if(leftTile < 0) {
			leftTile = 0;
		}

		if(rightTile > map.MAP_WIDTH_IN_TILES) {
			rightTile = map.MAP_WIDTH_IN_TILES;
		}

		if(topTile < 0) {
			topTile = 0;
		}

		if(bottomTile > map.MAP_HEIGHT_IN_TILES) {
			bottomTile = map.MAP_HEIGHT_IN_TILES;
		}

		//only checking the tiles where we know the sprite is
		for(int i = leftTile; i<=rightTile; i++) {
			for(int j= topTile; j<=bottomTile; j++) {
				Tile tile;
				try {
					tile= map.getLayer(map.getSize()-1).getTile(i, j);
					if(tile.isPassable()==false) {
						collision = true;
					}
				} catch (NullPointerException e) {
					continue;
				}
			}

		}
		return collision;
	}

	/*public void setRotation(double d) {
    	this.rotation = d;
    	//Recalculate the rectangle

    }*/
	public void rotate(double angle) {
		this.rotation += angle;
		//Rotate rectangle by deltaRotation;

	}

	public double getRotation() {
		return this.rotation;
	}

	//for debugging
	public void say() {
		System.out.println("I am a sprite");
	}
	public void setMap(Map map) {
		this.map = map;
	}

}
