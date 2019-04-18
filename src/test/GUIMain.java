package test;

import ast.Exp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.AstGenerator;
import scanner.MyScanner;
import visitor.EvalExpVisitor;
import visitor.ParExpVisitor;
import visitor.PostOrderVisitor;
import visitor.PreOrderVisitor;
import visitor.TreeExpVisitor;

/*
 * TODO
 * If a symbol is not one of the grammar alphabet, the program must to throw an Excpetion
 * */

public class GUIMain extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//String toValidate = "( 2 + 7 ) + 8 ^ 4";
		String toValidate = "( ( ( ( 13 - 4 ) + 1 ) * 5 ) + 1 ) / ( 4 * 4 + 1 )";
		MyScanner scanner = new MyScanner(toValidate);
		AstGenerator astGenerator = new AstGenerator(scanner);

		ParExpVisitor pv = new ParExpVisitor(); // parentesi --> in-order
		EvalExpVisitor ev = new EvalExpVisitor(); // valutatore
		TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem
		PreOrderVisitor preoV = new PreOrderVisitor(); //generazione della stampa pre-order
		PostOrderVisitor postoV = new PostOrderVisitor(); //generazione della stampa post-order
		
		/*
		StringBuilder sbOr = new StringBuilder();
		StringBuilder sbPre = new StringBuilder();
		StringBuilder sbPost = new StringBuilder();
		*/
		
		Exp e = astGenerator.parseExp();
		e.accept(pv);
		e.accept(ev);
		e.accept(tv);
		e.accept(preoV);
		e.accept(postoV);
		/*Formazione delle diverse tipologie di stampa
		ThePrinter.inorder(tv.getResult(), sbOr, " ");
		ThePrinter.preorder(tv.getResult(), sbPre, " ");
		ThePrinter.postorder(tv.getResult(), sbPost, " ");
		System.out.println("Expression (in order with parenthesis)" + e + " ==> " + pv.getResult() + " = " + ev.getResult() + ";\nin order: "
				+ sbOr.toString() + ";\npre order: " + sbPre.toString() + ";\npost order: " + sbPost.toString());
		
		*/
		System.out.println("Expression: " + e + " in-order with parenthesis => " + pv.getResult());
		System.out.println("Test pre-order visitor => " + preoV.getResult());
		System.out.println("Test post-order visitor => " + postoV.getResult());
		TreeView<String> treeview = new TreeView<>(tv.getResult());
		
		primaryStage.setTitle("The tree");
		
		VBox root = new VBox();
		root.getChildren().add(treeview);
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}
