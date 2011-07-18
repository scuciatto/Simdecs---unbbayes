
package unbbayes.util;

import java.util.Collection;
import java.util.HashSet;

import org.apache.xalan.processor.TransformerFactoryImpl;

/**
 * This class extends the apache Xalan's {@link TransformerFactoryImpl} in order to stop
 * throwing an exception when it finds the "indent-number" argument.
 * In order to apply this patch, you must set the "javax.xml.transform.TransformerFactory"
 * system property to this class. 
 * E.g. by using the command line argument -Djavax.xml.transform.TransformerFactory=unbbayes.util.XalanIndentNumberBugFixer
 * or by calling System.setProperty("javax.xml.transform.TransformerFactory", "unbbayes.util.XalanIndentNumberBugFixer");
 * 
 * @author Shou Matsumoto
 */
public class XalanIndentNumberBugFixer extends TransformerFactoryImpl {
	private Collection<String> ignoredArguments = new HashSet<String>();

	/**
	 * The default constructor must be public, so that Xalan can correctly instantiate it
	 * using reflection.
	 * It initializes {@link #getIgnoredArguments()} as "indent-number"
	 */
	public XalanIndentNumberBugFixer() {
		super();
		this.ignoredArguments = new HashSet<String>();
		this.ignoredArguments.add("indent-number");
	}

	/* (non-Javadoc)
	 * @see org.apache.xalan.processor.TransformerFactoryImpl#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String name, Object value)
			throws IllegalArgumentException {
		// verify if name is in the list of ignored argument names
		if (this.getIgnoredArguments() != null) {
			for (String ignoredName : this.getIgnoredArguments()) {
				if (ignoredName == null) {
					if (name == null) {
						return;	// both name and ignore name was null
					}
				} else if (ignoredName.equals(name)) {
					return;	// ignoredName was not null and it was equal to name.
				}
			}
		}
		// delegate to super if name was not supposed to be ignored
		super.setAttribute(name, value);
	}

	/**
	 * Arguments with these names will be ignored in {@link #setAttribute(String, Object)}
	 * @return the ignoredArguments
	 */
	public Collection<String> getIgnoredArguments() {
		return ignoredArguments;
	}

	/**
	 * Arguments with these names will be ignored in {@link #setAttribute(String, Object)}
	 * @param ignoredArguments the ignoredArguments to set
	 */
	public void setIgnoredArguments(Collection<String> ignoredArguments) {
		this.ignoredArguments = ignoredArguments;
	}

	
}
