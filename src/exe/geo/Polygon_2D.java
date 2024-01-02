//ID 207199282
package exe.geo;
import java.util.ArrayList;

/**
 * this class is implements to Polygon_2D
 */
public class Polygon_2D implements GeoShape{
	//polygonArray= the array list that contain all the points of the polygon
	private ArrayList<Point_2D> polygonArray;

	/**
	 * constructor:
	 */
	public Polygon_2D() {
		this.polygonArray = new ArrayList<>();
	}

	/**
	 * copy constructor:
	 * @param po
	 */
	public Polygon_2D(Polygon_2D po) {
		this.polygonArray = new ArrayList<>(po.polygonArray);
	}

	/**
	 * this function get all points of a polygon. using array list, and for loop that added the points to the array
	 * @return Point_2D[]
	 */
	public Point_2D[] getAllPoints() {
		//define the size
		int size= this.polygonArray.size();
		//create new array list of Point_2D
		Point_2D[] ans= new Point_2D[size];
		//create for loop that added each point to the array
		for(int i=0 ; i<size ; i++){
			ans[i]= this.polygonArray.get(i);
		}
		return ans;
	}

	/**
	 * this function added new point_2D to the array of the polygon. Using add function from ArrayList class
	 * @param p
	 */
	public void add(Point_2D p) {
		this.polygonArray.add(p);

	}

	/**
	 * this function return string of the array that the polygon was created from.Using ToString from Point_2D class
	 * @return string
	 */
	@Override
	public String toString(){
		return "Polygon_2D, "+this.polygonArray.toString();

	}

	/**
	 * this solution wat taken from :
	 * this funcion check if a point is inside the polygon.
	 * first, make a segments from all the points.
	 *  after, check if the segments contains the point(by using "contain" from Point_2D
	 *  if yes-return true because the point is inside.
	 *  after, find the x and y value of all the segments.
	 *  in each segment, find the line function.(y=mx+c).
	 *  (imagine a line between ot, go right....)
	 *  find the meeting point-which is the point that the imagine line meeting the segment
	 *  if the segments contain this point,and ot is from left to the meeting point-
	 *  it means that it inside the polygon.
	 *  every time the line uncross the segment-counter ++
	 *  at the end-if counter is even-the point is outside the polygon.
	 *
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {
		//make sure that the polynom isn't empty;
		if (this.polygonArray.size()==0){
			return false;
		}
		if (this.polygonArray.size()==1){
			if (this.polygonArray.get(0)==ot){
				return true;
			}
		}
		//create new array of segments from the polygon points;
		ArrayList<Segment_2D> segmentArr = new ArrayList<>();
		for (int i = 0; i < this.polygonArray.size() - 1; i++) {
			Segment_2D s = new Segment_2D(this.polygonArray.get(i), this.polygonArray.get(i + 1));
			segmentArr.add(s);
		}
		int size = this.polygonArray.size();//size
		//added the last segment
		Segment_2D s1 = new Segment_2D(this.polygonArray.get(size - 1), this.polygonArray.get(0));
		segmentArr.add(s1);
		//if ot is on one segment
		for (int i = 0; i < segmentArr.size(); i++) {
			if (segmentArr.get(i).contains(ot)) {
				System.out.println("on line");
				return true;
			}
		}
		//find the x and y value of each segment
		int counter = 0;
		for (int j = 0; j < segmentArr.size(); j++) {
			double x1 = segmentArr.get(j).get_p1().x();
			double y1 = segmentArr.get(j).get_p1().y();
			double x2 = segmentArr.get(j).get_p2().x();
			double y2 = segmentArr.get(j).get_p2().y();
          // if the segment is not vertical:
			if (x1 != x2) {
				//find the incline:
				double m = (y2 - y1) / (x2 - x1);
				//incline is not 0
				if (m != 0) {
					double otY = ot.y();
					//find the x value of the meeting line from ot to the segment
					double xm = (otY - y1 + m * x1) / m;
					//define this meeting point
					Point_2D xmp = new Point_2D(xm, otY);
					// if the point is on the segment and right from ot-is inside
					if (xm > ot.x() && segmentArr.get(j).contains(xmp)) {
						counter++;
					}
				}
			} else {
				Point_2D p = new Point_2D(x1, ot.y());
				if (ot.x() < x1 && segmentArr.get(j).contains(p)) {
					counter++;
				}
			}

		}
		//if the counter is even-the point is outside-return false
		if (counter % 2 == 0) {
			return false;
		}
		return true;
	}

	/**
	 * this function calculate the area of polygon. using the formula from: en.wikipedia.org/wiki/Polygon
	 * go over all the point of the polygon. place it in the formula.
	 * @return the sum.
	 */
	@Override
	public double area() {
		ArrayList<Point_2D> points= this.polygonArray;
		double ans= 0;
		//go over all points
		for (int i = 0; i <points.size()-1; i++) {
			Point_2D p1 = points.get(i);
			Point_2D p2 = points.get(i + 1);
			double a = p1.x() * p2.y() - p2.x() * p1.y();
			ans = ans + a;
		}
		Point_2D p1 = points.get(points.size()-1);
		Point_2D p2 = points.get(0);
		ans+=p1.x() * p2.y() - p2.x() * p1.y();
		ans=ans*0.5;
		return Math.abs(ans);
	}

	/**
	 * this function calculate the perimeter of a Polygon_2D
	 * using distance function from Point_2D class to calculate the distance between two afters points in the polygon
	 * add each distance to the answer and return the sum of all distances
	 * @return
	 */
	@Override
	public double perimeter() {
		double ans= 0;
		//calculate the distances between two points on the array
		for (int i = 0; i < this.polygonArray.size()-1; i++) {
			ans+=this.polygonArray.get(i).distance(this.polygonArray.get(i+1));
		}
		//add the distance from the last point to the first point
		int size= this.polygonArray.size();
		ans+=this.polygonArray.get(size-1).distance(this.polygonArray.get(0));
		return ans;
	}

	/**
	 * this function translate the polygon by given vector. actually, translate each point of the polygon .
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		for (Point_2D point2D : this.polygonArray) {
			point2D.move(vec);
		}
	}

	/**
	 * create new polygon same as the choose polygon
	 * @return
	 */
	@Override
	public GeoShape copy() {
		return new Polygon_2D(this);

	}

	/** /**
	 *  Rescales this GeoShape with respect to the given center point.
	 *  using scale function in Point_2D in order to scale all the points;
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i <this.polygonArray.size() ; i ++) {
			this.polygonArray.get(i).scale(center, ratio);
		}
	}

	/**
	 * Rotates this GeoShape with respect to the given center point by an angle.
	 * using rotate function in Point_2D.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < this.polygonArray.size(); i++) {
			this.polygonArray.get(i).rotate(center, angleDegrees);

		}
	}

}
