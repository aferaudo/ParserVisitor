package visitor;

import ast.*;

public interface ExpVisitor {

	public void visit(PlusExp e);
	public void visit(MinusExp e);
	public void visit(MulExp e);
	public void visit(DivExp e);
	public void visit(NumExp e);
	
	public void visit(PowExp e);
	
	
}
