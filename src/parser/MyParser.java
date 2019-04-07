package parser;

import scanner.MyScanner;
import scanner.Token;

/*
 * caratteri --> Scanner --> tokens --> Parser
 */
public class MyParser {

	// Prima realizzazione
	// PURO RICONOSCITORE
	
	// Grammatica di riferimento:
	// EXP ::= TERM {(+|-) TERM}
	// TERM ::= FACTOR {(*|/) FACTOR}
	
	// ANALISI TOP-DOWN:
	// Una procedura o funzione per ogni simbolo non terminale (EXP, TERM, FACTOR)
	// invocazione ricorsiva solo per il caso con il self-embedding (FACTOR ::= num | (EXP))
	
	
	private MyScanner scanner;
	private Token currentToken;
	
	public MyParser(MyScanner scanner) {
		
		this.scanner = scanner;
		this.currentToken = scanner.getNextToken();
	}
	
	
	public boolean parseExp() {
		print("parseExp", "currentToken");
		boolean result1 = parseTerm();
		print("parseExp", "nextToken");
		
		while(currentToken != null) {
			
			if(currentToken.equals("+")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				boolean result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = result1 && result2;
				
			}else if(currentToken.equals("-")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				boolean result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = result1 && result2;
				
			}else
				return result1;
				
		}
		
		return result1;
	}
	
	public boolean parseTerm() {
		print("parseTerm", "currentToken");
		boolean result1f = parseFactor();
		print("parseTerm", "nextToken");

		while(currentToken != null) {
			if(currentToken.equals("*")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				boolean result2f = parseFactor();
				print("parseTerm", "nextToken");
				
				result1f = result1f && result2f;
				
			}else if(currentToken.equals("/")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				boolean result2f = parseFactor();
				print("parseTerm", "nextToken");
				
				result1f = result1f && result2f;
				
			}else
				return result1f;
				
		}
		return result1f;
	}
	
	public boolean parseFactor() {
		boolean result;
		
		if(currentToken.equals("(")) {
			print("parseFactor", "currentToken");
			currentToken = scanner.getNextToken();
			print("parseFactor", "nextToken");
			result = parseExp();
			if(currentToken == null)
				return false;
			
			if(currentToken.equals(")")) {
				print("parseFactor", "currentToken");
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				return result;
			}
			else
				return false;
		}else 
			if(currentToken.isNumber()) {
				print("parseFactor", "currentToken");
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				return true;
			
			}else
				return false;
	}
	
	private void print(String who, String type) {
		if(currentToken != null)
			System.out.println(who + "\t\t" + type + " is " + currentToken.getTk());
	}
}
