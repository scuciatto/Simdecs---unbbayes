package unbbayes.util;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import unbbayes.prs.Node;

public class SortUtil {

	/**
	 * Responsible for ordering the given node list by the node's description.
	 * 
	 * @param nodeList
	 *            The node list to be ordered.
	 */
	public static void sortNodeListByDescription(List<Node> nodeList) {
		boolean haTroca = true;
		while (haTroca) {
			haTroca = false;
			for (int i = 0; i < nodeList.size() - 1; i++) {
				Node node1 = nodeList.get(i);
				Node node2 = nodeList.get(i + 1);
				if (node1.getDescription().compareToIgnoreCase(
						node2.getDescription()) > 0) {
					nodeList.set(i + 1, node1);
					nodeList.set(i, node2);
					haTroca = true;
				}
			}
		}
	}
	
	/**
	 * Responsible for ordering the given node list by the node's name.
	 * 
	 * @param nodeList
	 *            The node list to be ordered.
	 */
	public static void sortNodeListByName(List<Node> nodeList) {
		boolean haTroca = true;
		while (haTroca) {
			haTroca = false;
			for (int i = 0; i < nodeList.size() - 1; i++) {
				Node node1 = nodeList.get(i);
				Node node2 = nodeList.get(i + 1);
				if (node1.getName().compareToIgnoreCase(
						node2.getName()) > 0) {
					nodeList.set(i + 1, node1);
					nodeList.set(i, node2);
					haTroca = true;
				}
			}
		}
	}

	public static <T extends Comparable<? super T>> void sort(List<T> list) {
		Object a[] = list.toArray();
		Arrays.sort(a);
		ListIterator<T> i = list.listIterator();
		for (int j = 0; j < a.length; j++) {
			i.next();
			i.set((T) a[j]);
		}
	}

}
