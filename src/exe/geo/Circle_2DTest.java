//ID 207199282
package exe.geo;


import org.junit.*;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * this class tests Circle2D class:
 */

public class Circle_2DTest {
    /**
     * test that the radius of circle2D is as expected
     */
    @Test
    public void testGetRadius() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        double r0=0;
        Circle_2D c= new Circle_2D(center,r);
        Circle_2D c0= new Circle_2D(center,r0);
        assertEquals(5, c.getRadius(), 0.0);
        assertNotEquals(c.getRadius(),4.99);
        assertEquals(c0.getRadius(),0,0);
    }
    /**
     * test that the center of circle2D is as expected
     */
    @Test
    public void testGetCenter() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        assertEquals(c.getCenter(),center);
        assertTrue(!Objects.equals(c.getCenter(), new Point_2D(9, 0)));
    }
    /**
     * test that the string of circle2D is as expected
     */
    @Test
    public void testToString() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        String s= c.toString();
        String ex= "Circle_2D, 0.0,0.0, 5.0";
        assertEquals(s,ex);
        String ex2="0,0 , 5,0";
        assertNotEquals(c.toString(),ex2);
    }

    /**
     * tests that given Point 2D is inside the circle or not(calculate the
     * distance between the point and the center
     */
    @Test
    public void testContains() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        Point_2D p1= new Point_2D(4.9,0);
        Point_2D p2= new Point_2D(5.2222,0);
        Point_2D p3= new Point_2D(10,10);
        assertTrue(c.contains(p1));
        assertFalse(c.contains(p2));
        assertFalse(c.contains(p3));
        assertTrue(c.contains(center));
    }

    /**
     * test the area of a circle 2d
     */
    @Test
    public void testArea() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        assertEquals(c.area(),78.539,0.001);
    }

    /**
     * test the perimeter of a circle 2d
     */
    @Test
    public void testPerimeter() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        double perimeterEx= 31.415926535;
        assertEquals(c.perimeter(),perimeterEx,0.001);
    }

    /**
     * test the function translate. tests hat given circle translate to the right place
     */
    @Test
    public void testTranslate() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        c.translate(center);
        Circle_2D c2= new Circle_2D(center,r);
        assertEquals(c2.getCenter().x(), c.getCenter().x(), 0.0);
        Point_2D vec= new Point_2D(-3,0);
        c2.translate(vec);
        assertEquals(c2.getCenter().x(), vec.x(), 0.0);
        assertTrue(c2.contains(center));
        Point_2D vec2= new Point_2D(-3,50);
        c2.translate(vec2);
        assertEquals(5, c2.getRadius(), 0.0);
        assertEquals(c2.getCenter().y(), vec2.y(), 0.0);
        assertEquals(c2.area(), c.area(), 0.0);
    }

    /**
     * expected to return deep copy of a given circle2D
     */
    @Test
    public void testCopy() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        GeoShape cCopy=c.copy();
        assertTrue(cCopy.area()==c.area());
        assertTrue(cCopy.perimeter()==c.perimeter());
        assertTrue(cCopy instanceof Circle_2D);
        double r7= ((Circle_2D) cCopy).getRadius();
        assertEquals(r7,c.getRadius(),0.001);
    }

    /**
     * tests that making two times scale on a circle return the expected circle-radius and center
     */
    @Test
    public void testScale() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        Point_2D vec= new Point_2D(-5,1);
        c.scale(vec,2);
        assertEquals(c.toString(),"Circle_2D, 5.0,-1.0, 10.0");
        Point_2D vec2= new Point_2D(-22,3);
        c.scale(vec2,6.67856);
        assertEquals(c.toString(),"Circle_2D, 158.32112,-23.71424, 66.7856");
    }

    /**
     * tests the rotate function;
     */
    @Test
    public void TestRotate() {
        Point_2D center= new Point_2D(0,0);
        double r= 5;
        Circle_2D c= new Circle_2D(center,r);
        Point_2D vec= new Point_2D(-5,1);
        c.rotate(vec,45);
        assertEquals(c.getRadius(),5,0.001);
        assertEquals(c.toString(),"Circle_2D, -0.7573593128807152,3.8284271247461894, 5.0");
        Point_2D centerNew= new Point_2D(-0.7573593128807152,3.8284271247461894);
        assertEquals(c.getCenter(),centerNew);
    }
}