package engines;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import shapes.Shape;


/**
 * The Class DeletingEngine.
 */
public class DeletingEngine extends OptionEngine {

	/**
	 * Instantiates a new deleting engine.
	 *
	 * @param shape the shape
	 * @param array the array
	 * @param fixed the fixed
	 * @param currentColor the current color
	 * @param selectedShapes the selected shapes
	 */
	public DeletingEngine(String shape, ArrayList<Shape> array, Point fixed, Color currentColor,
			ArrayList<Integer> selectedShapes) {
		super(shape, array, fixed, currentColor, selectedShapes);

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#press()
	 */
	@Override
	public ArrayList<Shape> press() {
		JLabel label;
		if (selectedShapes.isEmpty()) {
			label = new JLabel("Please select a shape to delete");
			JOptionPane.showMessageDialog(null, label);

		} else {
			Collections.sort(selectedShapes);
			if (selectedShapes.size() > 1) {
				label = new JLabel("Delete shapes ?");
			} else {
				label = new JLabel("Delete shape ?");
			}
			JPanel panel = new JPanel(new GridLayout(3, 1));
			JRadioButton yes = new JRadioButton("Yes");
			JRadioButton no = new JRadioButton("No");
			ButtonGroup group = new ButtonGroup();
			group.add(yes);
			group.add(no);
			panel.add(label);
			panel.add(yes);
			panel.add(no);
			JOptionPane.showMessageDialog(null, panel);
			if (yes.isSelected()) {
				for (int i = selectedShapes.size() - 1; i >= 0; i--) {
					int index = selectedShapes.get(i);
					shapes.remove(index);
				}
			}
		}
		return shapes;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.oop.draw.OptionEngine#drag(java.awt.Point)
	 */
	@Override
	public ArrayList<Shape> drag(Point dragPoint) {
		return null;
	}

}