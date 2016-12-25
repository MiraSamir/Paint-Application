package engines;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import shapes.Shape;


/**
 * The Class ColoringEngine.
 */
public class ColoringEngine extends OptionEngine {

     /**
	 * Instantiates a new coloring engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public ColoringEngine(final String shape, final ArrayList<Shape> array,
			final Point fixed, final Color currentColor,
			final ArrayList<Integer> selectedShapes) {
		super(shape, array, fixed, currentColor, selectedShapes);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#press()
	 */
	@Override
	public ArrayList<Shape> press() {
		int index = detectionContains();
		if (index != -1) {
			shapes.get(index).setFillColor(currentColor);
		}
		return shapes;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#drag(java.awt.Point)
	 */
	@Override
	public ArrayList<Shape> drag(final Point dragPoint) {
		return null;
	}

}