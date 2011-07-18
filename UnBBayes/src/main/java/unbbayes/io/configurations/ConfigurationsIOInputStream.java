package unbbayes.io.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import unbbayes.gui.Configurations;

public class ConfigurationsIOInputStream implements ConfigurationsIO{

	public Configurations load(File file) throws IOException{
		
		FileInputStream fs = new FileInputStream(file); 
		ObjectInputStream obj = new ObjectInputStream(fs);
		Configurations cf = null;
		
		try {
			cf = (Configurations)obj.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		obj.close(); 
		return cf;
	
	}

	public void save(File file, Configurations config) throws IOException {
		FileOutputStream fs = new FileOutputStream(file); 
		ObjectOutputStream obj = new ObjectOutputStream(fs); 
		obj.writeObject(config); 
		obj.close(); 
	}

}
