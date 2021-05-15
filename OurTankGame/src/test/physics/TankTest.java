package test.physics;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import physics.Bullet;
import physics.Tank;

public class TankTest {
	JFXPanel panel = new JFXPanel();
	
	@Test
	public void testCollideTank() {
		Tank tank1 = new Tank("art/tank64.png", 0, 0);
		Tank tank2 = new Tank("art/tank64.png", 0, 0);
		Tank tank3 = new Tank("art/tank64.png", 200,200);
		Bullet bullet1 = new Bullet("imagesProjectAI/red-circle.png",tank3, 31,31);
		
		assertTrue(tank1.collide(tank2));
		assertFalse(tank1.collide(tank3));
		assertTrue(tank1.collide(bullet1));
		
	}
	
	@Test
	public void testAddScore() {
		Tank tank1 = new Tank("art/tank64.png", 0, 0);
		assertTrue(tank1.getScore() == 0);
		tank1.addScore(5);
		assertTrue(tank1.getScore() == 5);
	}

	@Test
	public void testAddHP() {
		Tank tank1 = new Tank("art/tank64.png", 0, 0);
		assertTrue(tank1.getHP() == 100);
		tank1.addHP(5);
		assertTrue(tank1.getHP() == 105);
	}

}
