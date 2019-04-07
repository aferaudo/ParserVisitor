package scanner;

import java.util.Scanner;

public class MyScanner {

	private String toValidate;
	private Scanner scanner;
	
	public MyScanner(String toValidate) {
		this.toValidate = toValidate;
		
		this.scanner = new Scanner(this.toValidate);
	}
	
	
	
	public Token getNextToken() {
		try {
			return new Token(scanner.next().trim());
		}catch(Exception e) {
			return null;
		}
	}
	
	
}
