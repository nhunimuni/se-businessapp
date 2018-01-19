package com.businessapp.logic;

import java.util.Arrays;


/**
 * ********************************************************************************
 * Local implementation class (unfinished) of calculator logic.
 * <p>
 * Instance is invoked with public interface method nextToken( Token tok ) passing
 * an input token that is created at the UI from a key event. Input tokens are defined
 * in CalculatorIntf and comprised of digits [0-9,.], numeric operators [+,-,*,/,VAT]
 * and control tokens [\backspace,=,C,CE,K_1000].
 * <p>
 * Outputs are are passed through display properties:<p>
 * 	- CalculatorIntf.DISPLAY for numbers and<p>
 * 	- CalculatorIntf.SIDEAREA for VAT calculations.
 * <p>
 * Method(s):
 *	- public void nextToken( Token tok );	;process next token from UI controller
 *
 *
 *
 * DIVIDE("\u00F7", (x, y) -> x / y),
 MULTIPLY("x", (x, y) -> x * y),
 SUBTRACT("-", (x, y) -> x - y),
 ADD("+", (x, y) -> x + y);
 *
 */
class CalculatorLogic implements CalculatorLogicIntf {

	private DoubleStack memory;
	private CharStack operators;
	private String postfix;


	private StringBuffer dsb = new StringBuffer();

	/**
	 * Local constructor.
	 */
	CalculatorLogic() {
		memory = new DoubleStack();
		operators = new CharStack();
		postfix = "";
		nextToken( Token.K_C );		// reset buffers
	}

	/**
     * Process next token from UI controller. Tokens are defined in CalculatorIntf.java
     * <p>
     * Outputs are are passed through display properties:
     * 	- CalculatorIntf.DISPLAY for numbers and
     * 	- CalculatorIntf.SIDEAREA for VAT calculations.
     * <p>
     * @param tok the next Token passed from the UI, CalculatorViewController.
     */
	public void nextToken( Token tok ) {
		String d = tok == Token.K_DOT ? "." : CalculatorLogicIntf.KeyLabels[tok.ordinal()];

			try {
				switch (tok) {
					case K_0: //memory.push(0.0);System.out.println(Arrays.toString(memory.data)); appendBuffer(d);break;
					case K_1: //memory.push(1.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_2: //memory.push(2.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_3: //memory.push(3.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_4: //memory.push(4.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_5: //memory.push(5.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_6: //memory.push(6.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_7: //memory.push(7.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_8: //memory.push(8.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
					case K_9: //memory.push(9.0);System.out.println(Arrays.toString(memory.data));appendBuffer(d);break;
						appendBuffer(d);
						break;

					case K_1000:
						nextToken(Token.K_1);
						nextToken(Token.K_0);
						nextToken(Token.K_0);
						nextToken(Token.K_0);
						break;


					case K_Nichts:
						break;

					case K_Nichts2:
						break;

					case K_KAuf:
						appendBuffer(d);
						break;

					case K_KZu:
						appendBuffer(d);
						break;

					case K_DIV:
						appendBuffer(d);
						//operators.push('/');
						break;

					case K_MUL:
						appendBuffer(d);
						//operators.push('*');
						break;


					case K_PLUS:
						appendBuffer(d);
						//operators.push('+');
						break;

					case K_MIN:
						appendBuffer(d);
						//operators.push('-');
						break;

					case K_EQ:

						String infix = dsb.toString();
						System.out.println(infix);

						String postfix = infixToPostfix(infix);
						System.out.println(postfix);

						double erg = getValue(postfix);

						System.out.println(Arrays.toString(memory.data));

						CalculatorLogicIntf.SIDEAREA.set(
								"Ergebnis: " + erg
						);
						break;

					case K_VAT:


						//Mehrwertsteur vom Brutto Betrag: Brutto – (Brutto / 1,19) = MwSt
						String input = DISPLAY.get();
						//String vat = Double.toString(Math.round((Double.parseDouble(input) / 119 * 19) * 100) / 100.0);
						String vat = Double.toString(Math.round((Double.parseDouble(input) - (Double.parseDouble(input)/1.19)) * 100) / 100.0);
						String netto = Double.toString(Math.round((Double.parseDouble(input) - Double.parseDouble(vat)) * 100) / 100.0);

						CalculatorLogicIntf.SIDEAREA.set(
								"Brutto: " + input + "\n" +
										VAT_RATE + "% MwSt: " + vat + "\n" +
										"Netto: " + netto
						);
						break;

					case K_DOT:
						appendBuffer(d);
						break;

					case K_BACK:
						//System.out.println(Arrays.toString(memory.data));
						//System.out.println(Arrays.toString(operators.data));
						dsb.setLength(Math.max(0, dsb.length() - 1));
						break;

					case K_C:
						CalculatorLogicIntf.SIDEAREA.set("");
						memory.delete();
						operators.delete();
					case K_CE:
						dsb.delete(0, dsb.length());
						break;

					default:
				}
				String display = dsb.length() == 0 ? "0" : dsb.toString();
				CalculatorLogicIntf.DISPLAY.set(display);

			} catch (ArithmeticException e) {
				CalculatorLogicIntf.DISPLAY.set(e.getMessage());
			}


		}

	/**
	 * Infix -> Postfix
	 * @param infix
	 * @return Postfix als String
	 */
	public String infixToPostfix(String infix){
		postfix = "";
		infix+=" ";
		operators.clear();
		boolean firstTime = true;
		for(int i=0; i<infix.length();i++){
			char ch = infix.charAt(i);
			if(ch =='-' && firstTime)
				ch='$';
			if(ch!=' ')
				firstTime = false;
			if(isADigit(ch))
				postfix += ch;
			else if("+-*/^$".indexOf(ch)>=0){
				while(leftFirst(operators.peek(),ch))
					postfix += operators.pop();
				operators.push(ch);
			}
			else if(ch == '('){
				operators.push(ch);
				firstTime = true;
			}
			else if(ch == ')') {
				while(operators.peek()!='(')
					postfix += operators.pop();
				operators.pop();

			}
		}
		while(!operators.empty())
			postfix += operators.pop();
		return postfix;

	}

	/**
	 * Ergebnis wird hier mit dem Postfix berechnet
	 * @param postfix
	 * @return Ergebnis
	 */
	public double getValue(String postfix){
		int i = 0;
		memory.clear();
		for(int n=0;n<postfix.length();n++){
			char ch = postfix.charAt(n);
			if('0'<=ch && ch<='9') {
				memory.push(ch - '0');
			}
			else {
				switch(ch) {
					case '+':
						double b = memory.pop();
						double a = memory.pop();
						memory.push(a+b);
						break;
					case '$':
						memory.push(-memory.pop());
						break;
					case '-':
						b = memory.pop();
						a = memory.pop();
						memory.push(a-b);
						break;
					case '*':
						b = memory.pop();
						a = memory.pop();
						memory.push(a*b);
						break;
					case '/':
						b = memory.pop();
						a = memory.pop();
						memory.push(a/b);
						if(b == 0.0) {
							throw new ArithmeticException("ERR: div by zero");
						}
						break;
				}
			}
		}
		return memory.pop();
	}

	//Priorität und Rangfolge der Operatoren wird sortiert
	private boolean leftFirst(char a, char b) {
		if(a =='^' && b=='^')
			return false;
		int r = rank(a);
		int s = rank(b);
		return r>=s;
	}

	//Priorität der Operatoren wird getestet
	private int rank(char ch){
		switch(ch) {
			case'+': case'-':
				return 1;
			case'*': case'/':
				return 2;
			case'^':
				return 3;
			default:
				return 0;
		}
	}

	//Test ob char eine Zahl von 0 bis 0 ist
	private boolean isADigit(char ch){
		if((ch>='0' && ch<='9') || ch=='.')
			return true;
		return false;
	}

	/*
	 * Private method(s).
	 */
	private void appendBuffer( String d ) {
		if( dsb.length() <= DISPLAY_MAXDIGITS ) {
			dsb.append( d );
		}
	}
}
