package unbbayes.io.log;

public class IdentationLevel {

	public static int SPACES_FOR_IDENTATION = 4;  
	private static char SEPARATOR = '.'; 
	
	private int number; 
	
	private int level; 
	
	private IdentationLevel father;

	private int childrenQuant = 0; 
	
	public IdentationLevel(IdentationLevel _father){
		this.father = _father;
		if(_father != null){
			this.level = father.getLevel() + 1; 
			this.number = father.getChildrenQuant();
			father.plusChildrenQuant(); 
		}else{
			this.level = 1; 
			this.number = 1; 
		}
	}
	
	protected void plusChildrenQuant(){
		childrenQuant++; 
	}
	
	protected int getChildrenQuant(){
		return childrenQuant; 
	}
	
	public int getNumber() {
		return number;
	}

	public IdentationLevel getFather() {
		return father;
	}

	public int getLevel() {
		return level;
	} 
	
	/**
	 * Identification of this nivel in the format: 
	 * 4.2.2.1 (where . is the SEPARATOR)
	 */
	public String getIdentificationNumber(){
		String identificationNumber = ""; 
		
		if(father != null){
			identificationNumber+= father.getIdentificationNumber() + SEPARATOR; 
		}
		
		identificationNumber+= number; 
		
		return identificationNumber; 
	}
	
	/**
	 * Mount a string for the identation of a line of log of this level. 
	 */
	public String getIdentationSpaces(){
		String spaces = ""; 
		
		for(int i=0; i < level; i++){
			for(int j = 0; j < SPACES_FOR_IDENTATION; j++){
				spaces+= " "; 
			}
		}
		
		return spaces; 
	}
}
