//ID-207199282
package exe.geo;
/**
 * This class represents a 2D segment on the plane,
 */
public class Segment_2D implements GeoShape{
    private Point_2D p1;
	private Point_2D p2;
	/**
	 * this is a cop constructor to Segment_2D by given 2 points.
	 * @param a,b;
	 */
	public Segment_2D(Point_2D a, Point_2D b) {
		this.p1=new Point_2D(a);
		this.p2=new Point_2D(b);
	}
	/**
	 * this is a copy constructor to Segment_2D
	 * @param t1
	 */
	public Segment_2D(Segment_2D t1) {
		this.p1=t1.get_p1();
		this.p2=t1.get_p2();
	}
	/**
	 * this function get the first point from a segment
	 * @return Point_2D
	 */
	public Point_2D get_p1() {
		return this.p1;
	}

	/**
	 * this function get the second point from a segment
	 * @return Point_2D
	 */
	public Point_2D get_p2() {
		return  this.p2;
	}

	/**
	 * this function check if point is on the segment.
	 * calculate the distance between the two point of the segment(p1,p2),
	 * calculate the distance between ot to p1, and to p2. if the sum more than the distance between the two points-
	 * it is mean that the point is not on the segment.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {
		//the distance between p1 and p2
		double distance= this.p1.distance(this.p2);
		//the distance between ot to p1 and p2
		double distanceFromP1= ot.distance(this.p1);
		double distanceFromP2= ot.distance(this.p2);
		double distanceP1P2= distanceFromP2+distanceFromP1;
		if ((distanceP1P2<=distance+0.001)){
			return true;
		}
		return false;
	}

	/**
	 * this function return 0 because segment have no area.
	 * @return 0
	 */
	@Override
	public double area() {
		return 0;
	}

	/**
	 * this function return twice the length in the segment.
	 * @return double area
	 */
	@Override
	public double perimeter() {
		double ans= 2*(this.p2.distance(this.p1));
		return ans;
	}

	/**
	 * this function use 'move' function in order to translate the segments according the given vector.
	 * move p1 and p2.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
	}

	/**
	 * This method computes a new (deep) copy of this segment
	 * 	@return a deep copy of this GeoShape.
	 */
	@Override
	public GeoShape copy() {
		return new Segment_2D(this.p1,this.p2);
	}
	/**
	 *  Rescales this GeoShape with respect to the given center point.
	 *  using scale function in Point_2D in order to scale all the points;
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this.p1.scale(center, ratio);
		this.p2.scale(center, ratio);
	}
	/**
	 * Rotates this GeoShape with respect to the given center point by an angle.
	 * using rotate function in Point_2D.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
	}
	@Override
	public String toString(){
		return "Segment_2D, "+ p1.toString()+p2.toString();
	}
}