package visitor;

import ast.*;
import javafx.scene.control.TreeItem;

/**
 * Questo visitor creerà in base all'Exp che andrà a visitare un albero
 * che rappresenta l'ast generato
 * @author angeloferaudo
 *
 */
public class TreeExpVisitor implements ExpVisitor{
	
	TreeItem <String> result = new TreeItem<String>(); 
	
	public TreeItem<String> getResult(){
		return result;
	}
	
	public void visitOpExp(OpExp e) {
	
			TreeItem<String> temporary = new TreeItem<String>(e.myOp());
//			
//			e.getLeft().accept(this);
//			temporary1 = result;
//			temporary1.setValue(result.getValue());
//			
//			e.getRight().accept(this);
//			TreeItem<String> temporary2 = new TreeItem<String>();
//			temporary2 = result;
//			temporary2.setValue(result.getValue());
//			
//			//result.getChildren().add(temporary);
//			result.setValue(e.myOp());
//			result.getChildren().add(temporary1);
//			result.getChildren().add(temporary2);
			/*
			 * Cerca di attaccare i sotto alberi
			 * 
			 * */
			
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

}
