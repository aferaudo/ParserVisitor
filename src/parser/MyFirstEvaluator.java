package parser;

import scanner.MyScanner;
import scanner.Token;

public class MyFirstEvaluator {

	/*
	 * Il concetto è uguale al precedente solo che in questo caso
	 * non si tratta di un puro riconoscitore, ma di un PARSER COMPLETO.
	 * */
	
	/*
	 * Occorre definire una funzione di interpretazione:
	 * f: L --> S
	 * L = linguaggio
	 * S = insieme di possibili significati
	 * 
	 *  Per definire questa funzione l'idea furma è quella di 
	 *  SEGUIRE PARI PARI LA SINTASSI!
	 *  Per ogni regola sintattica, una regola semantica!
	 *  
	 *  Conclusione il nostro parser diventerà un INTERPRETE CON VALUTAZIONE IMMEDIATA
	 */
	
	private MyScanner scanner;
	private Token currentToken;
	
	public MyFirstEvaluator(MyScanner scanner) {
		
		this.scanner = scanner;
		this.currentToken = scanner.getNextToken();
	}
	
	/*In questo caso tutte le funzioni devono restituire un inter*/
	
	
	public int parseExp() {
		print("parseExp", "currentToken");
		int result1 = parseTerm();
		print("parseExp", "nextToken");
		
		while(currentToken != null) {
			
			if(currentToken.equals("+")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				int result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = result1 + result2;
				
			}else if(currentToken.equals("-")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseExp", "currentToken");
				int result2 = parseTerm();
				print("parseExp", "nextToken");
				
				result1 = result1 - result2;
				
			}else
				return result1;
				
		}
		
		return result1;
	}
	
	public int parseTerm() {
		print("parseTerm", "currentToken");
		int result1f = parsePow();
		print("parseTerm", "nextToken");

		while(currentToken != null) {
			if(currentToken.equals("*")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				int result2f = parsePow();
				print("parseTerm", "nextToken");
				
				result1f = result1f * result2f;
				
			}else if(currentToken.equals("/")) {
				
				// Mi muovo avanti nella stringa
				currentToken = scanner.getNextToken();
				print("parseTerm", "currentToken");
				int result2f = parsePow();
				print("parseTerm", "nextToken");
				
				result1f = result1f / result2f;
				
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
	
	public int parsePow() {
		
		print("parsePow", "currentToken");
		int p1 = parseFactor();
		print("parsePow", "nextToken");
		
		while(currentToken != null) {
			
			if(currentToken.equals("^")) {
				print("parsePow", "currentToken");
				currentToken = scanner.getNextToken();
				print("parsePow", "nextToken");
				int p2 = parseFactor();
				int res;
				for (res=1;p2>0;p2--){
					res = res*p1;
				}
				
				p1 = res;
				return p1;
			}else
				return p1;
		}
		return p1;
	}
	
	public int parseFactor() {
		int result;
		
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
				result = Integer.parseInt(currentToken.getTk());
				currentToken = scanner.getNextToken();
				print("parseFactor", "nextToken");
				return result;
			}else
				throw new IllegalArgumentException();
	}
	
	private void print(String who, String type) {
		if(currentToken != null)
			System.out.println(who + "\t\t" + type + " is " + currentToken.getTk());
	}
}
	
	 

