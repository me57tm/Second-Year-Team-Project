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

    public Sprite(String imageFileName)
    {
        this();
        setImage(imageFileName);
    }

    public void setImage(String imageFileName)
    {
        this.image = new Image(imageFileName);
        this.boundary.setSize(this.image.getWidth(), this.image.getHeight());
    }


    public Rectangle getBoundary()
    {
        this.boundary.setPosition(this.position.x, this.position.y);
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

    //ignore commented out code but pls don't delete it
    public void update(double deltaTime, Map map)
    {
    	// increase elapsed time for this sprite
    	this.elapsedTime += deltaTime;
        // update position according to velocity
    	Vector oldPosition = this.position;
//    	this.position.x = this .velocity.x* deltaTime;
//    	this.position.y = this .velocity.y* deltaTime;
    	this.position.add(this.velocity.x* deltaTime, this.velocity.y * deltaTime);
    	if(this.collides(map)) {
        	this.position=oldPosition;
        }
//    	this.position.y = this .velocity.y* deltaTime;
//    	if(this.collides(map)) {
//        	this.position=oldPosition;
//        }
        
//    	for(int i = 0; i<layer.widthInTiles; i++) {
//    		for(int j=0; j<layer.heightInTiles; j++) {
//    			try {
//    				if(this.getBoundary().overlaps(layer.getTileRectangle(layer.getTile(i, j)))==false) {
//        	        	this.position.add(this .velocity.x* deltaTime, this.velocity.y * deltaTime);
//        	        }
//    			} catch(NullPointerException e) {
//    				continue;
//    			}
//    			
//    		}
//    	}
//    	
    	
    	
    	
//        if(this.collides(map)) {
//        	this.position=oldPosition;
//        }
    	//System.out.println("collision="+this.wallCollisionCheck(layer));
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
        //context.rotate(this.getBoundary().rotation);
        context.strokeRect(this.position.getX(), this.position.getY(), this.getBoundary().width, this.getBoundary().height);
        
        context.translate(this.position.x, this.position.y);
        context.rotate(this.rotation);
        //context.translate(-this.image.getWidth()/2, -this.image.getHeight()/ 2);
        context.drawImage(this.image, 0, 0);

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
    public boolean collides (Map map) {
    	boolean collision = false;
    	
    	//checking on which tiles are the four corners of the sprite, having an idea of where is the sprite on the tilemap
    	int leftTile = (int) this.position.x / map.TILE_WIDTH;
    	int rightTile = ((int) this.position.x + (int) this.boundary.width) / map.TILE_WIDTH;
        int topTile = (int) this.position.y / map.TILE_HEIGHT;
        int bottomTile = ((int) this.position.y + (int) this.boundary.height) / map.TILE_HEIGHT;
        
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
       
       System.out.println("sprite location: left:"+leftTile+" right:"+rightTile+" top:"+topTile+" bottom:"+bottomTile);
       
       //only checking the tiles where we know the sprite is
       for(int i = leftTile; i<rightTile; i++) {
    	   for(int j= topTile; j<bottomTile; j++) {
    		   Tile tile;
    		   try {
    			   tile= map.getLayer(map.getSize()-1).getTile(i, j);
    			   if(tile.isPassable()==false) {
        			   collision = true;
        			   System.out.println("collision!");
        		   }
    		   } catch (NullPointerException e) {
    			   continue;
        		   }
    		   }
    		   
    	   }
       
       return collision;
    }

}
