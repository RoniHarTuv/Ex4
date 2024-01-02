//ID 207199282
package exe.geo;
/**
 * This class represents a 2D axis parallel rectangle.
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D downLeft;
	private Point_2D upRight;
	private Point_2D upLeft;
	private Point_2D downRight;
	//dx,dy= side length of the rectangle;
	public double dx() {return downRight.x() - downLeft.x();}
	public double dy() {return upRight.y() - downLeft.y();}


	/**
	 * this is a constructor to Rect_2D from given two points
	 * @param p1
	 * @param p2
	 */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this.downLeft = new Point_2D(Math.min(p1.x(),p2.x()), Math.min(p1.y(),p2.y()));
		this.upRight = new Point_2D(Math.max(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
		this.downRight= new Point_2D(Math.max(p1.x(),p2.x()),Math.min(p1.y(),p2.y()));
		this.upLeft = new Point_2D(Math.min(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
	}

	/**
	 * this is a copy constructor to Rect_2D
	 * @param t1
	 */
	public Rect_2D(Rect_2D t1) {
		this.downLeft =new Point_2D(t1.downLeft);
		this.upRight =new Point_2D(t1.upRight);
		this.downRight=new Point_2D(t1.downRight);
		this.upLeft=new Point_2D(t1.upLeft);
	}

	/**
	 * this is a boolean function return true if point is inside the Rect_2D
	 * this function check if rectangle contain point "ot".
	 * if the ot is inside-the area of the four triangle that was created fron each point to ot-will be same as the rectangle
	 * else, if the area is more than the triangle area- ot is outside.
	 * @param ot - a query 2D point
	 * @return true/false
	 */
	@Override
	public boolean contains(Point_2D ot) {
		double area= this.area();
		Triangle_2D t1= new Triangle_2D(ot,this.upLeft,this.upRight);
		Triangle_2D t2= new Triangle_2D(ot,this.upRight,this.downRight);
		Triangle_2D t3= new Triangle_2D(ot,this.downRight,this.downLeft);
		Triangle_2D t4= new Triangle_2D(ot,this.downLeft,this.upLeft);
		double t11= t1.area();
		double t22= t2.area();
		double t33= t3.area();
		double t44= t4.area();
		double areAll= t11+t22+t33+t44;
		return Math.abs(areAll-area)<0.001;

	}

	/**
	 * this function return the string of a rectangle: [downLeft , upRight];
	 * @return
	 */
	@Override
	public String toString(){
		return "Rect_2D, " + this.downLeft + ", " + this.upRight;
	}

	/**
	 * this function calculate the area of rectangle, using the formula-weight * height
	 * @return double area
	 */
	@Override
	public double area() {
		double a=this.downLeft.distance(downRight);
		double b= this.downLeft.distance(upLeft);
		return a*b;
	}
	/**
	 * this function calculate the perimeter of rectangle, using the formula-weight *2 + height*2
	 * @return double perimeter
	 */
	@Override
	public double perimeter() {
		return this.downLeft.distance(downRight)+this.downLeft.distance(upLeft) +this.upLeft.distance(upRight)+ this.upRight.distance(downRight);
	}
	/**
	 * this function use 'move' function in order to move each point of the Rect_2D, and translate it.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		downLeft.move(vec);
		upRight.move(vec);
		upLeft.move(vec);
		downRight.move(vec);
	}
	/**
	 * This method computes a new (deep) copy of this Rect_2D.
	 * 	@return a deep copy of this Rect_2D.
	 */
	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}
	/**
	 *  Rescales this GeoShape with respect to the given center point.
	 *  using scale function in Point_2D in order to scale all the points;
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this.downLeft.scale(center, ratio);
		this.downRight.scale(center, ratio);
		this.upLeft.scale(center, ratio);
		this.upRight.scale(center, ratio);
	}
	/**
	 * Rotates this GeoShape with respect to the given center point by an angle.
	 * using rotate function in Point_2D.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.downLeft.rotate(center, angleDegrees);
		this.downRight.rotate(center, angleDegrees);
		this.upLeft.rotate(center, angleDegrees);
		this.upRight.rotate(center, angleDegrees);
	}

	public Point_2D[] recPoints(){
		return new Point_2D[]{downLeft, upLeft, upRight, downRight};
	}
}
