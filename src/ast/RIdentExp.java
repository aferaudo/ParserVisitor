package ast;

import visitor.ExpAssignVisitor;
import visitor.ExpVisitor;

/*
 * Questa classe rappresenta la parte destra di un'operazione di assegnamento
 * nell'albero sintattico
 */

public class RIdentExp extends Exp {

	private String name;
	private int value;

	public RIdentExp(String name, int value) {

		this.name = name;
		this.value = value;
	}

	public RIdentExp(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void accept(ExpVisitor v) {
		((ExpAssignVisitor)v).visit(this);
	}

}
