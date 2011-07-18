package unbbayes.io.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import unbbayes.gui.Configurations;

public interface ConfigurationsIO {

	public Configurations load(File file) throws IOException;
	
	public void save(File file, Configurations config) throws IOException; 
	
}
