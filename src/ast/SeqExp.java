package ast;

import visitor.ExpVisitor;
import visitor.ExpSeqAssignVisitor;

public class SeqExp extends OpExp{

	public SeqExp(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public String myOp() {
		return ",";
	}

	@Override
	public void accept(ExpVisitor v) {
		((ExpSeqAssignVisitor)v).visit(this);
	}

}
