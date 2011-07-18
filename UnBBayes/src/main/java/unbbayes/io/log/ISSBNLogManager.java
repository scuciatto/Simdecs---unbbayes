package unbbayes.io.log;

public interface ISSBNLogManager extends ILogManager{

	public void printBox1Bar(); 
	public void printBox1(String text); 

	public void printBox2Bar(); 
	public void printBox2(String text); 
	
	public void printBox3Bar(IdentationLevel identationNivel); 
	public void printBox3(IdentationLevel identationNivel, int position, String text); 
	
	public void printSectionSeparation(); 

	public void printText(IdentationLevel identationNivel, boolean printNumber, String text); 
	
	public void skipLine(); 
	
}
