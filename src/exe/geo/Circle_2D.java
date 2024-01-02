//ID 207199282
package exe.geo;
/** 
 * This class represents a 2D circle in the plane.
 */
public class Circle_2D implements GeoShape {
	private Point_2D _center;
	private double _radius;
	/**
	 * this is a constructor to Circle from given centre and radius
	 * @param cen
	 * @param rad
	 */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	/**
	 * this function gets the radius of Cycle_2D
	 * @return double
	 */
	public double getRadius()
	{return this._radius;}
	/**
	 * this function gets the centre of Cycle_2D
	 * @return Point_2D
	 */
	public Point_2D getCenter()
	{ return _center;}

	/**
	 * this function return the string "centre , radius"
	 * @return
	 */
	 @Override
	    public String toString() {
		return "Circle_2D, "+ this.getCenter().toString()+", " + this.getRadius();
		}

	/**
	 * this functions check if Point_2D is inside the given circle
	 * check if the distance between ot to center is more than the radius. if yes-the point is out of the cycle
	 * @param ot - a query 2D point
	 * @return true/false
	 */
	@Override
	public boolean contains(Point_2D ot) {
		return (ot.distance(this.getCenter()) <= this.getRadius());
	}

	/**
	 * this function calculate the area of cycle according the formula- radius^2*pai
	 * @return double
	 */
	@Override
	public double area() {
		double r= this._radius;
		return r*r*Math.PI;
	}
	/**
	 * this function calculate the perimeter of cycle according the formula-radius*2*pai
	 * @return double
	 */
	@Override
	public double perimeter() {
		return ((this.getRadius())*(2)*(Math.PI));
	}
	/**
	 * this function use 'move' function in order to move centre of the Cycle_2D, and translate it.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		 this._center.move(vec);
	}
	/**
	 * This method computes a new (deep) copy of this Cycle_2D.
	 * 	@return a deep copy of this Cycle_2D.
	 */
	@Override
	public GeoShape copy() {
		return new Circle_2D(this.getCenter(),this.getRadius());
	}
	/**
	 *  Rescales this GeoShape with respect to the given center point.
	 *  using scale function in Point_2D in order to scale all the center;
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._center.scale(center,ratio);
		this._radius*=ratio;
	}
	/**
	 * Rotates this GeoShape with respect to the given center point by an angle.
	 * using rotate function in Point_2D.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center,angleDegrees);
	}

}
