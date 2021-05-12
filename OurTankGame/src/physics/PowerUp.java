package physics;


import java.util.Random;

public class PowerUp extends Sprite {
	protected String type;
	protected double expiryTime = 8f;
	
	public PowerUp(String type,double x,double y) {
		super();
		switch(type) {
		case "Speed":
			setImage("art/powerup.png");
			break;
		case "Score":
//			this.expiryTime = 10f;
			setImage("art/coin-export1.png");
			break;
		case "Energy":
			setImage("art/energy-export1.png");
		}
		this.type=type;
		this.position.x = x;
		this.position.y = y;
		this.boundary.setPosition(this.position.x - this.image.getWidth()/2, this.position.y - this.image.getHeight()/2);
	}
	public void apply(Tank t) {
		switch(type) {
		case "Speed":
			t.setSpeedModifier(3);
			break;
		case "Score":
			t.addScore(30);
			break;
		case "Energy":
			t.addHP(20);			
		}
		t.setPowerUp(this);
	}
	
	public void remove(Tank t) {
		switch (type) {
		case "Speed":
			t.setSpeedModifier(1);
		}
	}
	
	@Override
	public void update(double deltaTime, Map map) {
		super.update(deltaTime,map);
		if (this.elapsedTime > this.expiryTime) this.hp = -1;
	}
	
	public static PowerUp randomPowerUp(int x, int y) {
		PowerUp p = null;
		Random rand = new Random();
		int r = rand.nextInt(3);
		switch (r) {
		case 0:
			p = new PowerUp("Speed",x,y);
			break;
		case 1:
			p = new PowerUp("Score",x,y);
			break;
		case 2:
			p = new PowerUp("Energy",x,y);
			break;
		}
		return p;
	}
	
	public boolean collide(Sprite other)
	{ 
		if (this.hp <= 0 || other.hp<=0){
			return false;
		}
		if (this.overlaps(other)) {
			//run collision code
			//System.out.println("Collision2");
			
			if (other instanceof Bullet) {
				Bullet b = (Bullet) other;
				//System.out.println("you what");
				apply(b.getParent());
				this.hp = -1;
				b.hp = -1;
			}
			else if (other instanceof Tank) {
				Tank t = (Tank) other;
				apply(t);
				this.hp = -1;
			}
			return true;
		}
		return false;
	}
	
}
