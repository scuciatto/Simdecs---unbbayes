package unbbayes.controller;

import java.awt.Component;
import java.awt.Cursor;
import java.util.ResourceBundle;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.swing.JFrame;

/**
 * Controller for Java Helper
 * 
 * @author Laecio
 */
public class JavaHelperController {

	private static JavaHelperController singleton; 
	
	private ResourceBundle resource = 
		unbbayes.util.ResourceController.newInstance().getBundle(unbbayes.controller.resources.ControllerResources.class.getName());
	
	public static JavaHelperController getInstance(){
		if(singleton == null){
			singleton = new JavaHelperController(); 
		}
		
		return singleton; 
	}
	
	/**
	 * Open a help using the JavaHelp System.
	 * 
	 * @param component Component father of the JavaHelp Component
	 */
	public void openHelp(Component component) throws Exception
	{	 component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		String className = component.getClass().getName();
		HelpSet set = null;
		if (className.equals("unbbayes.datamining.gui.InvokerMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/DataMiningHelp/Data_Mining.hs"));
		}
		else if (className.equals("unbbayes.datamining.gui.id3.DecisionTreeMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/DataMiningHelp/Decision_Tree.hs"));
		}
				else if (className.equals("unbbayes.datamining.gui.c45.DecisionTreeMain"))
				{	 set = new HelpSet(null, getClass().getResource("/help/C45Help/C45.hs"));
				}
				else if (className.equals("unbbayes.datamining.gui.evaluation.EvaluationMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/DataMiningHelp/Evaluation.hs"));
		}
		else if (className.equals("unbbayes.datamining.gui.naivebayes.NaiveBayesMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/DataMiningHelp/Naive_Bayes.hs"));
		}
		else if (className.equals("unbbayes.datamining.gui.preprocessor.PreprocessorMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/DataMiningHelp/Preprocessor.hs"));
		}
		else if (className.equals("unbbayes.datamining.gui.neuralmodel.NeuralModelMain"))
		{	 set = new HelpSet(null, getClass().getResource("/help/CNMHelp/cnm.hs"));
		}
				else if (className.equals("unbbayes.datamining.gui.neuralnetwork.NeuralNetworkMain"))
				{	 set = new HelpSet(null, getClass().getResource("/help/BpnHelp/BpnHelp.hs"));
				}
				else if (className.equals("unbbayes.gui.UnBBayesFrame"))
				{	 set = new HelpSet(null, getClass().getResource("/help/JUnBBayes.hs"));
				}
		else
		{
					component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					throw new Exception("HelpSet not found "+this.getClass().getName());
		}

		JHelp help = new JHelp(set);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setContentPane(help);
		f.pack();
		f.setLocationRelativeTo(component); 
		f.setTitle(resource.getString("helperDialogTitle"));
		f.setVisible(true);
		
		component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}	
	
}
