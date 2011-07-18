/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
 *
 *  This file is part of UnBBayes.
 *
 *  UnBBayes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnBBayes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnBBayes.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package unbbayes.prs.hybridbn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ResourceBundle;

import unbbayes.prs.Node;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.ResourceController;

// FIXME We have to refactor the Node inheritance to separate discrete from continuous and other messy things!
// FIXME GATO no continuous node
/**
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class ContinuousNode extends TreeVariable implements Serializable {
	
	public final static int MEAN_MARGINAL_INDEX = 0;
	public final static int VARIANCE_MARGINAL_INDEX = 1;
    
	//by young2
	private static Color staticColor = Color.GREEN;	
	
	private static final long serialVersionUID = 1L;
	
	private CNNormalDistribution cnNormalDistribution;
	
	private ResourceBundle resource = ResourceController.newInstance().getBundle(
			unbbayes.prs.hybridbn.resources.HybridBnResources.class.getName());
	
	// Please, avoid using @Override annotation, since it makes interface extraction (refactor) very difficult,
	// because it supposes a inherited method is declared always inside a class, 
	// and fails if it becomes declared inside an interface.
//	@Override
	public int getType() {
		return Node.CONTINUOUS_NODE_TYPE;
	}
	
	public static Color getStaticColor(){
		return staticColor;
	}
	
	public ContinuousNode() {
		cnNormalDistribution = new CNNormalDistribution(this);
		this.appendState(resource.getString("meanName"));
		this.appendState(resource.getString("varianceName"));
		
		//by young2
		setColor(staticColor);
	}
	
	public CNNormalDistribution getCnNormalDistribution() {
		return cnNormalDistribution;
	}
	
	@Override
	protected void marginal() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addParent(Node parent) throws InvalidParentException {
		if (parent.getType() != Node.PROBABILISTIC_NODE_TYPE && parent.getType() != Node.CONTINUOUS_NODE_TYPE) {
			throw new InvalidParentException(resource.getString("continuousNodeInvalidParentException"));
		}
		super.addParent(parent);
		cnNormalDistribution.refreshParents();
	}
	
	@Override
	public void removeParent(Node parent) {
		super.removeParent(parent);
		cnNormalDistribution.refreshParents();
	}
	
	@Override
	public void addChild(Node child) throws InvalidParentException {
		if (child.getType() != Node.CONTINUOUS_NODE_TYPE) {
			throw new InvalidParentException(resource.getString("continuousNodeInvalidParentException"));
		}
		super.addChild(child);
		cnNormalDistribution.refreshParents();
	}
	
	@Override
	public void removeChild(Node child) {
		// TODO Auto-generated method stub
		super.removeChild(child);
		cnNormalDistribution.refreshParents();
	}

}
