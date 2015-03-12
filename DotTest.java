import static org.junit.Assert.*;

import org.junit.Test;


public class DotTest {

	@Test
	public void testConstructor() {
		Dot myDot = new Dot();
		assertTrue(myDot.getColor() >= 1 && myDot.getColor() <=5);
		
		myDot = new Dot(3);
		assertTrue(myDot.getColor() == 3);
	}
    @Test
    public void testgetColor() {
        Dot myDot = new Dot(5);
        assertEquals(myDot.getColor(),5);
    }
	
	@Test
	public void testIsSameColor(){
    	Dot dot1 = new Dot(2);
    	Dot dot2 = new Dot(2);
    	assertTrue(dot1.isSameColor(dot2));
    	
    	dot2 = new Dot(3);
    	
    	assertFalse(dot1.isSameColor(dot2));
    }
	
	@Test
	public void testisColor(){
    	Dot dot1 = new Dot(2);
    	Dot dot2 = new Dot(2);
    	assertTrue(dot1.isSameColor(dot2));
    	
    	assertTrue(dot1.isColor(2));
    	assertFalse(dot1.isColor(1));
    }

}
