package engines;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import draw.Controller;
import shapes.Circle;
import shapes.Ellipse;
import shapes.FreeShape;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;


/**
 * The Class DrawingEngine.
 */
public class DrawingEngine extends OptionEngine {

	/**
	 * Instantiates a new drawing engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public DrawingEngine(String shape, ArrayList<Shape> array, Point fixed, Color currentColor,
			ArrayList<Integer> selectedShapes) {
		super(shape, array, fixed, currentColor, selectedShapes);
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#press()
	 */
	@Override
	public ArrayList<Shape> press() {
		if (selectedButton.equals("Circle")) {
			shapes.add(new Circle(fixed, 0.0, currentColor));
		} else if (selectedButton.equals("Line")) {
			Constructor<?>[] constructor = Controller.getLoadedClass2().getConstructors();
			Shape obj;
			try {
				obj = (Shape) constructor[0].newInstance(fixed, fixed, currentColor);
				shapes.add(obj);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
			}
		} else if (selectedButton.equals("Ellipse")) {
			shapes.add(new Ellipse(fixed, 0.0, 0.0, currentColor));
		} else if (selectedButton.equals("Rectangle")) {
			shapes.add(new Rectangle(fixed, currentColor, 0, 0));
		} else if (selectedButton.equals("Square")) {
			shapes.add(new Square(fixed, currentColor, 0));
		} else if (selectedButton.equals("Triangle")) {
			Constructor<?>[] constructor = Controller.getLoadedClass1().getConstructors();
			Shape obj;
			try {
				obj = (Shape) constructor[0].newInstance(fixed, fixed, fixed, currentColor);
				shapes.add(obj);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
			}

		} else if (selectedButton.equals("Free")) {
			ArrayList<Point> array = new ArrayList<Point>();
			array.add(fixed);
			array.add(new Point(fixed.x + 1, fixed.y + 1));
			shapes.add(new FreeShape(array, currentColor));
		}
		return shapes;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#drag(java.awt.Point)
	 */
	@Override
	public ArrayList<Shape> drag(Point dragPoint) {
		shapes.get(shapes.size() - 1).updateDrawing(fixed, dragPoint);
		return shapes;

	}

}