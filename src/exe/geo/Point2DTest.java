//ID 207199282
package exe.geo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * this class test Point_2D class
 */
public class Point2DTest {
    public static final double EPS1 = 0.001;

    @Test
    // tests the x function:
    public void testX(){
        Point_2D p= new Point_2D(5,7);
        assertEquals(p.x(),5,0);

    }

    @Test
    // tests the y function:
    public void testY(){
        Point_2D p= new Point_2D(5,7);
        assertEquals(p.y(),7,0);
    }

    @Test
    // tests the ix function:
    public void testIntX(){
        Point_2D p= new Point_2D(5,7);
        assertEquals(p.ix(),5,0);

    }

    @Test
    // tests the iy function:
    public void testIntY(){
        Point_2D p= new Point_2D(5,7);
        assertEquals(p.iy() ,7,0);

    }

    @Test
    // test the add function
    public void testAdd(){
        Point_2D p0= new Point_2D(3,4);
        Point_2D p= new Point_2D(p0);
        assertEquals(p,p0);

    }

    @Test
    // test the toString function:
    public void testToString(){
        Point_2D p= new Point_2D(6,7.866);
        assertEquals(p.toString(),"6.0,7.866");
    }

    @Test
    // tests the distance from origin function:
    public void testDistanceOf(){
        Point_2D p= new Point_2D(0,7.866);
        assertEquals(p.distance(),7.866,0);
    }

    @Test
    // tests the distance function (2 points):
    public void testDistance(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        assertEquals(p2.distance(p1),9,0);
    }

    @Test
    // test the equals function:
    public void testEquals(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        Point_2D p3= new Point_2D(0,4);
        assertEquals(p3,p2);
        assertNotEquals(p1,p3);
    }

    @Test
    // tests the close2equals function:
    public void testClose2equals(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        Point_2D p3= new Point_2D(0,4);
        assertTrue(p2.close2equals(p3,0.001));
    }

    @Test
    // tests the vector function:
    public void testVector(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        Point_2D p3= p2.vector(p1);
        assertEquals("0.0,-9.0", p3.toString());
    }

    @Test
    // tests the move function:
    public void testMove(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        p2.move(p1);
        assertEquals(p2.toString(),"0.0,-1.0");
    }

    @Test
    // tests the rotate function:
    public void testRotate(){
        Point_2D p2= new Point_2D(0,4);
        Point_2D p1= new Point_2D(0,-5);
        p1.rotate(p2,90);
        assertEquals("9.0,4.0",p1.toString());
    }

    /**
     * auxiliary function for comparing points;
     * checks that the x,y values of 2 points are closer than epsilon
     * @param p1 first point
     * @param p2 second point
     * @return true if points are closer than epsilon, otherwise false.
     */
    private boolean equals(Point_2D p1, Point_2D p2){
        return (Math.abs(p1.x()-p2.x())<EPS1 && Math.abs(p1.y()-p2.y())<EPS1);
    }
}
