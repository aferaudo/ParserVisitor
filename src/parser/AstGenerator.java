package parser;

import ast.AssignExp;
import ast.DivExp;
import ast.Exp;
import ast.LIdentExp;
import ast.MinusExp;
import ast.MulExp;
import ast.NumExp;
import ast.PlusExp;
import ast.PowExp;
import ast.RIdentExp;
import ast.SeqExp;
import scanner.MyScanner;
import scanner.Token;

public class AstGenerator {

	/*
	 * In questo caso vogliamo un parser completo che effettui una meta-valutazione
	 * Quindi generazione dell'ALBERO SINTATTICO
	 * 
	 * Si potrebbe usare albero di derivazione, tuttavia presenta troppe ridondanze
	 * per questo motivo si decide di realizzare un albero in una versione più compatta
	 * chiamato Abstract Syntax Tree
	 * 
	 * Per fare questo ho bisogno di qualcosa che mi permetta di rappresentare i nodi
	 * In particolare avrò 5 diverse tipologie di nodi: +, -, /, *, num (in realtà in questo caso serve anche la potenza)
	 * Dovrò fare una classe per ognuna di essi, considerando che il num non è un operatore ma un numero.
	 * 
	 * Conclusioni --> DALLA NOSTRA ESPRESSIONE DA VALIDARE OTTENIAMO IL NOSTRO ALBERO SINTATTICO
	 * 
	 * VERSIONE 0.0
	 * */
	
	/*
	 * UPGRADE --> VERSIONE 0.1
	 * Aggiunta dell'operatore assegnamento
	 * EXP ::= ASSIGN
	 * ASSIGN ::= IDENT = EXP
	 * 
	 * FACTOR ::= $IDENT
	 * */
	
	private MyScanner scanner;
	private Token currentToken;
	
	public AstGenerator(MyScanner scanner) {
		this.scanner = scanner;
		this.currentToken = scanner.getNextToken();
	}
	

	/*Versione 0.1: Introdotta la possibilità di inserire variabili in sequenza*/
	public Exp parseSeq() {
		print("parseSeq", "currentToken");
		Exp result1 = parseExp();
		print("parseSeq", "nextToken");
		while(currentToken != null) {
			if(currentToken.equals(",")) {
				print("parseSeq", "currentToken");
				currentToken = scanner.getNextToken();
				Exp result2 = parseExp();
				print("parseSeq", "nextToken");
				if(result2 != null)
					result1 = new SeqExp(result1, result2);
				else
					return null;
			}
		}
		return result1;
	}
	
	public Exp parseExp() {
		print("parseExp", "currentToken");
		Exp result1 = parseTerm();
		print("parseExp", "nextToken");
		
		while(currentToken != null) {
			
			if(currentToken.equals("+")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				Exp result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = new PlusExp(result1, result2);
				
			}else if(currentToken.equals("-")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				Exp result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = new MinusExp(result1, result2);
				
			}else if(currentToken.isIdentifier()) { // Versione 0.1
				String id = currentToken.getTk();
				print("parseExp", "currentToken");
				result1 = new LIdentExp(id);
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				if(!currentToken.equals("="))
					return null;
				
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				Exp result2 = parseExp();
				print("parseExp", "nextToken");
				return new AssignExp(result1, result2);
			}else
				return result1;
				
		}
		
		return result1;
	}
	
	public Exp parseTerm() {
		print("parseTerm", "currentToken");
		Exp result1f = parsePow();
		print("parseTerm", "nextToken");

		while(currentToken != null) {
			if(currentToken.equals("*")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				Exp result2f = parsePow();
				print("parseTerm", "nextToken");
				
				result1f = new MulExp(result1f, result2f);
				
			}else if(currentToken.equals("/")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				Exp result2f = parsePow();
				print("parseTerm", "nextToken");
				
				result1f = new DivExp(result1f, result2f);
				
			}else
				return result1f;
				
		}
		return result1f;
	}
	
	/*
	 * Introduciamo il concetto di potenza
	 * Essendo più prioritaria rispetto alle altre operazioni
	 * Deve essere posta tra la parseTerm e la parseFactor
	 * */
	
	public Exp parsePow() {
		
		print("parsePow", "currentToken");
		Exp p1 = parseFactor();
		print("parsePow", "nextToken");
		
		while(currentToken != null) {
			
			if(currentToken.equals("^")) {
				print("parsePow", "currentToken");
				currentToken = scanner.getNextToken();
				print("parsePow", "nextToken");
				Exp p2 = parsePow();
				
				return new PowExp(p1,p2);
			}else
				return p1;
		}
		return p1;
	}
	
	public Exp parseFactor() {
		Exp result;
		
		if(currentToken.equals("(")) {
			print("parseFactor", "currentToken");
			currentToken = scanner.getNextToken();
			print("parseFactor", "nextToken");
			result = parseExp();
			if(currentToken == null)
				throw new IllegalArgumentException();
			
			if(currentToken.equals(")")) {
				print("parseFactor", "currentToken");
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				return result;
			}
			else
				throw new IllegalArgumentException();
		}else 
			if(currentToken.isNumber()) {
				print("parseFactor", "currentToken");
				result = new NumExp(Integer.parseInt(currentToken.getTk()));
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				return result;
			}else if(currentToken.equals("$")) { // Versione 0.1
				
				print("parseFactor", "currentToken");
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				String id = currentToken.getTk();
				
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				
				return new RIdentExp(id);
				
			}else
				return null;
	}
	
	private void print(String who, String type) {
		if(currentToken != null)
			System.out.println(who + "\t\t" + type + " is " + currentToken.getTk());
	}
	
	/*
	 * Come effettuare la valutazione dell'albero sintattico così ottenuto?
	 * Potrei usare una funzione eval all'interno del parser
	 */
	
	// Supponiamo che il dominio di valutazione siano numeri interi.
	/*
	 * Funzione troppo verbosa. Aggiungere una nuova produzione richiederebbe un aggiornamento di tale funzione
	 * Tuttavia, risulta più conveniente nel caos in cui volessimo diverse interpretazioni.
	 * */
	public int eval(Exp e) {
		if(e instanceof PlusExp) {
			return eval(((PlusExp) e).getLeft()) + eval(((PlusExp) e).getRight());
		}
		else if(e instanceof MinusExp) {
			return eval(((MinusExp) e).getLeft()) - eval(((MinusExp) e).getRight());
		}
		else if(e instanceof MulExp) {
			return eval(((MulExp) e).getLeft()) * eval(((MulExp) e).getRight());
		}
		else if(e instanceof DivExp) {
			return eval(((DivExp) e).getLeft()) / eval(((DivExp) e).getRight());
		}
		else if(e instanceof PowExp) {
			return (int) Math.pow(eval(((PowExp) e).getRight()) , eval(((PowExp) e).getLeft()));
		}else if(e instanceof NumExp)
			return ((NumExp) e).getValue();
		else
			return 0;
	}
}
