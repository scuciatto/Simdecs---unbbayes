package unbbayes.util;

/**
 * This is a special interface used when you want create a way for the user
 * to interate with the algorithm. You should define what the questions that 
 * the algorithm should ask to the user and define a code for each question and 
 * a list of arguments. The interface implementation should implementation a form
 * for make each question. 
 * 
 * @author Laecio Lima dos Santos (laecio@gmail.com)
 */
public interface InterationHelper {

	public Object getValue(int code, Object... arguments);
	
}
