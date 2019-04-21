package ast;

import visitor.ExpVisitor;
import visitor.ExpAssignVisitor;

public class AssignExp extends OpExp{

	public AssignExp(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public String myOp() {
		return "=";
	}

	@Override
	public void accept(ExpVisitor v) {
		((ExpAssignVisitor)v).visit(this);
	}

}
