package visitor;

import ast.*;
import javafx.scene.control.TreeItem;

/**
 * Questo visitor creerà in base all'Exp che andrà a visitare un albero
 * che rappresenta l'ast generato
 * @author angeloferaudo
 *
 */
public class TreeExpVisitor implements ExpAssignVisitor, ExpSeqAssignVisitor{
	
	TreeItem <String> result = new TreeItem<String>(); 
	
	public TreeItem<String> getResult(){
		return result;
	}
	
	public void visitOpExp(OpExp e) {
	
			TreeItem<String> temporary = new TreeItem<String>(e.myOp());
			
			e.getLeft().accept(this);
			temporary.getChildren().add(result);
			result = new TreeItem<String>();
			
			e.getRight().accept(this);
			temporary.getChildren().add(result);
			
			result = temporary;
			
	}
	
	@Override
	public void visit(PlusExp e) {
		visitOpExp(e);
			
	}

	@Override
	public void visit(MinusExp e) {
		visitOpExp(e);
	}

	@Override
	public void visit(MulExp e) {
		visitOpExp(e);
	}

	@Override
	public void visit(DivExp e) {
		visitOpExp(e);
	}

	@Override
	public void visit(NumExp e) {
		result.setValue(e.toString());
	}

	@Override
	public void visit(PowExp e) {
		visitOpExp(e);
	}

	@Override
	public void visit(AssignExp e) {
		visitOpExp(e);
	}

	@Override
	public void visit(LIdentExp e) {
		result.setValue(e.getName());
	}

	@Override
	public void visit(RIdentExp e) {
		result.setValue(e.getName());
	}

	@Override
	public void visit(SeqExp e) {
		visitOpExp(e);
	}

}
