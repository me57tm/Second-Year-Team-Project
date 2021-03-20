package physics;

public class PowerUp extends Sprite {
	protected String type;
	
	public PowerUp(String type) {
		super();
		switch(type) {
		case "Speed":
			setImage("imagesProjectAI/bulletpowerup.png");
		}
		this.type=type;
	}
	
	public void apply(Tank t) {
		switch(type) {
		case "Speed":
			t.setSpeedModifier(3);
		}
		t.setPowerUp(this);
	}
	
	public void remove(Tank t) {
		switch (type) {
		case "Speed":
			t.setSpeedModifier(1);
		}
	}
	
	public boolean collide(Sprite other)
	{
		if (this.hp <= 0 || other.hp<=0){
			return false;
		}
		if (this.overlaps(other)) {
			//run collision code
			System.out.println("Collision2");
			
			if (other instanceof Bullet) {
				Bullet b = (Bullet) other;
				System.out.println("you what");
				apply(b.getParent());
				this.hp = -1;
				b.hp = -1;
			}
			else if (other instanceof Tank) {
				Tank t = (Tank) other;
				apply(t);
				this.hp = -1;
				t.hp = -1;
			}
			return true;
		}
		return false;
	}
	
}