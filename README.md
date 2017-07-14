# **Description:** 
Drawing and painting applications are very popular and have a huge user base.
“Paint” Application is a user-friendly painting application that offers number of features that includes:
- Drawing shapes like Line Segment, Elliptical shapes (Ellipses and Circles) and Polygons (Rectangles and Squares).
- Coloring shapes: either their borders or their inside body.
- Resizing.
- Undo and Redo any instruction so as to make the application more usable.
- Moving and deleting any shape.
- One of the main features is saving user’s drawings in a file and modifying it later. User can choose whether to save their drawings in an XML or a JSON file.
The concept of dynamic class loading is widely spread in computer applications. It is an option that allows the user to extend application features at runtime.
- “Paint” Application provides an option that allows for selecting the class library file to load, on selecting and loading the desired file, the isolated shape is appended to the available list of shapes in the application# 

# More added features:
- Free hand Button: <br>
Allows the user to draw a free hand line.
User can change the free-hand line color either before drawing or after.
User can delete a selected free-hand line.
User can save and load free-hand line together with other implemented shapes.

<a href="https://ibb.co/fjaTPa"><img src="https://preview.ibb.co/df84xv/Screenshot_439.png" alt="Screenshot_439" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>private image upload</a><br />
- Selecting Multiple Shapes: <br>
An added feature is that user can select multiple drawn shapes at a time and they can either: <br>
• Move selected shapes as one object. <br>
• Delete selected shapes. <br>
# “Paint” User Guide: <br>
1- Shapes panel at the top:
Panel containing buttons of Shapes that can be drawn. <br>
Triangle and Line Segment Shapes Buttons are disables by default, to be active User must dynamically load external jars.
2- Options panel at the left:
Panel containing all the options that user can use on shapes drawn. <br>
2- Color chooser Panel at the bottom. <br>
   Cursor changes its shape when resizing, moving and drawing. <br>
<a href="https://ibb.co/cn9xja"><img src="https://preview.ibb.co/mAQEVF/Screenshot_441.png" alt="Screenshot_441" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>private image upload</a><br />

3- User can move objects by selecting the desired shape and drag its center. <br>
4- User can resize shapes by selecting them and dragging the squares positioned at the corners, once the mouse is released the shape is released.<br>
5- To color shapes user chooses the desired color first from the color chooser pane. <br>
<a href="https://ibb.co/dq7BAF"><img src="https://preview.ibb.co/kQN04a/Screenshot_442.png" alt="Screenshot_442" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>private image upload</a><br />

# Paint Design Patterns and Implementation: <br>
1 – MVC pattern: <br>
“MVC Pattern stands for Model-View-Controller Pattern. Model–view– controller (MVC) is a software design pattern for implementing user interfaces on computers. It divides a given software application into three
interconnected parts, so as to separate internal representations of information from the ways that information is presented to or accepted from the user.” <br>
2 – Strategy Design Pattern: <br>
“The strategy pattern (also known as the policy pattern) is a software design pattern that enables an algorithm's behavior to be selected at runtime. The strategy pattern defines a family of algorithms,
encapsulates each algorithm, and makes the algorithms interchangeable within that family.
Strategy lets the algorithm vary independently from clients that use it.”
Strategy Design pattern is used in implementing both Save and Load techniques, since “Paint” Application enables users to save/load both xml and json files. Strategy Pattern helps in having two different implementations depending on user’s desire for one option. <br>
3- Command Design Pattern: <br>
“In object-oriented programming, the command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time. This information includes the method name, the object that owns the method and values for the method parameters.” <br>
Object Oriented Principles: <br>
### 1- Interfaces: <br>
- IShape , ILoad , ISave interfaces defines the contract/ methods used by classes which implements them.<br>
### 2- Abstraction: <br>
- Abstract Shape Class implements IShape, defines the common attributes and methods shared by all shapes which extends Shape Class.<br>
- Abstract Command Class which is extended by History, Save and Load Classes to be Invoked through Memory Invoker.<br>
- Abstract Polygon Class defines common attributes and methods shared by Polygon shapes.<br>
- Abstract OptionEngine Class defines common attributes and methods shared by the core painting classes: Drawing, Coloring, Moving and Resizing Classes.<br>
### 3- Polymorphism: <br>
- Controller Class deals with drawing paint tools through an OptionEngine. <br>
- Controller Class deals with Memory tools through an Invoker instance which Invokes a Command to be executed.
### 4- Inheritance: <br>
- LineSegment, Ellipse, Rectangle, FreeShape Classes inherit their common attributes and methods from Abstract Shape Class.  <br>
- Circle class inherits its attributes and methods from Ellipse Class. <br>
- Square class inherits its attributes and methods from Rectangle Class. <br>
- Drawing, Moving, Resizing, Coloring engine Classes inherit their common attributes and methods from an Abstract OptionEngine Class. <br>
- Memory classes e.g. Save, Load , History classes inherits their common execute method from an abstract Command Class.<br>
### 5- Encapsulation:
Encapsulation principle is preserved in “Paint” application with each Class having private attributes can only be accessed through getters and setters. <br>
## UML Diagram:- 
<a href="https://ibb.co/mDgMAF"><img src="https://preview.ibb.co/dzsiPa/paintUML.gif" alt="paintUML" border="0"></a><br /><a target='_blank' href='https://imgbb.com/'>private image upload</a><br />
