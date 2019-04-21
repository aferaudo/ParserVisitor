package scanner;

public class Token {

	private String tk;
	
	public Token(String tk) {
		this.tk = tk;
	}
	
	public boolean isNumber() {
		try {
			
			Integer.parseInt(tk);
			
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	public String getTk() {
		return tk;
	}

	/**
	 * Questo metodo verifica se il token così aggeregato è un identificatore
	 * 
	 * un'identificatore può contenere sia lettere che numeri!
	 * Quindi è semplicemente un !isNumber
	 * @return
	 */
	public boolean isIdentifier() {
		return !isNumber() && !tk.equals(",") && !tk.equals("$");
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof String)
			return this.tk.equals((String)obj);
		else if(obj instanceof Token) {
			Token that = (Token)obj;
			return this.getTk().equals(that.getTk());
		}else
			return false;
	}
	
}
