/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 2
 Instructor: Professor Alexander
 Description: Notation Class
 Due: 09/30/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
/**
 * Notation Class 
 * 
 * @author Anthony Liu
 *
 */
public class Notation {

	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix The infix expression in string format
	 * @return The postfix expression in a string format
	 * @throws InvalidNotationFormatException If the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		NotationQueue<Character> queue = new NotationQueue<>(infix.length());
		NotationStack<Character> stack = new NotationStack<>(infix.length());
		char[] str = infix.toCharArray();

		try {
			for (char current : str) {
				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					queue.enqueue(current);
					continue;
				}
				if (current == '(') {
					stack.push(current);
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (!queue.isEmpty()) {
						char top = stack.top();
						if (top == '*' || top == '/' || current == '-' && top == '-' || current == '-' && top == '+'
								|| current == '+' && top == '-' || current == '+' && top == '+') {
							queue.enqueue(stack.pop());

						}
					}
					stack.push(current);
					continue;
				}
				if (current == ')') {
					while (stack.top() != '(') {
						queue.enqueue(stack.pop());
						if (stack.top() == null) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}

			}
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		return queue.toString();

	}
	/**
	 * Converts the postfix expression to the infix expression
	 * @param postfix The postfix expression in string format
	 * @return The infix expression in string format
	 * @throws InvalidNotationFormatException If the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		NotationStack<String> stack = new NotationStack<>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char current = postfix.charAt(i);

				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					stack.push(Character.toString(current));
					continue;
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String first = stack.pop();
					String second = stack.pop();
					String s = "(" + second + current + first + ")";
					stack.push(s);

				}
			}

		} catch (StackUnderflowException | StackOverflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	}
/**
 * Evaluates a postfix expression from a string to a double
 * @param postfixExpr The postfix expression in String format
 * @return The evaluation of the postfix expression as a double 
 * @throws InvalidNotationFormatException If the postfix expression format is invalid
 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {

		NotationStack<Double> stack = new NotationStack<>(postfixExpr.length());
		char[] str = postfixExpr.toCharArray();

		try {
			for (char current : str) {
				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current) || current == '(') {
					stack.push(Double.parseDouble(Character.toString(current)));
					continue;
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					double right = stack.pop();
					double left = stack.pop();

					switch (current) {
					case '*':
						stack.push(left * right);
						break;
					case '/':
						stack.push(left / right);
						break;
					case '+':
						stack.push(left + right);
						break;
					case '-':
						stack.push(left - right);
					}

				}

			}

		} catch (StackOverflowException | StackUnderflowException ignore) {
			throw new InvalidNotationFormatException();
		}

		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}

		return Double.parseDouble(stack.toString());

	}
	/**
	 * Evaluates a infix expression from a String to double
	 * @param infixExpr The infix expression in String format
	 * @return The evaluation of the infix method as a double
	 * @throws InvalidNotationFormatException If the infix expression format is invalid
	 */
	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
	}

}
