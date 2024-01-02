//ID207199282
package exe.geo;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * this class tests Polygon_2D class
 */
public class Polygon_2DTest {
    /**
     * tests that the function return array of Point2D of the polygon
     */
    @Test
    public void testGetAllPoints() {
        //create new polygon:
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        //check each place:
        Point_2D p1= new Point_2D(0,0);
        assertTrue(polygon.getAllPoints()[0].close2equals(p1, 0.001));
        Point_2D p2= new Point_2D(2,3);
        assertTrue(polygon.getAllPoints()[1].close2equals(p2, 0.001));
        Point_2D p3= new Point_2D(0,6);
        assertTrue(polygon.getAllPoints()[2].close2equals(p3, 0.001));
        assertFalse(polygon.getAllPoints()[2].close2equals(p1, 0.001));
    }

    /**
     * test that the point added to the polygon is in the correct place, tnd changed the polygon
     */
    @Test
    public void testAdd() {
        //create new polygon:
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        //add new point:
        Point_2D p1Add = new Point_2D(-5,0);
        Point_2D p2Add = new Point_2D(-5,5);
        polygon.add(p1Add);
        assertTrue(polygon.getAllPoints()[4].close2equals(p1Add,0.001));
        assertFalse(polygon.getAllPoints()[4].close2equals(p2Add,0.001));
    }

    /**
     * tests that the string of a polygon is as expected:
     */
    @Test
    public void testToString() {
        //create new polygon:
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        assertEquals(polygon.toString(),"Polygon_2D, [0.0,0.0, 2.0,3.0, 0.0,6.0, -2.0,3.0]");
    }

    /**
     * test if a polygon contains a point.(few different polygons)
     */
    @Test
    public void testContains() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        Point_2D p1= new Point_2D(1,2);
        Point_2D p2= new Point_2D(50,6);
        Point_2D p3= new Point_2D(0,2.987);
        Point_2D p4=new Point_2D(0,7);
        assertTrue(polygon.contains(p1));
        assertFalse(polygon.contains(p2));
        assertTrue(polygon.contains(p3));
        assertFalse(polygon.contains(p4));
        //different polygon
        Point_2D a1=new Point_2D(1,2);
        Point_2D a2=new Point_2D(3,3);
        Point_2D a3=new Point_2D(4,5);
        Point_2D a4=new Point_2D(5,3);
        Point_2D a5=new Point_2D(6,5);
        Point_2D a6=new Point_2D(8,2);
        Polygon_2D A= new Polygon_2D();
        A.add(a1);
        A.add(a2);
        A.add(a3);
        A.add(a4);
        A.add(a5);
        A.add(a6);
        Point_2D p5= new Point_2D(7,3);
        assertTrue(A.contains(p5));
        Point_2D p6= new Point_2D(8,4);
        assertFalse(polygon.contains(p6));
        Point_2D p7= new Point_2D(7.8,2);
        assertTrue(A.contains(p7));
        Point_2D p8= new Point_2D(5.9,5);
        assertFalse(A.contains(p8));
    }

    /**
     * tests that the area of a polygon is same as this polygon(diamond);
     */
    @Test
    public void testArea() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        assertEquals(polygon.area(),12,0.001);

    }

    /**
     * tests that the perimeter of a polygon is same as this polygon(diamond);
     */
    @Test
    public void testPerimeter() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        assertEquals(polygon.perimeter(),14.4222,0.001);
        assertNotEquals(polygon.perimeter(),14,0.001);
    }

    /**
     * test that the translation of the polygon return the expected String.
     */
    @Test
    public void testTranslate() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        Point_2D vec= new Point_2D(-1,0);
        polygon.translate(vec);
        assertEquals(polygon.toString(),"Polygon_2D, [-1.0,0.0, 1.0,3.0, -1.0,6.0, -3.0,3.0]");
        assertEquals(polygon.area(),12,0.001);
    }

    /**
     * tests that the copy is as expected:
     */
    @Test
    public void testCopy() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        Polygon_2D polCopy= new Polygon_2D(polygon);
        assertEquals(polCopy.area(),polygon.area(),0.001);
        assertEquals(polCopy.toString(),polygon.toString());
        assertEquals(polCopy.perimeter(),polygon.perimeter(),0.001);
        GeoShape p= polygon.copy();
        assertEquals(p.perimeter(),polygon.perimeter(),0.00);
        assertEquals(p.area(), polygon.area(), 0.0);
    }

    /**
     * tests that the scale with the Point_2D(1,2) return the expected polygon;
     */
    @Test
    public void testScale() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        Point_2D vec= new Point_2D(1,2);
        polygon.scale(vec,2);
        assertEquals("[-1.0,-2.0, 3.0,4.0, -1.0,10.0, -5.0,4.0]", Arrays.toString(polygon.getAllPoints()));
    }

    @Test
    public void testRotate() {
    }
}