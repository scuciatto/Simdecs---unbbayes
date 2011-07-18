/**
 * 
 */
package unbbayes.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author Shou Matsumoto
 * A static holder for application.property file.
 * The default place to look for is a file named "application.property"
 * at classloader path. If not found, it will also search the src/main/resources
 */
public class ApplicationPropertyHolder implements
		Serializable {

	private static final long serialVersionUID = -1670788505984056699L;
	
	private static Properties property;
	private static String applicationPropertyPath = "application.properties";
	
	

	static {
		// initialize property using default values
		property = new Properties();
		try {
			property.load(ApplicationPropertyHolder.class.getClassLoader().getResourceAsStream(applicationPropertyPath));
		} catch (Exception e) {
			try {
				applicationPropertyPath = "src/main/resources/" + applicationPropertyPath;
				property.load(ApplicationPropertyHolder.class.getClassLoader().getResourceAsStream(applicationPropertyPath));
			} catch (Exception e2) {
				e.printStackTrace();
				e2.printStackTrace();
			}
		}
	}
	


	/**
	 * @return the property
	 */
	public static Properties getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public static void setProperty(Properties property) {
		ApplicationPropertyHolder.property = property;
	}

	/**
	 * @return the applicationPropertyPath
	 */
	protected static String getApplicationPropertyPath() {
		return applicationPropertyPath;
	}

	/**
	 * @param applicationPropertyPath the applicationPropertyPath to set
	 */
	protected static void setApplicationPropertyPath(String applicationPropertyPath) {
		ApplicationPropertyHolder.applicationPropertyPath = applicationPropertyPath;
	}
	
	

}
