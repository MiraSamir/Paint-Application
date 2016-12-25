package shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


/**
 * The Class Rectangle.
 */
public class Rectangle extends Polygon {

	/**
	 * Instantiates a new rectangle.
	 *
	 * @param topLeft the top left
	 * @param borderColor the border color
	 * @param Length the length
	 * @param width the width
	 */
	public Rectangle(Point topLeft, Color borderColor, double Length, double width) {
		this.borderColor = borderColor;
		this.pointsArray = new ArrayList<Point>();
		this.length = Length;
		this.width = width;
		this.pointsArray.add( topLeft);
		this.pointsArray.add( new Point(topLeft.x + this.width.intValue(), topLeft.y));
		this.pointsArray.add( new Point(topLeft.x + this.width.intValue(), topLeft.y + this.length.intValue()));
		this.pointsArray.add( new Point(topLeft.x, topLeft.y + this.length.intValue()));
		this.fillColor = new Color(255, 255, 255, 0);
		this.isSelected = false;
		this.type = "rectangle";

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#getCenter()
	 */
	@Override
	public Point getCenter() {
		Point p = new Point();
		p.x = (int) (pointsArray.get(0).x + ((width) / 2));
		p.y = (int) (pointsArray.get(0).y + ((length) / 2));

		return p;
	}



	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		return ((pointsArray.get(0).x <= p.x && p.x <= (pointsArray.get(0).x + width)
				&& (pointsArray.get(0).y <= p.y && p.y <= (pointsArray.get(0).y + length))));
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void updateDrawing(Point fixed, Point current) {
		int xR = Math.min(fixed.x, current.x);
		int yR = Math.min(fixed.y, current.y);
		int widthR = Math.max(fixed.x - current.x, current.x - fixed.x);
		int lengthR = Math.max(fixed.y - current.y, current.y - fixed.y);
		this.pointsArray.set(0, new Point(xR, yR));
		this.pointsArray.set(1,new Point(xR + widthR, yR) ) ;
		this.pointsArray.set(2,new Point(xR + widthR, yR + lengthR));
		this.pointsArray.set(3,new Point(xR, yR + lengthR)) ;

		this.length = lengthR * 1.0;
		this.width = widthR * 1.0;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#resizingShape(java.awt.Point, int)
	 */
	@Override
	public void resizingShape(Point dragged, int index) {

		int xR = Math.min(fixedCorner.x, dragged.x);
		int yR = Math.min(fixedCorner.y, dragged.y);
		int widthR = Math.max(fixedCorner.x - dragged.x, dragged.x - fixedCorner.x);
		int lengthR = Math.max(fixedCorner.y - dragged.y, dragged.y - fixedCorner.y);

		this.pointsArray.set(0, new Point(xR, yR));
		this.pointsArray.set(1,new Point(xR + widthR, yR) ) ;
		this.pointsArray.set(2,new Point(xR + widthR, yR + lengthR));
		this.pointsArray.set(3,new Point(xR, yR + lengthR)) ;
		this.length = lengthR * 1.0;
		this.width = widthR * 1.0;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#getFixedCorner(java.lang.Integer)
	 */
	@Override
	public void getFixedCorner(Integer corner) {
		switch (corner) {
		case 1:
			this.fixedCorner = shapeAttributes.get(3).getCenter();

			break;
		case 2:
			this.fixedCorner = shapeAttributes.get(4).getCenter();
			break;
		case 3:
			this.fixedCorner = shapeAttributes.get(1).getCenter();
			break;
		case 4:
			this.fixedCorner = shapeAttributes.get(2).getCenter();
			break;

		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Polygon#clone()
	 */
	@Override
	public Shape clone() {
		Point point1 = new Point(this.getPointsArray().get(0).x, this.getPointsArray().get(0).y);
		Double length = new Double(this.getLength());
		Double width = new Double(this.getWidth());
		Color borderColor = new Color(this.getBorderColor().getRGB());
		Color fillColor = new Color(this.getFillColor().getRGB());
		Shape sh = new Rectangle(point1, borderColor, length, width);
		sh.setFillColor(fillColor);

		return sh;
	}

}