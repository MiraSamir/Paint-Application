package shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


/**
 * The Class Shape.
 */
public abstract class Shape implements IShape, Cloneable {

    /** The border color. */
	protected Color borderColor;

	/** The fill color. */
	protected Color fillColor;

	/** The center. */
	protected Point center;

	/** The is selected. */
	protected Boolean isSelected;

	/** The shape attributes. */
	protected ArrayList<Shape> shapeAttributes;

	/** The fixed corner. */
	protected Point fixedCorner;

	/** The type. */
	protected String type;

	/** The length. */
	protected Double length;

	/** The width. */
	protected Double width;

	/** The points array. */
	protected ArrayList<Point> pointsArray;

	/** The epsilon. */
	protected final Double epsilon = 0.2;

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getShapeAttributes()
	 */
	@Override
	public ArrayList<Shape> getShapeAttributes() {
		return shapeAttributes;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getCenter()
	 */
	@Override
	public abstract Point getCenter();

	/**
	 * Fill array attributes.
	 *
	 * @return the array list
	 */
	public ArrayList<Shape> fillArrayAttributes() {
		ArrayList<Shape> list = new ArrayList<Shape>();
		Shape circle = new Ellipse(new Point(this.getCenter().x - 10,
				getCenter().y - 10), 20.0, 20.0, Color.CYAN);
		list.add(circle);

		for (int i = 0; i < pointsArray.size(); i++) {
			Shape square = new Rectangle(
					new Point(this.getPointsArray().get(i).x
							- 10,
							(this.getPointsArray().get(i).y - 10))
					, Color.PINK,
					20.0, 20.0);
			list.add(square);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#setBorderColor(java.awt.Color)
	 */
	@Override
	public void setBorderColor(Color color) {
		this.borderColor = color;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getBorderColor()
	 */
	@Override
	public Color getBorderColor() {
		return borderColor;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#setFillColor(java.awt.Color)
	 */
	@Override
	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getFillColor()
	 */
	@Override
	public Color getFillColor() {
		return fillColor;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#draw(java.awt.Graphics2D)
	 */
	@Override
	public abstract void draw(java.awt.Graphics2D canvas);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#contains(java.awt.Point)
	 */
	@Override
	public abstract boolean contains(Point p);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public abstract void updateDrawing(Point fixed, Point current);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #setIsSelected(java.lang.Boolean)
	 */
	@Override
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getIsSelected()
	 */
	@Override
	public Boolean getIsSelected() {
		return this.isSelected;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#movingShape(java.awt.Point)
	 */
	@Override
	public abstract void movingShape(Point center);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #resizingShape(java.awt.Point, int)
	 */
	@Override
	public abstract void resizingShape(Point dragged, int index);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #getFixedCorner(java.lang.Integer)
	 */
	@Override
	public abstract void getFixedCorner(Integer corner);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #distance(java.awt.Point, java.awt.Point)
	 */
	@Override
	public Double distance(Point a, Point b) {
		int dx = (a.x - b.x);
		int dy = (a.y - b.y);
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getType()
	 */
	@Override
	public String getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getLength()
	 */
	@Override
	public Double getLength() {
		return this.length;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getWidth()
	 */
	@Override
	public Double getWidth() {
		return this.width;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#getPointsArray()
	 */
	@Override
	public ArrayList<Point> getPointsArray() {
		return this.pointsArray;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #setPointsArray(java.util.ArrayList)
	 */
	@Override
	public void setPointsArray(ArrayList<Point> pointsArray) {
		this.pointsArray = pointsArray;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#setWidth(java.lang.Double)
	 */
	@Override
	public void setWidth(Double width) {
		this.width = width;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#setLength(java.lang.Double)
	 */
	@Override
	public void setLength(Double length) {

		this.length = length;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape#isOnBorder(java.awt.Point)
	 */
	@Override
	public abstract boolean isOnBorder(Point p);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.IShape
	 * #isOnLine(java.awt.Point, java.awt.Point, java.awt.Point)
	 */
	@Override
	public boolean isOnLine(Point point, Point p1, Point p2) {
		if ((distance(point, p1) + distance(point, p2))
				- distance(p1, p2) <= epsilon) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Shape clone();
}
