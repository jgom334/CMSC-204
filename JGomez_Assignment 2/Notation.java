public class Notation {

	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postFixExpr
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postFixExpr) throws InvalidNotationFormatException {
		Double finalResult = 0.0;
		MyStack<String> stack = new MyStack<String>();

		try {
			for (int i = 0; i < postFixExpr.length(); i++) {
				if (postFixExpr.charAt(i) == ' ')
					continue;
				if (Character.isDigit(postFixExpr.charAt(i)) || postFixExpr.charAt(i) == '(')
					stack.push(Character.toString(postFixExpr.charAt(i)));

				else if (postFixExpr.charAt(i) == '+' || postFixExpr.charAt(i) == '-' || postFixExpr.charAt(i) == '/'
						|| postFixExpr.charAt(i) == '*') {
					Double second = Double.parseDouble(stack.pop());
					Double first = Double.parseDouble(stack.pop());
					Double answer = 0.0;

					switch (postFixExpr.charAt(i)) {
					case '+':
						answer = first + second;
						stack.push(answer.toString());
						break;
					case '-':
						answer = first - second;
						stack.push(answer.toString());
						break;
					case '/':
						answer = first / second;
						stack.push(answer.toString());
						break;
					case '*':
						answer = first * second;
						stack.push(answer.toString());
						break;
					}
				}

			}
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() != 1)
			throw new InvalidNotationFormatException();
		finalResult = Double.parseDouble(stack.pop());
		return finalResult;
	}

	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix the infix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException-if the postfix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyQueue<Character> queueSolved = new MyQueue<Character>();
		MyStack<Character> arithmaticOperators = new MyStack<Character>();

		try {

			for (int i = 0; i < infix.length(); i++) {
				if (infix.charAt(i) == ' ')
					continue;
				else if (Character.isDigit(infix.charAt(i))) {
					queueSolved.enqueue(infix.charAt(i));
					continue;
				}

				else if (infix.charAt(i) == '+' || infix.charAt(i) == '-' || infix.charAt(i) == '/'
						|| infix.charAt(i) == '*') {
					while (precedence(infix.charAt(i)) <= precedence(arithmaticOperators.top())
							&& !arithmaticOperators.isEmpty()) {
						queueSolved.enqueue(arithmaticOperators.top());
						arithmaticOperators.pop();
					}
					arithmaticOperators.push(infix.charAt(i));
					continue;
				} else if (infix.charAt(i) == '(') {
					arithmaticOperators.push(infix.charAt(i));
					continue;
				} else if (infix.charAt(i) == ')') {
					while (arithmaticOperators.top() != '(' && !arithmaticOperators.isEmpty()) {
						queueSolved.enqueue(arithmaticOperators.top());
						arithmaticOperators.pop();
					}

					arithmaticOperators.pop();
					continue;
				}
				while (!arithmaticOperators.isEmpty()) {
					queueSolved.enqueue(arithmaticOperators.top());
					arithmaticOperators.pop();
				}

			}
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}

		return queueSolved.toString();
	}

	/**
	 * Convert an infix expression into a postfix expression
	 * @param postfix  the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException- if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> myStack = new MyStack<String>();
		try {

			int index = 0;

			while (index < postfix.length()) {
				if (postfix.charAt(index) == ' ') {
					continue;
				}
				if (Character.isDigit(postfix.charAt(index))) {
					myStack.push(Character.toString(postfix.charAt(index)));

				}

				else if (postfix.charAt(index) == '+' || postfix.charAt(index) == '-' || postfix.charAt(index) == '/'
						|| postfix.charAt(index) == '*') {
					String second = myStack.pop();
					String first = myStack.pop();
					String str = "(" + first + postfix.charAt(index) + second + ")";
					myStack.push(str);
				}

				index++;

			}
		} catch (Exception e) {
			throw new InvalidNotationFormatException();
		}

		return myStack.toString();

	}

	private static int precedence(char operator) {
		if (operator == '/' || operator == '*')
			return 1;
		if (operator == '+' || operator == '-')
			return 0;
		return -1;
	}
}