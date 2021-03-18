package physics;

import java.util.ArrayList;

public class Tank extends Sprite {
	protected double speedModifier = 1;
	protected PowerUp pow;
	
	public Tank(String imageFileName) {
		super(imageFileName);
	}
	
	
	
	
	public void move(ArrayList<String> keyPressedList) {//TODO: name this something that makes more sense
		if (keyPressedList.contains("LEFT") )
		{
			rotation -= 3;
		}

		if (keyPressedList.contains("RIGHT"))
			rotation += 3;
		
		if (keyPressedList.contains("UP"))
		{
			velocity.setAngle(rotation);
			velocity.setLength(50*speedModifier);
			
		}
		else if (keyPressedList.contains("DOWN"))
		{
			velocity.setAngle(rotation);
			velocity.setLength(-50*speedModifier);
			
		}
		else {
				velocity.setLength(0);
			}

//		if (keyJustPressedList.contains("SPACE"))
//		{
//			context.save();
//
//			Sprite laser = new Sprite("imagesProjectAI/red-circle.png");
//
//			laser.position.set(tank.position.x, tank.position.y);
//			laser.velocity.setLength(200);
//			laser.velocity.setAngle(tank.rotation);
//			laserListT.add(laser);
//
//		}
	}
	
	
	
	@Override
	public boolean collide(Sprite other)
	{
		if (this.hp <= 0 || other.hp<=0){
			return false;
		}
		if (this.overlaps(other)) {
			//run collision code
			System.out.println("Collision");
			
			if (other instanceof Bullet) {
				Bullet b = (Bullet) other;
				if (b.getParent() != this) {
					System.out.println("Hello this code is here");
					this.hp -= b.damage;
					System.out.println(hp);
					b.hp = -1;
				}
			}
			return true;
		}
		return false;
	}
	
	public double getSpeedModifier() {
		return speedModifier;
	}


	public void setSpeedModifier(double speedModifier) {
		this.speedModifier = speedModifier;
	}
	
	public void setPowerUp(PowerUp pow) {
		if (this.pow != null) {
			this.pow.remove(this);
		}
		this.pow = pow;
	}
}
