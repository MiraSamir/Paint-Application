package backend.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backend.ISave;
import history.Command;
import shapes.Shape;


/**
 * The Class SaveXML.
 */
public class SaveXML extends Command implements ISave {

    /** The document builder factory. */
	private DocumentBuilderFactory documentBuilderFactory;

	/** The document builder. */
	private DocumentBuilder documentBuilder;

	/** The file path. */
	private String filePath;

	/**
	 * Instantiates a new save xml.
	 *
	 * @param shapesArray the shapes array
	 * @param path the path
	 */
	public SaveXML(ArrayList<Shape> shapesArray, String path) {
		this.shapesOnPanel = shapesArray;
		this.filePath = path;
		File file = new File(this.filePath);
		file.delete();
		file.deleteOnExit();
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Command#execute()
	 */
	@Override
	public ArrayList<Shape> execute() {
		Document dom;
		documentBuilderFactory = DocumentBuilderFactory.newInstance();

		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			dom = documentBuilder.newDocument();
			Element rootElement = dom.createElement("shapes");
			dom.appendChild(rootElement);
			for (int i = 0; i < shapesOnPanel.size(); i++) {

				Element shape = dom.createElement("shape");
				shape.setAttribute("type", shapesOnPanel.get(i).getType());
				rootElement.appendChild(shape);

				Element pointsArray = dom.createElement("pointsArray");
				shape.appendChild(pointsArray);
				if (shapesOnPanel.get(i).getType().equals("free")) {
					pointsArray.setAttribute("size", String.valueOf(shapesOnPanel.get(i).getPointsArray().size()));
				}

				for (int j = 0; j < (shapesOnPanel.get(i).getPointsArray().size()); j++) {
					Element thePoint = dom.createElement("point" + j);
					if (shapesOnPanel.get(i).getPointsArray().get(j) != null) {
						Element x = dom.createElement("x");
						x.appendChild(
								dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getPointsArray().get(j).x)));
						thePoint.appendChild(x);
						Element y = dom.createElement("y");
						y.appendChild(
								dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getPointsArray().get(j).y)));
						thePoint.appendChild(y);
					} else {
						Element x = dom.createElement("x");
						x.appendChild(dom.createTextNode("0"));
						thePoint.appendChild(x);
						Element y = dom.createElement("y");
						y.appendChild(dom.createTextNode("0"));
						thePoint.appendChild(y);
					}
					pointsArray.appendChild(thePoint);

				}
				shape.appendChild(pointsArray);
				rootElement.appendChild(shape);

				Element length = dom.createElement("length");
				if (shapesOnPanel.get(i).getLength() != null) {
					length.appendChild(dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getLength().intValue())));
				} else {
					length.appendChild(dom.createTextNode("0"));
				}
				shape.appendChild(length);
				rootElement.appendChild(shape);

				Element width = dom.createElement("width");
				if (shapesOnPanel.get(i).getWidth() != null) {

					width.appendChild(dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getWidth().intValue())));
				} else {
					width.appendChild(dom.createTextNode("0"));
				}
				shape.appendChild(width);
				rootElement.appendChild(shape);

				Element borderColor = dom.createElement("borderColor");
				borderColor.appendChild(dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getBorderColor())));
				shape.appendChild(borderColor);
				rootElement.appendChild(shape);

				Element fillColor = dom.createElement("fillColor");
				fillColor.appendChild(dom.createTextNode(String.valueOf(shapesOnPanel.get(i).getFillColor())));
				shape.appendChild(fillColor);
				rootElement.appendChild(shape);
			}

			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath)));

			} catch (Exception te) {
			}
		} catch (ParserConfigurationException pce) {

		}
		return shapesOnPanel;

	}

}
