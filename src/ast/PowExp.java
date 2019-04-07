package ast;

import visitor.ExpVisitor;

public class PowExp extends OpExp{

	public PowExp(Exp left, Exp right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String myOp() {
		return "^";
	}
	
	@Override
	public void accept(ExpVisitor v) {
		v.visit(this);
	}
}
