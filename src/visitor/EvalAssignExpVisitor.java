package visitor;

import java.util.HashMap;
import java.util.Map;

import ast.AssignExp;
import ast.LIdentExp;
import ast.RIdentExp;
import ast.SeqExp;

/*
 * Classe dedicata alla creazione dell'environment per l'assegnamento di varibili.
 * Approccio: assegnamento distruttivo
 * */
public class EvalAssignExpVisitor extends EvalExpVisitor implements ExpAssignVisitor, ExpSeqAssignVisitor{

	private Map<String, Integer> environment = new HashMap<String,Integer>();
	
	
	
	@Override
	public void visit(AssignExp e) {
		e.getLeft().accept(this);
		String left = ((LIdentExp)e.getLeft()).getName();
		
		e.getRight().accept(this);
		int right = (int)(getEvaluation());
		
		environment.put(left, right); //inserisco la variabile all'interno dell'environment
		
		System.out.println("Visitor valuation: " + environment.toString());
	}

	@Override
	public void visit(LIdentExp e) {
		result = e.getName();
	}

	@Override
	public void visit(RIdentExp e) {
		String id = e.getName();
		
		Integer value = environment.get(id);
		
		if(value == null) 
			throw new RuntimeException("invalid identifier");
		else
			result = value;
	}

	@Override
	public void visit(SeqExp e) {
		e.getLeft().accept(this); // va solo valutato per produrre i side effect
		
		e.getRight().accept(this);
		result = getEvaluation();
	}

}
