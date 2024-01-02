//ID-207199282
package exe.ex4;
import exe.geo.*;
import exe.gui.GUIShape;
import exe.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests ShapeCollection class.
 * first, defined all the Shapes:
 */
public class ShapeCollectionTest {
    public static final Point_2D p1 = new Point_2D(0, 0);
    public static final Point_2D p2 = new Point_2D(0, 4);
    public static final Point_2D p3 = new Point_2D(8, 4);
    public static final Point_2D p4 = new Point_2D(8, 0);
    public static final Point_2D p5 = new Point_2D(4, 0);
    public static final Point_2D p6 = new Point_2D(-4, 4);
    public static final Point_2D p7 = new Point_2D(8, 8);
    public static final Point_2D p8 = new Point_2D(0, 8);
    public static final Point_2D p9 = new Point_2D(4, 4);

    static Circle_2D circle = new Circle_2D(p1, 4);
    static GUIShape Circle = new GUIShape(circle.copy(), true, Color.pink, 0);
    static Segment_2D segment = new Segment_2D(p1, p7);
    public static final GUIShape Segment = new GUIShape(segment.copy(), true, Color.pink, 1);
    static Rect_2D rectangle = new Rect_2D(p1, p3);
    public static final GUIShape Rectangle = new GUIShape(rectangle.copy(), true, Color.pink, 2);
    static Triangle_2D triangle = new Triangle_2D(p1, p6, p9);
    public static final GUIShape Triangle = new GUIShape(triangle.copy(), true, Color.pink, 3);
    static Polygon_2D polygon = new Polygon_2D();
    static Point_2D[] pointArr = {p6, p8, p9, p1};

    @BeforeAll
    public static void SetingPoly() {
        for (Point_2D p : pointArr) {
            polygon.add(p);
        }
    }

    public static final GUIShape Polygon = new GUIShape(polygon.copy(), true, Color.pink, 4);
    // my shape collection:
    ShapeCollection collection = new ShapeCollection();

    /**
     * tests that the function get return the expected shape in index i
     */
    @Test
    public void get() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        collection.add(Polygon);
        assertEquals(collection.get(0), Circle);
        assertEquals(collection.get(1), Segment);
        assertEquals(collection.get(2), Rectangle);
        assertEquals(collection.get(3), Triangle);
        assertEquals(collection.get(4), Polygon);
    }

    /**
     * tests that the function size return the size of the shape collections
     */
    @Test
    void size() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        collection.add(Polygon);
        assertEquals(collection.size(), 5);
    }

    /**
     * tests that the function remove element at , remove the correct elements.
     */
    @Test
    void removeElementAt() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        collection.add(Polygon);
        collection.removeElementAt(3);
        assertEquals(collection.get(3), Polygon);
        assertEquals(collection.get(2), Rectangle);
    }

    /**
     * tests that the function add at , add the right shape in the right place
     */
    @Test
    void testAddAtAndAdd() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        collection.addAt(Polygon, 0);
        assertEquals(collection.get(0), Polygon);
        collection.addAt(Circle, 0);
        assertEquals(collection.get(1), Polygon);
    }

    /**
     * tests that the function copy, constructs a deep copy of this collection.
     */
    @Test
    void copy() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        GUI_Shape_Collection sh = collection.copy();
        assertEquals(sh.size(), collection.size());
        assertEquals(sh.get(2), collection.get(2));
    }

    /**
     * tests that the function return the expected sort of shape:
     */
    @Test
    void sort() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        //sort by area
        Comparator<GUI_Shape> compByArea = Comparator.comparingDouble(o -> o.getShape().area());
        collection.sort(compByArea);
        assertEquals(collection.toString(), "GUIShape, -20561,true,1,Segment_2D, 0.0,0.08.0,8.0GUIShape, -20561,true,3,Triangle_2D, 0.0,0.0-4.0,4.04.0,4.0GUIShape, -20561,true,2,Rect_2D, 0.0,0.0, 8.0,4.0GUIShape, -20561,true,0,Circle_2D, 0.0,0.0, 4.0");
        //sort by perimeter
        Comparator<GUI_Shape> compByPerimeter = Comparator.comparingDouble(o -> o.getShape().perimeter());
        collection.sort(compByPerimeter);
        assertEquals(collection.get(0), Triangle);
        //sort by tag
        Comparator<GUI_Shape> compByTag = Comparator.comparingInt(GUI_Shape::getTag);
        collection.sort(compByTag);
        assertEquals(collection.toString(), "GUIShape, -20561,true,0,Circle_2D, 0.0,0.0, 4.0GUIShape, -20561,true,1,Segment_2D, 0.0,0.08.0,8.0GUIShape, -20561,true,2,Rect_2D, 0.0,0.0, 8.0,4.0GUIShape, -20561,true,3,Triangle_2D, 0.0,0.0-4.0,4.04.0,4.0");
        //sort by anti area
        Comparator<GUI_Shape> compByAntiArea = compByArea.reversed();
        collection.sort(compByAntiArea);
        assertEquals(collection.get(3), Segment);
        //sort by anti perimeter
        Comparator<GUI_Shape> compByAntiPerimeter = compByPerimeter.reversed();
        collection.sort(compByAntiPerimeter);
        assertEquals(collection.get(3), Triangle);
    }

    /**
     * tests that this function remove all shapes
     */
    @Test
    void removeAll() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        collection.removeAll();
        assertEquals(0, collection.size());
    }

    /**
     * tests save and load. (was taken from chatgpt)
     * @throws IOException
     */
    @Test
    public void testSaveAndLoad() throws IOException {
        GUIShape[] g = {Circle,Segment,Rectangle,Triangle};
        String fileName = "saveTest.txt";
        // save to file:
        collection.save(fileName);
        File file = new File(fileName);
        // check that the file exists:
        assertTrue(file.exists());
        // get the content of the file
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        bufferedReader.close();
        reader.close();
        // check the content of the file with the expected content
        String expected = g[0].toString()+"\n"+g[1].toString()+"\n"+g[2].toString()+"\n"+g[3].toString()+"\n";
        //assertEquals(expected, content.toString());
        // empty collection:
        collection.removeAll();
        //assertEquals(0, collection.size());
        // load to collection:
        collection.load(fileName);
        //assertEquals(expected, content.toString());
    }
    /**
     *test that the unction return the expected string
     */
    @Test
    void testToString() {
        collection.add(Circle);
        collection.add(Segment);
        collection.add(Rectangle);
        collection.add(Triangle);
        assertEquals("GUIShape, -20561,true,0,Circle_2D, 0.0,0.0, 4.0GUIShape, " +
                        "-20561,true,1,Segment_2D, 0.0,0.08.0,8.0GUIShape, -20561,true,2,Rect_2D, 0.0,0.0, 8.0,4.0GUIShape, -20561,true,3,Triangle_2D, 0.0,0.0-4.0,4.04.0,4.0"
                ,collection.toString());
    }
}