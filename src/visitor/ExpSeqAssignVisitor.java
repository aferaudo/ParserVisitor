package visitor;

import ast.SeqExp;

public interface ExpSeqAssignVisitor extends ExpVisitor{

	public void visit(SeqExp e);
	
}
