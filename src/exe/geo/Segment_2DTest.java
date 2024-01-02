//ID-207199282
package exe.geo;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * this class tests Segment_2D class
 */
public class Segment_2DTest {
    /**
     * test that get the expected p1 of a segment
     */
    @Test
    public void testGet_p1() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p3p2= new Segment_2D(p3,p2);
        assertTrue(p1p2.get_p1().close2equals(p1,0.001));
        assertFalse(p1p2.get_p1().close2equals(p3,0.001));
        assertTrue(p3p2.get_p1().close2equals(p3,0.001));
        assertFalse(p3p2.get_p1().close2equals(p2,0.001));
        assertFalse(p3p2.get_p1().close2equals(p1,0.001));
    }
    /**
     * test that get the expected p2 of a segment
     */
    @Test
    public void testGet_p2() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p3p2= new Segment_2D(p3,p2);
        assertFalse(p1p2.get_p2().close2equals(p1,0.001));
        assertFalse(p1p2.get_p2().close2equals(p3,0.001));
        assertTrue(p3p2.get_p2().close2equals(p2,0.001));
        assertFalse(p3p2.get_p2().close2equals(p3,0.001));
        assertTrue(p1p2.get_p2().close2equals(p2,0.001));
    }

    /**
     * test that segment contains and not contain a given point.
     */
    @Test
    public void testContains() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Point_2D p4=new Point_2D(0,1.75);
        Point_2D p5=new Point_2D(2,0);
        Point_2D p6=new Point_2D(5.6,0);
        Point_2D p7=new Point_2D(0,7.01);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        assertTrue(p1p2.contains(p1));
        assertTrue(p1p2.contains(p5));
        assertTrue(p1p3.contains(p4));
        assertFalse(p1p3.contains(p2));
        assertFalse(p1p2.contains(p6));
        assertFalse(p1p3.contains(p6));
        assertFalse(p1p3.contains(p7));
    }

    /** test that return 0 each time
     *
     */
    @Test
    public void testArea() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        assertTrue(p1p2.area()==0);
        assertTrue(p1p3.area()==0);
        assertFalse(p1p2.area()==0.009);
    }

    /**
     * test that the perimeter is the length*2, and if the length is 0 return 0;
     */
    @Test
    public void testPerimeter() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        Segment_2D p1p1=new Segment_2D(p1,p1);
        assertEquals(11, p1p2.perimeter(), 0.0);
        assertEquals(14, p1p3.perimeter(), 0.0);
        assertEquals(0, p1p1.perimeter(), 0.0);
    }

    /**
     * tests that the segment translate to the expected 2 point
     */

    @Test
    public void testTranslate() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Point_2D p4=new Point_2D(0.0,5.5);
        Point_2D p5= new Point_2D(5.5,5.5);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        p1p2.translate(p4);
        p1p3.translate(p2);
        assertTrue(p1p2.get_p1().close2equals(p4,0.001));
        assertTrue(p1p2.get_p2().close2equals(p5,0.001));
        assertTrue(p1p3.get_p1().close2equals(p2,0.001));
    }

    /**
     * test that the function return new segment as the given segment
     */
    @Test
    public void testCopy() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        GeoShape p1p2Copy= p1p2.copy();
        GeoShape p1p3Copy=p1p3.copy();
        assertEquals(p1p2Copy.perimeter(), p1p2.perimeter(), 0.0);
        assertEquals(p1p3Copy.area(), p1p3.area(), 0.0);
        assertFalse(p1p2Copy.contains(p3));
        assertTrue(p1p3Copy.contains(p1));
    }

    /**
     * tests that the segment move by scale as exepted
     */
    @Test
    public void testScale() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Point_2D p4=new Point_2D(0.0,5.5);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        Segment_2D p1p1=new Segment_2D(p1,p1);
        Point_2D center= new Point_2D(2,0);
        double ratio= 0.5;
        p1p2.scale(center,ratio);
        Point_2D p11= new Point_2D(1,0);
        Point_2D p22= new Point_2D(3.75,0);
        assertEquals(p1p2.get_p1(),p11);
        assertEquals(p1p2.get_p2(),p22);
        p1p3.scale(center,ratio);
        assertEquals(p1p3.get_p1(),p11);
    }

    /**
     * tests that the segment rotate as exepted.
     */
    @Test
    public void testRotate() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(5.5,0);
        Point_2D p3= new Point_2D(0,7);
        Point_2D p4=new Point_2D(0.0,5.5);
        Segment_2D p1p2= new Segment_2D(p1,p2);
        Segment_2D p1p3= new Segment_2D(p1,p3);
        p1p2.rotate(p3,60);
        assertEquals(p1p2.get_p1().toString(),"6.06217782649107,3.5");
        assertEquals(p1p2.get_p2().toString(),"8.812177826491071,8.263139720814412");
        assertEquals(p1p2.area(),0,0.001);
    }
}