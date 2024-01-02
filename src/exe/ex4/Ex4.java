//ID-207199282
package exe.ex4;
import exe.geo.*;
import exe.gui.GUIShape;
import exe.gui.GUI_Shape;
import exe.gui.StdDraw_Ex4;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 */
/**
 * this class implements Ex4_GUI;
 */
public class Ex4 implements exe.gui.Ex4_GUI {
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1;
	private Polygon_2D _poly;
	private static int _tag=0;
	private  static Ex4 _winEx4 = null;
	private ArrayList<Point_2D> _points = new ArrayList<>();

	private Ex4() {
		init(null);
	}

	private Point_2D _rotateCen;

	public void init(GUI_Shape_Collection s) {
		if(s==null) {_shapes = new ShapeCollection();
		} else {_shapes = s;}// //shou,ld be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	/**
	 *this function return the type of gui shape from a string
	 * @param gui
	 * @return string
	 */
	public String getTypeFromString (GUI_Shape gui){
		String ans;
		String[] StringArray= gui.toString().split(",");
		System.out.println(StringArray[4]);
		ans= StringArray[4];
		System.out.println(Arrays.toString(StringArray));
		return  ans;
     }

	/**
	 * this function go over all the possible mode , and decide the right course of action.
	 */
	public void drawShapes() {
		StdDraw_Ex4.clear();
		//remove:
		if(_mode.equals("Remove")) {
			remove();
		}
		if (_mode.equals("Anti")){
			anti();
		}
		if (_mode.equals("All")){
			all();
		}
		if (_mode.equals("None")){
			none();
		}
		if (_mode.equals("Info")){
			info();
		}
		//the follow comparator return comparator According to the requested sorting method
		Comparator<GUI_Shape> compByArea= Comparator.comparingDouble(o -> o.getShape().area());
		if(_mode.equals("ByArea")){
			_shapes.sort(compByArea);
		}
		if(_mode.equals("ByAntiArea")) {
			Comparator<GUI_Shape> compByAntiArea= compByArea.reversed();
			_shapes.sort(compByAntiArea);
		}
		Comparator<GUI_Shape> compByPerimeter= Comparator.comparingDouble(o -> o.getShape().perimeter());
		if(_mode.equals("ByPerimeter")) {
			_shapes.sort(compByPerimeter);
		}
		if (_mode.equals("ByAntiPerimeter")){
			Comparator<GUI_Shape> compByAntiPerimeter= compByPerimeter.reversed();
			_shapes.sort(compByAntiPerimeter);
		}

		Comparator<GUI_Shape> compByString= (Comparator.comparing(this::getTypeFromString));
		if (_mode.equals("ByAntiToString")){
			_shapes.sort(compByString);
		}
		if (_mode.equals("ByToString")){
			Comparator<GUI_Shape> compByAntiString= compByString.reversed();
			_shapes.sort(compByAntiString);
		}
		Comparator<GUI_Shape> compByTag= Comparator.comparingInt(GUI_Shape::getTag);
		if (_mode.equals("ByTag")){
			_shapes.sort(compByTag);
		}
		if (_mode.equals("ByAntiTag")){
			Comparator<GUI_Shape> compByAntiTag= compByTag.reversed();
			_shapes.sort(compByAntiTag);
		}

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape sh = _shapes.get(i);
			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	/**
	 * The drawShape function takes a GUI_Shape object and uses the StdDraw_Ex4 library
	 * to draw the shape represented by the object. It supports drawing circles, polygons, rectangles, and line segments,
	 * with the ability to fill the shapes or leave them unfilled based on the isFill parameter.
	 * @param g
	 */
	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		//circle:
		if(gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D)gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		//polygon:
		if(gs instanceof Polygon_2D) {
			Polygon_2D p = (Polygon_2D) gs;
			Point_2D[] pa = p.getAllPoints();
			double[] x= new double[pa.length];
			double[] y= new  double[pa.length];
			for (int i = 0; i < pa.length; i++) {
				x[i]=pa[i].x();
				y[i]=pa[i].y();
			}
			if(isFill) {

				StdDraw_Ex4.filledPolygon(x,y);
			} else {
				StdDraw_Ex4.polygon(x,y);
			}
		}
		//rectangle:
		if(gs instanceof Rect_2D){
			Point_2D[] pr = ((Rect_2D) gs).recPoints();
			double[] x = {pr[0].x(), pr[1].x(), pr[2].x(), pr[3].x()};
			double[] y = {pr[0].y(), pr[1].y(), pr[2].y(), pr[3].y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x,y);
			} else {
				StdDraw_Ex4.polygon(x,y);
			}
		}
		//segment:
		if (gs instanceof Segment_2D){
			Segment_2D s = (Segment_2D)gs;
			StdDraw_Ex4.line(s.get_p1().x(),s.get_p1().y(),s.get_p2().x(),s.get_p2().y());
		}
		//triangle:
		if(gs instanceof Triangle_2D) {
			Point_2D[] pa = ((Triangle_2D) gs).getAllPoints();
			double[] x= new double[pa.length];
			double[] y= new  double[pa.length];
			for (int i = 0; i < pa.length; i++) {
				x[i]=pa[i].x();
				y[i]=pa[i].y();
			}
			if(isFill) {

				StdDraw_Ex4.filledPolygon(x,y);
			} else {
				StdDraw_Ex4.polygon(x,y);
			}
		}

	}

	/**
	 * setting the color of the shape
	 * @param c
	 */
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	/**
	 * set if fill the shape by selected color or not
	 */
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	/**
	 * decide the color and the mode of the color of the guiShape
	 * @param p
	 */
	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		if(p.equals("Save")) {save();}
		if(p.equals("Load")) {load();}
		drawShapes();
	}

	/**
	 *The mouseClicked function handles different actions based on the current mode and the clicked point.
	 *  It performs operations such as creating shapes, moving shapes, scaling shapes, copying shapes,
	 *  selecting points, and rotating shapes, and then redraws all the shapes on the canvas.
	 * @param p
	 */
	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		//circle:
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		//polygon:
		if(_mode.equals("Polygon") || _mode.equals("Triangle")) {
			if(_poly==null) {
				_poly = new Polygon_2D();
				_poly.add(p);
				_p1 = new Point_2D(p);
			}
			_poly.add(p);
		}
		if (_mode.equals("Triangle") && _poly.getAllPoints().length==4){
			_poly.add(p);
			Point_2D[] t = _poly.getAllPoints();
			_poly = new Polygon_2D();
			for(int i=0; i<t.length-1; i++){
				_poly.add(t[i]);
			}
			Point_2D[] mp= _poly.getAllPoints();
			Triangle_2D mt= new Triangle_2D(mp[0],mp[1],mp[2]);
			_gs=new GUIShape(mt,this._fill,this._color,_tag);
			_tag++;
			this._shapes.add(_gs);
			_p1=null;
			_gs=null;
			_poly=null;
		}
		//rectangle:
		if (_mode.equals("Rect")){
			if(_gs==null) {
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		// segment:
		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}


		// move:
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point_2D(p);} else {
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		//Scale_90%
		if(_mode.equals("Scale_90%")) {
			_p1=new Point_2D(p);
			scale_90();
		}
		//Scale_110%
		if(_mode.equals("Scale_110%")) {
			_p1 = new Point_2D(p);
			scale_110();
		}
		//Copy
		if(_mode.equals("Copy")) {
			if ( _p1==null) {
				_p1= new Point_2D(p);
			} else {
				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}
		//Point
		if(_mode.equals("Point")) {
			select(p);
		}
		//rotate: this formula of rotate was taken from chat gpt
		if(_mode.equals("Rotate")) {
			//define the first point
			if(_p1==null) {_p1 = new Point_2D(p);} else {
				//define the "vector"
				_rotateCen = _p1;
				Point_2D newPoint = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				double deg=0, x=newPoint.x(), y=newPoint.y();
				if(x==0){
					if(y>0){
						deg = Math.PI/2;
					} else if(y<0){
						deg = Math.PI*1.5;
					} else {
						deg = 0;
					}
				} else {
					deg = Math.atan2(y,x);
				}
				deg = Math.toDegrees(deg);
				if(deg<0){
					deg+=360;
				}
				rotate(deg);
				_p1 = null;
			}
		}
		drawShapes();
	}

	/**
	 *The select function iterates over the list of shapes _shapes,
	 * checks if the shape contains the clicked point p,
	 * and toggles the selected state of the shape using the setSelected method.
	 * This allows selecting or deselecting shapes based on the click position.
	 * @param p
	 */
	private void select(Point_2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	/**
	 * this function scale the shape that selected by 90%
	 */
	private void scale_90() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.scale(_p1,0.9);
			}
		}
	}

	/**
	 * this function scale the shape that selected by 110%
	 */
	private void scale_110() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.scale(_p1,1.1);
			}
		}
	}

	/**
	 * This method computes a new (deep) copy of the selected GeoShape.
	 * @return a deep copy
	 */
	private void copy() {
		//go over all the shapes
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			//check if selected
			if (s.isSelected() && g != null) {
				//copy each shape
				GeoShape gCopy= g.copy();
				gCopy.translate(_p1);
				_gs=new GUIShape(gCopy,s.isFilled(),s.getColor(),_tag);
				_tag++;
				this._shapes.add(_gs);
				_gs=null;
			}
		}
	}

	/**
	 * this function moving the selected geoshapes
	 */
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			//check if selected
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}

	/**
	 *this function remove the selected geo shape;
	 */
	private  void remove(){
		//go over all the shapes
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			//check which shape has been selected
			if(s.isSelected() && g!=null) {
				_shapes.removeElementAt(i);
				i--;
				drawShapes();
			}
		}
	}

	/**
	 * this function rotate the selected GeoShapes
	 */
	private  void rotate(double degree){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.rotate(_rotateCen, degree);
			}
		}
	}

	/**
	 *this function dis selected the GeoShapes that was selected.
	 */
	private  void anti(){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(!s.isSelected());
		}

	}

	/**
	 *this function select all the draw GeoShapes
	 */
	private  void all(){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(true);
		}
	}

	/**
	 * this function dis selected all the draw GeoShapes
	 */
	private  void none(){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			s.setSelected(false);
		}
	}

	/**
	 * this function print info about the drawing shapes
	 */
	private  void info(){
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			System.out.println(s.toString());
		}
	}

	/**
	 *this function use right click in mouse in order to finish drawing the polygon
	 * @param p
	 */
	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
		//polygon:
		if (_mode.equals("Polygon")) {
			_poly.add(p);
			_gs=new GUIShape(_poly,this._fill,this._color,_tag);
			_tag++;
			this._shapes.add(_gs);
			_p1=null;
			_gs=null;
			_poly=null;
		}
		drawShapes();
	}

	/**
	 * This function calculates the appropriate shape based on the current mode and the mouse cursor's position,
	 * and creates a temporary GUIShape to represent the shape being dynamically previewed.
	 * @param e
	 */
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			//circle:
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			//polygon/ Triangle:
			if(_mode.equals("Polygon") || _mode.equals("Triangle")) {
				Point_2D[] points = _poly.getAllPoints();
				points[points.length-1] = new Point_2D(p);
				_poly = new Polygon_2D();
				for (Point_2D point : points) {
					_poly.add(point);
					gs = new Polygon_2D(_poly);
				}
			}
			//rectangle:
			if (_mode.equals("Rect")){
				gs = new Rect_2D(_p1, p);
			}
			//segment:
			if (_mode.equals("Segment")){
				gs = new Segment_2D(_p1,p);
			}
			//drew the shape
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	/**
	 * return the collection of the shapes
	 * @return
	 */
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		return this._shapes;
	}

	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }

	/**
	 * ths function return String of info about the shape collection
	 * @return
	 */
	@Override
	public String getInfo() {
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
	// saves shapes to file; source: stackOverFlew
	public void save(){
		FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Save to Text file", FileDialog.SAVE);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null) {
			_shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}
	// loads shapes to file; source: stackOverFlew
	public void load(){
		_shapes.removeAll();
		FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Load from Text file", FileDialog.LOAD);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null) {
			_shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}
}
