/**
 * 
 */
package unbbayes.io;

import java.util.ArrayList;
import java.util.List;

import unbbayes.util.extension.UnBBayesModule;

/**
 * This is just a {@link FileExtensionIODelegator} with some customization
 * (names, content of delegators and its descriptions)
 * @author Shou Matsumoto
 *
 */
public class OwnerAwareFileExtensionIODelegator extends FileExtensionIODelegator {


	private UnBBayesModule owner;
	
	/**
	 * Default constructor
	 * @param the {@link UnBBayesModule} owning this IO class. This is used generally
	 * in order to extract module names.
	 */
	public OwnerAwareFileExtensionIODelegator(UnBBayesModule module) {
		super();
		this.owner = module;
		// customizing the content of the IO
		List<BaseIO> delegators = new ArrayList<BaseIO>();
		delegators.add(new NetIO());
		delegators.add(new XMLBIFIO());
		this.setDelegators(delegators);
	}
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.FileExtensionIODelegator#getName()
	 */
	public String getName() {
		return this.owner.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.FileExtensionIODelegator#getSupportedFilesDescription(boolean)
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly) {
		String ret = this.getName() + " - " + super.getSupportedFilesDescription(isLoadOnly);
		return ret;
	}

}
