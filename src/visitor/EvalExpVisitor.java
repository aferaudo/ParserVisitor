package visitor;

import ast.DivExp;
import ast.MinusExp;
import ast.MulExp;
import ast.NumExp;
import ast.PlusExp;
import ast.PowExp;

public class EvalExpVisitor implements ExpVisitor{
	
	int value = 0; // Essendo una valuator devo accumulare il risultato di volta in volta

	public int getResult() {
		return value;
	}
	
	@Override
	public void visit(PlusExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = getResult();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = getResult(); // lo conservo
		
		value = v1 + v2;
		
	}

	@Override
	public void visit(MinusExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = getResult();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = getResult(); // lo conservo
		
		value = v1 - v2;
	}
		

	@Override
	public void visit(MulExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = getResult();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = getResult(); // lo conservo
		
		value = v1 * v2;
		
	}

	@Override
	public void visit(DivExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = getResult();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = getResult(); // lo conservo
		
		value = v1 / v2;
		
	}

	@Override
	public void visit(NumExp e) {
		value = e.getValue();
	}

	@Override
	public void visit(PowExp e) {
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = getResult();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = getResult(); // lo conservo
		
		int res;
		for (res=1;v2>0;v2--){
			res = res*v1;
		}
		value = res;
	}

}
