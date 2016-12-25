package shapes;

import java.awt.Color;
import java.awt.Point;


/**
 * The Class Square.
 */
public class Square extends Rectangle {

    /**
     * *Instantiates a new square.
     * @param topLeft the top left
	 * @param borderColor the border color
	 * @param Length the length
	 */
	public Square(Point topLeft, Color borderColor, double Length) {
		super(topLeft, borderColor, Length, Length);
		this.type = "square";
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Rectangle
	 * #updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void updateDrawing(Point fixed, Point dragPoint) {
		int widthS = Math.abs(fixed.x - dragPoint.x);
		int heightS = Math.abs(fixed.y - dragPoint.y);
		widthS = Math.max(widthS, heightS);
		heightS = widthS;
		int xS = fixed.x > dragPoint.x ? fixed.x -
				widthS : fixed.x;
		int yS = fixed.y > dragPoint.y ? fixed.y -
				widthS : fixed.y;
		this.pointsArray.set(0, new Point(xS, yS));
		this.pointsArray.set(1, new Point(xS + widthS, yS));
		this.pointsArray.set(2, new Point(xS + widthS, yS + heightS));
		this.pointsArray.set(3, new Point(xS, yS + heightS));
		this.length = heightS * 1.0;
		this.width = widthS * 1.0;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Rectangle
	 * #resizingShape(java.awt.Point, int)
	 */
	@Override
	public void resizingShape(Point dragged, int index) {
		int widthS = Math.abs(fixedCorner.x - dragged.x);
		int heightS = Math.abs(fixedCorner.y - dragged.y);
		widthS = Math.max(widthS, heightS);
		heightS = widthS;
		int xS = fixedCorner.x > dragged.x ? fixedCorner.x -
				widthS : fixedCorner.x;
		int yS = fixedCorner.y > dragged.y ? fixedCorner.y -
				widthS : fixedCorner.y;
		this.pointsArray.set(0, new Point(xS, yS));
		this.pointsArray.set(1, new Point(xS + widthS, yS));
		this.pointsArray.set(2, new Point(xS + widthS, yS + heightS));
		this.pointsArray.set(3, new Point(xS, yS + heightS));
		this.length = heightS * 1.0;
		this.length = heightS * 1.0;
		this.width = widthS * 1.0;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Rectangle#clone()
	 */
	@Override
	public Shape clone() {
		Point point1 = new Point(this.getPointsArray().get(0).x,
				this.getPointsArray().get(0).y);
		Double length = new Double(this.getLength());
		Color borderColor = new Color(this.getBorderColor().getRGB());
		Color fillColor = new Color(this.getFillColor().getRGB());
		Shape sh = new Square(point1, borderColor, length);
		sh.setFillColor(fillColor);

		return sh;
	}

}
