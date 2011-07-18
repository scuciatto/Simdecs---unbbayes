package unbbayes.prs.bn.cpt.impl;

import java.util.Locale;
import java.util.ResourceBundle;

import unbbayes.prs.bn.ProbabilisticTable;
import unbbayes.prs.bn.cpt.ITableFunction;
import unbbayes.util.ResourceController;

public class FillUniformTableFunction implements ITableFunction {
	
	private ResourceBundle resource;
	
	public FillUniformTableFunction() {
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
		float[] totalSum = new float[table.tableSize()];
		int[] nEmptyStates = new int[table.tableSize()];
		float value;
		int[] coord = new int[table.variableCount()];
		for (int i = 0; i < table.tableSize(); i++) {
			value = table.getValue(i);
			coord = table.getMultidimensionalCoord(i);
			coord[0] = 0;
			totalSum[table.getLinearCoord(coord)] += value;
			if (value == 0f) {
				nEmptyStates[table.getLinearCoord(coord)] ++;
			}
		}
		
		for (int i = 0; i < table.tableSize(); i++) {
			coord = table.getMultidimensionalCoord(i);
			coord[0] = 0;
			if (table.getValue(i) == 0f) {
				value = (1 - totalSum[table.getLinearCoord(coord)]) / nEmptyStates[table.getLinearCoord(coord)];
				table.setValue(i, value);
			}
		}
	}

	public String getFunctionName() {
		return resource.getString("fillUniformFunction");
	}
	
	public String toString() {
		return getFunctionName();
	}
	
	public int compareTo(ITableFunction o) {
		return this.toString().compareTo(o.toString());
	}

}
