package draw;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


/**
 * The Class FileChooserHandler.
 */
public class FileChooserHandler {

	/** The save load file chooser. */
	private JFileChooser saveLoadFileChooser;
	
	/** The jar file chooser. */
	private JFileChooser jarFileChooser;
	
	/** The returned result. */
	private ArrayList<String> returnedResult;

	/**
	 * Instantiates a new file chooser handler.
	 *
	 * @param saveLoadFileChooser the save load file chooser
	 * @param jarFileChooser the jar file chooser
	 */
	public FileChooserHandler(JFileChooser saveLoadFileChooser, JFileChooser jarFileChooser) {
		this.saveLoadFileChooser = saveLoadFileChooser;
		this.jarFileChooser = jarFileChooser;
		this.returnedResult = new ArrayList<String>();

	}

	/**
	 * Show file chooser.
	 *
	 * @param x the x
	 * @return the array list
	 */
	@SuppressWarnings("static-access")
	public ArrayList<String> showFileChooser(int x) {
		if (x == 1) {
			saveLoadFileChooser.setApproveButtonText("Save");
			saveLoadFileChooser.setApproveButtonToolTipText("Click to save!");
			int result = saveLoadFileChooser.showOpenDialog(null);

			if (result == saveLoadFileChooser.APPROVE_OPTION) {
				File file = saveLoadFileChooser.getSelectedFile();
				String path = file.getAbsolutePath();
				
				FileFilter fileFilter = saveLoadFileChooser.getFileFilter();
				String s = fileFilter.getDescription();
				String extension = s.substring(s.lastIndexOf("*") + 1, s.lastIndexOf(")"));

				if (!path.endsWith(extension)) {
					path = path + extension;
				}
				if (extension.equals(".xml")) {
					returnedResult.add("saveXML");
					returnedResult.add(path);
				} else if (extension.equals(".json")) {
					returnedResult.add("saveJSON");
					returnedResult.add(path);
				}
				return returnedResult;
			} else {
				return null;
			}

		} else if (x == 0) {
			saveLoadFileChooser.setApproveButtonText("Load");
			saveLoadFileChooser.setApproveButtonToolTipText("Click to load!");
			int result = saveLoadFileChooser.showOpenDialog(null);
			if (result == saveLoadFileChooser.APPROVE_OPTION) {
				File file = saveLoadFileChooser.getSelectedFile();
				String path = file.getAbsolutePath();

				if (path.endsWith(".xml")) {
					returnedResult.add("loadXML");
					returnedResult.add(path);
				} else if (path.endsWith(".json")) {
					returnedResult.add("loadJSON");
					returnedResult.add(path);
				}

				return returnedResult;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * Gets the dynamic jar path.
	 *
	 * @return the dynamic jar path
	 */
	@SuppressWarnings("static-access")
	public String getDynamicJarPath() {

		jarFileChooser.setApproveButtonText("Load");
		jarFileChooser.setApproveButtonToolTipText("Load the jar 😉!");
		int result = jarFileChooser.showOpenDialog(null);

		if (result == jarFileChooser.APPROVE_OPTION) {
			File file = jarFileChooser.getSelectedFile();
			return file.getPath();
		} else {
			return null;
		}

	}

}
