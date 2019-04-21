package ast;

import visitor.ExpAssignVisitor;
import visitor.ExpVisitor;

/*
 * Questa classe rappresenta la parte sinistra di un'operazione di assegnamento
 * nell'albero sintattico
 * */
public class LIdentExp extends Exp{

	private String name;
	
	public LIdentExp(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(ExpVisitor v) {
		((ExpAssignVisitor)v).visit(this);
	}

	
}
