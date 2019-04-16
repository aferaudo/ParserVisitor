package test;

import ast.*;

import printing.ThePrinter;

import visitor.EvalExpVisitor;
import visitor.ParExpVisitor;
import visitor.TreeExpVisitor;

public class MyMain {

	
	public static void main(String[] args) {

		ParExpVisitor pv = new ParExpVisitor(); // parentesi
		EvalExpVisitor ev = new EvalExpVisitor(); // valutatore
		
		
		StringBuilder sbOr = new StringBuilder();
		StringBuilder sbPre = new StringBuilder();
		StringBuilder sbPost = new StringBuilder();

		TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem

		Exp e1 = new PlusExp(new NumExp(1), new NumExp(2));
		e1.accept(pv);
		e1.accept(ev);
		e1.accept(tv);
		/*Formazione delle diverse tipologie di stampa*/
		ThePrinter.inorder(tv.getResult(), sbOr, " ");
		ThePrinter.preorder(tv.getResult(), sbPre, " ");
		ThePrinter.postorder(tv.getResult(), sbPost, " ");
		System.out.println("Expression " + e1 + " ==> " + pv.getResult() + " = " + ev.getResult() + ";\nin order: "
				+ sbOr.toString() + ";\npre order: " + sbPre.toString() + ";\npost order: " + sbPost.toString());

	}

	

}
