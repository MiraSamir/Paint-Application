package backend.json;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import backend.ILoad;
import draw.Controller;
import history.Command;
import shapes.Circle;
import shapes.Ellipse;
import shapes.FreeShape;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;


/**
 * The Class LoadJSON.
 */
public class LoadJSON extends Command implements ILoad {
	
	/** The loaded array list. */
	private ArrayList<Shape> loadedArrayList;
	
	/** The file. */
	private File file;
	
	/** The error. */
	private static boolean error;

	/**
	 * Instantiates a new load json.
	 *
	 * @param path the path
	 */
	public LoadJSON(String path) {
		file = new File(path);
		error = false;
		loadedArrayList = new ArrayList<Shape>();
	}

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public static boolean isError() {
		return error;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Command#execute()
	 */
	@Override
	public ArrayList<Shape> execute() {

		JSONParser parser = new JSONParser();
		try {

			JSONArray obj = (JSONArray) parser.parse(new FileReader(file));
			for (int i = 0; i < obj.size(); i++) {
				Shape loadedShape = null;
				JSONObject shape = (JSONObject) obj.get(i);
				String localType = (String) shape.get("type");
				String fillColor = (String) shape.get("fillColor");

				Scanner in = new Scanner(fillColor);
				in.useDelimiter("\\D+");
				Color localFillColor = new Color(in.nextInt(), in.nextInt(), in.nextInt());
				in.close();

				String borderColor = (String) shape.get("borderColor");
				Scanner out = new Scanner(borderColor);
				out.useDelimiter("\\D+");
				Color localBorderColor = new Color(out.nextInt(), out.nextInt(), out.nextInt());
				out.close();

				String length = (String) shape.get("length");
				Double localLength = Double.parseDouble(length);

				String width = (String) shape.get("width");
				Double localWidth = Double.parseDouble(width);

				JSONArray pointsArray = (JSONArray) shape.get("pointsArray");
				ArrayList<Point> localPoints = new ArrayList<Point>();
				for (int x = 0; x < pointsArray.size(); x++) {
					localPoints.add(x, new Point());
				}
				for (int j = 0; j < pointsArray.size(); j++) {
					Scanner x = new Scanner(pointsArray.get(j).toString());
					x.useDelimiter("\\D+");
					localPoints.set(j, new Point(x.nextInt(), x.nextInt()));
					x.close();
				}
				if (localType.equals("circle")) {
					loadedShape = new Circle(localPoints.get(0), localLength, localBorderColor);
				} else if (localType.equals("ellipse")) {
					loadedShape = new Ellipse(localPoints.get(0), localLength, localWidth, localBorderColor);
				} else if (localType.equals("rectangle")) {
					loadedShape = new Rectangle(localPoints.get(0), localBorderColor, localWidth, localLength);
				} else if (localType.equals("square")) {
					loadedShape = new Square(localPoints.get(0), localBorderColor, localLength);
				} else if (localType.equals("triangle")) {
					if (Controller.getLoadedClass1() == null) {
						error = true;
					} else {
						Constructor<?>[] constructor = Controller.getLoadedClass1().getConstructors();
						try {
							loadedShape = (Shape) constructor[0].newInstance(localPoints.get(0), localPoints.get(1),
									localPoints.get(2), localBorderColor);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e) {

						}
					}

				} else if (localType.equals("line")) {
					if (Controller.getLoadedClass2() == null) {
						error = true;
					} else {
						Constructor<?>[] constructor = Controller.getLoadedClass2().getConstructors();
						try {
							loadedShape = (Shape) constructor[0].newInstance(localPoints.get(0), localPoints.get(1),
									localBorderColor);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e) {
						}
					}
				} else if (localType.equals("free")) {
					loadedShape = new FreeShape(localPoints, localFillColor);
				}
				if (loadedShape != null) {
					loadedShape.setPointsArray(localPoints);
					loadedShape.setBorderColor(localBorderColor);
					loadedShape.setFillColor(localFillColor);
					loadedShape.setWidth(localWidth);
					loadedShape.setLength(localLength);
					loadedArrayList.add(loadedShape);
				}
			}
		} catch (Exception e) {
		
			JLabel label = new JLabel("Please choose a valid json file");
			JOptionPane.showMessageDialog(null, label);
		}
		return loadedArrayList;
	}
}
