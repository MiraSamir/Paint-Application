package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;


/**
 * The Class Polygon.
 */
public abstract class Polygon extends Shape {

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getCenter()
	 */
	@Override
	public abstract Point getCenter();

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D canvas) {
		int[] arrayX = new int[pointsArray.size()];
		for (int i = 0; i < pointsArray.size(); i++) {
			arrayX[i] = pointsArray.get(i).x;
		}

		int[] arrayY = new int[pointsArray.size()];
		for (int i = 0; i < pointsArray.size(); i++) {
			arrayY[i] = pointsArray.get(i).y;
		}

		if (!isSelected) {
			canvas.setColor(this.getFillColor());
			canvas.fillPolygon(arrayX, arrayY, pointsArray.size());
			canvas.setColor(this.getBorderColor());
			canvas.drawPolygon(arrayX, arrayY, pointsArray.size());
		} else {
			Stroke oldStroke = canvas.getStroke();
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
			canvas.setStroke(dashed);
			canvas.setColor(this.getFillColor());
			canvas.fillPolygon(arrayX, arrayY, pointsArray.size());
			canvas.setColor(this.getBorderColor());
			canvas.drawPolygon(arrayX, arrayY, pointsArray.size());
			canvas.setStroke(oldStroke);

			this.shapeAttributes = fillArrayAttributes();
			for (Shape shape : shapeAttributes) {
				shape.draw(canvas);
			}
		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#contains(java.awt.Point)
	 */
	@Override
	public abstract boolean contains(Point p);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#updateDrawing(java.awt.Point, java.awt.Point)
	 */
	@Override
	public abstract void updateDrawing(Point fixed, Point current);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#movingShape(java.awt.Point)
	 */
	@Override
	public void movingShape(Point centerNew) {

		ArrayList<Point> tempArray = new ArrayList<Point>();

		for (int i = 0; i < pointsArray.size(); i++) {
			Point p = new Point();
			p.x = centerNew.x - this.getCenter().x + pointsArray.get(i).x;
			p.y = centerNew.y - this.getCenter().y + pointsArray.get(i).y;
			tempArray.add(p);
		}

		for (int j = 0; j < tempArray.size(); j++) {
			pointsArray.set(j, tempArray.get(j));
		}

		this.center = centerNew;

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#resizingShape(java.awt.Point, int)
	 */
	@Override
	public abstract void resizingShape(Point dragged, int index);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#getFixedCorner(java.lang.Integer)
	 */
	@Override
	public abstract void getFixedCorner(Integer corner);

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#clone()
	 */
	@Override
	public abstract Shape clone();

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Shape#isOnBorder(java.awt.Point)
	 */
	@Override
	public boolean isOnBorder(Point p) {
		boolean flag = false;
		for (int i = 0; i < pointsArray.size() - 1; i++) {
			if (isOnLine(p, pointsArray.get(i), pointsArray.get(i + 1))) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			flag = isOnLine(p, pointsArray.get(0), pointsArray.get(pointsArray.size() - 1));
		}

		return flag;
	}

}
