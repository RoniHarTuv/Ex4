//ID-207199282
package exe.geo;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 this class tests Triangle_2D class:
 */
public class Triangle_2DTest {

    /**
     * Tests that a triangle that wat created from 3 points-return the same 3 points in array
     */
    @Test
    public void testGetAllPoints() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Point_2D[] GetAll =T.getAllPoints();
        assertEquals(GetAll[0], P1);
        Point_2D p2get = new Point_2D(GetAll[1]);
        assertTrue(p2get.close2equals(P2,0.001));
        assertTrue(GetAll[2].close2equals(P3,0.001));
        assertFalse(GetAll[2].close2equals(P1,0.001));
    }

    /**
     * tests that agiven point_2D is inside the triangle or not
     */
    @Test
    public void teatContains() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Point_2D p4= new Point_2D(1.99,0);
        Point_2D p5= new Point_2D(7,0);
        Point_2D p6= new Point_2D(0,0);
        assertFalse(T.contains(p5));
        assertTrue(T.contains(p4));
        assertTrue(T.contains(P3));
        assertTrue(T.contains(p6));
    }

    /**
     * tests that the area is as expected, aka epsilon;
     */
    @Test
    public void testArea() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Triangle_2D T2= new Triangle_2D(T);
        assertEquals(T2.area(),4,0.001);
        assertEquals(T.area(),T2.area(),0.001);
        assertNotEquals(T.area(),4.001,0.001);

    }

    /**
     * tests that the perimeter is as expected, aka epsilon;
     */
    @Test
    public void testPerimeter() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Triangle_2D T2= new Triangle_2D(T);
        assertEquals(T.perimeter(),T2.perimeter(),0.001);
        assertNotEquals(T.perimeter(),9,0.001);
        assertEquals(T.perimeter(),9.6568,0.001);
        double perimeterT= ((Math.sqrt(8)*2)+4);
        assertEquals(T2.perimeter(),perimeterT,0.001);
    }

    /**
     * test that while translates the triangle:
     * 1. the area is not changing
     * 2.the point of the new place is as expected;
     */
    @Test
    public void testTranslate() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Point_2D vec= new Point_2D(0,0);
        T.translate(vec);
        assertEquals(T.area(),4,0.001);
        assertTrue(T.getAllPoints()[0].x()==2);
        assertTrue(T.getAllPoints()[1].x()==-2);
        assertTrue(T.getAllPoints()[2].x()==0);
        assertTrue(T.getAllPoints()[0].y()==T.getAllPoints()[1].y());

    }

    /**
     *  tests that a new (deep) copy of this Triangle_2D is as expected
     */
    @Test
    public void testCopy() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Triangle_2D T2= new Triangle_2D(T);
        GeoShape T3= T.copy();
        assertEquals(T3.area(),T.area(),0.001);
        assertEquals(T3.perimeter(),T2.perimeter(),0.001);
    }

    /**
     * tests that the expected triangle scale is true:
     */
    @Test
    public void testScale() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        double area= T.area();
        T.scale(P1,2);
        assertTrue(T.area()!=area);
        assertTrue(T.contains(P3));
        Point_2D p= new Point_2D (2, 0);
        Point_2D pp= new Point_2D (-6, 0);
        Point_2D ppp= new Point_2D (-2, 4);
        Triangle_2D Te= new Triangle_2D(p,pp,ppp);
        assertEquals(T.getAllPoints()[0].x(),Te.getAllPoints()[0].x(),0.001);
        assertEquals(T.getAllPoints()[1].x(),Te.getAllPoints()[1].x(),0.001);
        assertEquals(T.getAllPoints()[2].x(),Te.getAllPoints()[2].x(),0.001);
        assertTrue(T.contains(P1)&& Te.contains(pp));
        assertEquals(Te.perimeter(), T.perimeter(), 0.0);
    }

    /**
     * tests that the rotate function return the expected triangle:
     */
    @Test
    public void testRotate() {
        Point_2D P1= new Point_2D(2,0);
        Point_2D P2= new Point_2D(-2,0);
        Point_2D P3 =new Point_2D(0,2);
        Triangle_2D T=new Triangle_2D(P1,P2,P3);
        Point_2D vec= new Point_2D(2,5.975);
        T.rotate(vec,45);
        assertEquals(Arrays.toString(T.getAllPoints()),"[6.224963017589621,1.7500369824103785, 3.396535892843432,-1.0783901423358113, 3.39653589284343,1.7500369824103776]");
    }
}