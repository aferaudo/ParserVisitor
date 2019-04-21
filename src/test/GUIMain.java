package test;

import ast.Exp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.AstGenerator;
import scanner.MyScanner;
import visitor.EvalAssignExpVisitor;
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
		String toValidate = "k = 2 , a = 6 , y = $ a * $ k";
		MyScanner scanner = new MyScanner(toValidate);
		AstGenerator astGenerator = new AstGenerator(scanner);

		ParExpVisitor pv = new ParExpVisitor(); // parentesi --> in-order
		EvalAssignExpVisitor ev = new EvalAssignExpVisitor(); // valutatore
		TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem
		PreOrderVisitor preoV = new PreOrderVisitor(); //generazione della stampa pre-order
		PostOrderVisitor postoV = new PostOrderVisitor(); //generazione della stampa post-order
		
		
		Exp e = astGenerator.parseSeq(); //TODO correct errors on rident
		e.accept(pv);
		e.accept(ev);
		e.accept(tv);
		e.accept(preoV);
		e.accept(postoV);
		
		System.out.println("Expression: " + e + " in-order with parenthesis => " + pv.getResult() + " = " + ev.getEvaluation() + " evalutation");
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
