package test;

import ast.Exp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.AstGenerator;
import printing.ThePrinter;
import scanner.MyScanner;
import visitor.EvalExpVisitor;
import visitor.ParExpVisitor;
import visitor.TreeExpVisitor;

public class GUIMain extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String toValidate = "( 2 + 7 ) + 8 ^ 4";
		MyScanner scanner = new MyScanner(toValidate);
		AstGenerator astGenerator = new AstGenerator(scanner);

		ParExpVisitor pv = new ParExpVisitor(); // parentesi
		EvalExpVisitor ev = new EvalExpVisitor(); // valutatore
		TreeExpVisitor tv = new TreeExpVisitor(); // generatore TreeItem

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
