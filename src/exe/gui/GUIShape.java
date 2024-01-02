//ID-207199282
package exe.gui;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import exe.geo.GeoShape;
import java.awt.*;

public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	/**
	 * 	 * Constructor for GUIShape.
	 * 	 * @param g the GeoShape
	 * 	 * @param f whether the shape is filled
	 * 	 * @param c the color of the shape
	 * 	 * @param t the tag of the shape
	 */
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	/**
	 * copy constructor:
	 * @param ot GuiShape
	 */
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	/**
	 * @return the shape
	 */
	@Override
	public GeoShape getShape() {return _g;
	}

	/**
	 * @return is  the shape filled
	 */
	@Override
	public boolean isFilled() {
		return _fill;
	}

	/**
	 * set filled
	 * @param filled
	 */
	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	/**
	 * @return the color of the shape
	 */
	@Override
	public Color getColor() {
		return _color;
	}
	/**
	 * sets the color of the shape
	 * @param cl-the color to set
	 */
	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	/**
	 * @return the tag of the shape
	 */
	@Override
	public int getTag() {
		return _tag;
	}

	/**
	 * sets the tag of the shape
	 * @param tag
	 */
	@Override
	public void setTag(int tag) {
		_tag = tag;
	}
	/**
	 * @return  a GUI_Shape copy of the shape
	 */
	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}

	/**
	 * @return a string representing the shape
	 */
	@Override
	public String toString() {
		return "GUIShape, "+_color.getRGB()+","+_fill+","+_tag+","+this.getShape();
	}
	private void init(String[] ww) {
	}

	/**
	 *
	 * @return is the shape selected or not
	 */
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}

	/**
	 *sets whether the shape is selected
	 * @param s value to set
	 */
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}

	/**
	 *  sets the shape
	 * @param g shape to set
	 **/
	@Override
	public void setShape(GeoShape g) {
		_g= g.copy();
	}
}
