package visitor;

import ast.DivExp;
import ast.MinusExp;
import ast.MulExp;
import ast.NumExp;
import ast.PlusExp;
import ast.PowExp;

public class EvalExpVisitor implements ExpVisitor{
	
	public Object result; //versione 0.1
	
	public Object getEvaluation() {
		return result;
	}
	
	@Override
	public void visit(PlusExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = (Integer)getEvaluation();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = (Integer)getEvaluation(); // lo conservo
		
		result = v1 + v2;
		
	}

	@Override
	public void visit(MinusExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = (Integer)getEvaluation();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = (Integer)getEvaluation(); // lo conservo
		
		result = v1 - v2;
	}
		

	@Override
	public void visit(MulExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = (Integer)getEvaluation();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = (Integer)getEvaluation(); // lo conservo
		
		result = v1 * v2;
		
	}

	@Override
	public void visit(DivExp e) {
		
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = (Integer)getEvaluation();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = (Integer)getEvaluation(); // lo conservo
		
		result = v1 / v2;
		
	}

	@Override
	public void visit(NumExp e) {
		result = e.getValue();
	}

	@Override
	public void visit(PowExp e) {
		e.getLeft().accept(this); //recupero il valore dell'operando sinistro
		int v1 = (Integer)getEvaluation();	// lo conservo
		
		e.getRight().accept(this); // recupero il valore dell'operando destro
		int v2 = (Integer)getEvaluation(); // lo conservo
		
		int res;
		for (res=1;v2>0;v2--){
			res = res*v1;
		}
		result = res;
	}

}
