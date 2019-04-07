package printing;

import java.util.List;

import javafx.scene.control.TreeItem;

/**
 * Questa classe contiene i metodi per la stampa di alberi TreeItem in tre differenti modi
 * preorder
 * postorder
 * inorder
 *  --> Utili per JavaFx e già presenti in Swing
 * @author angeloferaudo
 *
 */

public class ThePrinter {

	/**
	 * Ordinamento preorder:
	 * Prima la radice poi i figli
	 * @param root
	 * @param sb
	 * @param separator
	 */
	public static <T> void preorder(TreeItem<T> root, StringBuilder sb, String separator) {
		
		if(root != null) {
			sb.append(root.getValue());
			
			List<TreeItem <T>> children = root.getChildren();
			if(children != null) { // è possibile che sia un nodo foglia
				for(TreeItem <T> c : children) {
					sb.append(separator);
					preorder(c, sb, separator);
				}
			}
			
		}
		
	}
	
	/**
	 * Ordinamento postorder:
	 * prima i figli poi la radice
	 * @param root
	 * @param sb
	 * @param separator
	 */
	public static <T> void postorder(TreeItem<T> root, StringBuilder sb, String separator) {
		
		if(root!= null) {
			List<TreeItem <T>> children = root.getChildren();
			if(children != null) { // è possibile che sia un nodo foglia
				for(TreeItem <T> c : children) {
					
					postorder(c,sb,separator);
					sb.append(separator);
				
				}
			}
			
			sb.append(root.getValue());
		}
	}
	
	/**
	 * Ordinamento inorder:
	 * figlio sinistro, radice, figlio destro
	 * In questo caso funziona soltanto nel caso di alberi binari!
	 * @param root
	 * @param sb
	 * @param separator
	 */
	public static <T> void inorder(TreeItem<T> root, StringBuilder sb, String separator) {
		
		if(root != null) {
			List<TreeItem <T>> children = root.getChildren();
			
			if(children != null && children.size() > 2)
				throw new IllegalArgumentException();
			
			if(children != null && children.size() > 0)
				inorder(children.get(0), sb, separator);
			
			sb.append(root.getValue() + separator);
			
			if(children != null && children.size() > 1)
				inorder(children.get(1), sb, separator);
		}
	}
}
