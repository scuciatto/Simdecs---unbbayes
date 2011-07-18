package unbbayes.prs.bn.cpt.impl;

import java.util.Locale;
import java.util.ResourceBundle;

import unbbayes.prs.bn.ProbabilisticTable;
import unbbayes.prs.bn.cpt.ITableFunction;
import unbbayes.util.ResourceController;

public class UniformTableFunction implements ITableFunction {
	
	private ResourceBundle resource;
	
	public UniformTableFunction() {
		try {
			this.resource = ResourceController.newInstance().getBundle(
					unbbayes.prs.bn.cpt.impl.resources.Resources.class.getName(),
					Locale.getDefault(),
					this.getClass().getClassLoader());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void applyFunction(ProbabilisticTable table) {
		int nStates = table.getVariableAt(0).getStatesSize();
		float value = 1f / nStates;
		for (int i = 0; i < table.tableSize(); i++) {
			table.setValue(i, value);
		}
	}

	public String getFunctionName() {
		return resource.getString("uniformFunction");
	}
	
	public String toString() {
		return getFunctionName();
	}
	
	public int compareTo(ITableFunction o) {
		return this.toString().compareTo(o.toString());
	}

}
