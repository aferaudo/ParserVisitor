package ast;

import visitor.ExpVisitor;

public class NumExp extends Exp{

	private int val;
	
	public NumExp(int val) {
		this.val = val;
	}
	
	public String toString() {
		return "" + val;
	}
	
	public int getValue() {
		return val;
	}

	@Override
	public void accept(ExpVisitor v) {
		v.visit(this);
	}
}
