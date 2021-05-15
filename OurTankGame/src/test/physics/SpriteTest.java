package test.physics;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import physics.Layer;
import physics.Map;
import physics.Sprite;
import physics.Tile;

public class SpriteTest {
	JFXPanel panel = new JFXPanel();
	
	@Test
	public void testCollide() {
		Sprite sprite1 = new Sprite("art/tank64.png",0,0);
		Sprite sprite2 = new Sprite("art/tank64.png",0,0);
		
		boolean expected = true;
		boolean actual = sprite1.collide(sprite2);
		
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testisShot() {
		Sprite tank1 = new Sprite("imagesProjectAI/red-circle.png", 0, 0);
		Sprite bullet1 = new Sprite("imagesProjectAI/red-circle.png",0,0);
		Sprite bullet2 = new Sprite("imagesProjectAI/red-circle.png",200,200);	
		
		boolean expected = true;
		boolean actual = tank1.isShot(bullet1);
		
		assertEquals(expected,actual);	
		assertFalse(tank1.isShot(bullet2));
	}
	
	@Test
	public void testOverlaps() {
		Sprite tank1 = new Sprite("art/tank64.png", 0, 0);
		Sprite tank2 = new Sprite("art/tank64.png", 63, 63);
		Sprite tank3 = new Sprite("art/tank64.png", 65, 65);
		
		System.out.println(tank1.overlaps(tank3));
		assertTrue(tank1.overlaps(tank2));
		assertFalse(tank1.overlaps(tank3));
	}
	
	@Test
	public void testCollideMap() {
		
		Map map = new Map();
		Layer layer1 = new Layer("abc", map.MAP_WIDTH_IN_TILES, map.MAP_HEIGHT_IN_TILES);
		Layer layer2 = new Layer("abc", map.MAP_WIDTH_IN_TILES, map.MAP_HEIGHT_IN_TILES);
		
		Tile tile = new Tile("art/texture-bg.png",false);
		layer2.addTile(tile,20, 20);
		
		map.addLayer(layer1);
		map.addLayer(layer2);
		
		Sprite tank1 = new Sprite("art/tank64.png", 640, 640);
		
		assertTrue(tank1.collideMap(map));
		
	}
	
	
	//Jonathan please do the test for collideMapFuture
	
//	public void testCollideMapFuture() {
//		write code here
//	}


}
