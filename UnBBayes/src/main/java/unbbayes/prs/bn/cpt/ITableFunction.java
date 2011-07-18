package unbbayes.prs.bn.cpt;

import unbbayes.prs.bn.ProbabilisticTable;

public interface ITableFunction extends Comparable<ITableFunction> {
	
	public String getFunctionName();
	
	public void applyFunction(ProbabilisticTable table);

}
