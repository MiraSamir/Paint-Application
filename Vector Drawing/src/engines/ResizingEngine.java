package engines;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import shapes.Shape;


/**
 * The Class ResizingEngine.
 */
public class ResizingEngine extends OptionEngine {

	/**
	 * Instantiates a new resizing engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public ResizingEngine(String shape, ArrayList<Shape> array, Point fixed, Color currentColor,
			ArrayList<Integer> selectedShapes) {
		super(shape, array, fixed, currentColor, selectedShapes);

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#press()
	 */
	@Override
	public ArrayList<Shape> press() {

		shapes.get(selectedShapes.get(0)).getFixedCorner(Integer.parseInt(selectedButton));

		return shapes;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#drag(java.awt.Point)
	 */
	@Override
	public ArrayList<Shape> drag(Point dragPoint) {

		shapes.get(selectedShapes.get(0)).resizingShape(dragPoint, Integer.parseInt(selectedButton));

		return shapes;

	}

}
