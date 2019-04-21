package test;

import ast.*;

import printing.ThePrinter;
import visitor.EvalAssignExpVisitor;
import visitor.EvalExpVisitor;
import visitor.ParExpVisitor;
import visitor.TreeExpVisitor;

public class MyMain {

	
	public static void main(String[] args) {

		ParExpVisitor pv = new ParExpVisitor(); // parentesi
		EvalExpVisitor ev = new EvalExpVisitor(); // valutatore
		EvalAssignExpVisitor eav = new EvalAssignExpVisitor(); //valutatore esteso alle parentesi
		
		StringBuilder sbOr = new StringBuilder();
		StringBuilder sbPre = new StringBuilder();
		StringBuilder sbPost = new StringBuilder();

		TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem

		Exp e1 = new PlusExp(new NumExp(1), new NumExp(2));
		e1.accept(pv);
		e1.accept(ev);
		e1.accept(tv);
		
		Exp e2 = new AssignExp(new LIdentExp("x"), new MinusExp(new NumExp(5), new NumExp(3)));
		Exp e3 = new AssignExp(new LIdentExp("z"), new AssignExp(new LIdentExp("y"), new MinusExp(new NumExp(5), new NumExp(8))));
		e2.accept(eav);
		e2.accept(pv);
		System.out.println("Expression " + e2 + " with parenthesis " + pv.getResult());
		e3.accept(eav);
		
		/*Formazione delle diverse tipologie di stampa*/
		ThePrinter.inorder(tv.getResult(), sbOr, " ");
		ThePrinter.preorder(tv.getResult(), sbPre, " ");
		ThePrinter.postorder(tv.getResult(), sbPost, " ");
		System.out.println("Expression " + e1 + " ==> " + pv.getResult() + " = " + ev.getEvaluation() + ";\nin order: "
				+ sbOr.toString() + ";\npre order: " + sbPre.toString() + ";\npost order: " + sbPost.toString());

	}

	

}
