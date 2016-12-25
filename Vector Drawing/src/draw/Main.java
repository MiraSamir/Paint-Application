package draw;

import javax.swing.JFrame;


/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.getPaintgui().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.getPaintgui().setSize(1370, 770);
		controller.getPaintgui().setTitle("Paint");
		controller.getPaintgui().setVisible(true);

	}

}
