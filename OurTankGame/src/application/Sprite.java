package application;

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

    public void deleteBullet(double screenWidth, double screenHeight)
    {
        double halfWidth = this.image.getWidth()/2;
        double halfHeight = this.image.getHeight()/2;

        if (this.position.x >= screenWidth - halfWidth) {
            this.position.x = 2*screenWidth;
        }
        if (this.position.x <= halfWidth) {
            this.position.x = (-2)*halfWidth;
        }
        if (this.position.y >= screenHeight - halfHeight) {
            this.position.y = 2*screenHeight;
        }
        if (this.position.y <= halfHeight) {
            this.position.y = (-2)*halfHeight;
        }
    }

    public void update(double deltaTime)
    {
    	// increase elapsed time for this sprite
    	this.elapsedTime += deltaTime;
        // update position according to velocity
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
        // wrap around screen
        this.limitFrame(1200,800);
    }

    public void updateBullet()
    {
        this.deleteBullet(1200,800);
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

        context.translate(this.position.x, this.position.y);
        context.rotate(this.rotation);
        context.translate(-this.image.getWidth()/2, -this.image.getHeight()/ 2);
        context.drawImage(this.image, 0, 0);

        context.restore();
    }

}
