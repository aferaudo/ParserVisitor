package visitor;

import ast.DivExp;
import ast.MinusExp;
import ast.MulExp;
import ast.NumExp;
import ast.OpExp;
import ast.PlusExp;
import ast.PowExp;

public class ParExpVisitor implements ExpVisitor{

	public String curs = "";
	
	public String getResult() {
		return curs;
	};
	
	public void visitOpExp(OpExp e) {
		e.getLeft().accept(this); 
		String sleft = getResult();
		e.getRight().accept(this);
		String sright = getResult();
		
		curs = "( " + sleft + " " + e.myOp() + " " + sright + " )";
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
		curs = e.toString();
	}

	@Override
	public void visit(PowExp e) {
		visitOpExp(e);
	}

}
