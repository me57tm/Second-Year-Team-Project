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
	
	
	 @Override
	 public void update(double deltaTime, Map map) {
		 super.update(deltaTime, map);
		 if (this.elapsedTime > 3) this.hp = -1;
		 this.limitFrame(1200,800); 
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
