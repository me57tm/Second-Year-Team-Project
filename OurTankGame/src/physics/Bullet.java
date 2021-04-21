package physics;

public class Bullet extends Sprite {
	protected Tank parent;
	protected double damage = 5;
	
	public Bullet(String string,Tank parent) {
		super(string);
		this.parent = parent;
	}
	public Bullet(double damage,String string,Tank parent) {
		super(string);
		this.parent = parent;
		this.damage = damage;
	}
	
	
	public void deleteBullet(double screenWidth, double screenHeight)//Maybe this should be renamed, Function checks to see if bullet is on screen, won't delete bullet unless it is off screen
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
	 
	 @Override
	 public void update(double deltaTime, Map map) {
		 super.update(deltaTime, map);
		 if (this.elapsedTime > 3) this.hp = -1;
		 this.deleteBullet(1200,800);
	 }
	 
	 public Tank getParent() {
		 return parent;
	 }
	 public double getDamage() {
		 return damage;
	 }
	 public void setDamage(double d) {
		 damage = d;
	 }
}
