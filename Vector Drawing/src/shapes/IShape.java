package shapes;

import java.awt.Point;
import java.util.ArrayList;


/**
 * The Interface IShape.
 */
public interface IShape {

	/**
	 * Gets the center.
	 *
	 * @return the center
	 */
	public java.awt.Point getCenter();

	/**
	 * Sets the border color.
	 *
	 * @param color the new border color
	 */
	public void setBorderColor(java.awt.Color color);

	/**
	 * Gets the border color.
	 *
	 * @return the border color
	 */
	public java.awt.Color getBorderColor();

	/**
	 * Sets the fill color.
	 *
	 * @param color the new fill color
	 */
	public void setFillColor(java.awt.Color color);

	/**
	 * Gets the fill color.
	 *
	 * @return the fill color
	 */
	public java.awt.Color getFillColor();

	/**
	 * Draw.
	 *
	 * @param canvas the canvas
	 */
	public void draw(java.awt.Graphics2D canvas);

	/**
	 * Gets the shape attributes.
	 *
	 * @return the shape attributes
	 */
	public ArrayList<Shape> getShapeAttributes();

	/**
	 * Sets the checks if is selected.
	 *
	 * @param isSelected the new checks if is selected
	 */
	public void setIsSelected(Boolean isSelected);

	/**
	 * Gets the checks if is selected.
	 *
	 * @return the checks if is selected
	 */
	public Boolean getIsSelected();

	/**
	 * Contains.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public boolean contains(java.awt.Point p);

	/**
	 * Checks if is on border.
	 *
	 * @param p the p
	 * @return true, if is on border
	 */
	public boolean isOnBorder (java.awt.Point p);

	/**
	 * Checks if is on line.
	 *
	 * @param p the p
	 * @param p1 the p1
	 * @param p2 the p2
	 * @return true, if is on line
	 */
	public boolean isOnLine (java.awt.Point p ,java.awt.Point p1,java.awt.Point p2);

	/**
	 * Update drawing.
	 *
	 * @param fixed the fixed
	 * @param current the current
	 */
	public void updateDrawing(java.awt.Point fixed, java.awt.Point current);

	/**
	 * Moving shape.
	 *
	 * @param center the center
	 */
	public void movingShape(java.awt.Point center);

	/**
	 * Resizing shape.
	 *
	 * @param dragged the dragged
	 * @param index the index
	 */
	public void resizingShape(java.awt.Point dragged, int index);

	/**
	 * Gets the fixed corner.
	 *
	 * @param corner the corner
	 * @return the fixed corner
	 */
	public void getFixedCorner(Integer corner);

	/**
	 * Distance.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	public Double distance(Point a, Point b);

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType();

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public Double getLength();

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public Double getWidth();

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(Double width);

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(Double length);

	/**
	 * Gets the points array.
	 *
	 * @return the points array
	 */
	public ArrayList<Point> getPointsArray();

	/**
	 * Sets the points array.
	 *
	 * @param pointsArray the new points array
	 */
	public void setPointsArray(ArrayList<Point> pointsArray);

	/**
	 * Clone.
	 *
	 * @return the shape
	 */
	public Shape clone() ;

}
