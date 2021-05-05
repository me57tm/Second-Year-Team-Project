package physics;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {

	
	@SuppressWarnings("deprecation")
	@Test
	public void testSetAngle() {
		Vector v = new Vector();
		v.set(1, 0);
		v.setAngle(90);
		assertEquals(v.getX(),0,0.01);
		assertEquals(v.getY(),-1,0.01);
	}

}
