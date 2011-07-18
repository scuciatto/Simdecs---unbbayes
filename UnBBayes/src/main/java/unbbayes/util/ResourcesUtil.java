package unbbayes.util;

public class ResourcesUtil {

	public static String getMensagemForArguments(String mensagem, String... args){
		
		String[] arguments = args; 
		
		for(int i = 0; i < arguments.length; i++){
			String target = "$"; 
			target+=i; 
			mensagem = mensagem.replace(target, arguments[i]); 
		}
		
		return mensagem; 
		
	}
	
	public static void main(String... args){
		String mensagem = "A variabel $0 precisa ser substituida pelo valor de $1."; 
		System.out.println(getMensagemForArguments(mensagem, "OV1", "OV2"));
	}
	
}
