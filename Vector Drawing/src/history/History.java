package history;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import draw.Controller;
import shapes.Shape;


/**
 * The Class History.
 */
public class History extends Command {

	/** The array. */
	private ArrayList<Shape> array;
	
	/** The determinator. */
	private String determinator;

	/**
	 * Instantiates a new history.
	 *
	 * @param s the s
	 * @param array the array
	 */
	public History(String s, ArrayList<Shape> array) {
		this.determinator = s;
		this.array = array;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.Command#execute()
	 */
	@Override
	public ArrayList<Shape> execute() {
		ArrayList<Shape> arrayReturned = new ArrayList<Shape>();
		if (determinator.equals("undo")) {
			undo();
			if (!Controller.getStackUndo().isEmpty()) {
				for (int i = 0; i < Controller.getStackUndo().peek().size(); i++) {
					Shape sh = Controller.getStackUndo().peek().get(i).clone();
					arrayReturned.add(sh);
				}
			}
			return arrayReturned;
		} else if (determinator.equals("redo")) {
			redo();

			if (!Controller.getStackUndo().isEmpty()) {
				for (int i = 0; i < Controller.getStackUndo().peek().size(); i++) {

					Shape sh = Controller.getStackUndo().peek().get(i).clone();
					arrayReturned.add(sh);
				}
			}
			return arrayReturned;
		} else {
			saveHistory();
			return array;
		}
	}

	/**
	 * Undo.
	 */
	private void undo() {
		if (!Controller.getStackUndo().isEmpty()) {
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			for (int i = 0; i < Controller.getStackUndo().peek().size(); i++) {
				Shape sh = Controller.getStackUndo().peek().get(i).clone();
				shapes.add(sh);
			}
			Controller.getStackUndo().pop();
			Controller.getStackRedo().push(shapes);
		} else {
			JLabel label = new JLabel("You Cannot Undo More !!");
			JOptionPane.showMessageDialog(null, label);
		}
	}

	/**
	 * Redo.
	 */
	private void redo() {
		if (!Controller.getStackRedo().isEmpty()) {
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			for (int i = 0; i < Controller.getStackRedo().peek().size(); i++) {
				Shape sh = Controller.getStackRedo().peek().get(i).clone();
				shapes.add(sh);
			}
			Controller.getStackRedo().pop();
			Controller.getStackUndo().push(shapes);
		} else {
			JLabel label = new JLabel("You Cannot Redo More !!!");
			JOptionPane.showMessageDialog(null, label);
		}
	}

	/**
	 * Save history.
	 */
	private void saveHistory() {
		ArrayList<Shape> newOne = new ArrayList<Shape>();
		for (int i = 0; i < array.size(); i++) {
			Shape sh = array.get(i).clone();
			newOne.add(sh);
		}
		Controller.getStackUndo().push(newOne);
		Controller.getStackRedo().clear();
	}
}
