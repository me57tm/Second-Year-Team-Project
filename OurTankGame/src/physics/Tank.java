package physics;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Tank extends Sprite {
	private int id;
	public double bulletMsg;
	private boolean good;
	private double x, y, fifty;
	private String name;

	protected double speedModifier = 1;
	protected PowerUp pow;

	public Tank(String imageFileName) {
		super(imageFileName);
	}

	public Tank(double x, double y, boolean good, String imageFileName) {
		super(imageFileName);
		this.x = x;
		this.y = y;
		this.good = good;

	}

	public Tank(String name, int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.name = name;

	}

	public void move(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList, GraphicsContext context,
			ArrayList<Bullet> laserListT) {
		
		if (keyPressedList.contains("LEFT"))
			//this.getBoundary().rotation -=3;
			rotation -= 3;

		if (keyPressedList.contains("RIGHT"))
			//this.getBoundary().rotation +=3;
			rotation += 3;

		if (keyPressedList.contains("UP")) {
			velocity.setAngle(rotation);
			velocity.setLength(50 * speedModifier);
			fifty = 50;
		} else if (keyPressedList.contains("DOWN")) {
			velocity.setAngle(rotation);
			velocity.setLength(-50 * speedModifier);
			fifty = -50;
		} else {
			velocity.setLength(0);
			fifty = 0;
		}

		if (keyJustPressedList.contains("SPACE")) {
			bulletMsg = 1;
		}

		if (bulletMsg == 1) {
			context.save();

			Bullet laser = new Bullet("imagesProjectAI/red-circle.png", this);

			laser.position.set(this.position.x, this.position.y);
			laser.velocity.setLength(200);
			laser.velocity.setAngle(this.rotation);
			laserListT.add(laser);

		}
	}

	public void enemyFire(Tank enemy, ArrayList<Bullet> laserListE) {
		if (bulletMsg == 1) {
			Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy);
			laserE.position.set(enemy.position.x, enemy.position.y);
			laserE.velocity.setLength(200);
			laserE.velocity.setAngle(enemy.rotation);
			laserListE.add(laserE);
			bulletMsg = 0;
		}
	}

	@Override
	public boolean collide(Sprite other) {
		if (this.hp <= 0 || other.hp <= 0) {
			return false;
		}
		if (this.overlaps(other)) {
			// run collision code
			System.out.println("Collision");

			if (other instanceof Bullet) {
				Bullet b = (Bullet) other;
				if (b.getParent() != this) {
					System.out.println("Tank hit");
					this.hp -= b.damage;
					System.out.println(hp);
					b.hp = -1;
				}
			}
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean collideMap(Map map) {
		boolean collision = super.collideMap(map);
		if (collision){
			
		}
		return collision;
	}
	
	
	@Override
	public void say() {
		System.out.println("I'm a Tank and my name is: "+name);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBulletMsg(double bulletMessage) {
		this.bulletMsg = bulletMessage;
	}

	public double getBulletMsg() {
		return bulletMsg;
	}

	public void isGood(boolean good) {
		this.good = good;
	}

	public boolean isGood() {
		return good;
	}

	public double getX() {
		return x;
	}

	public double getFifty() {
		return fifty;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Vector getV() {
		return velocity;
	}


}
