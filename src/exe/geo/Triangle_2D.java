//ID-207199282
package exe.geo;

/**
 * This class represents a 2D Triangle in the plane.
 */
public class Triangle_2D implements GeoShape{
	private Point_2D _P1 , _P2 , _P3 ;

	/**
	 * This is a Triangle_2D constructor- given 3 points
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._P1 = new Point_2D(p1);
		this._P2 =new Point_2D(p2);
		this._P3 =new Point_2D(p3);
	}
	/**
	 * This is a Triangle_2D copy constructor
	 * @param t1
	 */
	public Triangle_2D(Triangle_2D t1) {
		this._P1=new Point_2D(t1._P1);
		this._P2=new Point_2D(t1._P2);
		this._P3=new Point_2D(t1._P3);
	}

	/**
	 * this function create new array that contained each point of the triangle
	 * @return new Point_2D array
	 */
	public Point_2D[] getAllPoints() {
		//create new array:
		Point_2D[] ans= new Point_2D[3];
		//each place is point:
		ans[0]= new Point_2D(this._P1);
		ans[1]= new Point_2D(this._P2);
		ans[2]=new Point_2D(this._P3);
		return ans;
	}

	/**
	 * this function check if triangle contain point "ot".
	 * if the ot is inside-the area of the three triangle that was created fron each point to ot-will be same as the triangle
	 * else, if the area is more than the triangle area- ot is outside.
	 * @param ot - a query 2D point
	 * @return false/true
	 */
	@Override
	public boolean contains(Point_2D ot) {
		//the triangle area:
		double area= this.area();
		//create 3 new triangle from ot to each point;
		Triangle_2D t1= new Triangle_2D(ot,this._P1,this._P2);
		double t11= t1.area();
		Triangle_2D t2= new Triangle_2D(ot,this._P2,this._P3);
		double t12= t2.area();
		Triangle_2D t3= new Triangle_2D(ot,this._P1,this._P3);
		//calculate the sum of all triangles
		double t13= t3.area();
		double areafromOt= t11+t12+t13;
		//check if the area of all triangles is more or less from the area of the triangle:
		//if same area aka epsilon-return true. else- return false;
		if(Math.abs(area-areafromOt)< 0.001) {
			return true;
		}
		return false;
	}

	/**
	 * this function calculate the area of triangle. using formula,
	 * from- wikipedia.org/wiki/Heron%27s_formula
	 * the formula: Math.sqrt((s)*(s-A)*(s-B)*(s-C));
	 * s= the perimeter/2
	 * @return double area
	 */
	@Override
	public double area() {
		double A= this._P1.distance(this._P2);
		double B= this._P2.distance(this._P3);
		double C= this._P3.distance(this._P1);
		//using the formula to calculate the area:
		double s= (this.perimeter()/2);
		return Math.sqrt((s)*(s-A)*(s-B)*(s-C));
	}

	/**
	 * this function calculate the perimeter of the triangle
	 * using distance function from each point to each point.
	 * using formula of perimeter- a+b+c (segments of the triangle)
	 * @return
	 */
	@Override
	public double perimeter() {
		double a= this._P1.distance(this._P2);
		double b= this._P2.distance(this._P3);
		double c= this._P3.distance(this._P1);
		return a+b+c;
	}

	/**
	 * this function use 'move' function in order to move each point of the triangle, and translate it.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		this._P1.move(vec);
		this._P2.move(vec);
		this._P3.move(vec);
	}

	/**
	 * This method computes a new (deep) copy of this Triangle_2D.
	 * 	@return a deep copy of this Triangle_2D.
	 */
	@Override
	public GeoShape copy() {
		return new Triangle_2D(this._P1,this._P2,this._P3);
	}

	/**
	 *  Rescales this GeoShape with respect to the given center point.
	 *  using scale function in Point_2D in order to scale all the points;
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		this._P1.scale(center, ratio);
		this._P2.scale(center, ratio);
		this._P3.scale(center, ratio);
	}
	/**
	 * Rotates this GeoShape with respect to the given center point by an angle.
	 * using rotate function in Point_2D.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._P1.rotate(center, angleDegrees);
		this._P2.rotate(center, angleDegrees);
		this._P3.rotate(center, angleDegrees);
	}
	@Override
	public String toString(){
		return "Triangle_2D, "+ _P1.toString()+_P2.toString()+_P3.toString();
	}

}
