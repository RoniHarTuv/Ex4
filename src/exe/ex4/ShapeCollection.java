//ID-207199282
package exe.ex4;
import exe.geo.*;
import exe.gui.GUIShape;
import exe.gui.GUI_Shape;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;

	/**
	 * constructor:
	 */
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	/**
	 * this function return the shape in index i
	 *
	 * @param i - the index of the element
	 * @return gui shape
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	/**
	 * this function return the size of the Arraylist of all shape
	 *
	 * @return
	 */
	@Override
	public int size() {
		return _shapes.size();
	}

	/**
	 * this function remove(delete) all ths element from the Arraylist
	 *
	 * @param i - the index of the element to be removed.
	 * @return
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape ans = null;
		if (i >= 0 && i < this._shapes.size()) {
			//using remove function
			ans = _shapes.remove(i);
		}
		return ans;
	}

	/**
	 * this function add the given shape in the given index
	 *
	 * @param s - the gui_shape
	 * @param i - the location (index) in which s should be added
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if (i >= 0 && i < this._shapes.size()) {
			_shapes.add(i, s);
		}
	}

	/**
	 * this function added the given shape in the end of the arrayLst
	 *
	 * @param s - the gui_shape
	 */
	@Override
	public void add(GUI_Shape s) {
		if (s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	/**
	 * This method constructs a deep copy of this collection.
	 * the two collections are equal - yet they have no shared memory.
	 *
	 * @return
	 */
	@Override
	public GUI_Shape_Collection copy() {
		ShapeCollection ans = new ShapeCollection();
		ans._shapes.addAll(this._shapes);
		return ans;
	}

	/**
	 * this function sort the Shapes by the given comparator
	 *
	 * @param comp a linear order over gui_shapes as defined in java.util.Comparator
	 */
	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	/**
	 * This method simple removes all the elements from this collection.
	 */
	@Override
	public void removeAll() {
		this._shapes.removeAll(_shapes);
	}

	/**
	 * This method saves this gui_shape collection to a text file.
	 *
	 * @param file - the file name in which this collection will be saved.
	 */
	@Override
	public void save(String file) {
		try {
			FileWriter writer = new FileWriter(file);
			for (GUI_Shape g : _shapes) {
				System.out.println(g.toString());
				writer.write(g.toString() + "\n");
			}
			writer.close();
			System.out.println("File saved successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load (String file){
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			GeoShape g;
			while ((line = reader.readLine()) != null) {
				String[] s = line.split(",");
				if (s[4].equals("Circle_2D")) {
					double x = Double.parseDouble(s[5]),
							y = Double.parseDouble(s[6]),
							r = Double.parseDouble(s[7]);
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					int t = Integer.parseInt(s[3]);
					g = new Circle_2D(new Point_2D(x, y), r);
					_shapes.add(new GUIShape(g, f, c, t));
				}
				if (s[4].equals("Segment_2D")) {
					double x1 = Double.parseDouble(s[5]),
							y1 = Double.parseDouble(s[6]),
							x2 = Double.parseDouble(s[7]),
							y2 = Double.parseDouble(s[8]);
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					int t = Integer.parseInt(s[3]);
					g = new Segment_2D(new Point_2D(x1, y1), new Point_2D(x2, y2));
					_shapes.add(new GUIShape(g, f, c, t));
				}
				if (s[4].equals("Rect_2D")) {
					double x1 = Double.parseDouble(s[5]),
							y1 = Double.parseDouble(s[6]),
							x2 = Double.parseDouble(s[7]),
							y2 = Double.parseDouble(s[8]),
							x3 = Double.parseDouble(s[9]),
							y3 = Double.parseDouble(s[10]),
							x4 = Double.parseDouble(s[11]),
							y4 = Double.parseDouble(s[12]);
					double[] pt = {x1, y1, x2, y2, x3, y3, x4, y4};
					int t = Integer.parseInt(s[3]);
					Polygon_2D p = new Polygon_2D();
					for (int i = 0; i < 7; i += 2) {
						p.add(new Point_2D(pt[i], pt[i + 1]));
					}
					g = p.copy();
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					_shapes.add(new GUIShape(g, f, c, t));
				}
				if (s[4].equals("Polygon_2D") || s[4].equals("Triangle_2D")) {
					double[] pt = new double[s.length - 5];
					for (int i = 5; i < s.length; i++) {
						pt[i - 5] = Double.parseDouble(s[i]);
					}
					int t = Integer.parseInt(s[3]);
					Polygon_2D p = new Polygon_2D();
					for (int i = 0; i < pt.length - 1; i++) {
						p.add(new Point_2D(pt[i], pt[i += 1]));
					}
					g = p.copy();
					Color c = new Color(Integer.parseInt(s[1]));
					boolean f = Boolean.parseBoolean(s[2]);
					_shapes.add(new GUIShape(g, f, c, t));
				}
			}
			System.out.println(line);
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			e.printStackTrace();
		}
	}

	/**
	 * this function return the string of the shape collection
	 * @return the string
	 */
	@Override
	public String toString () {
		String ans = "";
		for (int i = 0; i < size(); i = i + 1) {
			ans += this.get(i);
		}
		return ans;
	}
}
