package test;

import ast.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.AstGenerator;
import printing.ThePrinter;
import scanner.MyScanner;
import visitor.EvalExpVisitor;
import visitor.ParExpVisitor;
import visitor.TreeExpVisitor;

public class MyMain extends Application {

	static String toValidate = "2 + 7 - ( 4 * 11 )";
	static MyScanner scanner = new MyScanner(toValidate);
	static AstGenerator astGenerator = new AstGenerator(scanner);
	
	static ParExpVisitor pv = new ParExpVisitor(); // parentesi
	static EvalExpVisitor ev = new EvalExpVisitor(); // valutatore
	static TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem

	public static void main(String[] args) {

		
		StringBuilder sbOr = new StringBuilder();
		StringBuilder sbPre = new StringBuilder();
		StringBuilder sbPost = new StringBuilder();

		TreeExpVisitor tv2 = new TreeExpVisitor(); // generatore TreeItem

		Exp e1 = new PlusExp(new NumExp(1), new NumExp(2));
		e1.accept(pv);
		e1.accept(ev);
		//e1.accept(tv2);
		/*Formazione delle diverse tipologie di stampa*/
		ThePrinter.inorder(tv2.getResult(), sbOr, " ");
		ThePrinter.preorder(tv2.getResult(), sbPre, " ");
		ThePrinter.postorder(tv2.getResult(), sbPost, " ");
		System.out.println("Expression " + e1 + " ==> " + pv.getResult() + " = " + ev.getResult() + ";\nin order: "
				+ sbOr.toString() + ";\npre order: " + sbPre.toString() + ";\npost order: " + sbPost.toString());
	
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StringBuilder sbOr = new StringBuilder();
		StringBuilder sbPre = new StringBuilder();
		StringBuilder sbPost = new StringBuilder();
		
		Exp e = astGenerator.parseExp();
		e.accept(pv);
		e.accept(ev);
		e.accept(tv);
		/*Formazione delle diverse tipologie di stampa*/
		//ThePrinter.inorder(tv.getResult(), sbOr, " ");
		ThePrinter.preorder(tv.getResult(), sbPre, " ");
		ThePrinter.postorder(tv.getResult(), sbPost, " ");
		System.out.println("Expression " + e + " ==> " + pv.getResult() + " = " + ev.getResult() + ";\nin order: "
				+ sbOr.toString() + ";\npre order: " + sbPre.toString() + ";\npost order: " + sbPost.toString());
		
		TreeView<String> treeview = new TreeView<>(tv.getResult());
		
		primaryStage.setTitle("The tree");
		
		VBox root = new VBox();
		root.getChildren().add(treeview);
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}
