package unbbayes.io.log;

public class SSBNTextLogManager extends TextLogManager 
                         implements ISSBNLogManager{

	private static int numColumns = 80; 
	
	private static char box1BarCharacter = '#';
	private static char box2BarCharacter = '='; 
	private static char box3BarCharacter = '-'; 
	private static char boxWallCharacter = '|';
	private static char separator = '*'; 
	
	public boolean enabled = true; 
	

	
	public void printBox1(String text) {
		super.appendln("" + ' ' + box1BarCharacter + ' ' + text + ' '); 
	}

	public void printBox1Bar() {
         this.printBar(box1BarCharacter); 
	}
	
	public void printBox2(String text) {
		super.appendln("" + ' ' + boxWallCharacter + ' ' + text + ' '); 
	}

	public void printBox2Bar() {
        this.printBar(box2BarCharacter); 
	}

	public void printBox3(IdentationLevel identationNivel, int position,
			String text) {
		super.appendln("" + ' ' + identationNivel.getIdentationSpaces() + boxWallCharacter + ' ' + text); 
	}

	public void printBox3Bar(IdentationLevel identationNivel) {
		super.append("" + ' ' + identationNivel.getIdentationSpaces()); 
        this.printBar(box3BarCharacter); 
	}

	public void printSectionSeparation() {
		this.printBar(separator); 
	}

	public void printText(IdentationLevel identationNivel, boolean printNumber,
			String text) {
		
		StringBuilder sb = new StringBuilder();
		
		if(identationNivel != null){
			sb.append(identationNivel.getIdentationSpaces()); 
			if(printNumber){
				sb.append(identationNivel.getIdentificationNumber()); 
			}
		}
		
		sb.append(' ' + text); 
		
		super.appendln("" + sb.toString() ); 
		
	}
	
	private void printBar(char character){
		StringBuilder bar = new StringBuilder(); 
		for(int i = 0; i < numColumns; i++){
			bar.append(character); 
		}
		super.appendln("" + ' ' + bar.toString() + ' '); 
	}

	public void skipLine() {
		super.appendln(""); 
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
