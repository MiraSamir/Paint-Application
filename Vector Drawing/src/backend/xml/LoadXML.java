package backend.xml;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
 * The Class LoadXML.
 */
public class LoadXML extends Command implements ILoad {
	
	/** The loaded array list. */
	private ArrayList<Shape> loadedArrayList;
	
	/** The document builder. */
	private DocumentBuilder documentBuilder;
	
	/** The file. */
	private File file;
	
	/** The error. */
	private static boolean error;

	/**
	 * Instantiates a new load xml.
	 *
	 * @param path the path
	 */
	public LoadXML(String path) {
		file = new File(path);

		loadedArrayList = new ArrayList<Shape>();
		error = false;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Command#execute()
	 */
	@Override
	public ArrayList<Shape> execute() {
		try {

			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			NodeList shapes = doc.getElementsByTagName("shape");

			for (int i = 0; i < shapes.getLength(); i++) {
				Shape loadedShape = null;
				Element shape = (Element) shapes.item(i);
				String type = shapes.item(i).getAttributes().getNamedItem("type").getNodeValue();

				int s = 0;
				if (type.equals("circle")) {
					s = 1;
				} else if (type.equals("ellipse")) {
					s = 1;
				} else if (type.equals("rectangle")) {
					s = 4;
				} else if (type.equals("square")) {
					s = 4;
				} else if (type.equals("triangle")) {
					s = 3;

				} else if (type.equals("line")) {
					s = 2;
				} else if (type.equals("free")) {
					s = Integer.parseInt(shape.getElementsByTagName("pointsArray").item(0).getAttributes()
							.getNamedItem("size").getNodeValue());
				}
				ArrayList<Point> localPoints = new ArrayList<Point>();
				for (int x = 0; x < s; x++) {
					localPoints.add(x, new Point());
				}
				for (int f = 0; f < s; f++) {

					Element point = (Element) shape.getElementsByTagName("point" + f).item(0);
					localPoints.set(f,
							new Point(Integer.parseInt(point.getElementsByTagName("x").item(0).getTextContent()),
									Integer.parseInt(point.getElementsByTagName("y").item(0).getTextContent())));

				}
				NodeList length = shape.getElementsByTagName("length");
				Double localLength = Double.parseDouble(length.item(0).getTextContent());

				NodeList width = shape.getElementsByTagName("width");
				Double localWidth = Double.parseDouble(width.item(0).getTextContent());

				NodeList fillColor = shape.getElementsByTagName("fillColor");
				String fillColorString = fillColor.item(0).getTextContent();

				Scanner in = new Scanner(fillColorString);
				in.useDelimiter("\\D+");
				Color localFillColor = new Color(in.nextInt(), in.nextInt(), in.nextInt());
				in.close();

				NodeList borderColor = shape.getElementsByTagName("borderColor");
				String borderColorString = borderColor.item(0).getTextContent();

				Scanner out = new Scanner(borderColorString);
				out.useDelimiter("\\D+");
				Color localBorderColor = new Color(out.nextInt(), out.nextInt(), out.nextInt());
				out.close();

				if (type.equals("circle")) {
					loadedShape = new Circle(localPoints.get(0), localLength, localBorderColor);

				} else if (type.equals("ellipse")) {
					loadedShape = new Ellipse(localPoints.get(0), localLength, localWidth, localBorderColor);

				} else if (type.equals("rectangle")) {
					loadedShape = new Rectangle(localPoints.get(0), localBorderColor, localWidth, localLength);

				} else if (type.equals("square")) {
					loadedShape = new Square(localPoints.get(0), localBorderColor, localLength);

				} else if (type.equals("triangle")) {
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

				} else if (type.equals("line")) {
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
				} else if (type.equals("free")) {
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
			//e.printStackTrace();
			
			JLabel label = new JLabel("Please choose a valid xml file");
			JOptionPane.showMessageDialog(null, label);
		}

		return loadedArrayList;
	}

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public static boolean isError() {
		return error;
	}
}