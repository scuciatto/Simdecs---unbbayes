/**
 * 
 */
package unbbayes.simdecs;

import java.io.Serializable;

/**
 * @author scuciatto
 *
 */
public class PerguntaNodo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pergunta;
	private String resposta0;
	private String resposta25;
	private String resposta50;
	private String resposta100;
	
	public PerguntaNodo(String pergunta, String resposta0, String resposta25,
			String resposta50, String resposta100) {
		
		this.pergunta = pergunta;
		this.resposta0 = resposta0;
		this.resposta25 = resposta25;
		this.resposta50 = resposta50;
		this.resposta100 = resposta100;
	}
	
	public PerguntaNodo() {
		
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta0() {
		return resposta0;
	}

	public void setResposta0(String resposta0) {
		this.resposta0 = resposta0;
	}

	public String getResposta25() {
		return resposta25;
	}

	public void setResposta25(String resposta25) {
		this.resposta25 = resposta25;
	}

	public String getResposta50() {
		return resposta50;
	}

	public void setResposta50(String resposta50) {
		this.resposta50 = resposta50;
	}

	public String getResposta100() {
		return resposta100;
	}

	public void setResposta100(String resposta100) {
		this.resposta100 = resposta100;
	}
}