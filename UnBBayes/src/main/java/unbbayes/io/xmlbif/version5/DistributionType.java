package unbbayes.io.xmlbif.version5;

public enum DistributionType {
	
	CONTINUOUS("Continuous"),
	DISCRETE("Discrete");
	
	private String name;
	
	DistributionType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
