package unbbayes.io.xmlbif.version6;

public enum EvidenceType {
	
	TRIGGER("Trigger"),
	EXCLUSIVE("Exclusive"),
	NECESSARY("Necessary"),
	COMPLEMENTARY("Complementary"),
	NA("NA");
	
	private String name;
	
	EvidenceType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
