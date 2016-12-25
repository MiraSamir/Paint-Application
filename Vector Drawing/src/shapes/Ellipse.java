package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;


/**
 * The Class Ellipse.
 */
public class Ellipse extends Shape {

	/** The fixed point on resizing. */
	protected Point fixedPointOnResizing;

	/**
	 * Instantiates a new ellipse.
	 *
	 * @param c the c
	 * @param x the x
	 * @param y the y
	 * @param border the border
	 */
	public Ellipse(Point c, Double x, Double y, Color border) {
		this.pointsArray = new ArrayList<Point>();
		this.pointsArray.add(c);
		this.length = x;
		this.width = y;
		this.fillColor = new Color(255, 255, 255, 0);
		this.borderColor = border;
		this.center = getCenter();
		this.isSelected = false;
		this.shapeAttributes = new ArrayList<Shape>();
		this.fixedPointOnResizing = new Point();
		this.type = "ellipse";
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getCenter()
	 */
	@Override
	public Point getCenter() {
		Point center = new Point();
		center.x = pointsArray.get(0).x + (length.intValue() / 2);
		center.y = pointsArray.get(0).y + (width.intValue() / 2);
		return this.center = center;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D canvas) {
		if (!this.isSelected) {
			canvas.setColor(this.getFillColor());
			canvas.fillOval((int) this.pointsArray.get(0).getX(), (int) this.pointsArray.get(0).getY(),
					this.length.intValue(), this.width.intValue());
			canvas.setColor(this.getBorderColor());
			canvas.drawOval((int) this.pointsArray.get(0).getX(), (int) this.pointsArray.get(0).getY(),
					this.length.intValue(), this.width.intValue());
		} else {
			Stroke oldStroke = canvas.getStroke();
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
			canvas.setStroke(dashed);
			canvas.setColor(this.getFillColor());
			canvas.fillOval((int) this.pointsArray.get(0).getX(), (int) this.pointsArray.get(0).getY(),
					this.length.intValue(), this.width.intValue());
			canvas.setColor(this.getBorderColor());
			canvas.drawOval((int) this.pointsArray.get(0).getX(), (int) this.pointsArray.get(0).getY(),
					this.length.intValue(), this.width.intValue());
			canvas.setStroke(oldStroke);

			this.shapeAttributes = fillArrayAttributes();
			for (IShape shape : shapeAttributes) {
				shape.draw(canvas);
			}
		}
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		double equation = ((((Math.pow(p.getX() - this.getCenter().getX(), 2)) / Math.pow(this.length / 2, 2)))
				+ ((Math.pow((p.getY() - this.getCenter().getY()), 2)) / Math.pow(this.width / 2, 2)));

		return ((equation < 1.0) || (Math.abs(equation - 1.0) <= 0.01));
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void updateDrawing(Point fixed, Point dragPoint) {
		int x = Math.min(fixed.x, dragPoint.x);
		int y = Math.min(fixed.y, dragPoint.y);
		int width = Math.max(fixed.x - dragPoint.x, dragPoint.x - fixed.x);
		int length = Math.max(fixed.y - dragPoint.y, dragPoint.y - fixed.y);
		this.pointsArray.set(0, new Point(x, y));
		this.length = width * 1.0;
		this.width = length * 1.0;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#fillArrayAttributes()
	 */
	@Override
	public ArrayList<Shape> fillArrayAttributes() {
		ArrayList<Shape> list = new ArrayList<Shape>();
		Shape circle = new Ellipse(new Point(this.getCenter().x - 10, getCenter().y - 10), 20.0, 20.0, Color.CYAN);
		Shape square1 = new Rectangle(
				new Point(this.getCenter().x - ((this.length).intValue()) / 2 - 10, this.getCenter().y - 10),
				Color.PINK, 20.0, 20.0);
		Shape square3 = new Rectangle(
				new Point(this.getCenter().x + ((this.length).intValue()) / 2 - 10, this.getCenter().y - 10),
				Color.PINK, 20.0, 20.0);
		Shape square2 = new Rectangle(
				new Point(this.getCenter().x - 10, this.getCenter().y - ((this.width).intValue()) / 2 - 10), Color.PINK,
				20.0, 20.0);
		Shape square4 = new Rectangle(
				new Point(this.getCenter().x - 10, this.getCenter().y + ((this.width).intValue()) / 2 - 10), Color.PINK,
				20.0, 20.0);

		list.add(circle);
		list.add(square1);
		list.add(square2);
		list.add(square3);
		list.add(square4);
		return list;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#movingShape(java.awt.Point)
	 */
	@Override
	public void movingShape(Point center) {
		this.center = center;
		this.pointsArray.get(0).x = this.center.x - ((this.length).intValue()) / 2;
		this.pointsArray.get(0).y = this.center.y - ((this.width).intValue()) / 2;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#resizingShape(java.awt.Point, int)
	 */
	@Override
	public void resizingShape(Point dragged, int index) {

		switch (index) {
		case 2:
			this.center = fixedPointOnResizing;
			this.pointsArray.get(0).x = this.fixedCorner.x;
			this.pointsArray.get(0).y = (int) (this.fixedPointOnResizing.y - distance(fixedPointOnResizing, dragged));
			setWidth(2 * distance(this.fixedPointOnResizing, dragged));
			break;
		case 1:
			this.center = fixedPointOnResizing;
			this.pointsArray.get(0).y = this.fixedCorner.y;
			this.pointsArray.get(0).x = (int) (this.fixedPointOnResizing.x - distance(fixedPointOnResizing, dragged));
			setLength(2 * distance(this.fixedPointOnResizing, dragged));
			break;
		case 3:
			this.center = fixedPointOnResizing;
			this.pointsArray.get(0).y = this.fixedCorner.y;
			this.pointsArray.get(0).x = this.fixedPointOnResizing.x
					- distance(this.fixedPointOnResizing, dragged).intValue();
			setLength(2 * distance(this.fixedPointOnResizing, dragged));
			break;

		case 4:
			this.center = fixedPointOnResizing;
			this.pointsArray.get(0).x = this.fixedCorner.x;
			this.pointsArray.get(0).y = this.fixedPointOnResizing.y
					- distance(this.fixedPointOnResizing, dragged).intValue();
			setWidth(2 * distance(this.fixedPointOnResizing, dragged));
			break;
		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getFixedCorner(java.lang.Integer)
	 */
	@Override
	public void getFixedCorner(Integer corner) {
		this.fixedPointOnResizing = this.getCenter();

		switch (corner) {
		case 1:
			this.fixedCorner = shapeAttributes.get(2).getCenter();

			break;
		case 2:
			this.fixedCorner = shapeAttributes.get(1).getCenter();
			break;
		case 3:
			this.fixedCorner = shapeAttributes.get(2).getCenter();
			break;
		case 4:
			this.fixedCorner = shapeAttributes.get(1).getCenter();
			break;
		}
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#clone()
	 */
	@Override
	public Shape clone() {
		Point point1 = new Point(this.getPointsArray().get(0).x, this.getPointsArray().get(0).y);
		Double length = new Double(this.getLength());
		Double width = new Double(this.getWidth());
		Color borderColor = new Color(this.getBorderColor().getRGB());
		Color fillColor = new Color(this.getFillColor().getRGB());
		Shape sh = new Ellipse(point1, length, width, borderColor);
		sh.setFillColor(fillColor);
		return sh;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#isOnBorder(java.awt.Point)
	 */
	@Override
	public boolean isOnBorder(Point p) {
		double equation = ((((Math.pow(p.getX() - this.getCenter().getX(), 2)) / Math.pow(this.length / 2, 2)))
				+ ((Math.pow((p.getY() - this.getCenter().getY()), 2)) / Math.pow(this.width / 2, 2)));

		return ((Math.abs(equation - 1.0) <= 0.1));
	}

}
