
package history;

import java.util.ArrayList;

import backend.json.LoadJSON;
import backend.json.SaveJSON;
import backend.xml.LoadXML;
import backend.xml.SaveXML;
import shapes.Shape;


/**
 * The Class MemoryInvoker.
 */
public class MemoryInvoker {

	/** The command. */
	private Command command;

	/**
	 * Instantiates a new memory invoker.
	 *
	 * @param commandType the command type
	 * @param commandBranchType the command branch type
	 * @param shapesArray the shapes array
	 */
	public MemoryInvoker(String commandType, String commandBranchType, ArrayList<Shape> shapesArray) {
		if (commandType.equals("saveXML")) {
			this.command = new SaveXML(shapesArray, commandBranchType);
		} else if (commandType.equals("saveJSON")) {
			this.command = new SaveJSON(shapesArray, commandBranchType);
		} else if (commandType.equals("loadJSON")) {
			this.command = new LoadJSON(commandBranchType);
		} else if (commandType.equals("history")) {
			this.command = new History(commandBranchType, shapesArray);
		} else if (commandType.equals("loadXML")) {
			this.command = new LoadXML(commandBranchType);
		}
	}

	/**
	 * Invoke.
	 *
	 * @return the array list
	 */
	public ArrayList<Shape> invoke() {
		ArrayList<Shape> arrayList = new ArrayList<Shape>();
		arrayList = command.execute();
		return arrayList;
	}
}
