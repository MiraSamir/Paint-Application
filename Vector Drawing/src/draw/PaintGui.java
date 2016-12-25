package draw;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classLoader.ResourceLoader;
import shapes.Shape;

/**
 * The Class PaintGui.
 */
public class PaintGui extends JFrame {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The free. */
    private JButton circle, line, ellipse, triangle, rectangle, square, free;
    
    /** The save. */
    private JButton delete, load, dynamicLoad, undo, redo, save;
    
    /** The option panel. */
    private JPanel mainPanel, shapesPanel, optionPanel;
    
    /** The color chooser. */
    private JColorChooser colorChooser;
    
    /** The options array. */
    private JButton[] buttonArray, optionsArray;
    
    /** The jar file chooser. */
    private JFileChooser fileChooser, jarFileChooser;
    
    /**
     * Instantiates a new paint gui.
     */
    public PaintGui() {
        setLayout(new BorderLayout());
        
        shapesPanel = new JPanel(new FlowLayout());
        shapesPanel.setBackground(Color.black);
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        optionPanel = new JPanel(new GridLayout(6, 1));
        
        fileChooser = new JFileChooser();
        jarFileChooser = new JFileChooser();
        buttonArray = new JButton[7];
        currentColor = Color.black;
        shapes = new ArrayList<Shape>();
        
        ImageIcon img = null, img2 = null, img3 = null, img4 = null, img5 = null, img6 = null, img7 = null;
        try {
            img = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/circle.png")));
            img4 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/line.png")));
            img3 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/triangle.png")));
            img5 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/ellipse.png")));
            img2 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/rectangle.png")));
            img6 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/square.png")));
            img7 = new ImageIcon(ImageIO.read(ResourceLoader.load("Images/free.png")));
            
        } catch (IOException e) {
            
        }
        circle = new JButton();
        circle.setVerticalTextPosition(SwingConstants.BOTTOM);
        circle.setHorizontalTextPosition(SwingConstants.CENTER);
        circle.setText("Circle");
        circle.setForeground(Color.orange);
        circle.setIcon(img);
        circle.setBackground(Color.BLACK);
        buttonArray[0] = circle;
        
        line = new JButton();
        line.setVerticalTextPosition(SwingConstants.BOTTOM);
        line.setHorizontalTextPosition(SwingConstants.CENTER);
        line.setText("Line");
        line.setForeground(Color.orange);
        line.setIcon(img4);
        line.setBackground(Color.BLACK);
        buttonArray[1] = line;
        
        triangle = new JButton();
        
        triangle.setVerticalTextPosition(SwingConstants.BOTTOM);
        triangle.setHorizontalTextPosition(SwingConstants.CENTER);
        triangle.setText("Triangle");
        triangle.setForeground(Color.orange);
        triangle.setIcon(img3);
        triangle.setBackground(Color.BLACK);
        buttonArray[2] = triangle;
        
        ellipse = new JButton();
        ellipse.setVerticalTextPosition(SwingConstants.BOTTOM);
        ellipse.setHorizontalTextPosition(SwingConstants.CENTER);
        ellipse.setText("Ellipse");
        ellipse.setForeground(Color.orange);
        ellipse.setIcon(img5);
        ellipse.setBackground(Color.BLACK);
        
        buttonArray[3] = ellipse;
        rectangle = new JButton();
        rectangle.setVerticalTextPosition(SwingConstants.BOTTOM);
        rectangle.setHorizontalTextPosition(SwingConstants.CENTER);
        rectangle.setText("Rectangle");
        rectangle.setForeground(Color.orange);
        rectangle.setIcon(img2);
        rectangle.setBackground(Color.BLACK);
        buttonArray[4] = rectangle;
        
        square = new JButton();
        square.setVerticalTextPosition(SwingConstants.BOTTOM);
        square.setHorizontalTextPosition(SwingConstants.CENTER);
        square.setText("Square");
        square.setForeground(Color.orange);
        square.setIcon(img6);
        square.setBackground(Color.BLACK);
        buttonArray[5] = square;
        
        free = new JButton();
        free.setVerticalTextPosition(SwingConstants.BOTTOM);
        free.setHorizontalTextPosition(SwingConstants.CENTER);
        free.setText("Free");
        free.setForeground(Color.orange);
        free.setIcon(img7);
        free.setBackground(Color.BLACK);
        buttonArray[6] = free;
        
        delete = new JButton("Delete");
        delete.setBackground(Color.black);
        delete.setForeground(Color.orange);
        delete.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        
        load = new JButton("Load");
        load.setBackground(Color.black);
        load.setForeground(Color.orange);
        load.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        
        save = new JButton("Save");
        save.setBackground(Color.black);
        save.setForeground(Color.orange);
        save.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        dynamicLoad = new JButton("Dynamic loading");
        dynamicLoad.setBackground(Color.black);
        dynamicLoad.setForeground(Color.orange);
        dynamicLoad.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        undo = new JButton("Undo");
        
        undo.setBackground(Color.black);
        undo.setForeground(Color.orange);
        undo.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        
        redo = new JButton("Redo");
        redo.setBackground(Color.black);
        redo.setForeground(Color.ORANGE);
        redo.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        
        optionsArray = new JButton[6];
        optionsArray[0] = delete;
        optionsArray[1] = undo;
        optionsArray[2] = redo;
        optionsArray[3] = save;
        optionsArray[4] = load;
        optionsArray[5] = dynamicLoad;
        
        shapesPanel.add(circle);
        shapesPanel.add(ellipse);
        shapesPanel.add(rectangle);
        shapesPanel.add(square);
        shapesPanel.add(line);
        shapesPanel.add(triangle);
        shapesPanel.add(free);
        
        optionPanel.add(delete);
        optionPanel.add(undo);
        optionPanel.add(redo);
        optionPanel.add(save);
        optionPanel.add(load);
        optionPanel.add(dynamicLoad);
        
        mainPanel = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void paintComponent(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics;
                super.paintComponent(g);
                Stroke oldStroke = g.getStroke();
                int thickness = 3;
                g.setStroke(new BasicStroke(thickness));
                if (!shapes.isEmpty()) {
                    for (Shape sh : shapes) {
                        sh.draw(g);
                    }
                }
                g.setStroke(oldStroke);
                
            }
            
        };
        mainPanel.setBackground(Color.WHITE);
        
        add(shapesPanel, BorderLayout.PAGE_START);
        add(optionPanel, BorderLayout.WEST);
        add(colorChooser, BorderLayout.PAGE_END);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Gets the save load file chooser.
     *
     * @return the save load file chooser
     */
    public JFileChooser getSaveLoadFileChooser() {
        return this.fileChooser;
    }
    
    /**
     * Gets the jar file chooser.
     *
     * @return the jar file chooser
     */
    public JFileChooser getJarFileChooser() {
        return this.jarFileChooser;
    }
    
    /**
     * Gets the options array.
     *
     * @return the options array
     */
    public JButton[] getOptionsArray() {
        return optionsArray;
    }
    
    /**
     * Gets the color chooser.
     *
     * @return the color chooser
     */
    public JColorChooser getColorChooser() {
        return colorChooser;
    }
    
    /**
     * Gets the main panel.
     *
     * @return the main panel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    /** The shapes. */
    private ArrayList<Shape> shapes;
    
    /**
     * Sets the shapes.
     *
     * @param shapes
     *            the new shapes
     */
    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
    
    /**
     * Gets the shapes.
     *
     * @return the shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    /** The current color. */
    private Color currentColor;
    
    /**
     * Sets the current color.
     *
     * @param currentColor
     *            the new current color
     */
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
    
    /**
     * Gets the current color.
     *
     * @return the current color
     */
    public Color getCurrentColor() {
        return currentColor;
    }
    
    /**
     * Gets the button array.
     *
     * @return the button array
     */
    public JButton[] getButtonArray() {
        return buttonArray;
    }
    
}