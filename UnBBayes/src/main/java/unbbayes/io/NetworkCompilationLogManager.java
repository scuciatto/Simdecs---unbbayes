package unbbayes.io;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import unbbayes.io.log.TextLogManager;
import unbbayes.prs.Node;
import unbbayes.prs.bn.Clique;
import unbbayes.prs.bn.JunctionTree;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.Separator;

/**
 * Extends the LogManager with methods for log the information about the 
 * network compilation process.  
 * 
 * @author Rommel N. Carvalho
 * @author Michael S. Onishi
 * @version 1.0
 */

public class NetworkCompilationLogManager extends TextLogManager{

	private static final long serialVersionUID = 4774442720083112613L;

	/**
     *  Initializes the logfile of network compilation. Writes the header inside the file.
     */
    public void reset() {
    	clear();
//        append(resource.getString("logHeader"));
    }
	
    public void finishLog(JunctionTree tree, ArrayList<Node> nodes) {
    	if (!this.isEnabled()) {
    		return;
    	}
        List clicks = tree.getCliques();
        Clique auxClique;
        Node node;
        PotentialTable auxTab;
        Separator auxSep;

        DecimalFormat df = new DecimalFormat();
        append(resource.getString("cliqueHeader"));

        int sizeclicks1 = clicks.size();
        for (int c = 0; c < sizeclicks1; c++) {
            auxClique = (Clique) clicks.get(c);

            int sizenodes1 = auxClique.getNodes().size();
            append(resource.getString("cliqueName") + c + "\n");
            for (int c2 = 0; c2 < sizenodes1; c2++) {
                append((auxClique.getNodes().get(c2)).getName() + "-");
            }

            append(resource.getString("potentialTableName"));
            auxTab = auxClique.getProbabilityFunction();
            int sizeDados = auxTab.tableSize();
            for (int c2 = 0; c2 < sizeDados; c2++) {
                append(df.format(auxTab.getValue(c2)) + " ");
            }

            append(resource.getString("utilityTableName"));
            auxTab = auxClique.getUtilityTable();
            sizeDados = auxTab.tableSize();
            for (int c2 = 0; c2 < sizeDados; c2++) {
                append(df.format(auxTab.getValue(c2)) + " ");
            }
            append("\n\n");
        }

        append(resource.getString("separatorHeader"));

        int sizeseparators = tree.getSeparatorsSize();
        for (int c = 0; c < sizeseparators; c++) {
            auxSep = tree.getSeparatorAt(c);
            append(resource.getString("separatorName") + c + " ");
            append(resource.getString("betweenName") + clicks.indexOf(auxSep.getClique1()) + 
            		resource.getString("andName") + clicks.indexOf(auxSep.getClique2()) + "\n");
            append(resource.getString("nodeName"));
            int sizenodes2 = auxSep.getNodes().size();
            for (int c2 = 0; c2 < sizenodes2; c2++) {
                node = (Node) auxSep.getNodes().get(c2);
                append(node.getName() + "-");
            }
            append("\n");
            auxTab = auxSep.getProbabilityFunction();
            if (auxTab != null) {
                int sizeDados = auxTab.tableSize();
                for (int c2 = 0; c2 < sizeDados; c2++) {
                    append(df.format(auxTab.getValue(c2)) + " ");
                }
                append("\n\n");
            }
        }

        append(resource.getString("potentialAssociatedHeader"));
        int sizenodes3 = nodes.size();
        for (int c = 0; c < sizenodes3; c++) {
            node = (Node) nodes.get(c);
            int sizeclicks = clicks.size();
            for (int c2 = 0; c2 < sizeclicks; c2++) {
                auxClique = (Clique) clicks.get(c2);
                if (auxClique.getAssociatedProbabilisticNodes().contains(node) || auxClique.getAssociatedUtilityNodes().contains(node)) {
                    append(resource.getString("nodeName") + node.getName() + 
                    resource.getString("cliqueLabel") + c2 + "\n");
                    break;
                }
            }
        }
    }
	
}
