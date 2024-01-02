//ID-207199282
package exe.gui;
import exe.geo.GeoShape;
import exe.geo.Point_2D;
import exe.geo.Polygon_2D;
import org.junit.Test;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import static org.junit.Assert.*;

/**
 * this class tests GUIShape class:
 * in each test create a new shape and check if the function return the expected ans
 */
public class GUIShapeTest {
    /**
     * tests that the shape is as expected
     */
    @Test
    public void getShape() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        Point_2D[] geoP= ((Polygon_2D) geo).getAllPoints();
        Point_2D[] guiP= ((Polygon_2D)gui.getShape()).getAllPoints();
        assertArrayEquals(guiP,geoP);
    }

    /**
     * test that the shape is filled or not;
     */
    @Test
    public void isFilled() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertTrue(gui.isFilled());
    }

    /**
     * tests that set filled function-true and false:
     */
    @Test
    public void setFilled() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        gui.setFilled(false);
        assertFalse(gui.isFilled());
        gui.setFilled(true);
        assertTrue(gui.isFilled());
    }

    /**
     * tests get the color of the shapes
     */
    @Test
    public void getColor() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertSame(gui.getColor(), Color.CYAN);
    }

    /**
     * tests the set color of the shape
     */
    @Test
    public void setColor() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertSame(gui.getColor(), Color.CYAN);
        gui.setColor(Color.DARK_GRAY);
        assertSame(gui.getColor(), Color.DARK_GRAY);
        gui.setColor(Color.pink);
        assertSame(gui.getColor(), Color.pink);
    }

    /**
     * tests that the function return the right tag
     */
    @Test
    public void getTag() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertEquals(1, gui.getTag());
    }

    /**
     * tests that the function set the expected right tag
     */
    @Test
    public void setTag() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        gui.setTag(5);
        assertEquals(5, gui.getTag());
        gui.setTag(7);
        assertEquals(7, gui.getTag());
    }

    /**
     * tests that the function copy return a deep copy of a shape
     */
    @Test
    public void copy() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        GUI_Shape gui2= gui.copy();
        Point_2D[] geoP= ((Polygon_2D) geo).getAllPoints();
        Point_2D[] guiP= ((Polygon_2D)gui.getShape()).getAllPoints();
        Point_2D[] gui2P= ((Polygon_2D)gui2.getShape()).getAllPoints();
        assertArrayEquals(guiP,gui2P);

    }

    /**
     * tests to string function
     */
    @Test
    public void testToString() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertEquals("GUIShape, java.awt.Color[r=0,g=255,b=255],true,1,Polygon_2D, [0.0,0.0, 2.0,3.0, 0.0,6.0, -2.0,3.0]", gui.toString());
    }

    /**
     * tests is selected function
     */
    @Test
    public void isSelected() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertFalse(gui.isSelected());

    }

    /**
     * tests set selected function
     */
    @Test
    public void setSelected() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        GeoShape geo= polygon.copy();
        GUIShape gui= new GUIShape(geo,true, Color.cyan,1);
        assertFalse(gui.isSelected());
        gui.setSelected(true);
        assertTrue(gui.isSelected());
        gui.setSelected(false);
        assertFalse(gui.isSelected());
    }

    /**
     * tests set shape function
     */
    @Test
    public void setShape() {
        Polygon_2D polygon= new Polygon_2D();
        Point_2D[] pointArr= {new Point_2D(0,0),new Point_2D(2,3),new Point_2D(0,6),new Point_2D(-2,3)};
        for (int i = 0; i < 4; i++) {
            polygon.add(pointArr[i]);
        }
        Polygon_2D rek= new Polygon_2D();
        GeoShape geo= rek.copy();
        GUIShape guiRek= new GUIShape(geo, true,Color.BLACK,2);
        assertFalse(Arrays.equals(pointArr, ((Polygon_2D)guiRek.getShape()).getAllPoints()));
        guiRek.setShape(polygon);
        assertArrayEquals(pointArr,((Polygon_2D)guiRek.getShape()).getAllPoints());
    }
}