package draw;

import java.io.File;


/**
 * The Class ExtensionFileFilter.
 */
public class ExtensionFileFilter extends javax.swing.filechooser.FileFilter {

	/** The extensions. */
	private java.util.List<String> extensions;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new extension file filter.
	 *
	 * @param exts the exts
	 * @param desc the desc
	 */
	public ExtensionFileFilter(String[] exts, String desc) {
		if (exts != null) {
			extensions = new java.util.ArrayList<String>();

			for (String ext : exts) {
				extensions.add(ext.replace(".", "").trim().toLowerCase());
			}
		}
		description = (desc != null) ? desc.trim() : "Custom File List";
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		if (extensions == null) {
			return false;
		}
		for (String ext : extensions) {
			if (f.getName().toLowerCase().endsWith("." + ext))
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

}
