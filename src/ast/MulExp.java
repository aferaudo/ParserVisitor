package ast;

import visitor.ExpVisitor;

public class MulExp extends OpExp{

	public MulExp(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public String myOp() {
		return "*";
	}

	@Override
	public void accept(ExpVisitor v) {
		v.visit(this);
	}
	
}
