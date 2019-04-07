package ast;

public abstract class OpExp extends Exp{

	Exp left, right;

	protected OpExp(Exp left, Exp right) {
		this.left = left;
		this.right = right;
	}
	
	public abstract String myOp(); // metodo che deve essere implementato da tutte le sottoclassi
							// rappresentanti tutti gli operatori
	
	
	public String toString() {
		
		return left.toString() + myOp() + right.toString();
	}

	public Exp getLeft() {
		return left;
	}

	public Exp getRight() {
		return right;
	}

	
	
	
}
