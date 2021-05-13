package physics;

import java.util.ArrayList;
import audio.AudioManager;
import javafx.scene.canvas.GraphicsContext;

/**
 * Create the tank object in the map, the tank object is an inherited class of
 * the sprite object.
 */

public class Tank extends Sprite {
	private int id;
	public double bulletMsg, fire;
	public Map map;
	private boolean good;
	private double x, y, fifty;
	private String name = "PlayerUnknown";
	private int score, dead, compass, up, down = 0;
	private double lastShot = 0;

	protected double speedModifier = 1;
	protected PowerUp pow;

	public Tank(String s, double x, double y) {
		super(s, x, y);
	}

	public Tank(String name, int x, int y, boolean good) {
		this.x = this.position.x = x;
		this.y = this.position.y = y;
		this.good = good;
		this.name = name;

	}

	public void moveOnline(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList,
			GraphicsContext context, ArrayList<Bullet> laserListT) {

		if (keyPressedList.contains("LEFT"))
			compass = 1;
		if (keyPressedList.contains("RIGHT"))
			compass = (-1);
		if (keyPressedList.contains("UP")) {
			up = 1;
			fifty = 50;
		} else if (keyPressedList.contains("DOWN")) {
			down = 1;
			fifty = -50;
		} else {
			up = 0;
			down = 0;
			fifty = 0;
		}
		context.save();
		if (keyPressedList.contains("SPACE")) {
			fire = 1;
		}
		if (bulletMsg == 1) {
			if (id % 2 == 0) {
				if (elapsedTime - lastShot > 0.75 && this.hp > 0) {

					Bullet laser = new Bullet("imagesProjectAI/Blue.png", this);
					AudioManager.play("shoot");
					laser.position.set(this.position.x, this.position.y);
					laser.velocity.setLength(200);
					laser.velocity.setAngle(this.rotation);
					laserListT.add(laser);
					lastShot = elapsedTime;
				}
				bulletMsg = 0;
			} else {
				if (elapsedTime - lastShot > 0.75 && this.hp > 0) {

					Bullet laser = new Bullet("imagesProjectAI/red-circle.png", this);
					AudioManager.play("shoot");
					laser.position.set(this.position.x, this.position.y);
					laser.velocity.setLength(200);
					laser.velocity.setAngle(this.rotation);
					laserListT.add(laser);
					lastShot = elapsedTime;
				}
				bulletMsg = 0;
			}
		}
	}

	public void enemyFireOnline(GraphicsContext context, ArrayList<Bullet> laserListE) {
		if (bulletMsg == 1) {
			if (id % 2 == 0) {
				if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
					Bullet laser = new Bullet("imagesProjectAI/Blue.png", this);
					AudioManager.play("shoot");

					laser.position.set(this.position.x, this.position.y);
					laser.velocity.setLength(200);
					laser.velocity.setAngle(this.rotation);
					laserListE.add(laser);
					lastShot = elapsedTime;

				}
				bulletMsg = 0;
			}
			if(id % 2 != 0) {
				if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
					Bullet laser = new Bullet("imagesProjectAI/red-circle.png", this);
					AudioManager.play("shoot");

					laser.position.set(this.position.x, this.position.y);
					laser.velocity.setLength(200);
					laser.velocity.setAngle(this.rotation);
					laserListE.add(laser);
					lastShot = elapsedTime;

				}
				bulletMsg = 0;
			}
		}
	}

	public void move(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList, GraphicsContext context,
			ArrayList<Bullet> laserListT) {

		if (keyPressedList.contains("LEFT"))
			// this.getBoundary().rotation -=3;
			rotation -= 3;

		if (keyPressedList.contains("RIGHT"))
			// this.getBoundary().rotation +=3;
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

		if (keyPressedList.contains("SPACE")) {
			bulletMsg = 1;
		}

		if (bulletMsg == 1) {
			context.save();
			if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
				Bullet laser = new Bullet("imagesProjectAI/Blue.png", this);
				AudioManager.play("shoot");

				laser.position.set(this.position.x, this.position.y);
				laser.velocity.setLength(200);
				laser.velocity.setAngle(this.rotation);
				laserListT.add(laser);
				lastShot = elapsedTime;

			}
		}
	}

	public void moveWASD(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList,
			GraphicsContext context, ArrayList<Bullet> laserListT) {

		if (keyPressedList.contains("A"))
			// this.getBoundary().rotation -=3;
			rotation -= 3;

		if (keyPressedList.contains("D"))
			// this.getBoundary().rotation +=3;
			rotation += 3;

		if (keyPressedList.contains("W")) {
			velocity.setAngle(rotation);
			velocity.setLength(50 * speedModifier);
			fifty = 50;
		} else if (keyPressedList.contains("S")) {
			velocity.setAngle(rotation);
			velocity.setLength(-50 * speedModifier);
			fifty = -50;
		} else {
			velocity.setLength(0);
			fifty = 0;
		}

		if (keyPressedList.contains("SPACE")) {
			bulletMsg = 1;
		}

		if (bulletMsg == 1) {
			context.save();
			if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
				Bullet laser = new Bullet("imagesProjectAI/Blue.png", this);
				AudioManager.play("shoot");

				laser.position.set(this.position.x, this.position.y);
				laser.velocity.setLength(200);
				laser.velocity.setAngle(this.rotation);
				laserListT.add(laser);
				lastShot = elapsedTime;

			}
		}

	}

	public void moveLocalWASD(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList,
			GraphicsContext context, ArrayList<Bullet> laserListT) {

		if (keyPressedList.contains("A"))
			// this.getBoundary().rotation -=3;
			rotation -= 3;

		if (keyPressedList.contains("D"))
			// this.getBoundary().rotation +=3;
			rotation += 3;

		if (keyPressedList.contains("W")) {
			velocity.setAngle(rotation);
			velocity.setLength(50 * speedModifier);
			fifty = 50;
		} else if (keyPressedList.contains("S")) {
			velocity.setAngle(rotation);
			velocity.setLength(-50 * speedModifier);
			fifty = -50;
		} else {
			velocity.setLength(0);
			fifty = 0;
		}

		if (keyPressedList.contains("J")) {
			bulletMsg = 1;
		}

		if (bulletMsg == 1) {
			context.save();
			if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
				Bullet laser = new Bullet("imagesProjectAI/Blue.png", this);
				AudioManager.play("shoot");

				laser.position.set(this.position.x, this.position.y);
				laser.velocity.setLength(200);
				laser.velocity.setAngle(this.rotation);
				laserListT.add(laser);
				lastShot = elapsedTime;

			}
		}

	}

	public void moveLocal(ArrayList<String> keyPressedList, ArrayList<String> keyJustPressedList,
			GraphicsContext context, ArrayList<Bullet> laserListT) {

		if (keyPressedList.contains("LEFT"))
			// this.getBoundary().rotation -=3;
			rotation -= 3;

		if (keyPressedList.contains("RIGHT"))
			// this.getBoundary().rotation +=3;
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

		if (keyPressedList.contains("SPACE")) {
			bulletMsg = 1;
		}

		if (bulletMsg == 1) {
			context.save();
			if (elapsedTime - lastShot > 0.75 && this.hp > 0) {
				Bullet laser = new Bullet("imagesProjectAI/red-circle.png", this);
				AudioManager.play("shoot");

				laser.position.set(this.position.x, this.position.y);
				laser.velocity.setLength(200);
				laser.velocity.setAngle(this.rotation);
				laserListT.add(laser);
				lastShot = elapsedTime;

			}
		}

	}

	@Override
	public boolean collide(Sprite other) {
		if (this.hp <= 0 || other.hp <= 0) {
			return false;
		}
		if (this.overlaps(other)) {
			// run collision code
			// System.out.println("Collision");

			if (other instanceof Bullet) {
				Bullet b = (Bullet) other;
				if (b.getParent() != this) {
					// System.out.println("Tank hit");
					this.hp -= b.damage;
					this.score -= b.damage;
					b.getParent().addScore((int) b.damage);
					if (this.hp <= 0) { // tank is dead
						AudioManager.play("explode");
					}
					// System.out.println(hp);
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
		if (collision) {

		}
		return collision;
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

	public int getScore() {
		return this.score;
	}

	public void addScore(int i) {
		this.score += i;
	}

	public void setScore(int i) {
		this.score = i;
	}

	public int getHP() {
		return this.hp;
	}

	public void setHP(int i) {
		this.hp = i;
	}

	public void addHP(int i) {
		this.hp += i;

	}

	public int getDead() {
		return dead;
	}

	public void setDead(int dead) {
		this.dead = dead;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public int getCompass() {
		return compass;
	}

	public void setCompass(int compass) {
		this.compass = compass;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public double getLastShot() {
		return lastShot;
	}

	public void setLastShot(double lastShot) {
		this.lastShot = lastShot;
	}

	public double getFire() {
		return fire;
	}

	public void setFire(double fire) {
		this.fire = fire;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
