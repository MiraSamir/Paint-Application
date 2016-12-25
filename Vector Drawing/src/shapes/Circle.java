package shapes;

import java.awt.Color;
import java.awt.Point;


/**
 * The Class Circle.
 */
public class Circle extends Ellipse {

	/**
	 * Instantiates a new circle.
	 *
	 * @param c topleft
	 * @param x the major axis
	 * @param border the border color
	 */
	public Circle(final Point c, final Double x, final Color border) {
		super(c, x, x, border);
		this.type = "circle";
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Ellipse
	 * #updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void updateDrawing(final Point fixed, final Point dragPoint) {
		int widthC = Math.abs(fixed.x - dragPoint.x);
		int heightC = Math.abs(fixed.y - dragPoint.y);
		widthC = Math.max(widthC, heightC);
		heightC = widthC;
		int xC = fixed.x > dragPoint.x ? fixed.x - widthC : fixed.x;
		int yC = fixed.y > dragPoint.y ? fixed.y - widthC : fixed.y;
		this.pointsArray.set(0, new Point(xC, yC));
		this.length = heightC * 1.0;
		this.width = widthC * 1.0;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Ellipse
	 * #resizingShape(java.awt.Point, int)
	 */
	@Override
	public void resizingShape(final Point dragged, final int index) {

		int widthC = Math.abs(fixedPointOnResizing.x - dragged.x);
		int heightC = Math.abs(fixedPointOnResizing.y - dragged.y);
		widthC = Math.max(widthC, heightC);
		heightC = widthC;
		this.pointsArray.get(0).y =
				this.fixedPointOnResizing.y - widthC;
		this.pointsArray.get(0).x =
				this.fixedPointOnResizing.x - heightC;
		setLength(2 * heightC * 1.0);
		setWidth(2 * widthC * 1.0);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Ellipse#clone()
	 */
	@Override
	public Shape clone() {
		Point point1 = new Point(this.getPointsArray().get(0).x,
				this.getPointsArray().get(0).y);
		Double length = new Double(this.getLength());
		Color borderColor = new Color(this.getBorderColor().getRGB());
		Color fillColor = new Color(this.getFillColor().getRGB());
		Shape sh = new Circle(point1, length, borderColor);
		sh.setFillColor(fillColor);
		return sh;

	}

}
