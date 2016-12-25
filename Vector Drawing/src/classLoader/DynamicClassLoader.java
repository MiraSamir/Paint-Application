package classLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * The Class DynamicClassLoader.
 */
public class DynamicClassLoader {

	/** The path. */
	private String path;
	
	/** The loaded class. */
	private Class<?> loadedClass;
	
	/** The type. */
	private String type;

	/**
	 * Instantiates a new dynamic class loader.
	 *
	 * @param path the path
	 * @param type the type
	 */
	public DynamicClassLoader(String path, String type) {
		this.path = path;
		this.loadedClass = null;
		this.type = type;
	}

	/**
	 * Execute.
	 *
	 * @return the class
	 */
	public Class<?> execute() {
		File myJarFile = new File(path);
		URL myJarUrl;
		try {
			myJarUrl = myJarFile.toURI().toURL();
			ClassLoader loader = URLClassLoader.newInstance(new URL[] { myJarUrl });
			loadedClass = loader.loadClass("eg.edu.alexu.csd.oop.draw." + type);

		} catch (Exception e) {
			JLabel label = new JLabel("Please choose a valid jar file");
			JOptionPane.showMessageDialog(null, label);
		}
		return loadedClass;
	}

}
