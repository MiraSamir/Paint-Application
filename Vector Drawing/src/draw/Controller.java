package draw;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.json.LoadJSON;
import backend.xml.LoadXML;
import classLoader.DynamicClassLoader;
import engines.ColoringEngine;
import engines.DeletingEngine;
import engines.DrawingEngine;
import engines.MovingEngine;
import engines.OptionEngine;
import engines.ResizingEngine;
import history.MemoryInvoker;
import shapes.Shape;


/**
 * The Class Controller.
 */
public class Controller implements ActionListener, ChangeListener {

	/** The paintgui. */
	private PaintGui paintgui;

	/** The selected shape. */
	private String selectionMode, selectedShape;

	/** The engine. */
	private OptionEngine engine;

	/** The selected shapes. */
	private ArrayList<Integer> selectedShapes;

	/** The memory invoker. */
	private MemoryInvoker memoryInvoker;

	/** The stack redo. */
	private static Stack<ArrayList<Shape>> stackUndo, stackRedo;

	/** The loaded class2. */
	private static Class<?> loadedClass1, loadedClass2;

	/** The file chooser handler. */
	private FileChooserHandler fileChooserHandler;

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		paintgui = new PaintGui();
		selectionMode = "";
		stackUndo = new Stack<ArrayList<Shape>>();
		stackRedo = new Stack<ArrayList<Shape>>();
		selectedShapes = new ArrayList<>();
		loadedClass1 = null;
		loadedClass2 = null;
		paintgui.getButtonArray()[2].setVisible(false);
		paintgui.getButtonArray()[1].setVisible(false);
		;

		for (int i = 0; i < paintgui.getButtonArray().length; i++) {
			paintgui.getButtonArray()[i].addActionListener(this);
		}
		for (int i = 0; i < paintgui.getOptionsArray().length; i++) {
			paintgui.getOptionsArray()[i].addActionListener(this);
		}
		saveLoadFileFilter(paintgui.getSaveLoadFileChooser());
		jarFileFilter(paintgui.getJarFileChooser());
		paintgui.getMainPanel().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point fixed = e.getPoint();
				if (selectionMode.equals("draw")) {
					engine = new DrawingEngine(selectedShape, paintgui.getShapes(), fixed, paintgui.getCurrentColor(),
							selectedShapes);
					paintgui.setShapes(engine.press());
					paintgui.repaint();
				} else if (selectionMode.equals("color")) {
					engine = new ColoringEngine(selectedShape, paintgui.getShapes(), fixed, paintgui.getCurrentColor(),
							selectedShapes);
					paintgui.setShapes(engine.press());
					paintgui.repaint();
				} else {
					checking(e.getPoint());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (selectionMode.equals("draw") || selectionMode.equals("moving") || selectionMode.equals("resizing")
						|| selectionMode.equals("color")) {
					memoryInvoker = new MemoryInvoker("history", "save", paintgui.getShapes());
					paintgui.setShapes(memoryInvoker.invoke());
				}
				paintgui.getMainPanel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				selectionMode = "";

			}
		});
		paintgui.getMainPanel().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if (selectionMode.equals("draw") || selectionMode.equals("moving")
						|| selectionMode.equals("resizing")) {
					paintgui.setShapes(engine.drag(arg0.getPoint()));
					paintgui.repaint();
					if (selectionMode.equals("moving")) {
						paintgui.getMainPanel().setCursor(new Cursor(Cursor.MOVE_CURSOR));
					} else if (selectionMode.equals("resizing")) {
						paintgui.getMainPanel().setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
					} else if (selectionMode.equals("draw")) {
						paintgui.getMainPanel().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					}
				}
			}
		});
		paintgui.getColorChooser().getSelectionModel().addChangeListener(this);
		paintgui.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Save Your Masterpiece Before Closing?",
						"Exit Program ", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					ArrayList<String> result = new ArrayList<String>();
					fileChooserHandler = new FileChooserHandler(paintgui.getSaveLoadFileChooser(),
							paintgui.getJarFileChooser());
					result = fileChooserHandler.showFileChooser(1);
					if (result != null) {
						memoryInvoker = new MemoryInvoker(result.get(0), result.get(1), paintgui.getShapes());
						paintgui.setShapes(memoryInvoker.invoke());

					}
				}
			}

		});
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonPressed = e.getActionCommand();
		if (buttonPressed.equals("Circle") || buttonPressed.equals("Line") || buttonPressed.equals("Rectangle")
				|| buttonPressed.equals("Square") || buttonPressed.equals("Ellipse") || buttonPressed.equals("Free")
				|| buttonPressed.equals("Triangle")) {
			selectionMode = "draw";
			selectedShape = buttonPressed;
		} else if (buttonPressed.equals("Delete")) {
			engine = new DeletingEngine(selectedShape, paintgui.getShapes(), new Point(0, 0),
					paintgui.getCurrentColor(), selectedShapes);
			paintgui.setShapes(engine.press());
			memoryInvoker = new MemoryInvoker("history", "save", paintgui.getShapes());
			paintgui.setShapes(memoryInvoker.invoke());
			selectedShapes.clear();
			paintgui.repaint();
		} else if (buttonPressed.equals("Undo")) {
			memoryInvoker = new MemoryInvoker("history", "undo", paintgui.getShapes());
			paintgui.setShapes(memoryInvoker.invoke());
			selectedShapes.clear();
			paintgui.repaint();
		} else if (buttonPressed.equals("Redo")) {
			memoryInvoker = new MemoryInvoker("history", "redo", paintgui.getShapes());
			paintgui.setShapes(memoryInvoker.invoke());
			selectedShapes.clear();
			paintgui.repaint();
		} else if (buttonPressed.equals("Save")) {

			ArrayList<String> result = new ArrayList<String>();
			fileChooserHandler = new FileChooserHandler(paintgui.getSaveLoadFileChooser(),
					paintgui.getJarFileChooser());
			result = fileChooserHandler.showFileChooser(1);
			if (result != null) {
				memoryInvoker = new MemoryInvoker(result.get(0), result.get(1), paintgui.getShapes());
				paintgui.setShapes(memoryInvoker.invoke());
				selectedShapes.clear();
				paintgui.repaint();
			}
		} else if (buttonPressed.equals("Load")) {

			ArrayList<String> result1 = new ArrayList<String>();
			fileChooserHandler = new FileChooserHandler(paintgui.getSaveLoadFileChooser(),
					paintgui.getJarFileChooser());
			result1 = fileChooserHandler.showFileChooser(0);
			if (result1 != null) {
				memoryInvoker = new MemoryInvoker(result1.get(0), result1.get(1), paintgui.getShapes());
				paintgui.setShapes(memoryInvoker.invoke());
				selectedShapes.clear();
				paintgui.repaint();
				stackUndo.clear();
				stackRedo.clear();
				memoryInvoker = new MemoryInvoker("history", "save", paintgui.getShapes());
				paintgui.setShapes(memoryInvoker.invoke());
				selectedShapes.clear();
				paintgui.repaint();
				if (LoadJSON.isError() || LoadXML.isError()) {
					JLabel label = new JLabel(
							"Missing files must be loaded first in order to get your full masterpiece");
					JOptionPane.showMessageDialog(null, label);
				}
			}

		} else if (buttonPressed.equals("Dynamic loading")) {
			fileChooserHandler = new FileChooserHandler(paintgui.getSaveLoadFileChooser(),
					paintgui.getJarFileChooser());
			String path = fileChooserHandler.getDynamicJarPath();
			String type = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
			DynamicClassLoader dynamicLoading = new DynamicClassLoader(path, type);
			Class<?> classloaded = dynamicLoading.execute();
			if (classloaded != null) {
				if (type.equals("Triangle")) {
					Controller.loadedClass1 = classloaded;
					paintgui.getButtonArray()[2].setVisible(true);
				} else if (type.equals("LineSegment")) {
					Controller.loadedClass2 = classloaded;
					paintgui.getButtonArray()[1].setVisible(true);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		paintgui.setCurrentColor(paintgui.getColorChooser().getColor());
		selectionMode = "color";
	}

	/**
	 * Gets the paintgui.
	 *
	 * @return the paintgui
	 */
	public PaintGui getPaintgui() {
		return paintgui;
	}

	/**
	 * Checking.
	 *
	 * @param p the p
	 */
	public void checking(Point p) {
		engine = new MovingEngine(selectedShape, paintgui.getShapes(), p, paintgui.getCurrentColor(), selectedShapes);
		int index = engine.detectionContains();
		if (index != -1) {
			if (!paintgui.getShapes().get(index).getIsSelected()) {
				paintgui.getShapes().get(index).setIsSelected(true);
				selectedShapes.add(index);
				paintgui.repaint();
			} else {
				for (int i = 0; (paintgui.getShapes().get(index).getShapeAttributes() != null)
						&& i < paintgui.getShapes().get(index).getShapeAttributes().size(); i++) {
					if (paintgui.getShapes().get(index).getShapeAttributes().get(i).contains(p)) {
						if (i == 0) {
							selectionMode = "moving";
							engine = new MovingEngine(selectedShape, paintgui.getShapes(), p,
									paintgui.getCurrentColor(), selectedShapes);
						} else {
							selectionMode = "resizing";
							ArrayList<Integer> indexOfShape = new ArrayList<Integer>();
							indexOfShape.add(index);
							engine = new ResizingEngine(String.valueOf(i), paintgui.getShapes(), p,
									paintgui.getCurrentColor(), indexOfShape);
							paintgui.setShapes(engine.press());
							paintgui.repaint();
						}
						break;
					}
				}
			}
		} else {
			for (int j = 0; j < selectedShapes.size(); j++) {
				if (paintgui.getShapes().size() > 0)
					paintgui.getShapes().get(selectedShapes.get(j)).setIsSelected(false);
			}
			paintgui.repaint();
			selectedShapes.clear();
		}
	}

	/**
	 * Gets the stack undo.
	 *
	 * @return the stack undo
	 */
	public static Stack<ArrayList<Shape>> getStackUndo() {
		return stackUndo;
	}

	/**
	 * Sets the stack undo.
	 *
	 * @param stackUndo the new stack undo
	 */
	public static void setStackUndo(Stack<ArrayList<Shape>> stackUndo) {
		Controller.stackUndo = stackUndo;
	}

	/**
	 * Gets the stack redo.
	 *
	 * @return the stack redo
	 */
	public static Stack<ArrayList<Shape>> getStackRedo() {
		return stackRedo;
	}

	/**
	 * Sets the stack redo.
	 *
	 * @param stackRedo the new stack redo
	 */
	public static void setStackRedo(Stack<ArrayList<Shape>> stackRedo) {
		Controller.stackRedo = stackRedo;
	}

	/**
	 * Gets the loaded class1.
	 *
	 * @return the loaded class1
	 */
	public static Class<?> getLoadedClass1() {
		return Controller.loadedClass1;
	}

	/**
	 * Gets the loaded class2.
	 *
	 * @return the loaded class2
	 */
	public static Class<?> getLoadedClass2() {
		return Controller.loadedClass2;
	}

	/**
	 * Save load file filter.
	 *
	 * @param fileChooser the file chooser
	 */
	public void saveLoadFileFilter(JFileChooser fileChooser) {
		fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter(new String[] { ".xml" }, "Text Documents (*.xml)"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter(new String[] { ".json" }, "Text Documents (*.json)"));
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	/**
	 * Jar file filter.
	 *
	 * @param fileChooser the file chooser
	 */
	public void jarFileFilter(JFileChooser fileChooser) {
		fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter(new String[] { ".jar" }, "Text Documents (*.jar)"));
		fileChooser.setAcceptAllFileFilterUsed(false);
	}
}
