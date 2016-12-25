package engines;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import shapes.Shape;


/**
 * The Class MovingEngine.
 */
public class MovingEngine extends OptionEngine {

	/**
	 * Instantiates a new moving engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public MovingEngine(String shape, ArrayList<Shape> array, Point fixed, Color currentColor,
			ArrayList<Integer> selectedShapes) {
		super(shape, array, fixed, currentColor, selectedShapes);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#press()
	 */
	@Override
	public ArrayList<Shape> press() {
		return null;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#drag(java.awt.Point)
	 */
	@Override
	public ArrayList<Shape> drag(Point dragPoint) {

		for (int i = 0; i < selectedShapes.size(); i++) {

			Point newCenter = new Point();
			newCenter.x = shapes.get(selectedShapes.get(i)).getCenter().x - fixed.x + dragPoint.x;
			newCenter.y = shapes.get(selectedShapes.get(i)).getCenter().y - fixed.y + dragPoint.y;
			shapes.get(selectedShapes.get(i)).movingShape(newCenter);
		}
		fixed = dragPoint;
		return shapes;
	}

}
