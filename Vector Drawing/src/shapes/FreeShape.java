package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;


/**
 * The Class FreeShape.
 */
public class FreeShape extends Shape {

	/**
	 * Instantiates a new free shape.
	 *
	 * @param shapePoints the shape points
	 * @param currentColor the current color
	 */
	public FreeShape(ArrayList<Point> shapePoints, Color currentColor) {

		this.pointsArray = shapePoints;
		this.fillColor = this.borderColor = currentColor;
		this.isSelected = false;
		this.type = "free";

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getCenter()
	 */
	@Override
	public Point getCenter() {
		return null;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D canvas) {

		for (int i = 0; i < pointsArray.size() - 1; i++) {
			if (!isSelected) {
				canvas.setColor(this.fillColor);
				canvas.drawLine(pointsArray.get(i).x, pointsArray.get(i).y, pointsArray.get(i + 1).x,
						pointsArray.get(i + 1).y);
			} else {
				Stroke oldStroke = canvas.getStroke();
				Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 },
						0);
				canvas.setStroke(dashed);
				canvas.setColor(this.borderColor);
				canvas.drawLine(pointsArray.get(i).x, pointsArray.get(i).y, pointsArray.get(i + 1).x,
						pointsArray.get(i + 1).y);
				canvas.setStroke(oldStroke);
			}
		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {

		for (int i = 0; i < pointsArray.size(); i++) {
			if (Math.abs(p.x - pointsArray.get(i).x) <= 4 && Math.abs(p.y - pointsArray.get(i).y) <= 4) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void updateDrawing(Point fixed, Point current) {
		pointsArray.add(current);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#movingShape(java.awt.Point)
	 */
	@Override
	public void movingShape(Point center) {

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#resizingShape(java.awt.Point, int)
	 */
	@Override
	public void resizingShape(Point dragged, int index) {
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getFixedCorner(java.lang.Integer)
	 */
	@Override
	public void getFixedCorner(Integer corner) {
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#isOnBorder(java.awt.Point)
	 */
	@Override
	public boolean isOnBorder(Point p) {
		return this.contains(p);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#clone()
	 */
	@Override
	public Shape clone() {
		ArrayList<Point> newArray = new ArrayList<>();
		for (int i = 0; i < pointsArray.size(); i++) {
			Point p = new Point();
			p.x = pointsArray.get(i).x;
			p.y = pointsArray.get(i).y;
			newArray.add(p);
		}
		Color color = new Color(this.getBorderColor().getRGB());
		Shape free = new FreeShape(newArray, color);
		free.fillColor = new Color(this.getFillColor().getRGB());
		return free;
	}

}
