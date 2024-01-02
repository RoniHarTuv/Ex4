//ID 207199282
package exe.geo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * this class tests Rect2D class:
 */
public class Rect_2DTest {
    /**
     * test that dx of a rectangle is true
     */
    @Test
    public void testDx() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Point_2D p3= new Point_2D(-4,2);
        Rect_2D re1 = new Rect_2D(p1,p2);
        Rect_2D re2 = new Rect_2D(p1,p3);
        double redx1= re1.dx();
        double redx2= re2.dx();
        double ex= 4.0;
        assertEquals(ex,redx1,0);
        assertEquals(redx1, redx2, 0.0);
    }

    /**
     * tests that dy of a rectangle is true
     */
    @Test
    public void testDy() {
        Point_2D p1= new Point_2D("0,0");
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        double redy= re.dy();
        double ex= 2.0;
        assertEquals(ex,redy,0);
    }

    /**
     * tests if given point inside the rect or not. consider the points of the rectangle
     */
    @Test
    public void testContains() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Point_2D p3= new Point_2D(6.1,2);
        Point_2D p4= new Point_2D(2.9,1.7);
        Point_2D p5= new Point_2D(-1,0);
        Point_2D p6= new Point_2D("-2,0");
        Rect_2D re = new Rect_2D(p1,p2);
        assertFalse(re.contains(p3));
        assertTrue(re.contains(p4)); /////////////not true
        assertTrue(re.contains(p1));
        assertFalse(re.contains(p5));
        assertFalse(re.contains(p6));
    }

    /**
     * tests that the area of a given rectangle is true
     */
    @Test
    public void testArea() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        double area= re.area();
        assertEquals(8,area,0);
        assertNotEquals(8.7,area,0);
        //create a area==0:
        Rect_2D re2= new Rect_2D(p1,p1);
        assertEquals(0,re2.area(),0);
        Rect_2D re3= new Rect_2D(re2);
        assertEquals(0,re3.area(),0);
    }

    /**
     * tests that the perimeter is as expected
     */
    @Test
    public void testPerimeter() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        double pe = re.perimeter();
        assertEquals(pe,12, 0);
        //create perimeter 0:
        Rect_2D re2= new Rect_2D(p1,p1);
        assertEquals(re2.perimeter(),0,0);
        //use other constructor
        Rect_2D re3= new Rect_2D(re);
        assertEquals(12,re3.perimeter(),0);
    }

    /**
     * tests that a given ret, and it translates, return the correct nea place of the rect
     */
    @Test
    public void testTranslate() {
        Point_2D vec= (new Point_2D(-4,0));
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        Point_2D p3= new Point_2D(-4,2);
        Point_2D p4= new Point_2D(-8,0);
        Rect_2D re2= new Rect_2D(p3,p4);
        re.translate(vec);
        assertTrue(re.dx()==4);
        assertTrue(re.dy()==2);
        assertTrue(re.area()==8);
        assertTrue(re.perimeter()==12);
        assertFalse(re.contains(new Point_2D(1,0)));
    }

    /**
     * tests that this function return the deep copy of the given rect2D
     */
    @Test
    public void testCopy() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        re.copy();
        assertTrue(re.perimeter()==12);
        assertTrue(re.contains(p2));
        assertTrue(re.area()==8);

    }

    /**
     * tests that a scale rect2D create different rect2D with different area, perimeter...
     * tests that the points of it as exepted;
     */
    @Test
    public void testScale() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        re.scale(p1,7);
        assertTrue(re.dx()==28.0);
        assertTrue(re.dy()==14.0);
        assertTrue(re.perimeter()>45);
        Rect_2D re0 = new Rect_2D(p1,p1);
        assertEquals(re0.perimeter(),0,0.001);
        assertEquals(re0.area(),0,0.001);
    }

    /**
     * tests that rect rotates return the expected string of rect, and also didn't change the area,perimeter...
     */
    @Test
    public void testRotateAndToString() {
        Point_2D p1= new Point_2D(0,0);
        Point_2D p2= new Point_2D(4,2);
        Rect_2D re = new Rect_2D(p1,p2);
        Point_2D vec= new Point_2D(6,7);
        re.rotate(vec,90);
        assertEquals(4.0, re.dy(), 0.0);
        assertEquals(0.0, re.dx(), 0.0);
        re.rotate(vec,160);
        assertEquals(-4.442810769794974, re.dy(), 0.0);
        assertEquals(-1.3680805733026746, re.dx(), 0.0);
        assertEquals("Rect_2D, 1.4742725144526547,15.032296727995135, 1.985577182721796,10.58948595820016",re.toString());
    }
}