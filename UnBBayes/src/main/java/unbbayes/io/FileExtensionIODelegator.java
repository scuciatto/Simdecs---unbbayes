/**
 * 
 */
package unbbayes.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unbbayes.io.exception.LoadException;
import unbbayes.io.extension.jpf.PluginAwareFileExtensionIODelegator;
import unbbayes.prs.Graph;

/**
 * This class delegates to a specified IO class by comparing the file extensions,
 * using {@link BaseIO#supportsExtension(String)}.
 * @author Shou Matsumoto
 *
 */
public class FileExtensionIODelegator implements BaseIO {

	private List<BaseIO> delegators;
	
	private String name = "Delegator by file extension";
	
	/**
	 * Default constructor is not public. Use {@link #newInstance()} instead.
	 * @deprecated
	 */
	protected FileExtensionIODelegator() {
		this.setDelegators(new ArrayList<BaseIO>());
	}
	
	/**
	 * Constructor method.
	 * @return {@link PluginAwareFileExtensionIODelegator}.
	 */
	public static FileExtensionIODelegator newInstance() {
//		FileExtensionIODelegator ret = PluginAwareFileExtensionIODelegator.newInstance();
		return new FileExtensionIODelegator();
	}
	
	/**
	 * Since this class is a delegator to another IO class, there can be more
	 * than one compatible IO for a given file. This method returns all compatible
	 * IO classes for a given file.
	 * @param file
	 * @param use true if it must return the IO classes for loading a file. Use "false" if it
	 * is for saving a file.
	 * @return
	 */
	public List<BaseIO> getCompatibleIOs(File file, boolean isLoadOnly) {
		// find all compatible IO classes
		List<BaseIO> compatibleIOs = new ArrayList<BaseIO>();
		
		for (BaseIO io : this.getDelegators()) {
			if (io.supports(file, isLoadOnly)) {
				compatibleIOs.add(io);
			}
		}
		
		return compatibleIOs;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.BaseIO#load(java.io.File)
	 */
	public Graph load(File input) throws LoadException, IOException {
		if (this.getDelegators() == null || input == null) {
			throw new LoadException();
		}
		
		// find all compatible IO classes
		List<BaseIO> compatibleIOs = this.getCompatibleIOs(input, true);
		
		if (compatibleIOs.size() == 1) {
			// there is only one compatible IO. Use it.
			return compatibleIOs.iterator().next().load(input);
		} else if (compatibleIOs.size() > 1) {
			// there were more than 1 compatible IO. This is unexpected.
			throw new MoreThanOneCompatibleIOException(compatibleIOs);
		}
		
		throw new LoadException();
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.BaseIO#save(java.io.File, unbbayes.prs.Graph)
	 */
	public void save(File output, Graph net) throws IOException {
		if (this.getDelegators() == null || output == null) {
			throw new IOException();
		}
		
		// find all compatible IO classes
		List<BaseIO> compatibleIOs = this.getCompatibleIOs(output, false);
		
		if (compatibleIOs.size() == 1) {
			// there is only one compatible IO. Use it.
			compatibleIOs.iterator().next().save(output, net);
			return;
		} else if (compatibleIOs.size() > 1) {
			// there were more than 1 compatible IO. This is unexpected.
			throw new MoreThanOneCompatibleIOException(compatibleIOs);
		}
		
		// if we reach this code, no storing was actually done.
		throw new IOException();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#supports(java.io.File, boolean)
	 */
	public boolean supports(File file, boolean isLoadOnly) {
		if (this.getDelegators() == null) {
			return false;
		}
		
		for (BaseIO io : this.getDelegators()) {
			if (io.supports(file, isLoadOnly)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @return the delegators
	 */
	public List<BaseIO> getDelegators() {
		return delegators;
	}

	/**
	 * @param delegators the delegators to set
	 */
	public void setDelegators(List<BaseIO> delegators) {
		this.delegators = delegators;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFileExtensions(boolean)
	 */
	public String[] getSupportedFileExtensions(boolean isLoadOnly) {
		ArrayList<String> ret = new ArrayList<String>();
		List<BaseIO> delegators = this.getDelegators();
		if (delegators != null) {
			for (BaseIO io : delegators) {
				String [] delegatorExtensions = io.getSupportedFileExtensions(isLoadOnly);
				if (delegatorExtensions != null) {
					for (String ext : delegatorExtensions) {
						ret.add(ext);
					}
				}
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFilesDescription(boolean)
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly) {
		String ret = new String();
		List<BaseIO> delegators = this.getDelegators();
		if (delegators != null) {
			for (BaseIO io : delegators) {
				String desc = io.getSupportedFilesDescription(isLoadOnly);
				if (desc != null && (desc.trim().length() > 0)) {
					ret += (desc + ", ");
				}
			}
		}
		if (ret.lastIndexOf(", ") < 0) {
			return ret;
		}
		return ret.substring(0, ret.lastIndexOf(", "));
	}
	
	/**
	 * This is an exception thrown when more than one IO class
	 * can be used to delegate.
	 * Callers catching this exception may use {@link #getIOs()} to
	 * get the available IOs and ask the user to select the desired one.
	 * @author Shou Matsumoto
	 *
	 */
	public class MoreThanOneCompatibleIOException extends IOException {
		
		private static final long serialVersionUID = 2794093384529074900L;
		private List<BaseIO> ios;
		
		/**
		 * Basic constructor initializing fields
		 */
		public MoreThanOneCompatibleIOException(List<BaseIO> ios) {
			this("More than one plausible I/O was found.", ios);
		}
		
		/**
		 * Constructor initializing fields and basic message.
		 * @param message
		 * @param ios
		 */
		public MoreThanOneCompatibleIOException(String message, List<BaseIO> ios) {
			this(message, null, ios);
		}
		
		/**
		 * Constructor initializing fields, basic message and its cause (Throwable)
		 * @param message
		 * @param t
		 * @param ios
		 */
		public MoreThanOneCompatibleIOException(String message, Throwable t, List<BaseIO> ios) {
			super(message);
			this.ios = ios;
			if (t != null) {
				this.setStackTrace(t.getStackTrace());
				this.initCause(t);
			}
		}
		
		/**
		 * The available I/O classes plausible for the given extension.
		 * A code catching this exception may use this to build a list
		 * for a user to choose the desired I/O class to use.
		 * @return the ios : a non-null collection.
		 */
		public List<BaseIO> getIOs() {
			if (this.ios == null) {
				this.ios = new ArrayList<BaseIO>();
			}
			return ios;
		}
		
	}
	
	/**
	 * Obtains an array of names from a given list of I/O classes.
	 * The order of the returned array must be equal to the given list of I/O classes.
	 * This method is used by {@link #openFile(File)} in order to fill a list
	 * of I/O component's names, in order to ask users what I/O they prefer to use,
	 * when multiple options are available. 
	 * @param ios
	 * @return array of names.
	 * @see BaseIO#getName()
	 * @see #findIOByName(List)
	 */
	public static String[] getNamesFromIOs(List<BaseIO> ios) {
		String[] ret = new String[ios.size()];
		for (int i = 0; i < ios.size(); i++) {
			ret[i] = ios.get(i).getName();
		}
		return ret;
	}
	
	/**
	 * Obtains the first I/O class having its {@link BaseIO#getName()} equals
	 * to the given parameter.
	 * @param ios
	 * @param name
	 * @return null if not found. Returns an instance of BaseIO if found.
	 * @see BaseIO#getName()
	 * @see #getNamesFromIOs(List)
	 */
	public static BaseIO findIOByName(List<BaseIO> ios, String name) {
		if (name == null) {
			// special case: looking for null name
			for (BaseIO baseIO : ios) {
				if (baseIO.getName() == null) {
					return baseIO;
				}
			}
		} else {
			for (BaseIO baseIO : ios) {
				if (name.equals(baseIO.getName())) {
					return baseIO;
				}
			}
		}
		return null;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
