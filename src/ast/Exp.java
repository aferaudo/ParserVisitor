package ast;

import visitor.ExpVisitor;

public abstract class Exp {
	
	public abstract void accept(ExpVisitor v);
}
