package backend.json;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import backend.ISave;
import history.Command;
import shapes.Shape;


/**
 * The Class SaveJSON.
 */
public class SaveJSON extends Command implements ISave {

    /** The file path. */
	private String filePath;

	/**
	 * Instantiates a new save json.
	 *
	 * @param shapesArray the shapes array
	 * @param path the path
	 */
	public SaveJSON(ArrayList<Shape> shapesArray, String path) {
		this.filePath = path;
		this.shapesOnPanel = shapesArray;
		this.filePath = path;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Command#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Shape> execute() {

		JSONArray array = new JSONArray();
		for (int i = 0; i < shapesOnPanel.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("type", shapesOnPanel.get(i).getType());
			JSONArray pointsArray = new JSONArray();
			for (int j = 0; j < shapesOnPanel.get(i).getPointsArray().size(); j++) {
				if (shapesOnPanel.get(i).getPointsArray().get(j) != null) {
					pointsArray.add(shapesOnPanel.get(i).getPointsArray().get(j).toString());
				} else {
					pointsArray.add(new Point(0, 0).toString());
				}
			}
			obj.put("pointsArray", pointsArray);
			if (shapesOnPanel.get(i).getLength() != null) {
				obj.put("length", shapesOnPanel.get(i).getLength().toString());
			} else {
				obj.put("length", "0");
			}
			if (shapesOnPanel.get(i).getWidth() != null) {
				obj.put("width", shapesOnPanel.get(i).getWidth().toString());
			} else {
				obj.put("width", "0");
			}
			obj.put("fillColor", shapesOnPanel.get(i).getFillColor().toString());
			obj.put("borderColor", shapesOnPanel.get(i).getBorderColor().toString());
			array.add(obj);
		}
		try {
			FileWriter file = new FileWriter(this.filePath);
			file.write(array.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return shapesOnPanel;

	}

}

