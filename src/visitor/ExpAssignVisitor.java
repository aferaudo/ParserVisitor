package visitor;

import ast.AssignExp;
import ast.LIdentExp;
import ast.RIdentExp;

public interface ExpAssignVisitor extends ExpVisitor{

	public void visit(AssignExp e);
	public void visit(LIdentExp e);
	public void visit(RIdentExp e);
	
}
