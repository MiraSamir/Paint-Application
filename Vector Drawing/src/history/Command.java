package history;

import java.util.ArrayList;

import shapes.Shape;


/**
 * The Class Command.
 */
public abstract class Command {

    /** The shapes on panel.*/
  protected ArrayList<Shape> shapesOnPanel;

	/**
	 * Execute.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Shape> execute();

}
