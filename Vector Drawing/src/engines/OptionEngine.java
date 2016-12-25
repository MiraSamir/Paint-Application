package engines;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import shapes.Shape;


/**
 * The Class OptionEngine.
 */
public abstract class OptionEngine {
	
	/** The selected button. */
	protected String selectedButton;
	
	/** The shapes. */
	protected ArrayList<Shape> shapes;
	
	/** The selected shapes. */
	protected ArrayList<Integer> selectedShapes;
	
	/** The fixed. */
	protected Point fixed;
	
	/** The current color. */
	protected Color currentColor;

	/**
	 * Instantiates a new option engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public OptionEngine(String shape, ArrayList<Shape> array, Point fixed, Color currentColor,
			ArrayList<Integer> selectedShapes) {
		this.selectedButton = shape;
		this.shapes = array;
		this.fixed = fixed;
		this.currentColor = currentColor;
		this.selectedShapes = selectedShapes;
	}

	 /**
 	 * Detection contains.
 	 *
 	 * @return the int
 	 */
 	public int detectionContains() {
			int index = -1;
			for (int i = shapes.size() - 1; i >= 0; i--) {
				if (shapes.get(i).contains(fixed)) {
					index = i;
					break;
				}
			}
			return index;
		}

		/**
		 * Detection on border.
		 *
		 * @return the int
		 */
		public int detectionOnBorder() {
			int index = -1;
			for (int i = shapes.size() - 1; i >= 0; i--) {
				if (shapes.get(i).isOnBorder(fixed)) {
					index = i;
					break;
				}
			}
			return index;
		}

	/**
	 * Press.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Shape> press();
	
	/**
	 * Drag.
	 *
	 * @param dragPoint the drag point
	 * @return the array list
	 */
	public abstract ArrayList<Shape> drag(Point dragPoint);

}
